/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.heinvdende.versionreview.test.client.ui;

import com.heinvdende.versionreview.test.modules.repository.domain.CodeFile;
import com.heinvdende.versionreview.test.modules.repository.domain.Task;
import com.heinvdende.versionreview.test.modules.repository.domain.User;
import com.heinvdende.versionreview.test.client.generate.GenObjects;
import com.heinvdende.versionreview.test.modules.filemembers.codecompare.changes.MethodChanges;
import com.heinvdende.versionreview.test.modules.filefunctions.UpdateClasses;
import com.heinvdende.versionreview.test.modules.filefunctions.impl.UpdateClassesImpl;
import com.heinvdende.versionreview.test.modules.repository.persistence.CodeFileRepository;
import com.heinvdende.versionreview.test.modules.repository.persistence.factory.RepositoryFactory;
import japa.parser.ast.body.MethodDeclaration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.util.List;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author Heinrich
 */
public class UICodeDropUtility extends javax.swing.JFrame {

    private boolean mIsMinimized = false;
    
    /**
     * Creates new form CodeDropUtilityUI
     */
    public UICodeDropUtility() {
        initComponents();
        positionBottomRight();
        addDropFunctionality();
        populateComboBoxes();
        showTasks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        panelDrop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonChangeTask = new javax.swing.JButton();
        buttonViewLast = new javax.swing.JButton();
        comboBoxUsers = new javax.swing.JComboBox();
        buttonChangeProject = new javax.swing.JButton();
        comboBoxProjects = new javax.swing.JComboBox();
        comboBoxTasks = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(217, 217, 217));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(205, 205, 205)));

        buttonClose.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonClose.setForeground(new java.awt.Color(153, 0, 0));
        buttonClose.setText("X");
        buttonClose.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonClose.setBorderPainted(false);
        buttonClose.setFocusPainted(false);
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        panelContent.setBackground(new java.awt.Color(217, 217, 217));

        panelDrop.setBackground(new java.awt.Color(217, 217, 217));
        panelDrop.setBorder(javax.swing.BorderFactory.createTitledBorder("Drop Here"));

        javax.swing.GroupLayout panelDropLayout = new javax.swing.GroupLayout(panelDrop);
        panelDrop.setLayout(panelDropLayout);
        panelDropLayout.setHorizontalGroup(
            panelDropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 82, Short.MAX_VALUE)
        );
        panelDropLayout.setVerticalGroup(
            panelDropLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Project");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Task");

        buttonChangeTask.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        buttonChangeTask.setText("Change");
        buttonChangeTask.setToolTipText("");
        buttonChangeTask.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonChangeTask.setBorderPainted(false);
        buttonChangeTask.setFocusPainted(false);

        buttonViewLast.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        buttonViewLast.setText("View Last Changes");
        buttonViewLast.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonViewLast.setBorderPainted(false);
        buttonViewLast.setFocusPainted(false);

        comboBoxUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUsersActionPerformed(evt);
            }
        });

        buttonChangeProject.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        buttonChangeProject.setText("Change");
        buttonChangeProject.setToolTipText("");
        buttonChangeProject.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        buttonChangeProject.setBorderPainted(false);
        buttonChangeProject.setFocusPainted(false);
        buttonChangeProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangeProjectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContentLayout = new javax.swing.GroupLayout(panelContent);
        panelContent.setLayout(panelContentLayout);
        panelContentLayout.setHorizontalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addComponent(panelDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxProjects, 0, 105, Short.MAX_VALUE)
                            .addComponent(comboBoxTasks, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonChangeTask, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonChangeProject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelContentLayout.createSequentialGroup()
                        .addComponent(comboBoxUsers, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonViewLast)))
                .addContainerGap())
        );
        panelContentLayout.setVerticalGroup(
            panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonChangeProject)
                    .addComponent(comboBoxProjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buttonChangeTask)
                    .addComponent(comboBoxTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonViewLast)
                    .addComponent(comboBoxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelContentLayout.createSequentialGroup()
                .addComponent(panelDrop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(buttonClose)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
        if(!mIsMinimized) {
            panelContent.setVisible(false);
            this.revalidate();
            this.repaint();
            this.pack();
            positionBottomRight();
            mIsMinimized = true;
        }
        else {
            panelContent.setVisible(true);
            this.revalidate();
            this.repaint();
            this.pack();
            positionBottomRight();
            mIsMinimized = false;
        }           
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void comboBoxUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUsersActionPerformed
        showTasks();
    }//GEN-LAST:event_comboBoxUsersActionPerformed

    private void buttonChangeProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeProjectActionPerformed
        MethodChanges mc = new MethodChanges();
        mc.doCheck(null, new MethodDeclaration());
    }//GEN-LAST:event_buttonChangeProjectActionPerformed
 
    /**
     * User Interface Methods
     */
    private void showTasks() {
        String userStr = comboBoxUsers.getSelectedItem().toString();
        User user = GenObjects.getInstance().getUser(userStr);
        
        List<Task> taskList = user.getTaskList();

        comboBoxTasks.removeAllItems();
        for(Task task : taskList) {
            comboBoxTasks.addItem(task.getName());
        }
        comboBoxTasks.setSelectedIndex(0);
    }
    
    private void populateComboBoxes() {
        for(User user : GenObjects.getInstance().getUsers()) {
            comboBoxUsers.addItem(user.getUsername());
        }
        comboBoxUsers.setSelectedIndex(0);
        comboBoxProjects.addItem(GenObjects.getInstance().getPurchaseProject().getName());
    }
    
    private void addDropFunctionality() {
        new  FileDrop( panelDrop, new FileDrop.Listener()
        {   
            @Override
            public void  filesDropped( File[] files )
            {   
                if(files.length > 0) {
                    File droppedFile;
                    User user;
                    String userStr;
                    Task task;
                    
                    for(int i=0;i<files.length;i++) {
                        droppedFile = files[i];

                        // GEN get user
                        userStr = comboBoxUsers.getSelectedItem().toString();
                        user = GenObjects.getInstance().getUser(userStr);

                        // GEN get task
                        task = getCurrentTask();
                        
                        if(user != null) {
                            CodeFile file = new CodeFile();
                            file.setFilePath(droppedFile.getPath());
                            file.setUser(user);
                            
                            UpdateClasses service = new UpdateClassesImpl();
                            Task newTask = service.addClass(file, task);
                            
                            // GEN update task
                            GenObjects.getInstance().setTask(newTask, getCurrentTask().getName());

                            System.out.println("Class Added for " + user.getUsername() + " with task " + task.getName());
                        }
                        else
                            System.out.println("User is null....");  
                    }
                }
            } 
        });
    }
    
    private void positionBottomRight() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = ge.getMaximumWindowBounds();
        int x = (int) rect.getMaxX() - (this.getWidth()+2);
        int y = (int) rect.getMaxY() - (this.getHeight()+2);
        this.setLocation(x, y);
    }
    
    /**
     * Other Methods
     */
    private Task getCurrentTask() {
        String task = comboBoxTasks.getSelectedItem().toString();
        return GenObjects.getInstance().getTask(task);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonChangeProject;
    private javax.swing.JButton buttonChangeTask;
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonViewLast;
    private javax.swing.JComboBox comboBoxProjects;
    private javax.swing.JComboBox comboBoxTasks;
    private javax.swing.JComboBox comboBoxUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelDrop;
    // End of variables declaration//GEN-END:variables
}
