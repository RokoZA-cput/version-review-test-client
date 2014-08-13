/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.BodyChildrenService;
import japa.parser.ast.Node;
import japa.parser.ast.stmt.BlockStmt;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class BodyChanges extends MemberChangesStrategy {

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new BodyChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public String getType() {
        return "Body";
    }

    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof BlockStmt || node == null)
            return true;
        
        return false;   
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof BlockStmt) {
            //BlockStmt body = (BlockStmt) node;
            //return body.toString();
            
            // Just return empty to avoid large strings in database
            return "body statement";
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

    @Override
    public int getMarkerType() {
        return FileChange.MARKER_HIGHLIGHT;
    }
}
