/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.Member;
import com.heinvdende.versionreview.test.modules.filemembers.factory.FactoryFacade;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.strategy.StrategyChooser;
import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich Van den Ende
 */
public abstract class MembersVisitor {
    
    private CodeFile file;

    public MembersVisitor(CodeFile file) {
        this.file = file;
    }
    
    public void setFile(CodeFile file) {
        this.file = file;
    }

    // The only difference between each node is its children
    // This method must retrieve each member's (Node) children nodes
    // This should not include this node
    public abstract List<Node> getChildMembers(Node node);
    
    // User isInstance of for the VisitorChooser and Exception Handling
    public abstract boolean isInstanceOf(Node node);
    
    // This method will be called from the client
    public List<Member> getMembers(Node currentNode, Member parentMember) {
        List<Member> classMembers = new ArrayList<>();

        // Visit this member
        Member currentMember = visit(currentNode, parentMember);
        classMembers.add(currentMember);

        // Get all children of the node
        List<? extends Node> memberNodes = getChildMembers(currentNode);

        // iterate throught each member and retrieve the changes
        for(Node node : memberNodes) {
            MembersVisitor visitor = StrategyChooser.getVisitorStrategy(node);
            
            if(visitor != null) {
                classMembers.addAll(visitor.getMembers(node, currentMember));
            }
        }
        
        return classMembers;
    }
    
    // This method creates the member from a node
    private Member visit(Node node, Member parentMember) {
        Member member = FactoryFacade.getMemberFromNode(node, parentMember, null);    
        return member;
    }
}
