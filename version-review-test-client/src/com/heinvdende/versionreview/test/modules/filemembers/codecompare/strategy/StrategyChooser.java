/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.strategy;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.AnnotationChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.BodyChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.ClassChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.ConstructorChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.MemberChangesStrategy;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.MethodChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.ParameterChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.StatementChanges;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareStrategy;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareParameters;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareConstructor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareMethods;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareAnnotations;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareClass;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareBody;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare.CompareStatements;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.AnnotationMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.BodyMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.ClassMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.ConstructorMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.MembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.MethodMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.ParameterMembersVisitor;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.membervisitor.StatementMembersVisitor;
import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class StrategyChooser {
    private static List<CompareStrategy> checkStrats;
    private static List<MemberChangesStrategy> compareStrats;
    private static List<MembersVisitor> membersVisitors;
    
    public static MembersVisitor getVisitorStrategy(Node node) {
        populateVisitorList();
        
        for(MembersVisitor strat : membersVisitors) {
            if(strat.isInstanceOf(node))
                return strat;
        }
        
        System.out.println(node.getClass() + " not implemented yet.");
        return null;
    }
    
    public static CompareStrategy getCompareStrategy(Node node) {
        populateCompareList();
        
        for(CompareStrategy strat : checkStrats) {
            if(strat.isInstanceOf(node))
                return strat;
        }
        
        System.out.println(node.getClass() + " not implemented yet.");
        return null;
    }
    
    public static MemberChangesStrategy getChangesStrategy(Node node) {
        populateChangesList();
        
        for(MemberChangesStrategy strat : compareStrats) {
            if(strat.isInstanceOf(node))
                return strat;
        }
        
        System.out.println(node.getClass() + " not implemented yet.");
        return null;
    }
    
    private static void populateCompareList() {
        checkStrats = new ArrayList<>();
        checkStrats.add(new CompareAnnotations());
        checkStrats.add(new CompareBody());
        checkStrats.add(new CompareClass());
        checkStrats.add(new CompareConstructor());
        checkStrats.add(new CompareMethods());
        checkStrats.add(new CompareParameters());
        checkStrats.add(new CompareStatements());
    }
    
    private static void populateVisitorList() {
        membersVisitors = new ArrayList<>();
        membersVisitors.add(new ClassMembersVisitor(null));
        membersVisitors.add(new AnnotationMembersVisitor(null));
        membersVisitors.add(new BodyMembersVisitor(null));
        membersVisitors.add(new ConstructorMembersVisitor(null));
        membersVisitors.add(new MethodMembersVisitor(null));
        membersVisitors.add(new ParameterMembersVisitor(null));
        membersVisitors.add(new StatementMembersVisitor(null));
    }
    
    private static void populateChangesList() {
        compareStrats = new ArrayList<>();
        compareStrats.add(new AnnotationChanges());
        compareStrats.add(new BodyChanges());
        compareStrats.add(new ClassChanges());
        compareStrats.add(new ConstructorChanges());
        compareStrats.add(new MethodChanges());
        compareStrats.add(new ParameterChanges());
        compareStrats.add(new StatementChanges());
    }
}
