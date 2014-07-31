/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.factories;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import japa.parser.ast.Node;

/**
 *
 * @author Heinrich
 */
public class FileChangeFactory {
    public static FileChange getFileChange(Node node, String memberString, String type) {
        FileChange change = new FileChange();
        change.setStartLine(node.getBeginLine());
        change.setEndLine(node.getEndLine());
        change.setMemberHeader(memberString);
        change.setType(type);
        
        return change;

    }
}
