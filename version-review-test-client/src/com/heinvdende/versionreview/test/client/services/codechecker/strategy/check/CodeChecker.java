/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.check;

import japa.parser.ast.Node;

/**
 *
 * @author Heinrich
 */
public class CodeChecker {
    private CheckStrategy strategy;
    
    public CodeChecker(CheckStrategy strat) {
        strategy = strat;
    }

    public boolean doCheck(Node oldNode, Node newNode) {
        return strategy.doCheck(oldNode, newNode);
    }

}
