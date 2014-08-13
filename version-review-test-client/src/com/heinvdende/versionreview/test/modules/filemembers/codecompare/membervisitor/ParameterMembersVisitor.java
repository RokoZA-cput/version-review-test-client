/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.ParameterChildrenService;
import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import japa.parser.ast.Node;
import japa.parser.ast.body.Parameter;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ParameterMembersVisitor extends MembersVisitor {

    public ParameterMembersVisitor(CodeFile file) {
        super(file);
    }

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new ParameterChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Parameter)
            return true;
        
        return false;   
    }
}
