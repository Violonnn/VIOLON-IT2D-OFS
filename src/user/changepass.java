/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import config.dbConnector;
import config.passhash;
import config.session;
import java.awt.Color;
import java.awt.Image;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaapplication8.login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class changepass extends javax.swing.JFrame {

    /**
     * Creates new form userDashboard
     */
    public changepass() {
        initComponents();
        
        
            Icon i1 = info.getIcon();
        ImageIcon icon1 = (ImageIcon)i1;
        Image image1 = icon1.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
        info.setIcon(new ImageIcon(image1)); 
             
//            Icon i2 = userr.getIcon();
//        ImageIcon icon2 = (ImageIcon)i2;
//        Image image2 = icon2.getImage().getScaledInstance(userr.getWidth(), userr.getHeight(), Image.SCALE_SMOOTH);
//        userr.setIcon(new ImageIcon(image2)); 
        
            Icon i3 = back.getIcon();
        ImageIcon icon3 = (ImageIcon)i3;
        Image image3 = icon3.getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
        back.setIcon(new ImageIcon(image3)); 
        
         Icon i4 = menuu.getIcon();
        ImageIcon icon4 = (ImageIcon)i4;
        Image image4 = icon4.getImage().getScaledInstance(menuu.getWidth(), menuu.getHeight(), Image.SCALE_SMOOTH);
        menuu.setIcon(new ImageIcon(image4)); 
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        juser = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        idd = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        menuu = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        newpass = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        upd = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        conpass = new javax.swing.JPasswordField();
        oldpass = new javax.swing.JPasswordField();
        jPanel8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(239, 35, 35), 5, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        juser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/account.png"))); // NOI18N
        jPanel2.add(juser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 10, 10));

        jPanel5.setBackground(new java.awt.Color(239, 35, 35));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Order Flow System");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jPanel1.setBackground(new java.awt.Color(239, 35, 35));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 260, 10));

        idd.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        idd.setForeground(new java.awt.Color(255, 255, 255));
        idd.setText("UID");
        jPanel5.add(idd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 120, -1));

        jLabel8.setFont(new java.awt.Font("Gorlock", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("OFS");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jPanel9.setBackground(new java.awt.Color(239, 35, 35));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Home");
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 100, 20));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 190, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/orderflow.png"))); // NOI18N
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 460));

        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Dashboard / Account Details / Change Password");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 31, 280, 60));

        jPanel6.setBackground(new java.awt.Color(204, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("/");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 12, 50, -1));

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Home");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 12, -1, -1));

        jLabel10.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("/");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 12, 50, -1));

        jLabel11.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Back");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jLabel11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel11KeyPressed(evt);
            }
        });
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 12, 30, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-logout.png"))); // NOI18N
        jPanel6.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 20, 20));

        menuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngegg.png"))); // NOI18N
        jPanel6.add(menuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));

        jLabel13.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User Dashboard");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 11, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 570, 40));

        jPanel3.setBackground(new java.awt.Color(233, 233, 233));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 540, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 540, 40));

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Gorlock", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("Change Password");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 5, 300, -1));

        jLabel23.setFont(new java.awt.Font("Gorlock", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Change Password");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 300, 30));

        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-password-50.png"))); // NOI18N
        jPanel4.add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 550, 50));

        jLabel18.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Current Password");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, -1, 20));

        jLabel19.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("New Password");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, 20));

        newpass.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(251, 21, 21)));
        newpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newpassActionPerformed(evt);
            }
        });
        jPanel2.add(newpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 140, 30));

        jLabel20.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Confirm Password");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, 20));

        upd.setBackground(new java.awt.Color(255, 102, 102));
        upd.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        upd.setForeground(new java.awt.Color(255, 255, 255));
        upd.setText("Update Password");
        upd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updActionPerformed(evt);
            }
        });
        jPanel2.add(upd, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 150, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 120, 30));

        jLabel17.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Fill Out Details");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 300, 30));

        conpass.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(251, 21, 21)));
        jPanel2.add(conpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 300, 140, 30));

        oldpass.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(251, 21, 21)));
        jPanel2.add(oldpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 140, 30));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, -1, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 120, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        jLabel11.setForeground(Color.black);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        jLabel11.setForeground(new Color(240,240,240));
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel11KeyPressed
     
    }//GEN-LAST:event_jLabel11KeyPressed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
       details log = new details();
       log.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
            session ss = session.getInstance();
     
        if(ss.getUid()== 0){
            JOptionPane.showMessageDialog(null,"No Account, Login First!");
            login log = new login();
            log.setVisible(true);
            this.dispose();
        } else {
        
//        name.setText(""+ss.getLname());
//        email.setText(""+ss.getEmail());
        idd.setText("UID: "+ss.getUid());
        }
    }//GEN-LAST:event_formWindowActivated

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        userDashboard us = new userDashboard();
        us.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        jPanel8.setBackground(new Color(255,51,51));
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        jPanel8.setBackground(new Color(239,35,35));
    }//GEN-LAST:event_jPanel9MouseExited

    private void updActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updActionPerformed
   
         if (oldpass.getText().isEmpty() || newpass.getText().isEmpty() | conpass.getText().isEmpty())
        {
             JOptionPane.showMessageDialog(null,"Fill Out All Details!"); 
        }else {
        
                try {
                dbConnector db = new dbConnector();
                session ss = session.getInstance();             
                   String query = "SELECT * FROM tbl_user WHERE user_id ='"+ss.getUid()+"'";
                   ResultSet rs = db.getData(query);
                   if (rs.next()){
                      String olddbpass = rs.getString("user_pass");
                      String oldhash = passhash.hashPassword(oldpass.getText());
                      String pass1 = newpass.getText();
                      String pass2 = conpass.getText();
                       
                       if (!olddbpass.equals(oldhash)){
                        JOptionPane.showMessageDialog(null,"Current Password is Incorrect!");
                         oldpass.setText("");
                       }else if (newpass.getText().length() < 8 || !newpass.getText().equals(conpass.getText()) || conpass.getText().length() < 8 ){
                         if (newpass.getText().length() < 8 || conpass.getText().length() < 8){
                         JOptionPane.showMessageDialog(null, "Password must exceed to 8 characters!");            
                         } else {
                         JOptionPane.showMessageDialog(null, "Passwords do not match!");            
                         }
                       } else if (olddbpass.equals(passhash.hashPassword(pass1)) || olddbpass.equals(passhash.hashPassword(pass2))){
                         JOptionPane.showMessageDialog(null,"Fill Out New Password!");
                         newpass.setText("");
                         conpass.setText("");
                       }else {
                              String npass = passhash.hashPassword(newpass.getText());
                           db.updateData("UPDATE tbl_user SET user_pass = '"+npass+"'");
                           JOptionPane.showMessageDialog(null,"Successfully Updated!");
                           login log = new login();
                           log.setVisible(true);
                           this.dispose();
                       }
                       
                   }
                } catch (SQLException | NoSuchAlgorithmException ex){
                    System.out.println(""+ex);
                }
         }
    }//GEN-LAST:event_updActionPerformed

    private void newpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newpassActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(changepass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(changepass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(changepass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(changepass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new changepass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JPasswordField conpass;
    private javax.swing.JLabel idd;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel juser;
    private javax.swing.JLabel menuu;
    private javax.swing.JPasswordField newpass;
    private javax.swing.JPasswordField oldpass;
    public javax.swing.JButton upd;
    // End of variables declaration//GEN-END:variables
}
