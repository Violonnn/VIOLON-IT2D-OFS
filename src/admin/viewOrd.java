/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import config.dbConnector;
import config.session;
import java.awt.Color;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaapplication8.login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import user.details;

/**
 *
 * @author Admin
 */
public class viewOrd extends javax.swing.JFrame {
  private int orderId;
    /**
     * Creates new form adminDashboard1
     */
    public viewOrd(String orderId) {
        initComponents();
        displayData(orderId);
   

      
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
//         Icon i4 = stat.getIcon();
//        ImageIcon icon4 = (ImageIcon)i4;
//        Image image4 = icon4.getImage().getScaledInstance(stat.getWidth(), stat.getHeight(), Image.SCALE_SMOOTH);
//        stat.setIcon(new ImageIcon(image4));
//        
//         Icon i5 = info.getIcon();
//        ImageIcon icon5 = (ImageIcon)i5;
//        Image image5 = icon5.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
//        info.setIcon(new ImageIcon(image5));
        
    }

   

    
    private void displayData(String orderId) {
        try {
        dbConnector dbc = new dbConnector();

        // Fetch item details
        String itemSql = "SELECT i.itemName, oi.orderQuan, oi.itemPrice, " +
                         "(oi.itemPrice * oi.orderQuan) AS subtotal " +
                         "FROM tbl_order_items oi " +
                         "JOIN tbl_item i ON oi.itemID = i.itemID " +
                         "WHERE oi.orderID = '" + orderId + "'";
        ResultSet rsItems = dbc.getData(itemSql);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Item Name", "Quantity", "Price", "Subtotal"}, 0);
        double total = 0;

        while (rsItems.next()) {
            String name = rsItems.getString("itemName");
            int quantity = rsItems.getInt("orderQuan");
            double price = rsItems.getDouble("itemPrice");
            double subtotal = rsItems.getDouble("subtotal");
            total += subtotal;

            model.addRow(new Object[]{name, quantity, "₱" + price, "₱" + subtotal});
        }
        itemsTable.setModel(model);
        rsItems.close();

        // Fetch order info
        String orderSql = "SELECT customer, address, phone, orderDate FROM tbl_order WHERE orderID = '" + orderId + "'";
        ResultSet rsOrder = dbc.getData(orderSql);

        if (rsOrder.next()) {
            customerLabel.setText(rsOrder.getString("customer"));
            addressLabel.setText(rsOrder.getString("address"));
            phoneLabel.setText(rsOrder.getString("phone"));
            dateLabel.setText(rsOrder.getString("orderDate"));
        }
        rsOrder.close();

        totalLabel.setText("₱" + String.format("%.2f", total));

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading order details: " + e.getMessage());
        e.printStackTrace();
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

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
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
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        customerLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        dateLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        dateLabel2 = new javax.swing.JLabel();
        dateLabel3 = new javax.swing.JLabel();
        dateLabel4 = new javax.swing.JLabel();
        dateLabel5 = new javax.swing.JLabel();
        approve1 = new javax.swing.JButton();

        jScrollPane2.setViewportView(jEditorPane1);

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
        jPanel3.add(email2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 110, -1));

        jLabel14.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Order Flow System");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 20, -1, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/manager.png"))); // NOI18N
        mana.setAlignmentY(10.0F);
        jPanel1.add(mana, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 5, 30, 30));

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
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(566, 12, 30, -1));

        jLabel3.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("/");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 50, -1));

        jLabel5.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("/");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 11, -1, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu.png"))); // NOI18N
        menu.setAlignmentY(10.0F);
        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 14, 10, 10));

        jLabel12.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("Admin Dashboard");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 11, -1, -1));

        jLabel8.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Home");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 11, 100, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 630, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 11, -1, -1));

        jLabel11.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dashboard / Home / Order / Approve Pending Orders");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 630, 30));

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

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(itemsTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 350, 280));

        jLabel7.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("View Order Details");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 250, 40));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 240, 20));

        customerLabel.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        customerLabel.setText("a");
        jPanel2.add(customerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 150, 40));

        dateLabel.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        dateLabel.setText("a");
        jPanel2.add(dateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 150, 50));

        addressLabel.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        addressLabel.setText("a");
        jPanel2.add(addressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 210, 150, 40));

        totalLabel.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        totalLabel.setText("a");
        jPanel2.add(totalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 170, 50));

        phoneLabel.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        phoneLabel.setText("a");
        jPanel2.add(phoneLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, 150, 40));

        dateLabel1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel1.setText("Contact #:");
        jPanel2.add(dateLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 260, 60, 40));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 150, 205, -1));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 205, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 205, 3));

        dateLabel2.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel2.setText("Total:");
        jPanel2.add(dateLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 110, 50));

        dateLabel3.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel3.setText("Customer Name: ");
        jPanel2.add(dateLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 110, 40));

        dateLabel4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel4.setText("Address:");
        jPanel2.add(dateLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, 60, 40));

        dateLabel5.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel5.setText("Data of Ordered:");
        jPanel2.add(dateLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 110, 50));

        approve1.setBackground(new java.awt.Color(255, 102, 102));
        approve1.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        approve1.setForeground(new java.awt.Color(255, 255, 255));
        approve1.setText("Return");
        approve1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approve1ActionPerformed(evt);
            }
        });
        jPanel2.add(approve1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 190, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 832, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
          
       approvePen log = new approvePen();
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
//            JOptionPane.showMessageDialog(null,"No Account, Login First!");
//            login log = new login();
//            log.setVisible(true);
//            this.dispose();
        } else {
        
//        Lname.setText(""+ss.getLname());
//        email1.setText(""+ss.getEmail());
        email2.setText("UID: "+ss.getUid());
        }
    }//GEN-LAST:event_formWindowActivated

    private void approve1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approve1ActionPerformed
         approvePen pen = new approvePen();
         pen.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_approve1ActionPerformed

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
            java.util.logging.Logger.getLogger(viewOrd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewOrd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewOrd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewOrd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new viewOrd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton approve1;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dateLabel1;
    private javax.swing.JLabel dateLabel2;
    private javax.swing.JLabel dateLabel3;
    private javax.swing.JLabel dateLabel4;
    private javax.swing.JLabel dateLabel5;
    private javax.swing.JLabel email2;
    private javax.swing.JTable itemsTable;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel mana;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel order3;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
