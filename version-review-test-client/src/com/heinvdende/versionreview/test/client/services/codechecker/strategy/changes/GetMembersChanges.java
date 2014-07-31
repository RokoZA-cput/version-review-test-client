/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.services.codechecker.StrategyChooser;
import com.heinvdende.versionreview.test.factories.FileChangeFactory;
import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class GetMembersChanges {
    public static List<FileChange> getSingleMemberChanges(Node oldMember, Node newMember) {
        List<FileChange> list = new ArrayList<>();
        List<FileChange> tmpList = null;
        ChangesStrategy strat;

        // Get strat
        strat = StrategyChooser.getChangeStrategy(newMember);
        
        if(newMember != null) {
            if(oldMember != null) {
                tmpList = strat.doCheck(oldMember, newMember);
                if(tmpList != null) {
                    list.addAll(tmpList);
                }
                else {
                    list.add(FileChangeFactory.getFileChange(newMember, strat.getMemberHeader(newMember), FileChange.TYPE_ADD));
                }
            }
            else
                list.add(FileChangeFactory.getFileChange(newMember, strat.getMemberHeader(newMember), FileChange.TYPE_ADD));
        }
        
        return list;
    }
    
    public static List<FileChange> getMemberListChanges(List<? extends Node> oldMembers, List<? extends Node> newMembers) {
        List<FileChange> list = new ArrayList<>();
        
        // Check for any nodes in the new list
        if(newMembers != null) {
            ChangesStrategy strat;
            boolean memberFound;
            List<FileChange> tmpList;

            for(Node newMember : newMembers) {
                memberFound = false;
                tmpList = null;
                // Set strat
                strat = StrategyChooser.getChangeStrategy(newMember);
                if(strat != null) {
                    if(oldMembers != null) {
                        for(Node oldMember : oldMembers) {
                            tmpList = strat.doCheck(oldMember, newMember);

                            if(tmpList != null) {
                                memberFound = true;
                                list.addAll(tmpList);
                                break;
                            }
                        }
                    }
                    
                    if(!memberFound) {
                        list.add(FileChangeFactory.getFileChange(newMember, strat.getMemberHeader(newMember), FileChange.TYPE_ADD));
                    }
                }
            }
        }
        
        return list;
    }
}
