/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test;

import com.heinvdende.versionreview.test.client.ui.UICodeDropUtility;
import com.heinvdende.versionreview.test.client.ui.UICodeView;

/**
 *
 * @author Heinrich
 */
public class MainClass {
    public static void main(String[] args) {
        
        new UICodeView().setVisible(true);
        new UICodeDropUtility().setVisible(true);
        
        /*try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
            new ViewCodeUI().setVisible(true);
            new CodeDropUtilityUI().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
