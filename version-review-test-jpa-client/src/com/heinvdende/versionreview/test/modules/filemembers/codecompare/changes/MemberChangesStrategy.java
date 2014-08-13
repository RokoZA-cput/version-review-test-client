/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.repository.domain.Member;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.strategy.StrategyChooser;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareStrategy;
import com.heinvdende.versionreview.test.modules.filemembers.factory.FactoryFacade;
import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 * Checks if the method, parameter, annotation, body, etc. is the same in both objects.
 * @author Heinrich
 */
public abstract class MemberChangesStrategy {
    
    public abstract String getMemberHeader(Node node);
    public abstract boolean isInstanceOf(Node node);
    public abstract String getType();
    public abstract int getMarkerType();
    
    // The only difference between each node is its children
    // This method must retrieve each member's (Node) children nodes
    // This should not include this node
    public abstract List<Node> getChildMembers(Node node);
    
    // This method will be called if there is no parent Node, such as classes
    // More understandable 
    public List<FileChange> doCheck(Node oldNode, Node newNode) {
        return doCheck(oldNode, newNode, null);
    }
    
    // This method will be called if there should be a parent node
    public List<FileChange> doCheck(Node oldNode, Node newNode, Member parentMember) {
        List<FileChange> list = new ArrayList<>();
        
        // Check for correct types
        if(!isInstanceOf(oldNode) || !isInstanceOf(newNode))
            return null;
        
        // Get Compare Strategy
        CompareStrategy strat = StrategyChooser.getCompareStrategy(newNode);
        // Create member from node
        Member currentMember = FactoryFacade.getMemberFromNode(newNode, parentMember, null); 
        
        if(oldNode == null) {
            list.add(visit(currentMember, FileChange.TYPE_ADD));
        }
        // Return null if the nodes are not equal
        // This null is then used in the getMemberListChanges() function
        else if(!strat.doCheck(oldNode, newNode)) {
            return null;
        }

        // Get members
        List<? extends Node> oldMembers = null;
        
        if(oldNode != null)
            oldMembers = getChildMembers(oldNode);

        List<? extends Node> newMembers = getChildMembers(newNode);
        
        list.addAll(getMemberListChanges(oldMembers, newMembers, currentMember));
        
        if(oldNode != null) {
            // Check if any changes occurred to the method
            if(list.size() > 0) {
                list.add(visit(currentMember, FileChange.TYPE_MOD));
            }
        }
        
        return list;
    } 
    
    private FileChange visit(Member member, String type) {
        return FactoryFacade.getFileChange(member, type, getMarkerType());
    }
    
    private List<FileChange> getMemberListChanges(List<? extends Node> oldMembers, List<? extends Node> newMembers, Member parentMember) {
        List<FileChange> list = new ArrayList<>();
        
        // Check for any nodes in the new list
        if(newMembers != null) {
            MemberChangesStrategy changesStrat;
            boolean memberFound;
            List<FileChange> tmpList;

            for(Node newMember : newMembers) {
                memberFound = false;
                tmpList = null;
                // Set strat
                changesStrat = StrategyChooser.getChangesStrategy(newMember);
                if(changesStrat != null) {
                    if(oldMembers != null) {
                        for(Node oldMember : oldMembers) {
                            tmpList = changesStrat.doCheck(oldMember, newMember, parentMember);
                            if(tmpList != null) {
                                memberFound = true;
                                list.addAll(tmpList);
                                break;
                            }
                        }
                    }
                    
                    if(!memberFound || oldMembers == null) {
                        tmpList = changesStrat.doCheck(null, newMember, parentMember);
                        list.addAll(tmpList);
                    }
                }
            }
        }
        
        return list;
    }

}
