/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes;

import com.heinvdende.versionreview.test.modules.filemembers.codecompare.childmembers.AnnotationChildrenService;
import com.heinvdende.versionreview.test.modules.repository.domain.FileChange;
import japa.parser.ast.Node;
import japa.parser.ast.expr.AnnotationExpr;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class AnnotationChanges extends MemberChangesStrategy {

    @Override
    public List<Node> getChildMembers(Node node) {
        List<Node> list = null;
        if(isInstanceOf(node))
            list = new AnnotationChildrenService().getChildMembers(node);
        
        return list;
    }
    
    @Override
    public boolean isInstanceOf(Node node) {
        if(node instanceof AnnotationExpr || node == null)
            return true;
        
        return false;
    }

    @Override
    public String getType() {
        return "Annotation";
    }

    @Override
    public String getMemberHeader(Node node) {
        if(node instanceof AnnotationExpr) {
            AnnotationExpr annotation = (AnnotationExpr) node;

            return annotation.getName().getName();
        }
        
        throw new RuntimeException("Not correct Node type.");
    }

    @Override
    public int getMarkerType() {
        return FileChange.MARKER_UNDERLINE;
    }
}
