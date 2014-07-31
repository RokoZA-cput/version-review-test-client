/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.membervisitors;

import com.heinvdende.versionreview.test.client.domain.ChangedCodeFile;
import com.heinvdende.versionreview.test.client.domain.FileChange;
import com.heinvdende.versionreview.test.client.domain.CodeFile;
import com.heinvdende.versionreview.test.client.domain.TaskClass;
import com.heinvdende.versionreview.test.client.domain.User;
import com.heinvdende.versionreview.test.client.services.codechecker.StrategyChooser;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.ChangesStrategy;
import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heinrich
 */
public class FinalMemberVisitor extends VoidVisitorAdapter {

    private ChangedCodeFile finalFile = new ChangedCodeFile();

    public ChangedCodeFile getFinalFile() {
        return finalFile;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        addChange(n, arg, FileChange.MARKER_NEW_FILE, "Class");
    }
    
    @Override
    public void visit(MethodDeclaration n, Object arg) {
        super.visit(n, arg);
        addChange(n, arg, FileChange.MARKER_HIGHLIGHT, "Method");
    }

    @Override
    public void visit(BlockStmt n, Object arg) {
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        
        List<Statement> stmts = n.getStmts();
        
        if(stmts != null) {
            for(Statement s : stmts) {
                addChange(s, arg, FileChange.MARKER_UNDERLINE, "Statement");
            }
        }
    }
    
    @Override
    public void visit(Parameter n, Object arg) {
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        addChange(n, arg, FileChange.MARKER_UNDERLINE, "Parameter");
    }

    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        addChange(n, arg, FileChange.MARKER_HIGHLIGHT, "Constructor");
    }
    
    private void addChange(Node n, Object arg, int markerType, String memberType) {
        if(arg instanceof TaskClass) {
            TaskClass taskClass = (TaskClass) arg;
            
            ChangesStrategy strat = StrategyChooser.getChangeStrategy(n);
            if(strat != null) {
                String memberHeader = strat.getMemberHeader(n);

                Map<User, CodeFile> map = taskClass.getUserFinalFiles();

                for (Map.Entry<User, CodeFile> entry : map.entrySet()) {
                    User user = entry.getKey();
                    if(entry.getValue() instanceof ChangedCodeFile) {
                        ChangedCodeFile file = (ChangedCodeFile) entry.getValue();

                        for(FileChange change : file.getChanges()) {
                            if(checkMemberHeader(change.getMemberHeader(), memberHeader)) {
                                FileChange newChange = new FileChange();
                                newChange.setUser(user);
                                newChange.setStartLine(n.getBeginLine());
                                newChange.setEndLine(n.getEndLine());
                                newChange.setMemberHeader(memberHeader);
                                newChange.setType(change.getType());
                                newChange.setMarkerType(markerType);
                                newChange.setMemberType(memberType);

                                finalFile.getChanges().add(newChange);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean checkMemberHeader(String oldHeader, String newHeader) {
        return oldHeader.replaceAll("\\s", "").equals(newHeader.replaceAll("\\s", ""));
    }
}
