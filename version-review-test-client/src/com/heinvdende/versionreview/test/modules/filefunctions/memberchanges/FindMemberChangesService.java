/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filefunctions.memberchanges;

import com.heinvdende.versionreview.test.modules.repository.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import com.heinvdende.versionreview.test.modules.repository.domain.Member;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heinrich
 */
public class FindMemberChangesService {

    public FindMemberChangesService() {
    }
    
    public List<FileChange> getFileChanges(List<Member> members, Map<User, CodeFile> userChanges) {
        
        List<FileChange> newChanges = new ArrayList<>();
        List<FileChange> tmpList;
        ChangedCodeFile tmpFile;

        for(Member member : members) {
            for (Map.Entry<User, CodeFile> entry : userChanges.entrySet()) {
                User user = entry.getKey();
                
                tmpFile = (ChangedCodeFile) entry.getValue();
                tmpList = new ArrayList<>(tmpFile.getChanges());
                
                for(FileChange change : tmpList) {
                    if(compareMembers(member, change.getMember())) {
                        // Change change's member, to keep the ID of the change
                        // too keep reference to the nodes of the CodeFile
                        change.setMember(member);
                        change.setUser(user);

                        newChanges.add(change);
                        tmpList.remove(change);
                        break;
                    }
                }
            }
        }

        return newChanges;
    }
    
    private boolean compareMembers(Member member1, Member member2) {

        Member currentMember1 = member1;
        Member currentMember2 = member2;
        
        // When the parent member reach null it means it has found the root member
        while(currentMember1 != null && member2 != null) {
            if(!compareMethodHeaders(currentMember1, currentMember2)) {
                return false;
            }
            
            currentMember1 = currentMember1.getParentMember();
            currentMember2 = currentMember2.getParentMember();
        }
        
        // If both null, both ended at the same root without any differences
        if(currentMember1 == null && currentMember2 == null)
            return true;
        
        return false;
    }
    
    private boolean compareMethodHeaders(Member member1, Member member2) {
        if(member1.getType().equals(member2.getType()) && member1.getMemberHeader().equals(member2.getMemberHeader()))
            return true; 
        
        return false;
    }
}
