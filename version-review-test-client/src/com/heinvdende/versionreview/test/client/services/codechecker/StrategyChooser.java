/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker;

import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.AnnotationChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.BodyChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.ChangesStrategy;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.ClassChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.ConstructorChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.MethodChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.ParameterChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes.StatementChanges;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckStrategy;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckParameters;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckConstructor;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckMethods;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckAnnotations;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckClass;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckBody;
import com.heinvdende.versionreview.test.client.services.codechecker.strategy.check.CheckStatements;
import japa.parser.ast.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class StrategyChooser {
    private static List<CheckStrategy> checkStrats;
    private static List<ChangesStrategy> changeStrats;
    
    public static CheckStrategy getCheckStrategy(Node node) {
        populateCheckList();
        
        for(CheckStrategy strat : checkStrats) {
            if(strat.isInstanceOf(node))
                return strat;
        }
        
        System.out.println(node.getClass() + " not implemented yet.");
        return null;
    }
    
    public static ChangesStrategy getChangeStrategy(Node node) {
        populateChangesList();
        
        for(ChangesStrategy strat : changeStrats) {
            if(strat.isInstanceOf(node))
                return strat;
        }
        
        System.out.println(node.getClass() + " not implemented yet.");
        return null;
    }
    
    private static void populateCheckList() {
        checkStrats = new ArrayList<>();
        checkStrats.add(new CheckAnnotations());
        checkStrats.add(new CheckBody());
        checkStrats.add(new CheckClass());
        checkStrats.add(new CheckConstructor());
        checkStrats.add(new CheckMethods());
        checkStrats.add(new CheckParameters());
        checkStrats.add(new CheckStatements());
    }
    
    private static void populateChangesList() {
        changeStrats = new ArrayList<>();
        changeStrats.add(new AnnotationChanges());
        changeStrats.add(new BodyChanges());
        changeStrats.add(new ClassChanges());
        changeStrats.add(new ConstructorChanges());
        changeStrats.add(new MethodChanges());
        changeStrats.add(new ParameterChanges());
        changeStrats.add(new StatementChanges());
    }
}
