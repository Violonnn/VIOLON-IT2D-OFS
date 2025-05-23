/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import javaapplication8.login;
import javaapplication8.register;
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
public class adminRegi extends javax.swing.JFrame {

    /**
     * Creates new form adminRegi
     */
    public adminRegi() {
        initComponents();
         init();
         Icon i2 = mana.getIcon();
        ImageIcon icon2 = (ImageIcon)i2;
        Image image2 = icon2.getImage().getScaledInstance(mana.getWidth(), mana.getHeight(), Image.SCALE_SMOOTH);
        mana.setIcon(new ImageIcon(image2));
        
           Icon i1 = menu.getIcon();
        ImageIcon icon1 = (ImageIcon)i1;
        Image image1 = icon1.getImage().getScaledInstance(menu.getWidth(), menu.getHeight(), Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(image1));
        
       
    }
    
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

      public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }
    
    
    
    
     public static String em, usname, contact;
          public boolean duplicateCheck(){
              dbConnector dbc = new dbConnector();
              try{
                String query = "SELECT * FROM tbl_user WHERE user_username = '" + username.getText() + "' OR user_email = '" + email.getText() + "' OR user_phone = '" + phone.getText()+"'";
            ResultSet resultSet = dbc.getData(query);
           
                   if (resultSet.next()){                      
                       em = resultSet.getString("user_email");                   
                       if(em.equals(email.getText())){
                           JOptionPane.showMessageDialog(null, "Email was already used!");
                           email.setText("");
                       }
                       
                       usname =resultSet.getString("user_username");
                       if(usname.equals(username.getText())){
                           JOptionPane.showMessageDialog(null, "Username was already used!");
                           username.setText("");
                       }
                       
                       contact =resultSet.getString("user_phone");
                       if(contact.equals(phone.getText())){
                           JOptionPane.showMessageDialog(null, "Phone number was already used!");
                           phone.setText("");
                       }
                       
                       
     
                       return true;
                   }else {
                       return false;
                   }
              }catch (SQLException ex){
                  System.out.println(""+ex);
                  return false;
              }
          }
    
                    public boolean updatecheck(){
              dbConnector dbc = new dbConnector();
              try{
                String query = "SELECT * FROM tbl_user WHERE (user_username = '" + username.getText() + "' OR user_email = '" + email.getText() + "' OR user_phone = '" + phone.getText()+"') AND user_id != '"+uid.getText()+"'";
            ResultSet resultSet = dbc.getData(query);
           
                   if (resultSet.next()){                      
                       em = resultSet.getString("user_email");                   
                       if(em.equals(email.getText())){
                           JOptionPane.showMessageDialog(null, "Email was already used!");
                           email.setText("");
                       }
                       
                       usname =resultSet.getString("user_username");
                       if(usname.equals(username.getText())){
                           JOptionPane.showMessageDialog(null, "Username was already used!");
                           username.setText("");
                       }
                       contact =resultSet.getString("user_phone");
                       
                       if(contact.equals(phone.getText())){
                           JOptionPane.showMessageDialog(null, "Phone number was already used!");
                           phone.setText("");
                       }
                       
                       
     
                       return true;
                   }else {
                       return false;
                   }
              }catch (SQLException ex){
                  System.out.println(""+ex);
                  return false;
              }
          }
                    
                    private String initialFName, initialMName, initialLName, initialUsername, initialPhone, initialEmail;

public void init() {
    // Initialize form and capture initial values
    initializeForm();
}

