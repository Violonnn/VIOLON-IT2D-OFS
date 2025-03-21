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
public class details extends javax.swing.JFrame {

    /**
     * Creates new form userDashboard
     */
    public details() {
        initComponents();
        
        
//            Icon i1 = info.getIcon();
//        ImageIcon icon1 = (ImageIcon)i1;
//        Image image1 = icon1.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
//        info.setIcon(new ImageIcon(image1)); 
//             
            Icon i2 = image.getIcon();
        ImageIcon icon2 = (ImageIcon)i2;
        Image image2 = icon2.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(image2)); 
        
            Icon i3 = back.getIcon();
        ImageIcon icon3 = (ImageIcon)i3;
        Image image3 = icon3.getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
        back.setIcon(new ImageIcon(image3)); 
        
         Icon i4 = menuu.getIcon();
        ImageIcon icon4 = (ImageIcon)i4;
        Image image4 = icon4.getImage().getScaledInstance(menuu.getWidth(), menuu.getHeight(), Image.SCALE_SMOOTH);
        menuu.setIcon(new ImageIcon(image4)); 
        
        
    }
    
 
    
        public boolean updatecheck(){
            
              try{
                    dbConnector dbc = new dbConnector();
                    session ss = session.getInstance();
                String query = "SELECT * FROM tbl_user WHERE (user_email = '" + email.getText() + "' OR user_phone = '" + phone.getText()+"') AND user_id != '"+ss.getUid()+"'";
            ResultSet resultSet = dbc.getData(query);
           
                   if (resultSet.next()){                      
                       em = resultSet.getString("user_email");                   
                       if(em.equals(email.getText())){
                           JOptionPane.showMessageDialog(null, "Email was already used!");
                          
                       }
                       
                       contact =resultSet.getString("user_phone");
                       if(contact.equals(phone.getText())){
                           JOptionPane.showMessageDialog(null, "Phone number was already used!");
                          
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
        jLabel15 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lname1 = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        fname2 = new javax.swing.JLabel();
        fname3 = new javax.swing.JLabel();
        fname5 = new javax.swing.JLabel();
        fname6 = new javax.swing.JLabel();
        fn = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        mn = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        ln = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        phone = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        email2 = new javax.swing.JLabel();
        lname = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        fname1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        fname4 = new javax.swing.JLabel();
        PIC = new javax.swing.JLabel();
        select = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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
        jLabel12.setText("Dashboard / Account Details");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 31, 180, 60));

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

        jLabel15.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 102, 102));
        jLabel15.setText("Click Here!");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 420, 80, 30));

        jPanel9.setBackground(new java.awt.Color(252, 252, 252));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lname1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        lname1.setForeground(new java.awt.Color(51, 51, 51));
        lname1.setText("Personal Information");
        jPanel9.add(lname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 10));

        fname.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname.setForeground(new java.awt.Color(51, 51, 51));
        fname.setText("Phone");
        jPanel9.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 90, 20));

        fname2.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname2.setForeground(new java.awt.Color(51, 51, 51));
        fname2.setText("First Name");
        jPanel9.add(fname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, 20));

        fname3.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname3.setForeground(new java.awt.Color(51, 51, 51));
        fname3.setText("Middle Name");
        jPanel9.add(fname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, 90, 20));

        fname5.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname5.setForeground(new java.awt.Color(51, 51, 51));
        fname5.setText("Email");
        jPanel9.add(fname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, 20));

        fname6.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname6.setForeground(new java.awt.Color(51, 51, 51));
        fname6.setText("Last Name");
        jPanel9.add(fname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 90, 20));

        fn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        fn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnActionPerformed(evt);
            }
        });
        jPanel9.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 30));

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

        jPanel9.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        mn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        mn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnActionPerformed(evt);
            }
        });
        jPanel9.add(mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 140, 30));

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

        jPanel9.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, 30));

        ln.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        ln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnActionPerformed(evt);
            }
        });
        jPanel9.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 140, 30));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, 30));

        email.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel9.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 140, 30));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 30));

        phone.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        jPanel9.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 140, 30));

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

        jPanel9.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, 30));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 520, 160));

        jPanel8.setBackground(new java.awt.Color(252, 252, 252));
        jPanel8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user (1).png"))); // NOI18N
        jPanel8.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 50, 50));

        email2.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        email2.setForeground(new java.awt.Color(204, 204, 204));
        email2.setText("mstevenviolon@gmail.com");
        jPanel8.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 170, 10));

        lname.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        lname.setForeground(new java.awt.Color(51, 51, 51));
        lname.setText("Violon,");
        jPanel8.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 60, 10));

        user.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        user.setForeground(new java.awt.Color(204, 204, 204));
        user.setText("(testest)");
        jPanel8.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 90, 10));

        fname1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        fname1.setForeground(new java.awt.Color(51, 51, 51));
        fname1.setText("Marc Steven");
        jPanel8.add(fname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 90, 10));

        jPanel10.setBackground(new java.awt.Color(255, 102, 102));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fname4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        fname4.setForeground(new java.awt.Color(255, 255, 255));
        fname4.setText("Update");
        jPanel10.add(fname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 50, 20));

        jPanel8.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, 30));
        jPanel8.add(PIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 50));

        select.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        select.setForeground(new java.awt.Color(255, 51, 51));
        select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        select.setText("Upload Picture");
        select.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMouseExited(evt);
            }
        });
        jPanel8.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 68, 60, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 520, 80));

        jLabel25.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(51, 51, 51));
        jLabel25.setText("Change Password?");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 120, 30));

        jLabel26.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("My Profile");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 128, 120, 30));

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
        
