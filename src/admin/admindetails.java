/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static admin.adminRegi.contact;
import static admin.adminRegi.em;
import static admin.adminRegi.getHeightFromWidth;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class admindetails extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard1
     */
    public admindetails() {
        initComponents();
        
        Icon i1 = menu.getIcon();
        ImageIcon icon1 = (ImageIcon)i1;
        Image image1 = icon1.getImage().getScaledInstance(menu.getWidth(), menu.getHeight(), Image.SCALE_SMOOTH);
        menu.setIcon(new ImageIcon(image1));
        
        Icon i2 = mana.getIcon();
        ImageIcon icon2 = (ImageIcon)i2;
        Image image2 = icon2.getImage().getScaledInstance(mana.getWidth(), mana.getHeight(), Image.SCALE_SMOOTH);
        mana.setIcon(new ImageIcon(image2));
        
//         Icon i3 = eye.getIcon();
//        ImageIcon icon3 = (ImageIcon)i3;
//        Image image3 = icon3.getImage().getScaledInstance(eye.getWidth(), eye.getHeight(), Image.SCALE_SMOOTH);
//        eye.setIcon(new ImageIcon(image3));
//        
         Icon i4 = stat.getIcon();
        ImageIcon icon4 = (ImageIcon)i4;
        Image image4 = icon4.getImage().getScaledInstance(stat.getWidth(), stat.getHeight(), Image.SCALE_SMOOTH);
        stat.setIcon(new ImageIcon(image4));
        
//         Icon i5 = info.getIcon();
//        ImageIcon icon5 = (ImageIcon)i5;
//        Image image5 = icon5.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
//        info.setIcon(new ImageIcon(image5));
        
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
        public boolean updatecheck(){
              dbConnector dbc = new dbConnector();
              session ss = session.getInstance();
              try{
                String query = "SELECT * FROM tbl_user WHERE (user_username = '" + phone1.getText() + "' OR user_email = '" + email.getText() + "' OR user_phone = '" + phone.getText()+"') AND user_id != '"+ss.getUid()+"'";
            ResultSet resultSet = dbc.getData(query);
           
                   if (resultSet.next()){                      
                       em = resultSet.getString("user_email");                   
                       if(em.equals(email.getText())){
                           JOptionPane.showMessageDialog(null, "Email was already used!");
                           email.setText("");
                       }
                       
                       usname =resultSet.getString("user_username");
                       if(usname.equals(phone1.getText())){
                           JOptionPane.showMessageDialog(null, "Username was already used!");
                           phone1.setText("");
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
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        mana = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        email3 = new javax.swing.JLabel();
        lname = new javax.swing.JLabel();
        user = new javax.swing.JLabel();
        fname1 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        fname4 = new javax.swing.JLabel();
        stat = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lname1 = new javax.swing.JLabel();
        fname = new javax.swing.JLabel();
        fname2 = new javax.swing.JLabel();
        fname3 = new javax.swing.JLabel();
        fname5 = new javax.swing.JLabel();
        fname6 = new javax.swing.JLabel();
        fn = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        mn = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        ln = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        phone = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        phone1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        fname7 = new javax.swing.JLabel();
        fname8 = new javax.swing.JLabel();
        changePass = new javax.swing.JCheckBox();
        fname9 = new javax.swing.JLabel();
        pass2 = new javax.swing.JPasswordField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        pass1 = new javax.swing.JPasswordField();
        jPanel20 = new javax.swing.JPanel();
        remove = new javax.swing.JButton();
        select = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        prof = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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
        jPanel3.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 110, -1));

        jLabel14.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Order Flow System");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 20, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 630));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manager.png"))); // NOI18N
        mana.setAlignmentY(10.0F);
        jPanel1.add(mana, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 5, 30, 30));

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
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(495, 11, -1, -1));

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

        jLabel7.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Account Details");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 11, -1, -1));

        jLabel13.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Admin Dashboard");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 570, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, -1, -1));

        jLabel11.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dashboard / Home / Account Details");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 570, 30));

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

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Account Information");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 17, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 580, 80));

        jPanel9.setBackground(new java.awt.Color(252, 252, 252));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email3.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        email3.setForeground(new java.awt.Color(204, 204, 204));
        email3.setText("mstevenviolon@gmail.com");
        jPanel9.add(email3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 170, 10));

        lname.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        lname.setForeground(new java.awt.Color(51, 51, 51));
        lname.setText("Violon,");
        jPanel9.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 130, 10));

        user.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        user.setForeground(new java.awt.Color(204, 204, 204));
        user.setText("(testest)");
        jPanel9.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 90, 10));

        fname1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        fname1.setForeground(new java.awt.Color(51, 51, 51));
        fname1.setText("Marc Steven");
        jPanel9.add(fname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 150, 10));

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

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, 30));

        stat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/software-engineer.png"))); // NOI18N
        stat.setAlignmentY(10.0F);
        jPanel9.add(stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 12, 50, 50));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 540, 80));

        jPanel11.setBackground(new java.awt.Color(252, 252, 252));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lname1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        lname1.setForeground(new java.awt.Color(51, 51, 51));
        lname1.setText("Personal Information");
        jPanel11.add(lname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 10));

        fname.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname.setForeground(new java.awt.Color(51, 51, 51));
        fname.setText("Phone");
        jPanel11.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 90, 20));

        fname2.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname2.setForeground(new java.awt.Color(51, 51, 51));
        fname2.setText("First Name");
        jPanel11.add(fname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 90, 20));

        fname3.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname3.setForeground(new java.awt.Color(51, 51, 51));
        fname3.setText("Middle Name");
        jPanel11.add(fname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 90, 20));

        fname5.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname5.setForeground(new java.awt.Color(51, 51, 51));
        fname5.setText("Email");
        jPanel11.add(fname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 90, 20));

        fname6.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname6.setForeground(new java.awt.Color(51, 51, 51));
        fname6.setText("Last Name");
        jPanel11.add(fname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 90, 20));

        fn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        fn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnActionPerformed(evt);
            }
        });
        jPanel11.add(fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 30));

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

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, 30));

        mn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        mn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnActionPerformed(evt);
            }
        });
        jPanel11.add(mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 140, 30));

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

        jPanel11.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, 30));

        ln.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        ln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnActionPerformed(evt);
            }
        });
        jPanel11.add(ln, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 140, 30));

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

        jPanel11.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, -1, 30));

        email.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel11.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, 30));

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

        jPanel11.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 30));

        phone.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        phone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneActionPerformed(evt);
            }
        });
        jPanel11.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 140, 30));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, 30));

        phone1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        phone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phone1ActionPerformed(evt);
            }
        });
        jPanel11.add(phone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 140, 30));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, 30));

        fname7.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname7.setForeground(new java.awt.Color(51, 51, 51));
        fname7.setText("Username");
        jPanel11.add(fname7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 90, 20));

        fname8.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname8.setForeground(new java.awt.Color(51, 51, 51));
        fname8.setText("New Password");
        jPanel11.add(fname8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 90, 20));

        changePass.setBackground(new java.awt.Color(252, 252, 252));
        changePass.setFont(new java.awt.Font("Gorlock", 0, 11)); // NOI18N
        changePass.setText("Change Password");
        changePass.setBorder(null);
        changePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePassActionPerformed(evt);
            }
        });
        jPanel11.add(changePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 20));

        fname9.setFont(new java.awt.Font("Gorlock", 0, 10)); // NOI18N
        fname9.setForeground(new java.awt.Color(51, 51, 51));
        fname9.setText("Confirm Password");
        jPanel11.add(fname9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 90, 20));

        pass2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        pass2.setEnabled(false);
        jPanel11.add(pass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 140, 30));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 10, 30));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 30));

        pass1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        pass1.setEnabled(false);
        jPanel11.add(pass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 140, 30));

        jPanel20.setBackground(new java.awt.Color(252, 252, 252));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove.setBackground(new java.awt.Color(255, 102, 102));
        remove.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        remove.setForeground(new java.awt.Color(255, 255, 255));
        remove.setText("Remove");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel20.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 140, 40));

        select.setBackground(new java.awt.Color(255, 102, 102));
        select.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        select.setForeground(new java.awt.Color(255, 255, 255));
        select.setText("Select");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        jPanel20.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 140, 40));

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 80));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 110, 100));

        prof.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        prof.setForeground(new java.awt.Color(51, 51, 51));
        prof.setText("Profile Picture");
        jPanel20.add(prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 90, 30));

        jPanel11.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 320, 150));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 265, 540, 350));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        jTextField1.setBorder(null);
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, -1, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

       order log = new order();
       log.setVisible(true);
       this.dispose();
         
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
         jLabel1.setForeground(Color.red);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jLabel1.setForeground(new Color(51,51,51));
    }//GEN-LAST:event_jLabel1MouseExited

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        session ss = session.getInstance();
     
        if(ss.getUid()== 0){
            JOptionPane.showMessageDialog(null,"No Account, Login First!");
            login log = new login();
            log.setVisible(true);
            this.dispose();
        } else {
        
        lname.setText(""+ss.getLname());
        fname1.setText(""+ss.getFname());
        email2.setText("UID: "+ss.getUid());
        email3.setText(""+ss.getEmail());
        user.setText("("+ss.getUsername()+")");
        }
    }//GEN-LAST:event_formWindowActivated

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

        if(fn.getText().isEmpty() || mn.getText().isEmpty() || ln.getText().isEmpty()
                || phone.getText().isEmpty() || email.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }else if (updatecheck()){
            System.out.println("Duplicate Existed!");
            return;
        }else if (phone.getText().length() != 11 || !phone.getText().matches("\\d+") ||
                (!phone.getText().substring(0, 2).equals("09"))) {
            JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");
            return;
        } else if(!pattern.matcher(emailInput).matches()) {
            JOptionPane.showMessageDialog(null, "Invalid Email!");
            return;
        }
        
        session ss = session.getInstance();
        String sql = "UPDATE tbl_user SET user_Fname = '" + fn.getText() + "', user_Mname = '" + mn.getText() + "', " +
                                "user_Lname = '" + ln.getText() + "', user_email = '" + email.getText() + "', user_phone = '" + phone.getText() + "', " +
                                "user_image = '"+destination+"'";
        
        if (changePass.isSelected()) {
            try {
                dbConnector db = new dbConnector();
                String query = "SELECT user_pass FROM tbl_user WHERE user_id ='" + ss.getUid() + "'";
                ResultSet rs = db.getData(query);
                if (rs.next()){
                    String olddbpass = rs.getString("user_pass");
                    String pass11 = new String(pass1.getPassword());
                    String pass22 = new String(pass2.getPassword());

                    if (pass1.getPassword().length < 8 || pass2.getPassword().length < 8){
                        JOptionPane.showMessageDialog(null, "Password must exceed to 8 characters!");
                        return;
                    } else if (!pass11.equals(pass22)){
                        JOptionPane.showMessageDialog(null, "Passwords do not match!");
                        return;
                    } else if (olddbpass.equals(passhash.hashPassword(pass11))){
                        JOptionPane.showMessageDialog(null,"Fill Out New Password!");
                        pass1.setText("");
                        pass2.setText("");
                        return;
                    } else {
                        String npass = passhash.hashPassword(pass1.getText());
                        sql += ", user_pass = '" + npass + "'";
                    }
                }
            } catch (SQLException | NoSuchAlgorithmException ex){
                System.out.println(""+ex);
                return;
            }
        }
      
        sql += " WHERE user_id = '" + ss.getUid() + "'";
        dbConnector dbc = new dbConnector();
        dbc.updateData(sql);

        if(destination.isEmpty()){
            File existingFile = new File(oldpath);
            if(existingFile.exists()){
                existingFile.delete();
            }
        } else {
            if(!(oldpath.equals(path))){
                imageUpdater(oldpath, path);
            }
        }

        
        order log = new order();
        log.setVisible(true);
        this.dispose();
    

    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        jPanel10.setBackground(Color.red);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        jPanel10.setBackground(new Color(255,102,102));
    }//GEN-LAST:event_jPanel10MouseExited

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

    private void phone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phone1ActionPerformed

    private void changePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePassActionPerformed
        boolean checked = changePass.isSelected();
        pass2.setEnabled(checked);
        pass1.setEnabled(checked);
        if (!checked) {
            pass2.setText("");
            pass1.setText("");
        }
    }//GEN-LAST:event_changePassActionPerformed

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
            java.util.logging.Logger.getLogger(admindetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admindetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admindetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admindetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admindetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox changePass;
    public javax.swing.JTextField email;
    private javax.swing.JLabel email2;
    private javax.swing.JLabel email3;
    public javax.swing.JTextField fn;
    private javax.swing.JLabel fname;
    private javax.swing.JLabel fname1;
    private javax.swing.JLabel fname2;
    private javax.swing.JLabel fname3;
    private javax.swing.JLabel fname4;
    private javax.swing.JLabel fname5;
    private javax.swing.JLabel fname6;
    private javax.swing.JLabel fname7;
    private javax.swing.JLabel fname8;
    private javax.swing.JLabel fname9;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTextField ln;
    private javax.swing.JLabel lname;
    private javax.swing.JLabel lname1;
    private javax.swing.JLabel mana;
    private javax.swing.JLabel menu;
    public javax.swing.JTextField mn;
    private javax.swing.JLabel order3;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    public javax.swing.JTextField phone;
    public javax.swing.JTextField phone1;
    public javax.swing.JLabel prof;
    public javax.swing.JButton remove;
    public javax.swing.JButton select;
    private javax.swing.JLabel stat;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