private void initializeForm() {
    // Store initial values of the text fields
    initialFName = fn.getText().trim();
    initialMName = mn.getText().trim();
    initialLName = ln.getText().trim();
    initialUsername = username.getText().trim();
    initialPhone = phone.getText().trim();
    initialEmail = email.getText().trim();
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
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        order3 = new javax.swing.JLabel();
        email2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        home = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        mana = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        ln = new javax.swing.JTextField();
        type = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        mn = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        fn = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        phone = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        newpas = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        can = new javax.swing.JButton();
        uid = new javax.swing.JTextField();
        pass1 = new javax.swing.JPasswordField();
        jPanel13 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        upd = new javax.swing.JButton();
        stats = new javax.swing.JComboBox<>();
        haha = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        remove = new javax.swing.JButton();
        select = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        prof = new javax.swing.JLabel();
        pass2 = new javax.swing.JPasswordField();
        jPanel19 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        changePass = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jPanel2.setPreferredSize(new java.awt.Dimension(760, 500));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(74, 73, 73)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 180, 10));

        order3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/orderflow.png"))); // NOI18N
        order3.setAlignmentY(10.0F);
        jPanel3.add(order3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        email2.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        email2.setForeground(new java.awt.Color(204, 204, 204));
        email2.setText("UID:");
        jPanel3.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 110, -1));

        jLabel23.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Order Flow System");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 20, -1, -1));

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel17MouseExited(evt);
            }
        });
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        home.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setText("Home");
        jPanel17.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 3, -1, -1));

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 180, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 620));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manager.png"))); // NOI18N
        mana.setAlignmentY(10.0F);
        jPanel1.add(mana, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 5, 30, 30));

        jLabel1.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 12, -1, -1));

        jLabel3.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("/");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 20, -1));

        jLabel5.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("/");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, -1, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        menu.setAlignmentY(10.0F);
        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 14, 10, 10));

        jLabel6.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Admin Dashboard");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, -1, -1));

        jLabel10.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("User Configuration");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 11, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 600, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, -1, -1));

        jLabel11.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dashboard / Home / Users List / User Configuration");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 600, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 60, -1, -1));

        ln.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        ln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnActionPerformed(evt);
            }
        });
        jPanel2.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 140, 30));

        type.setFont(new java.awt.Font("Gorlock", 0, 11)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Select", "Admin", "User" }));
        type.setBorder(null);
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        jPanel2.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 130, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, 30));

        mn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        mn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnActionPerformed(evt);
            }
        });
        jPanel2.add(mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 140, 30));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, -1, 30));

        fn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        fn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnActionPerformed(evt);
            }
        });
        jPanel2.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 160, 140, 30));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, -1, 30));

        username.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 140, 30));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, -1, 30));

        email.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 190, 30));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 480, -1, 30));

        phone.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        jPanel2.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 190, 30));

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

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 120, 30));

        jLabel8.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("First Name");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, 40));

        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Status");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, -1, 20));

        jLabel15.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("UID");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, 30));

        jLabel16.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Last Name");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, -1, 30));

        jLabel17.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Username");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, -1, 20));

        newpas.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        newpas.setForeground(new java.awt.Color(51, 51, 51));
        newpas.setText("New Password");
        jPanel2.add(newpas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, -1, 20));

        jLabel20.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Email");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, -1, 20));

        jLabel21.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(51, 51, 51));
        jLabel21.setText("Phone");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, -1, 20));

        jLabel22.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Type");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, 20));

        can.setBackground(new java.awt.Color(255, 102, 102));
        can.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        can.setForeground(new java.awt.Color(255, 255, 255));
        can.setText("Cancel");
        can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canActionPerformed(evt);
            }
        });
        jPanel2.add(can, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, 140, 40));

        uid.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        uid.setEnabled(false);
        uid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uidActionPerformed(evt);
            }
        });
        jPanel2.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 60, 20));

        pass1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        pass1.setEnabled(false);
        jPanel2.add(pass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 190, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 149, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 150, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 70, 20));

        jLabel25.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Middle Name");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, 40));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));

        add.setBackground(new java.awt.Color(255, 102, 102));
        add.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel2.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 540, 140, 40));

        upd.setBackground(new java.awt.Color(255, 102, 102));
        upd.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        upd.setForeground(new java.awt.Color(255, 255, 255));
        upd.setText("Update");
        upd.setEnabled(false);
        upd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updActionPerformed(evt);
            }
        });
        jPanel2.add(upd, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 540, 140, 40));

        stats.setFont(new java.awt.Font("Gorlock", 0, 11)); // NOI18N
        stats.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Select", "Active", "Pending" }));
        stats.setBorder(null);
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsActionPerformed(evt);
            }
        });
        jPanel2.add(stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 130, 30));

        haha.setFont(new java.awt.Font("Gorlock", 1, 18)); // NOI18N
        haha.setForeground(new java.awt.Color(51, 51, 51));
        haha.setText("User Registration");
        jPanel2.add(haha, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 96, -1, -1));

        jPanel9.setBackground(new java.awt.Color(252, 252, 252));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove.setBackground(new java.awt.Color(255, 102, 102));
        remove.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        remove.setForeground(new java.awt.Color(255, 255, 255));
        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel9.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 140, 40));

        select.setBackground(new java.awt.Color(255, 102, 102));
        select.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        select.setForeground(new java.awt.Color(255, 255, 255));
        select.setText("Select");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel9.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 140, 40));

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel9.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 110, 100));

        prof.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        prof.setForeground(new java.awt.Color(51, 51, 51));
        prof.setText("Profile Picture");
        jPanel9.add(prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 320, 150));

        pass2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        pass2.setEnabled(false);
        jPanel2.add(pass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 190, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 120, 30));

        jLabel24.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(51, 51, 51));
        jLabel24.setText("Confirm Password");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, -1, 20));

        changePass.setBackground(new java.awt.Color(255, 255, 255));
        changePass.setFont(new java.awt.Font("Gorlock", 0, 11)); // NOI18N
        changePass.setText("Change Password");
        changePass.setBorder(null);
        changePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassActionPerformed(evt);
            }
        });
        jPanel2.add(changePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        userList1 ad = new userList1();
        ad.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jLabel1.setForeground(Color.red);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jLabel1MouseExited

    private void lnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnActionPerformed

    private void mnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnActionPerformed

    private void fnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_canActionPerformed
           
        userList1 user = new userList1();
        user.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_canActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        session ss = session.getInstance();
        
        email2.setText("UID: "+ss.getUid());
    }//GEN-LAST:event_formWindowActivated

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        adminDashboard1 reg = new adminDashboard1();
        reg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        jPanel17.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        jPanel17.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_jPanel17MouseExited

    private void uidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uidActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed

         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                        "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailRegex);

    String emailInput = email.getText().trim();

    if (fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || 
        username.getText().isEmpty() || pass1.getText().isEmpty() || 
        phone.getText().isEmpty() || email.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "All fields are required!");
    } else if (duplicateCheck()) {
        System.out.println("Duplicate Existed!");
    } else if (type.getSelectedItem().toString().equals(" Select")) {
        JOptionPane.showMessageDialog(null, "Please Select User Type");
    } else if (stats.getSelectedItem().toString().equals(" Select")) {
        JOptionPane.showMessageDialog(null, "Please Select User Status");
    } else if (pass1.getText().length() < 8) {
        JOptionPane.showMessageDialog(null, "Password must exceed 8 characters!");
    } else if (phone.getText().length() != 11 || !phone.getText().matches("\\d+") || 
               (!phone.getText().substring(0, 2).equals("09"))) {
        JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");
    } else if (destination == null || destination.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select an image!");
    } else if (!pattern.matcher(emailInput).matches()) {
        JOptionPane.showMessageDialog(null, "Invalid Email!");
    } else {
        dbConnector dbc = new dbConnector();
        Connection conn = dbc.getConnection();

        try {
            String pass = passhash.hashPassword(pass1.getText());

            String sql = "INSERT INTO tbl_user (user_Fname, user_Mname, user_Lname, user_email, " +
                         "user_phone, user_username, user_pass, user_type, user_stats, user_image) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, fn.getText());
            pstmt.setString(2, mn.getText());
            pstmt.setString(3, ln.getText());
            pstmt.setString(4, email.getText());
            pstmt.setString(5, phone.getText());
            pstmt.setString(6, username.getText());
            pstmt.setString(7, pass);
            pstmt.setString(8, type.getSelectedItem().toString());
            pstmt.setString(9, stats.getSelectedItem().toString());
            pstmt.setString(10, destination);

            int rowsInserted = pstmt.executeUpdate();
            
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int generatedId = 1;
            if(generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            
            session ss = session.getInstance();
            dbConnector db = new dbConnector();
            String action = "Added User Record with ID No.  "+generatedId;
            db.insertData("INSERT INTO tbl_logs (user_id, actions, date) VALUE ('"+ss.getUid()+"','"+action+"','"+LocalDateTime.now()+"')");

            if (rowsInserted > 0) {
                try {
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "Inserted Data Successfully!");
                    login log = new login();
                    log.setVisible(true);
                    this.dispose();
                } catch (IOException ex) {
                    System.out.println("Insert Image Error: " + ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Connection Error!");
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
    
    }//GEN-LAST:event_addActionPerformed

    private void updActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updActionPerformed

         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        String emailInput = email.getText().trim();

        if(fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || username.getText().isEmpty()
                || phone.getText().isEmpty() || email.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }else if (updatecheck()){
            System.out.println("Duplicate Existed!");
            return;
        }else if (type.getSelectedItem() == " Select" || stats.getSelectedItem() == " Select"){
            JOptionPane.showMessageDialog(null, "Please Fill Out User Type and Role!");
            return;
        }else if (phone.getText().length() != 11 || !phone.getText().matches("\\d+") ||
                (!phone.getText().substring(0, 2).equals("09"))) {
            JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");
            return;
        } else if(!pattern.matcher(emailInput).matches()) {
            JOptionPane.showMessageDialog(null, "Invalid Email!");
            return;
        }

       try {
    dbConnector db = new dbConnector();
    Connection conn = db.getConnection();

    String sql = "UPDATE tbl_user SET user_Fname = ?, user_Mname = ?, user_Lname = ?, user_email = ?, user_phone = ?, " +
                 "user_username = ?, user_type = ?, user_stats = ?, user_image = ?";

    String newPass = null;

    if (changePass.isSelected()) {
        String query = "SELECT user_pass FROM tbl_user WHERE user_id = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, uid.getText());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String olddbpass = rs.getString("user_pass");
            String pass11 = new String(pass1.getPassword());
            String pass22 = new String(pass2.getPassword());

            if (pass1.getPassword().length < 8 || pass2.getPassword().length < 8) {
                JOptionPane.showMessageDialog(null, "Password must exceed to 8 characters!");
                return;
            } else if (!pass11.equals(pass22)) {
                JOptionPane.showMessageDialog(null, "Passwords do not match!");
                return;
            } else if (olddbpass.equals(passhash.hashPassword(pass11))) {
                JOptionPane.showMessageDialog(null, "Fill Out New Password!");
                pass1.setText("");
                pass2.setText("");
                return;
            } else {
                newPass = passhash.hashPassword(pass11);
                sql += ", user_pass = ?";
            }
        }
    }

    sql += " WHERE user_id = ?";

    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    pstmt.setString(1, fn.getText());
    pstmt.setString(2, mn.getText());
    pstmt.setString(3, ln.getText());
    pstmt.setString(4, email.getText());
    pstmt.setString(5, phone.getText());
    pstmt.setString(6, username.getText());
    pstmt.setString(7, type.getSelectedItem().toString());
    pstmt.setString(8, stats.getSelectedItem().toString());
    pstmt.setString(9, destination);
    
    
    
    int paramIndex = 10;
    if (newPass != null) {
        pstmt.setString(paramIndex++, newPass);
    }

    pstmt.setString(paramIndex, uid.getText());

    int rowsUpdated = pstmt.executeUpdate();
     
    
       ResultSet generatedKeys = pstmt.getGeneratedKeys();
            int generatedId = 1;
            if(generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
            
            session ss = session.getInstance();
            String action = "Updated User Record with ID No.  "+generatedId;
            db.insertData("INSERT INTO tbl_logs (user_id, actions, date) VALUE ('"+ss.getUid()+"','"+action+"','"+LocalDateTime.now()+"')");

    
    if (destination.isEmpty()) {
        File existingFile = new File(oldpath);
        if (existingFile.exists()) {
            existingFile.delete();
        }
    } else {
        if (!oldpath.equals(path)) {
            imageUpdater(oldpath, path);
        }
    }

    userList1 log = new userList1();
    log.setVisible(true);
    this.dispose();

} catch (SQLException | NoSuchAlgorithmException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
}
 
       
    }//GEN-LAST:event_updActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statsActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
       remove.setEnabled(false);
       select.setEnabled(true);
       image.setIcon(null);
       destination = "";
       path = "";
    }//GEN-LAST:event_removeActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
       JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        selectedFile = fileChooser.getSelectedFile();
                        destination = "src/userimage/" + selectedFile.getName();
                        path  = selectedFile.getAbsolutePath();
                        
                        
                        if(FileExistenceChecker(path) == 1){
                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                            destination = "";
                            path="";
                        }else{
                            image.setIcon(ResizeImage(path, null, image));
                            select.setEnabled(false);
                            remove.setEnabled(true);
                        }
                    } catch (Exception ex) {
                        System.out.println("File Error!");
                    }
                }

        
        
        
    }//GEN-LAST:event_selectActionPerformed

    private void changePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassActionPerformed
      boolean checked = changePass.isSelected();
        pass1.setEnabled(checked);
        pass2.setEnabled(checked);
        if (!checked) {
            pass1.setText("");
            pass2.setText("");
        }
    }//GEN-LAST:event_changePassActionPerformed

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
            java.util.logging.Logger.getLogger(adminRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminRegi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminRegi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton add;
    public javax.swing.JButton can;
    public javax.swing.JCheckBox changePass;
    public javax.swing.JTextField email;
    private javax.swing.JLabel email2;
    public javax.swing.JTextField fn;
    public javax.swing.JLabel haha;
    private javax.swing.JLabel home;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTextField ln;
    private javax.swing.JLabel mana;
    private javax.swing.JLabel menu;
    public javax.swing.JTextField mn;
    public javax.swing.JLabel newpas;
    private javax.swing.JLabel order3;
    public javax.swing.JPasswordField pass1;
    public javax.swing.JPasswordField pass2;
    public javax.swing.JTextField phone;
    public javax.swing.JLabel prof;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    public javax.swing.JComboBox<String> stats;
    public javax.swing.JComboBox<String> type;
    public javax.swing.JTextField uid;
    public javax.swing.JButton upd;
    public javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
