/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui.components;

import syntaxhighlight.Parser;
import syntaxhighlight.SyntaxHighlighter;
import syntaxhighlight.Theme;

/**
 *
 * @author Heinrich
 */
public class SyntaxHighlighterScrollPane extends SyntaxHighlighter {

    public SyntaxHighlighterScrollPane(Parser parser, Theme theme) {
        super(parser, theme);
    }
    
}
