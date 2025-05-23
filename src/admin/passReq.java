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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javaapplication8.login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;
import user.details;


/**
 *
 * @author Admin
 */
public class passReq extends javax.swing.JFrame {
  // In your class:
private String originalReason = "";
private String originalStatus = "";
private String originalDate = "";
private int selectedRequestId = -1; // from table's pass_id
private Connection conn;

    /**
     * Creates new form adminDashboard1
     */
    public passReq() {
        initComponents();
        displayData();
   
        
    itemsTable.getSelectionModel().addListSelectionListener(e -> {
    if (!e.getValueIsAdjusting()) {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedRequestId = (int) itemsTable.getValueAt(selectedRow, 0); // pass_id column

            originalReason = itemsTable.getValueAt(selectedRow, 3).toString();
            originalStatus = itemsTable.getValueAt(selectedRow, 4).toString().trim(); // status
            originalDate = itemsTable.getValueAt(selectedRow, 5).toString();

            user.setText(itemsTable.getValueAt(selectedRow, 1).toString());
            reas.setText(originalReason);
            date.setText(originalDate);
            stats.setSelectedItem(originalStatus);

            // Enable apply button ONLY if status is "Pending"
            if (originalStatus.equalsIgnoreCase("Pending")) {
                applyBtn.setEnabled(true);
            } else {
                applyBtn.setEnabled(false);
            }
        }
    }
});


      
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


    
  private void displayData() {
    dbConnector dbc = new dbConnector();

    String sql = "SELECT fr.pass_id, u.user_username, u.user_email, fr.reason, fr.status, fr.data, u.user_id " +
                 "FROM tbl_pass fr " +
                 "JOIN tbl_user u ON fr.user_id = u.user_id " +
                 "ORDER BY fr.status ASC, fr.data DESC";

    try {
        ResultSet rs = dbc.getData(sql);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Request ID", "Username", "Email", "Reason", "Status", "Requested On", "User ID"}, 0
        );

        while (rs.next()) {
            int requestId = rs.getInt("pass_id");
            String username = rs.getString("user_username");
            String email = rs.getString("user_email");
            String reason = rs.getString("reason");
            String status = rs.getString("status");
            String date = rs.getString("data");
            int userId = rs.getInt("user_id");

            model.addRow(new Object[]{requestId, username, email, reason, status, date, userId});
        }

        itemsTable.setModel(model);

        // Hide sensitive or unnecessary columns
        hideColumn(itemsTable, 2); // Email
        hideColumn(itemsTable, 3); // Reason
        hideColumn(itemsTable, 6); // User ID 👈 (this one is important to hide)

        rs.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error loading requests: " + e.getMessage());
    }
}


// Helper method to hide a column visually
private void hideColumn(JTable table, int colIndex) {
    TableColumn col = table.getColumnModel().getColumn(colIndex);
    col.setMinWidth(0);
    col.setMaxWidth(0);
    col.setWidth(0);
    col.setPreferredWidth(0);
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
        user = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        dateLabel1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        dateLabel3 = new javax.swing.JLabel();
        dateLabel4 = new javax.swing.JLabel();
        dateLabel5 = new javax.swing.JLabel();
        applyBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        reas = new javax.swing.JTextArea();
        stats = new javax.swing.JComboBox<>();

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
        itemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(itemsTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 350, 280));

        jLabel7.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Password Requests");
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

        user.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        user.setText("a");
        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, 150, 40));

        date.setFont(new java.awt.Font("Gorlock", 0, 12)); // NOI18N
        date.setText("a");
        jPanel2.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 150, 50));

        dateLabel1.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel1.setText("Reason:");
        jPanel2.add(dateLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 60, 40));

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

        dateLabel3.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel3.setText("Username:");
        jPanel2.add(dateLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 90, 40));

        dateLabel4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel4.setText("Status:");
        jPanel2.add(dateLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 60, 30));

        dateLabel5.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        dateLabel5.setText("Date of Request");
        jPanel2.add(dateLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 110, 50));

        applyBtn.setBackground(new java.awt.Color(255, 102, 102));
        applyBtn.setFont(new java.awt.Font("Gorlock", 1, 11)); // NOI18N
        applyBtn.setForeground(new java.awt.Color(255, 255, 255));
        applyBtn.setText("Apply");
        applyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyBtnActionPerformed(evt);
            }
        });
        jPanel2.add(applyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, 190, 30));

        reas.setColumns(20);
        reas.setRows(5);
        reas.setEnabled(false);
        jScrollPane3.setViewportView(reas);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, 240, 110));

        stats.setFont(new java.awt.Font("Gorlock", 0, 11)); // NOI18N
        stats.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Pending", "Approved ", "Rejected" }));
        stats.setBorder(null);
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statsActionPerformed(evt);
            }
        });
        jPanel2.add(stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 120, 30));

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

    private void applyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyBtnActionPerformed
   if (selectedRequestId == -1) {
        JOptionPane.showMessageDialog(null, "Please select a request first.");
        return;
    }

    String newReason = reas.getText().trim();
    String newStatus = stats.getSelectedItem().toString().trim();

    // Block update if original status is not Pending
    if (!originalStatus.equalsIgnoreCase("Pending")) {
        JOptionPane.showMessageDialog(null, "Only pending requests can be updated.");
        return;
    }

    // Check if there are changes
    boolean reasonChanged = !newReason.equals(originalReason);
    boolean statusChanged = !newStatus.equalsIgnoreCase(originalStatus);

    if (!reasonChanged && !statusChanged) {
        JOptionPane.showMessageDialog(null, "No changes detected. Request remains pending.");
        return;
    }

    try {
        Connection conn = new dbConnector().getConnection(); // ensure dbConnector has a connect() method returning Connection

        // Update the request
        String updateSQL = "UPDATE tbl_pass SET reason = ?, status = ? WHERE pass_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(updateSQL);
        pstmt.setString(1, newReason);
        pstmt.setString(2, newStatus);
        pstmt.setInt(3, selectedRequestId);
        int rows = pstmt.executeUpdate();

        // If approved, set force_reset to 1 for the user
        if (newStatus.equalsIgnoreCase("Approved")) {
            // Get the user_id from the table model (assuming it's hidden but still stored in the model)
            int rowIndex = itemsTable.getSelectedRow();
            int modelRowIndex = itemsTable.convertRowIndexToModel(rowIndex);
            int userId = (int) itemsTable.getModel().getValueAt(modelRowIndex, 6); // column 6 = user_id (make sure it's part of the hidden columns)

            String forceResetSQL = "UPDATE tbl_user SET force_reset = 1 WHERE user_id = ?";
            PreparedStatement resetStmt = conn.prepareStatement(forceResetSQL);
            resetStmt.setInt(1, userId);
            resetStmt.executeUpdate();
            resetStmt.close();
        }

        pstmt.close();
        conn.close();

        // Notify success
        JOptionPane.showMessageDialog(null, "Request updated successfully.");
        applyBtn.setEnabled(!newStatus.equalsIgnoreCase("Pending")); // disable if not pending

        // Refresh and reset
        originalReason = newReason;
        originalStatus = newStatus;
        displayData();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
    }
    }//GEN-LAST:event_applyBtnActionPerformed

    private void itemsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_itemsTableMouseClicked

    private void statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statsActionPerformed

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
            java.util.logging.Logger.getLogger(passReq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(passReq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(passReq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(passReq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
    private javax.swing.JButton applyBtn;
    private javax.swing.JLabel date;
    private javax.swing.JLabel dateLabel1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel mana;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel order3;
    private javax.swing.JTextArea reas;
    public javax.swing.JComboBox<String> stats;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
