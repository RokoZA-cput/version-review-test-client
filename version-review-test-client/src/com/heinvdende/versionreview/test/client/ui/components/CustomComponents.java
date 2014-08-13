/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui.components;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.client.services.LineNumber;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import syntaxhighlight.SyntaxHighlighter;
import syntaxhighlighter.SyntaxHighlighterParser;
import syntaxhighlighter.brush.BrushJava;
import syntaxhighlighter.brush.BrushXml;
import syntaxhighlighter.theme.ThemeEclipse;

/**
 *
 * @author Heinrich
 */
public class CustomComponents {
    
    public static final DefaultHighlightPainter addPainter = new DefaultHighlightPainter(Color.GREEN);
    public static final DefaultHighlightPainter modPainter = new DefaultHighlightPainter(Color.YELLOW);
    public static final DefaultHighlightPainter delPainter = new DefaultHighlightPainter(Color.pink);
    
    public static JScrollPane getFileTab(ChangedCodeFile file) throws Exception {
        SyntaxHighlighterParser parser = new SyntaxHighlighterParser(new BrushXml());
        parser.setBrush(new BrushJava());
        
        SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeEclipse());

        String filePath = file.getFilePath();
        highlighter.setName(filePath.substring(filePath.lastIndexOf('\\')+1));
        highlighter.setContent(new File(file.getFilePath()));

        // Add lines to list, and list their lengths to determine start and end positions of lines
        String s = highlighter.getHighlighter().getText();
        BufferedReader reader = new BufferedReader(new StringReader(s));
        
        String line;
        List<LineNumber> lines = new ArrayList<>();

        int prevEnd = 0;
        int size = 0;
        int length = 0;
        int lineCount = 0;
        
        while((line=reader.readLine()) != null) {
            // the SyntaxHighlighter library, create one additional blank line for every actual line, so we only use every second (including 0) line
            if(lineCount%2 == 0) {
                size = lines.size();
                // length+1, because cursor should move after last letter to highlight it
                // length+1, because the highlight functionality sees a new line as a character
                //           the new line is not highlighted, because the highligh functionality does not include the last character
                //           this also allows us to start the next line at the right position, which is the next line
                length = line.length()+2;
                
                // note that when a lines length is 0, there is only a new line character, therefore length should only be +1 not +2
                if(length == 2)
                    length = 1;
                
                if(size > 0) {
                    // set the start position to the previous line's end position
                    prevEnd = lines.get(size-1).getEnd();
                    lines.add(new LineNumber(size+1, prevEnd+1, prevEnd+length));
                }
                else
                    lines.add(new LineNumber(size+1, 0, length));
            }
            lineCount++;
        }

        List<FileChange> changeList = file.getChanges();
        
        highlightAllText(lines, changeList, highlighter);

        return highlighter; 
    }
    
    private static List<Integer> addedLines;
    private static void highlightAllText(List<LineNumber> lines, List<FileChange> changeList, SyntaxHighlighter highlighter) throws BadLocationException {
        addedLines = new ArrayList<>();
        for(FileChange c : changeList) {
            if(c.getMarkerType() == FileChange.MARKER_HIGHLIGHT) {
                for(int i=c.getClassMember().getStartLine();i<=c.getClassMember().getEndLine();i++) {
                    if(c.getType().equals(FileChange.TYPE_ADD)) {
                        if(!addedLines.contains(i))
                            highlightLine(lines, i, highlighter.getHighlighter().getHighlighter(), addPainter, c.getMarkerType());
                    }
                    else if(c.getType().equals(FileChange.TYPE_MOD))
                        highlightLine(lines, i, highlighter.getHighlighter().getHighlighter(), modPainter, c.getMarkerType());
                    else if(c.getType().equals(FileChange.TYPE_DEL))
                        highlightLine(lines, i, highlighter.getHighlighter().getHighlighter(), delPainter, c.getMarkerType());
                }
            }
        }
    }
    
    private static void highlightLine(List<LineNumber> lines, int lineNum, Highlighter area, DefaultHighlightPainter paint, int markerType) throws BadLocationException {
        LineNumber line = getLine(lines, lineNum);
        if(line != null) {
            switch(markerType) {
                case FileChange.MARKER_HIGHLIGHT: 
                    area.addHighlight(line.getStart(), line.getEnd(), paint);
                    addedLines.add(lineNum);
            }
        }
    }
    
    private static LineNumber getLine(List<LineNumber> lines, int lineNum) {
        for(LineNumber l : lines) {
            if(l.getLine() ==  lineNum) 
                return l;
        }
        
        return null;
    }
}
