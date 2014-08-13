/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.factory;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.strategy.StrategyChooser;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.MemberChangesStrategy;
import com.heinvdende.versionreview.test.modules.repository.domain.ClassMember;
import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import japa.parser.ast.Node;
import java.util.Date;

/**
 *
 * @author Heinrich
 */
public class FactoryFacade {
    public static FileChange getFileChange(ClassMember member, String type, int markerType) {
        FileChange change = new FileChange();
        change.setDate(new Date());
        change.setClassMember(member);
        change.setType(type);
        change.setMarkerType(markerType);
        
        return change;
    }
    
    public static ClassMember getMemberFromNode(Node node, ClassMember parentMember, CodeFile file) {
        ClassMember member = new ClassMember();
        
        // Get strat for membername and membertype
        MemberChangesStrategy strat = StrategyChooser.getChangesStrategy(node);
        if(strat != null) {
            // Populate Member values with node and add parent member
            member.setStartLine(node.getBeginLine());
            member.setEndLine(node.getEndLine());
            member.setCodeFile(file);
            member.setMemberHeader(strat.getMemberHeader(node));
            member.setType(strat.getType());
            member.setParentMember(parentMember);     
        }  
        
        return member;
    }
}
