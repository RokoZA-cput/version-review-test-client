/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.ParameterChildrenService;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import japa.parser.ast.Node;
import japa.parser.ast.body.Parameter;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ParameterChanges extends MemberChangesStrategy {

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new ParameterChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public String getType() {
        return "Parameter";
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Parameter || node == null)
            return true;
        
        return false;   
    }
    
    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof Parameter) {
            Parameter param = (Parameter) node;
            
            return param.toString() + " - " + param.getId().getArrayCount();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

    @Override
    public int getMarkerType() {
        return FileChange.MARKER_UNDERLINE;
    }
}
