/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.compare;

import japa.parser.ast.Node;

/**
 * Checks if the method, parameter, annotation, body, etc. is the same in both objects.
 * @author Heinrich
 */
public interface CompareStrategy {
    public boolean doCheck(Node oldNode, Node newNode);
    public boolean isInstanceOf(Node node);
}
