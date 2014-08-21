/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.StatementChildrenService;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import japa.parser.ast.Node;
import japa.parser.ast.stmt.Statement;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class StatementChanges extends MemberChangesStrategy {

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new StatementChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public String getType() {
        return "Statement";
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Statement || node == null)
            return true;
        
        return false;
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof Statement) {
            Statement stmt = (Statement) node;
            return stmt.toString();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

    @Override
    public int getMarkerType() {
        return FileChange.MARKER_HIGHLIGHT;
    }
    
}
