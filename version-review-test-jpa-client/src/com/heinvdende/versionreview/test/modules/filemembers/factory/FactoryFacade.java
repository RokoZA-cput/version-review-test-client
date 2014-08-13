/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.factory;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.repository.domain.Member;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.strategy.StrategyChooser;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.MemberChangesStrategy;
import japa.parser.ast.Node;
import java.util.Date;

/**
 *
 * @author Heinrich
 */
public class FactoryFacade {
    public static FileChange getFileChange(Member member, String type, int markerType) {
        FileChange change = new FileChange();
        change.setDate(new Date());
        change.setMember(member);
        change.setType(type);
        change.setMarkerType(markerType);
        
        return change;
    }
    
    public static Member getMemberFromNode(Node node, Member parentMember, CodeFile file) {
        Member member = new Member();
        
        // Get strat for membername and membertype
        MemberChangesStrategy strat = StrategyChooser.getChangesStrategy(node);
        if(strat != null) {
            // Populate Member values with node and add parent member
            member.setStartLine(node.getBeginLine());
            member.setEndLine(node.getEndLine());
            member.setFile(file);
            member.setMemberHeader(strat.getMemberHeader(node));
            member.setType(strat.getType());
            member.setParentMember(parentMember);     
        }  
        
        return member;
    }
}
