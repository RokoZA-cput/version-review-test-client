/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui.components;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.ClassMember;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Heinrich
 */
public class LinesPanel extends JPanel {

    private Map<Integer, TransparentLinePanel> lineItems = new HashMap<>();
    
    /**
     * Creates new form LinesPanel
     */
    public LinesPanel() {
        initComponents();
    }

    public void addLines(int linesAmount, ChangedCodeFile file) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        for(int i=0;i<=linesAmount;i++) {
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = i;
            c.gridheight = 1;
            c.gridwidth = 1;
            c.weightx = 1.0;
            c.anchor = GridBagConstraints.NORTH;
            TransparentLinePanel lineItem = new TransparentLinePanel();
            lineItems.put(i, lineItem);
            this.add(lineItem, c);
        }
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = linesAmount;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.NORTH;
        JPanel lastPanel = new JPanel();
        lastPanel.setOpaque(false);
        this.add(lastPanel, c);
        
        try {
            highlightAllLines(file.getChanges());
        }
        catch(BadLocationException e) {
            e.printStackTrace();
        }
        
        this.setOpaque(false);
        revalidate();
        repaint();
    }
    
    public void selectAllLines(ClassMember member) {
        for(int i=member.getStartLine();i<=member.getEndLine();i++) {   
            selectLine(i);
        }
    }
    
    public void unselectAllLines(ClassMember member) {
        for(int i=member.getStartLine();i<=member.getEndLine();i++) {   
            lineItems.get(i-1).unselectLine();
        }
    }
    
    public void makeSelectable(boolean isSelectable) {
        for(TransparentLinePanel panel : lineItems.values()) {
            panel.makeSelectable(isSelectable);
        }
    }
    
    // Need the actuall line number (ie. dont start at 0)
    public void selectLine(int line) {
        lineItems.get(line-1).selectLine();
    }
    
    // Need the actuall line number (ie. dont start at 0)
    public void unselectLine(int line) {
        lineItems.get(line-1).selectLine();
    }
    
    private List<Integer> addedLines;
    private void highlightAllLines(List<FileChange> changeList) throws BadLocationException {
        addedLines = new ArrayList<>();
        for(FileChange c : changeList) {
            if(c.getMarkerType() == FileChange.MARKER_HIGHLIGHT) {
                for(int i=c.getClassMember().getStartLine();i<=c.getClassMember().getEndLine();i++) {
                    setMember(i, c.getClassMember(), lineItems.get(i-1));
                    
                    lineItems.get(i-1).addFileChange(c);
                    if(c.getType().equals(FileChange.TYPE_ADD)) {
                        if(!addedLines.contains(i))
                            highlightLine(i, TransparentLinePanel.ADD_COLOUR, c);
                    }
                    else if(c.getType().equals(FileChange.TYPE_MOD))
                        highlightLine(i, TransparentLinePanel.MOD_COLOUR, c);
                }
            }
        }
    }
    
    private void highlightLine(int line, Color color, FileChange change) throws BadLocationException {
        lineItems.get(line-1).addColor(color);
        addedLines.add(line);
    }
    
    private void setMember(int line, ClassMember member, TransparentLinePanel panel) {
        
        if(member.getType().equalsIgnoreCase("method") || member.getType().equalsIgnoreCase("constructor")) {
            BufferedReader reader = new BufferedReader(new StringReader(member.getMemberHeader()));
            int length = reader.lines().toArray().length;

            // Displaying full sum for clarity
            // Length is the amount of annotation + the method header
            lineItems.get((line-1) + (length-1)).setMember(member);
        }
        else {
            lineItems.get(line-1).setMember(member);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
