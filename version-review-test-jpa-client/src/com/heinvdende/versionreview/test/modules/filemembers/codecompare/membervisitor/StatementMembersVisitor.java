/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.StatementChildrenService;
import japa.parser.ast.Node;
import japa.parser.ast.stmt.Statement;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class StatementMembersVisitor extends MembersVisitor {

    public StatementMembersVisitor(CodeFile file) {
        super(file);
    }

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new StatementChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof Statement)
            return true;
        
        return false;   
    }
}