//        name.setText(""+ss.getLname());
//        email.setText(""+ss.getEmail());
        idd.setText("UID: "+ss.getUid());
        lname.setText(""+ss.getLname());
        fname1.setText(""+ss.getFname());
        email2.setText(""+ss.getEmail());
        user.setText(""+ss.getUsername());
        }
    }//GEN-LAST:event_formWindowActivated

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        jLabel15.setForeground(Color.red);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        jLabel15.setForeground(new Color(255,51,51));
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        changepass ch = new changepass();
        ch.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void fnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnActionPerformed

    private void mnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnActionPerformed

    private void lnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void phoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
           
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
//                            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//
//        String emailInput = email.getText().trim();
//              
//        if(fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()){       
//             JOptionPane.showMessageDialog(null, "All fields are required!");            
//        }else if (updatecheck()){
//            System.out.println("Duplicate Existed!");
//        } else if (phone.getText().length() != 11 || !phone.getText().matches("\\d+") || 
//         (!phone.getText().substring(0, 2).equals("09"))) {  
//          JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");       
//        } else if(!pattern.matcher(emailInput).matches()) {
//            JOptionPane.showMessageDialog(null, "Invalid Email!");        
//        } else {       
//              dbConnector dbc = new dbConnector();     
//              try {
//       if(dbc.insertData("INSERT INTO tbl_user (user_Fname, user_Mname, user_Lname, user_email, user_phone) VALUES ('"+fn.getText()+"','"+mn.getText()+"','"+ln.getText()+"','"+email.getText()+"',"
//                        +"'"+phone.getText()+ "')")){
//           JOptionPane.showMessageDialog(null, "Inserted Data Succesfully!");
//           userDashboard log = new userDashboard();
//           log.setVisible(true);
//           this.dispose();
//       } else {
//           JOptionPane.showMessageDialog(null, "Connection Error!");
//       }
//        } catch(Exception ex){
//                  System.out.println(""+ex);
//        }
//        }
//        
        
String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
Pattern pattern = Pattern.compile(emailRegex);

String emailInput = email.getText().trim();

