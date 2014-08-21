/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui.components;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import java.io.File;
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
