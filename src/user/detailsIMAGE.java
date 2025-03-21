/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import static admin.adminRegi.contact;
import static admin.adminRegi.em;
import static admin.adminRegi.usname;
import config.dbConnector;
import config.passhash;
import config.session;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javaapplication8.login;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class detailsIMAGE extends javax.swing.JFrame {

    /**
     * Creates new form userDashboard
     */
    public detailsIMAGE() {
        initComponents();
        
        
//            Icon i1 = info.getIcon();
//        ImageIcon icon1 = (ImageIcon)i1;
//        Image image1 = icon1.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
//        info.setIcon(new ImageIcon(image1)); 
//             
//            Icon i2 = image.getIcon();
//        ImageIcon icon2 = (ImageIcon)i2;
//        Image image2 = icon2.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
//        image.setIcon(new ImageIcon(image2)); 
//        
            Icon i3 = back.getIcon();
        ImageIcon icon3 = (ImageIcon)i3;
        Image image3 = icon3.getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
        back.setIcon(new ImageIcon(image3)); 
        
         Icon i4 = menuu.getIcon();
        ImageIcon icon4 = (ImageIcon)i4;
        Image image4 = icon4.getImage().getScaledInstance(menuu.getWidth(), menuu.getHeight(), Image.SCALE_SMOOTH);
        menuu.setIcon(new ImageIcon(image4)); 
        
        
    }
    
 
    
//        public boolean updatecheck(){
//            
//              try{
//                    dbConnector dbc = new dbConnector();
//                    session ss = session.getInstance();
//                String query = "SELECT * FROM tbl_user WHERE (user_email = '" + email.getText() + "' OR user_phone = '" + phone.getText()+"') AND user_id != '"+ss.getUid()+"'";
//            ResultSet resultSet = dbc.getData(query);
//           
//                   if (resultSet.next()){                      
//                       em = resultSet.getString("user_email");                   
//                       if(em.equals(email.getText())){
//                           JOptionPane.showMessageDialog(null, "Email was already used!");
//                          
//                       }
//                       
//                       contact =resultSet.getString("user_phone");
//                       if(contact.equals(phone.getText())){
//                           JOptionPane.showMessageDialog(null, "Phone number was already used!");
//                          
//                       }
//                       
//                       
//     
//                       return true;
//                   }else {
//                       return false;
//                   }
//              }catch (SQLException ex){
//                  System.out.println(""+ex);
//                  return false;
//              }
//          }
        
    public String destination = "";
    File selectedFile;
    public String oldpath;
    public String path;
        
        public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/userimage", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    
    }
         public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
}
        
        public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            // Read the image file
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            // Get the original width and height of the image
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            // Calculate the new height based on the desired width and the aspect ratio
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
        
       


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        juser = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        idd = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        menuu = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        pic = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        rem = new javax.swing.JButton();
        upd = new javax.swing.JButton();
        add = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        jButton1.setText("jButton1");

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

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/orderflow.png"))); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 460));

        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Dashboard / Account Details / Profile Picture");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 31, 530, 60));

        jPanel6.setBackground(new java.awt.Color(204, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("/");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 12, 50, -1));

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

        jLabel14.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Home");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 12, -1, -1));

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

        jLabel17.setFont(new java.awt.Font("Gorlock", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ACCOUNT INFORMATION");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 30));

        jLabel16.setFont(new java.awt.Font("Gorlock", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 51, 51));
        jLabel16.setText("ACCOUNT INFORMATION");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 300, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 550, 50));

        jPanel8.setBackground(new java.awt.Color(252, 252, 252));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel16.add(pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 250));

        jPanel8.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 270));

        jLabel26.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Profile Picture");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 120, 30));

        rem.setBackground(new java.awt.Color(255, 102, 102));
        rem.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        rem.setForeground(new java.awt.Color(255, 255, 255));
        rem.setText("REMOVE");
        rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remActionPerformed(evt);
            }
        });
        jPanel8.add(rem, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 170, 50));

        upd.setBackground(new java.awt.Color(255, 102, 102));
        upd.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        upd.setForeground(new java.awt.Color(255, 255, 255));
        upd.setText("UPDATE PICTURE");
        upd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updActionPerformed(evt);
            }
        });
        jPanel8.add(upd, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, 170, 50));

        add.setBackground(new java.awt.Color(255, 102, 102));
        add.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("ADD PICTURE");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel8.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 170, 50));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 520, 290));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

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
       userDashboard log = new userDashboard();
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
        idd.setText("UID: "+ss.getUid());

        }
    }//GEN-LAST:event_formWindowActivated

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remActionPerformed
 
       
    }//GEN-LAST:event_remActionPerformed

    private void updActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
         

    }//GEN-LAST:event_addActionPerformed

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
            java.util.logging.Logger.getLogger(detailsIMAGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detailsIMAGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detailsIMAGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detailsIMAGE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new detailsIMAGE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    private javax.swing.JLabel back;
    private javax.swing.JLabel idd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel juser;
    private javax.swing.JLabel menuu;
    public javax.swing.JLabel pic;
    public javax.swing.JButton rem;
    public javax.swing.JButton upd;
    // End of variables declaration//GEN-END:variables
}
