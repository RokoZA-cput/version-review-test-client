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

    public static SyntaxHighlighter getFileTab(ChangedCodeFile file) throws Exception {
        SyntaxHighlighterParser parser = new SyntaxHighlighterParser(new BrushXml());
        parser.setBrush(new BrushJava());
        
        SyntaxHighlighter highlighter = new SyntaxHighlighter(parser, new ThemeEclipse());

        String filePath = file.getFilePath();
        highlighter.setName(filePath.substring(filePath.lastIndexOf('\\')+1));
        highlighter.setContent(new File(file.getFilePath()));

        return highlighter; 
    }

}