if (fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty() || 
    phone.getText().isEmpty() || email.getText().isEmpty()) {       
    JOptionPane.showMessageDialog(null, "All fields are required!");            
} else if (updatecheck()) {
    System.out.println("Duplicate Existed!");
} else if (phone.getText().length() != 11 || !phone.getText().matches("\\d+") || 
           (!phone.getText().substring(0, 2).equals("09"))) {  
    JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");       
} else if (!pattern.matcher(emailInput).matches()) {
    JOptionPane.showMessageDialog(null, "Invalid Email!");        
} else {       
    dbConnector dbc = new dbConnector();     
    try {
        session ss = session.getInstance();  // Get the current user session (assuming `session` holds user data)
        int userId = ss.getUid(); // Get the current user's ID
        
        // Step 1: Retrieve the current data from the database
        String selectQuery = "SELECT user_Fname, user_Mname, user_Lname, user_email, user_phone FROM tbl_user WHERE user_id = '" + userId + "'";
        ResultSet rs = dbc.getData(selectQuery);  // Assuming `getData()` is a method that fetches a ResultSet from the DB
        
        String currentFname = "", currentMname = "", currentLname = "", currentEmail = "", currentPhone = "";
        
        if (rs.next()) {
            currentFname = rs.getString("user_Fname");
            currentMname = rs.getString("user_Mname");
            currentLname = rs.getString("user_Lname");
            currentEmail = rs.getString("user_email");
            currentPhone = rs.getString("user_phone");
        }

        // Step 2: Compare the current values with the user input
        boolean noChangesDetected = currentFname.equals(fn.getText()) &&
                                    currentMname.equals(mn.getText()) &&
                                    currentLname.equals(ln.getText()) &&
                                    currentEmail.equals(email.getText()) &&
                                    currentPhone.equals(phone.getText());

        // Step 3: If no changes detected, show a message and do nothing
        if (noChangesDetected) {
            JOptionPane.showMessageDialog(null, "No changes detected. Update not required.");
        } else {
            // Step 4: If changes detected, proceed with the update
            
            String updateQuery = "UPDATE tbl_user SET user_Fname = '" + fn.getText() + "', "
                                 + "user_Mname = '" + mn.getText() + "', "
                                 + "user_Lname = '" + ln.getText() + "', "
                                 + "user_email = '" + email.getText() + "', "
                                 + "user_phone = '" + phone.getText() + "' "
                                 + "WHERE user_id = '" + userId + "'";  // Assuming `user_id` is the unique field to identify the user
            
            if (dbc.insertData(updateQuery)) {
                JOptionPane.showMessageDialog(null, "Updated Data Successfully!");
                login log = new login();
                log.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Connection Error!");
            }
        }
    } catch (Exception ex) {
        System.out.println("Error: " + ex);
    }
}

        
    
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
         jPanel10.setBackground(Color.red);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
       jPanel10.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jPanel10MouseExited

    private void selectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseEntered
       select.setForeground(Color.red);
    }//GEN-LAST:event_selectMouseEntered

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
    
    
        try{
               session ss = session.getInstance();
               int loginuser = ss.getUid();
               dbConnector db = new dbConnector();
               detailsIMAGE im = new detailsIMAGE();
               ResultSet rs = db.getData("SELECT * FROM tbl_user WHERE user_id ='"+loginuser+"'");
               if(rs.next()){
               if (!rs.getString("user_image").isEmpty()){       
               im.pic.setIcon(im.ResizeImage(rs.getString("user_image"), null, im.pic));
               im.oldpath = rs.getString("user_image");
               im.path = rs.getString("user_image");
               im.destination = rs.getString("user_image");
               im.add.setEnabled(true);
               im.upd.setEnabled(false);
               im.rem.setEnabled(false);
               im.setVisible(true);
               this.dispose();
               } else {
                  im.add.setEnabled(false);
                  im.upd.setEnabled(true);
                  im.rem.setEnabled(true);
                  im.setVisible(true);
                  this.dispose();
               }              
               } else {
                   JOptionPane.showMessageDialog(null, "Connection Error!");
               } 
               
           }catch(SQLException ex){
               System.out.println(""+ex);
           }
        
   
   
          
        
//        if(select.getText().equals("Select Image")){
//               JFileChooser fileChooser = new JFileChooser();
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    try {
//                        selectedFile = fileChooser.getSelectedFile();
//                        destination = "src/userimage/" + selectedFile.getName();
//                        path  = selectedFile.getAbsolutePath();
//                        
//                        
//                        if(FileExistenceChecker(path) == 1){
//                          JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
//                            destination = "";
//                            path="";
//                        }else{
//                            image.setIcon(ResizeImage(path, null, image));
//                            select.setText("Confirm??");                       
//                        }
//                    } catch (Exception ex) {
//                        System.out.println("File Error!");
//                    }
//                }
//        }else if (select.getText().equals("Confirm?")) {
//    
//      dbConnector db = new dbConnector();
//      if(db.insertData("INSERT INTO tbl_user (user_image) VALUES ('"+destination+"')")){                
//      
//       try { 
//          Files.copy(selectedFile.toPath(),new File(destination).toPath(),StandardCopyOption.REPLACE_EXISTING);
//          JOptionPane.showMessageDialog(null, "Image Successfully uploaded!");     
//          userDashboard us = new userDashboard();
//          us.setVisible(true);
//          this.dispose();
//          
//       }catch(IOException ex) {
//              System.out.println("Image Error"+ex);
//          }       
//      }else {
//            JOptionPane.showMessageDialog(null, "Connection Error!");
//      }  
//        } 
//        else {
//            select.setText("Select Image");
//            image.setIcon(null);
//            destination = "";
//            path = "";
//        }
//        
        
        
     
    }//GEN-LAST:event_selectMouseClicked

    private void selectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseExited
       select.setForeground(new Color(255,51,51));
    }//GEN-LAST:event_selectMouseExited

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
            java.util.logging.Logger.getLogger(details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PIC;
    private javax.swing.JLabel back;
    public javax.swing.JTextField email;
    private javax.swing.JLabel email2;
    public javax.swing.JTextField fn;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel fname1;
    private javax.swing.JLabel fname2;
    private javax.swing.JLabel fname3;
    private javax.swing.JLabel fname4;
    private javax.swing.JLabel fname5;
    private javax.swing.JLabel fname6;
    private javax.swing.JLabel idd;
    private javax.swing.JLabel image;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel juser;
    public javax.swing.JTextField ln;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel lname1;
    private javax.swing.JLabel menuu;
    public javax.swing.JTextField mn;
    public javax.swing.JTextField phone;
    private javax.swing.JLabel select;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
