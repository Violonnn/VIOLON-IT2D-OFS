/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import static admin.adminRegi.getHeightFromWidth;
import config.dbConnector;
import config.passhash;
import config.session;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaapplication8.login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Admin
 */
public class itemsList extends javax.swing.JFrame {

    /**
     * Creates new form adminDashboard1
     */
    public itemsList() {
        initComponents();
        
        displayData();
        
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
        
//         Icon i4 = stat.getIcon();
//        ImageIcon icon4 = (ImageIcon)i4;
//        Image image4 = icon4.getImage().getScaledInstance(stat.getWidth(), stat.getHeight(), Image.SCALE_SMOOTH);
//        stat.setIcon(new ImageIcon(image4));
        
    }
    
    
    boolean imageRemoved = false;
    
    
File selectedFile;
String path = "";
String destination = "";


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

    boolean checkadd = true;
    
    public void displayData() {

    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT itemID, itemName, itemPrice, itemQuan FROM tbl_item");

        // Set the table model to the result set data
        usersTable.setModel(DbUtils.resultSetToTableModel(rs));

        // Get the number of columns in the table model
        int columnCount = usersTable.getColumnCount();

        // Hide the itemID column (first column at index 0)
        usersTable.getColumnModel().getColumn(0).setMaxWidth(0);
        usersTable.getColumnModel().getColumn(0).setMinWidth(0);
        usersTable.getColumnModel().getColumn(0).setPreferredWidth(0);
        usersTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        usersTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        // Optional: Format the price column (assuming it is the 3rd column, index 2)
        for (int i = 0; i < usersTable.getRowCount(); i++) {
            double priceValue = Double.parseDouble(usersTable.getValueAt(i, 2).toString());
            String formattedPrice = String.format("₱%.2f", priceValue);
            usersTable.setValueAt(formattedPrice, i, 2);
        }

        // Add stock status dynamically (column 4 doesn't exist, so check and add logic for that)
        if (columnCount >= 4) { // Ensure there are enough columns
            for (int i = 0; i < usersTable.getRowCount(); i++) {
                int stockValue = Integer.parseInt(usersTable.getValueAt(i, 3).toString());  // stock column is at index 3
                String status = stockValue == 0 ? "Out of Stock" : "Available";
                
                // Update the status in the 4th column (index 3)
                usersTable.setValueAt(status, i, 3); // This should be within bounds (index 3 if you have 4 columns)
            }
        } else {
            System.out.println("Table does not have enough columns for status.");
        }

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }

}
        
    
    
    
     public int FileExistenceChecker(String destPath) {
    File file = new File(destPath);
    return file.exists() ? 1 : 0;
}
     
     
private void resetFields() {
     checkadd = true;
    ID.setText("");
    name.setText("");
    price.setText("");
    stock.setText("");
    destination = "";
    path = "";
    selectedFile = null;
    image.setIcon(null);
    imageRemoved = false; // RESET FLAG
    select.setEnabled(true);
    remove.setEnabled(false);
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
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        order2 = new javax.swing.JLabel();
        email1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        mana = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        menu = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        select = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        remove = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        add = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        price = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        add2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        add1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        add3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        prof = new javax.swing.JLabel();
        ID1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 5, true));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Order Flow System");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 20, -1, -1));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(74, 73, 73)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 180, 10));

        order2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/orderflow.png"))); // NOI18N
        order2.setAlignmentY(10.0F);
        jPanel3.add(order2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        email1.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        email1.setForeground(new java.awt.Color(204, 204, 204));
        email1.setText("UID: ");
        jPanel3.add(email1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 110, -1));

        jLabel14.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Items");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 590));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manager.png"))); // NOI18N
        mana.setAlignmentY(10.0F);
        jPanel1.add(mana, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 5, 30, 30));

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
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 11, -1, -1));

        jLabel3.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("/");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("/");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, -1, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        menu.setAlignmentY(10.0F);
        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 14, 10, 10));

        jLabel6.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Items List");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, -1, -1));

        jLabel8.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Admin Dashboard");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 740, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, -1, -1));

        jLabel11.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dashboard / Home / Items");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 740, 30));

        jPanel20.setBackground(new java.awt.Color(252, 252, 252));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 100));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 110, 100));

        select.setBackground(new java.awt.Color(51, 51, 51));
        select.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
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
        select.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Select");
        select.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jPanel20.add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 160, 40));

        remove.setBackground(new java.awt.Color(51, 51, 51));
        remove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeMouseExited(evt);
            }
        });
        remove.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Remove");
        remove.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jPanel20.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 160, 40));

        jPanel2.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 320, 160));

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        usersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usersTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(usersTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 380, 390));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Gorlock", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("ITEMS LIST ");
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 750, 50));

        ID.setBackground(new java.awt.Color(102, 102, 102));
        ID.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        ID.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 333, 60, 10));

        jPanel22.setBackground(new java.awt.Color(252, 252, 252));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        add.setBackground(new java.awt.Color(51, 51, 51));
        add.setBorder(new javax.swing.border.MatteBorder(null));
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addMouseExited(evt);
            }
        });
        add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("+   Add Item");
        add.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel22.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 80, 40));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Stock:");
        jPanel22.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel20.setBackground(new java.awt.Color(102, 102, 102));
        jLabel20.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 51, 51));
        jLabel20.setText("Price:");
        jPanel22.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel19.setBackground(new java.awt.Color(102, 102, 102));
        jLabel19.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Name:");
        jPanel22.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        name.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel22.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 210, 30));

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

        jPanel22.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        price.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jPanel22.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 210, 30));

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

        jPanel22.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, 30));

        add2.setBackground(new java.awt.Color(51, 51, 51));
        add2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add2MouseExited(evt);
            }
        });
        add2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("   Update Item");
        add2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel22.add(add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 80, 40));

        add1.setBackground(new java.awt.Color(51, 51, 51));
        add1.setBorder(new javax.swing.border.MatteBorder(null));
        add1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add1MouseExited(evt);
            }
        });
        add1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Clear Field");
        add1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel22.add(add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 80, 40));

        add3.setBackground(new java.awt.Color(51, 51, 51));
        add3.setBorder(new javax.swing.border.MatteBorder(null));
        add3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add3MouseExited(evt);
            }
        });
        add3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Delete Item");
        add3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel22.add(add3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 80, 40));

        stock.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockActionPerformed(evt);
            }
        });
        jPanel22.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 210, 30));

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

        jPanel22.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, 30));

        jPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 320, 180));

        prof.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        prof.setForeground(new java.awt.Color(51, 51, 51));
        prof.setText("Item Picture");
        jPanel2.add(prof, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 90, 20));

        ID1.setBackground(new java.awt.Color(102, 102, 102));
        ID1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        ID1.setForeground(new java.awt.Color(51, 51, 51));
        ID1.setText("Selected Item ID:");
        jPanel2.add(ID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
 
       order adm = new order();
       adm.setVisible(true);
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
         email1.setText("UID: "+ss.getUid());
         
    }//GEN-LAST:event_formWindowActivated

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        String priceText = price.getText();
    
    if (checkadd){
        if(name.getText().isEmpty() || price.getText().isEmpty() || stock.getText().isEmpty()){       
            JOptionPane.showMessageDialog(null, "All fields are required!");            
        } else if (!name.getText().matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(null, "Name must contain only letters and numbers!");
            name.setText("");
        } else if (!priceText.matches("\\d+")){
            JOptionPane.showMessageDialog(null, "Price must be in numbers!");  
            price.setText("");
        } else {
            dbConnector db = new dbConnector();
            try {
                if (!destination.isEmpty()) {
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                db.insertData("INSERT INTO tbl_item(itemName, itemPrice, itemQuan, itemImage) VALUES ('" +
                        name.getText() + "','" + price.getText() + "','" + stock.getText() + "','" + destination + "')");
                JOptionPane.showMessageDialog(null, "Item Successfully Added!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving image: " + e.getMessage());
            }

            displayData();
            resetFields(); // make sure you created the resetFields() method
        }
    } else {
        JOptionPane.showMessageDialog(null, "Cannot add selected Item!");
        JOptionPane.showMessageDialog(null, "Clearing the field to add item...");
        resetFields();
    }
    }//GEN-LAST:event_addMouseClicked

    private void addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseEntered
        add.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_addMouseEntered

    private void addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseExited
        add.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_addMouseExited

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void selectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseClicked
                              
    JFileChooser fileChooser = new JFileChooser();
    int returnValue = fileChooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        selectedFile = fileChooser.getSelectedFile();
        path = selectedFile.getAbsolutePath();
        destination = "src/userimage/" + selectedFile.getName();

        if (FileExistenceChecker(destination) == 1) {
            JOptionPane.showMessageDialog(null, "File Already Exists. Please rename or choose another.");
            destination = "";
            path = "";
        } else {
            image.setIcon(ResizeImage(path, null, image));
            image.setText(selectedFile.getName()); // assuming `picture` is your image JTextField
            select.setEnabled(false);
            remove.setEnabled(true);
        }
    }
    }//GEN-LAST:event_selectMouseClicked

    private void selectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseEntered
        select.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_selectMouseEntered

    private void selectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMouseExited
       select.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_selectMouseExited

    private void removeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseClicked
                         
   
    image.setIcon(null);
    image.setText("");
    destination = "";
    path = "";
    imageRemoved = true; // Don't update yet, just flag it
    select.setEnabled(true);
    remove.setEnabled(false);


    }//GEN-LAST:event_removeMouseClicked

    private void removeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseEntered
        remove.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_removeMouseEntered

    private void removeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeMouseExited
        remove.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_removeMouseExited

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
      int rowIndex = usersTable.getSelectedRow();
    if (rowIndex < 0) {
        JOptionPane.showMessageDialog(null, "Please Select an Item!");
    } else {
        try {
            dbConnector db = new dbConnector();
            TableModel tbl = usersTable.getModel();
            ResultSet rs = db.getData("SELECT * FROM tbl_item WHERE itemID ='" + tbl.getValueAt(rowIndex, 0) + "'");

            if (rs.next()) {
                ID.setText("" + rs.getString("itemID"));
                name.setText("" + rs.getString("itemName"));
                price.setText("" + rs.getString("itemPrice"));
                stock.setText("" + rs.getString("itemQuan"));
                
                // 📷 Load image
                String imagePath = rs.getString("itemImage");
                if (imagePath != null && !imagePath.isEmpty()) {
                    File imgFile = new File(imagePath);
                    if (imgFile.exists()) {
                        image.setIcon(ResizeImage(imagePath, null, image)); // assuming image is your JLabel
                        image.setText(imgFile.getName()); // optional: show file name if using a JTextField
                        destination = imagePath; // set for update tracking
                        path = imagePath;
                        select.setEnabled(false);
                        remove.setEnabled(true);
                    } else {
                        image.setIcon(null);
                        image.setText("");
                        select.setEnabled(true);
                        remove.setEnabled(false);
                    }
                } else {
                    image.setIcon(null);
                    image.setText("");
                    select.setEnabled(true);
                    remove.setEnabled(false);
                }

                add.setEnabled(false);
                checkadd = false;
            } else {
                JOptionPane.showMessageDialog(null, "Connection Error!");
            }
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_usersTableMouseClicked

    private void add1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add1MouseClicked
                             
    checkadd = true; // set to add mode

    // Clear all fields
    ID.setText("");
    name.setText("");
    price.setText("");
    stock.setText("");

    // Clear image
    image.setIcon(null);
    image.setText("");
    path = "";
    destination = "";
    selectedFile = null;

    // Enable image selection again
    select.setEnabled(true);
    remove.setEnabled(false);
 
    }//GEN-LAST:event_add1MouseClicked

    private void add1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add1MouseEntered
        add1.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_add1MouseEntered

    private void add1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add1MouseExited
       add1.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_add1MouseExited

    private void add2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseClicked
                                 
      if (ID.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Select item to update!");
        return;
    }

    String originalName = "";
    String originalPrice = "";
    String originalStock = "";
    String originalImage = "";

    try {
        dbConnector db = new dbConnector();
        ResultSet rs = db.getData("SELECT itemName, itemPrice, itemQuan, itemImage FROM tbl_item WHERE itemID = '" + ID.getText() + "'");
        if (rs.next()) {
            originalName = rs.getString("itemName");
            originalPrice = rs.getString("itemPrice");
            originalStock = rs.getString("itemQuan");
            originalImage = rs.getString("itemImage");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Could not retrieve original item data.");
            return;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Database error while fetching original data.");
        return;
    }

    // Check if any data has changed
    boolean imageChanged = !destination.equals(originalImage);
    boolean noChanges = name.getText().equals(originalName) &&
                        price.getText().equals(originalPrice) &&
                        stock.getText().equals(originalStock) &&
                        !imageChanged && !imageRemoved;

    if (noChanges) {
        JOptionPane.showMessageDialog(null, "No changes detected to update!");
        return;
    }

    // Validation
    if (name.getText().isEmpty() || price.getText().isEmpty() || stock.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "All fields are required!");
        return;
    }

    if (!price.getText().matches("\\d+(\\.\\d+)?")) {
        JOptionPane.showMessageDialog(null, "Price must be in numbers!");
        price.setText("");
        return;
    }

    try {
        dbConnector db = new dbConnector();

        // If the image is removed, delete the image from the file system
        if (imageRemoved) {
            // Delete the image file if it exists
            if (originalImage != null && !originalImage.isEmpty()) {
                File imageFile = new File(originalImage);
                if (imageFile.exists()) {
                    boolean isDeleted = imageFile.delete();  // Attempt to delete the file
                    if (isDeleted) {
                        System.out.println("Image successfully deleted.");
                    } else {
                        System.out.println("Failed to delete the image.");
                    }
                }
            }
            // Set itemImage to NULL in the database
            db.updateData("UPDATE tbl_item SET itemName = '" + name.getText() +
                    "', itemPrice = '" + price.getText() +
                    "', itemQuan = '" + stock.getText() +
                    "', itemImage = NULL WHERE itemID = '" + ID.getText() + "'");

        } else if (imageChanged && !destination.isEmpty()) {
            // If the image is changed, copy the new image to the destination folder
            Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            db.updateData("UPDATE tbl_item SET itemName = '" + name.getText() +
                    "', itemPrice = '" + price.getText() +
                    "', itemQuan = '" + stock.getText() +
                    "', itemImage = '" + destination +
                    "' WHERE itemID = '" + ID.getText() + "'");
        } else {
            // Just text fields changed, no image update
            db.updateData("UPDATE tbl_item SET itemName = '" + name.getText() +
                    "', itemPrice = '" + price.getText() +
                    "', itemQuan = '" + stock.getText() +
                    "' WHERE itemID = '" + ID.getText() + "'");
        }

        JOptionPane.showMessageDialog(null, "Item " + ID.getText() + " updated successfully!");
        displayData();
        resetFields();

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error updating item: " + e.getMessage());
    }

    // Reset imageRemoved flag just in case
    imageRemoved = false;
        
    }//GEN-LAST:event_add2MouseClicked

    private void add2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseEntered
          add2.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_add2MouseEntered

    private void add2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add2MouseExited
       add2.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_add2MouseExited

    private void add3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add3MouseClicked
          
    if (ID.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select an item to delete!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete this item?",
            "Delete Confirmation",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            dbConnector db = new dbConnector();
            db.updateData("DELETE FROM tbl_item WHERE itemID = '" + ID.getText() + "'");
            JOptionPane.showMessageDialog(null, "Item deleted successfully.");
            displayData();
            resetFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting item: " + e.getMessage());
        }
    }

    }//GEN-LAST:event_add3MouseClicked

    private void add3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add3MouseEntered
       add3.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_add3MouseEntered

    private void add3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add3MouseExited
      add3.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_add3MouseExited

    private void stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stockActionPerformed

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
            java.util.logging.Logger.getLogger(itemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(itemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(itemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(itemsList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new itemsList().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JLabel ID1;
    private javax.swing.JPanel add;
    private javax.swing.JPanel add1;
    private javax.swing.JPanel add2;
    private javax.swing.JPanel add3;
    private javax.swing.JLabel email1;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mana;
    private javax.swing.JLabel menu;
    public javax.swing.JTextField name;
    private javax.swing.JLabel order2;
    public javax.swing.JTextField price;
    public javax.swing.JLabel prof;
    private javax.swing.JPanel remove;
    private javax.swing.JPanel select;
    public javax.swing.JTextField stock;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
