/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.MethodChildrenService;
import japa.parser.ast.Node;
import japa.parser.ast.body.MethodDeclaration;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class MethodChanges extends MemberChangesStrategy {
    
    @Override
    public String getType() {
        return "Method";
    }
    
    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new MethodChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof MethodDeclaration || node == null)
            return true;
        
        return false;   
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof MethodDeclaration) {
            MethodDeclaration method = (MethodDeclaration) node;
            
            // Use the ')' character to determine the end of the method header
            String methodString = method.toString();
            int index = methodString.indexOf(')') + 1;
            return methodString.substring(0, index);
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

    @Override
    public int getMarkerType() {
        return FileChange.MARKER_HIGHLIGHT;
    }

}
