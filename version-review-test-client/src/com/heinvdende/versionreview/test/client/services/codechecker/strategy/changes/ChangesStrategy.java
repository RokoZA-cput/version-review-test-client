/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.codechecker.strategy.changes;

import com.heinvdende.versionreview.test.client.domain.FileChange;
import japa.parser.ast.Node;
import java.util.List;

/**
 * Checks if the method, parameter, annotation, body, etc. is the same in both objects.
 * @author Heinrich
 */
public interface ChangesStrategy {
    public List<FileChange> doCheck(Node oldNode, Node newNode);
    public String getMemberHeader(Node node);
    public boolean isInstanceOf(Node node);
}
