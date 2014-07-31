/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.services.membervisitors;

import japa.parser.ast.Node;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heinrich
 */
public class ClassVisitor extends VoidVisitorAdapter {

    private ClassOrInterfaceDeclaration fileClass;
    
    public ClassOrInterfaceDeclaration getFileClass() {
        return fileClass;
    }
    
    @Override
    public void visit(ClassOrInterfaceDeclaration n, Object arg) {
        super.visit(n, arg); //To change body of generated methods, choose Tools | Templates.
        fileClass = n;
    }

    
    
}
