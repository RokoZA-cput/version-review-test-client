/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services;

/**
 *
 * @author Heinrich
 */
public class LineNumber {
    private int line;
    private int start;
    private int end;

    public LineNumber() {
        
    }
    
    public LineNumber(int line, int start, int end) {
        this.line = line;
        this.start = start;
        this.end = end;
    }
    
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
