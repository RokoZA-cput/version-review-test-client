/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.BodyChildrenService;
import japa.parser.ast.Node;
import japa.parser.ast.stmt.BlockStmt;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class BodyMembersVisitor extends MembersVisitor {

    public BodyMembersVisitor(CodeFile file) {
        super(file);
    }

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new BodyChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof BlockStmt)
            return true;
        
        return false;   
    }
}
