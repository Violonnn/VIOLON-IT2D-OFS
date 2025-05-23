/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import admin.adminRegi;
import static admin.adminRegi.getHeightFromWidth;
import config.dbConnector;
import config.session;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javaapplication8.login;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author Admin
 */
public class addOrder extends javax.swing.JFrame {

    /**
     * Creates new form userDashboard
     */
    
    private List<CartItem> cartItems = new ArrayList<>();
    private DefaultTableModel cartTableModel;
    
    public addOrder() {
        initComponents();
            
          setupQuantityPanelListeners();
           setupUsersTableClickListener();
        
            Icon i1 = info.getIcon();
        ImageIcon icon1 = (ImageIcon)i1;
        Image image1 = icon1.getImage().getScaledInstance(info.getWidth(), info.getHeight(), Image.SCALE_SMOOTH);
        info.setIcon(new ImageIcon(image1)); 
            
            Icon i2 = oha.getIcon();
        ImageIcon icon2 = (ImageIcon)i2;
        Image image2 = icon2.getImage().getScaledInstance(oha.getWidth(), panelMinus.getHeight(), Image.SCALE_SMOOTH);
        oha.setIcon(new ImageIcon(image2)); 
 
            Icon i3 = back.getIcon();
        ImageIcon icon3 = (ImageIcon)i3;
        Image image3 = icon3.getImage().getScaledInstance(back.getWidth(), back.getHeight(), Image.SCALE_SMOOTH);
        back.setIcon(new ImageIcon(image3)); 
        
         Icon i4 = menuu.getIcon();
        ImageIcon icon4 = (ImageIcon)i4;
        Image image4 = icon4.getImage().getScaledInstance(menuu.getWidth(), menuu.getHeight(), Image.SCALE_SMOOTH);
        menuu.setIcon(new ImageIcon(image4)); 
        
        
         cartTableModel = new DefaultTableModel(new Object[]{"Item ID", "Item Name", "Price", "Quantity", "Subtotal"}, 0);
       cartTable.setModel(cartTableModel);
       
       
       
        displayData();
    }
    
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

private int parseInt(String text, int defaultValue) {
    try {
        return Integer.parseInt(text.trim());
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

private void setupUsersTableClickListener() {
    usersTable.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int viewRow = usersTable.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(null, "Please select an item!");
                return;
            }

            try {
                int modelRow = usersTable.convertRowIndexToModel(viewRow);
                TableModel tbl = usersTable.getModel();

                id.setText(tbl.getValueAt(modelRow, 0).toString());
                name.setText(tbl.getValueAt(modelRow, 1).toString());
                price.setText(tbl.getValueAt(modelRow, 2).toString());
                stock.setText(tbl.getValueAt(modelRow, 3).toString());

                int itemStock = parseInt(tbl.getValueAt(modelRow, 3).toString(), 0);
                quan.setText(itemStock > 0 ? "1" : "0");
               
                updateSubtotalLive();
            } catch (Exception ex) {
                System.out.println("[ERROR] Failed to populate fields: " + ex.getMessage());
            }
        }
    });
}

Map<Integer, Integer> reservedStockMap = new HashMap<>();

public void displayData() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData(
            "SELECT itemID, itemName, itemPrice, itemQuan, itemImage, " +
            "CASE WHEN itemQuan >= 1 THEN 'Available' ELSE 'Out of Stock' END AS Availability " +
            "FROM tbl_item"
        );

        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"ID", "Name", "Price", "Stock", "ImagePath", "Availability"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        while (rs.next()) {
            int id = rs.getInt("itemID");
            String name = rs.getString("itemName");
            double price = rs.getDouble("itemPrice");
            int dbStock = rs.getInt("itemQuan");
            String imagePath = rs.getString("itemImage");

            // 🔽 Subtract items already in cart from DB stock
            int cartQuantity = 0;
            for (CartItem item : cartItems) {
                if (item.itemID == id) {
                    cartQuantity = item.quantity;
                    break;
                }
            }

            int availableStock = dbStock - cartQuantity;
            if (availableStock < 0) availableStock = 0;

            String availability = availableStock >= 1 ? "Available" : "Out of Stock";

            model.addRow(new Object[]{
                id,
                name,
                "₱" + String.format("%.2f", price),
                availableStock,
                imagePath != null ? imagePath : "",
                availability
            });
        }
        rs.close();

        usersTable.setModel(model);

        // 🔒 Hide columns: ID, Stock, ImagePath
        for (int col : new int[]{0, 3, 4}) {
            TableColumn column = usersTable.getColumnModel().getColumn(col);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column.setPreferredWidth(0);
        }

        // 📌 Selection behavior
        usersTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && usersTable.getSelectedRow() != -1) {
                int row = usersTable.getSelectedRow();

                try {
                    stock.setText(usersTable.getModel().getValueAt(row, 3).toString());
                    int itemStock = parseInt(stock.getText(), 0);
                    quan.setText(itemStock > 0 ? "1" : "0");

                    String priceText = usersTable.getModel().getValueAt(row, 2).toString().replace("₱", "");
                    double itemPrice = Double.parseDouble(priceText);
                    price.setText("₱" + String.format("%.2f", itemPrice));
                    subto.setText("₱" + String.format("%.2f", itemStock == 0 ? 0 : itemPrice));

                    // 📷 Load item image
                    String imagePath = usersTable.getModel().getValueAt(row, 4).toString();
                    if (imagePath != null && !imagePath.isEmpty()) {
                        File imgFile = new File(imagePath);
                        if (imgFile.exists()) {
                            ImageIcon rawIcon = new ImageIcon(imagePath);
                            Image scaledImg = rawIcon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
                            image.setIcon(new ImageIcon(scaledImg));
                            image.setText("");
                        } else {
                            image.setIcon(null);
                            image.setText("Image not found");
                        }
                    } else {
                        image.setIcon(null);
                        image.setText("");
                    }

                } catch (Exception ex) {
                    System.out.println("[ERROR] " + ex.getMessage());
                }
            }
        });

    } catch (SQLException ex) {
        System.out.println("[SQL ERROR] " + ex.getMessage());
    }
}


//public void displayData() {
//    try {
//        dbConnector dbc = new dbConnector();
//        ResultSet rs = dbc.getData(
//            "SELECT itemID, itemName, itemPrice, itemQuan, " +
//            "CASE WHEN itemQuan >= 1 THEN 'Available' ELSE 'Out of Stock' END AS Availability " +
//            "FROM tbl_item"
//        );
//        usersTable.setModel(DbUtils.resultSetToTableModel(rs));
//        rs.close();
//
//        // Format price with ₱ sign
//        for (int i = 0; i < usersTable.getRowCount(); i++) {
//            Object value = usersTable.getValueAt(i, 2);
//            if (value != null) {
//                try {
//                    double priceVal = Double.parseDouble(value.toString());
//                    usersTable.setValueAt("₱" + String.format("%.2f", priceVal), i, 2);
//                } catch (NumberFormatException ignored) {}
//            }
//        }
//
//        // Hide itemID and itemQuan columns
//        for (int col : new int[]{0, 3}) {
//            TableColumn column = usersTable.getColumnModel().getColumn(col);
//            column.setMinWidth(0);
//            column.setMaxWidth(0);
//            column.setPreferredWidth(0);
//        }
//
//        usersTable.getSelectionModel().addListSelectionListener(event -> {
//            if (!event.getValueIsAdjusting() && usersTable.getSelectedRow() != -1) {
//                int row = usersTable.getSelectedRow();
//                stock.setText(usersTable.getValueAt(row, 3).toString());
//                int itemStock = parseInt(stock.getText(), 0);
//                quan.setText(itemStock > 0 ? "1" : "0");
//
//                try {
//                    double itemPrice = Double.parseDouble(usersTable.getValueAt(row, 2).toString().replace("₱", ""));
//                    price.setText("₱" + String.format("%.2f", itemPrice));
//                    subto.setText("₱" + String.format("%.2f", itemStock == 0 ? 0 : itemPrice));
//                } catch (NumberFormatException e) {
//                    price.setText("₱0.00");
//                    subto.setText("₱0.00");
//                }
//            }
//        });
//
//    } catch (SQLException ex) {
//        System.out.println("Errors: " + ex.getMessage());
//    }
//}

private void refreshCartTable() {
    cartTableModel.setRowCount(0);
    double total = 0;
    for (CartItem item : cartItems) {
        cartTableModel.addRow(item.toRow());
        total += item.subtotal;
    }
    totalLabel.setText("₱" + String.format("%.2f", total));
}

private void setupQuantityPanelListeners() {
    MouseAdapter quantityMouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Object source = e.getSource();

            // ✅ Place Order (must come FIRST to avoid dependency on table selection)
             if (source == placeOrderPanel) {
                 if (cartItems.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Cart is empty! Please add an item to place an order.");
        return;
       }

       if (cus.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter customer's name!.");
        return;
        }
       
       if (add.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter customer's address!");
        return;
        }
       
       if (con.getText().length() != 11 || !con.getText().matches("\\d+") || 
         (!con.getText().substring(0, 2).equals("09")) || con.getText().isEmpty()) {  
          JOptionPane.showMessageDialog(null, "Phone number must contain 11 digits with the first two digits being 09.");       
          return;
      } 
       
           int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to place the order?", "Confirm Order", JOptionPane.YES_NO_OPTION);
           if (confirm != JOptionPane.YES_OPTION) return;
       
           try {
    dbConnector dbc = new dbConnector();
    
    String orderDate = java.time.LocalDate.now().toString(); // today's date
    String orderStats = "Pending";
    int userID = Integer.parseInt(uid.getText()); // staff user ID
  
    String customerName = cus.getText().trim();
    String address = add.getText().trim();
    String phone = con.getText().trim();
    
     // Insert into tbl_order
    String insertOrderQuery = "INSERT INTO tbl_order (user_id, customer, address, phone, orderStats, orderDate) VALUES ('" +
        userID + "','" + customerName + "','" + address + "','" + phone + "','" + orderStats + "','" + orderDate + "')";

    boolean orderInserted = dbc.insertData(insertOrderQuery);

    if (!orderInserted) {
        JOptionPane.showMessageDialog(null, "Order insertion failed.");
        return;
    }

    // Get last inserted orderID (assuming your dbConnector has this method or similar)
    ResultSet rsOrderID = dbc.getData("SELECT MAX(orderID) AS last_id FROM tbl_order");
    int orderID = -1;
    if (rsOrderID.next()) {
        orderID = rsOrderID.getInt("last_id");
    }
    rsOrderID.close();

    if (orderID == -1) {
        JOptionPane.showMessageDialog(null, "Failed to retrieve new order ID.");
        return;
    }

    // Insert into tbl_order_items and update stock
    for (CartItem item : cartItems) {
        String insertItemQuery = "INSERT INTO tbl_order_items (orderID, itemID, orderQuan, itemPrice) VALUES ('" +
            orderID + "','" + item.itemID + "','" + item.quantity + "','" + item.itemPrice + "')";
        dbc.insertData(insertItemQuery);

        String updateStockQuery = "UPDATE tbl_item SET itemQuan = itemQuan - " + item.quantity + " WHERE itemID = " + item.itemID;
        dbc.insertData(updateStockQuery);
    }

    JOptionPane.showMessageDialog(null, "Order placed successfully!");
    cartItems.clear();
    refreshCartTable();
    clearFields();
    displayData();

} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "Error placing order: " + ex.getMessage());
    ex.printStackTrace();
}
            }

            // ✅ Remove Item
            if (source == removeItemPanel) {
                int selectedCartRow = cartTable.getSelectedRow();
                if (selectedCartRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an item in the cart to remove.");
                    return;
                }

                int itemIDToRemove = Integer.parseInt(cartTable.getValueAt(selectedCartRow, 0).toString());
                int quantityToRestore = Integer.parseInt(cartTable.getValueAt(selectedCartRow, 3).toString());

                CartItem itemToRemove = cartItems.stream()
                        .filter(item -> item.itemID == itemIDToRemove)
                        .findFirst().orElse(null);

                if (itemToRemove != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this item from the cart?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
                    if (confirm != JOptionPane.YES_OPTION) return;
                  
                    cartItems.remove(itemToRemove);
 
                    for (int i = 0; i < usersTable.getRowCount(); i++) {
                        int itemID = Integer.parseInt(usersTable.getValueAt(i, 0).toString());
                        if (itemID == itemIDToRemove) {
                            int updatedStock = parseInt(usersTable.getValueAt(i, 3).toString(), 0) + quantityToRestore;
                            usersTable.setValueAt(updatedStock, i, 3);
                            usersTable.setValueAt(updatedStock > 0 ? "Available" : "Out of Stock", i, 4);
                            usersTable.setRowSelectionInterval(i, i);
                            break;
                        }
                    }

                    refreshCartTable();
                    clearFields();
                    displayData();
                    JOptionPane.showMessageDialog(null, "Item removed from cart.");
                }
                return;
            }

            // ✅ Clear Cart
            if (source == clearItemPanel) {
                if (cartItems.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Cart is already empty!");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear the cart?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    restoreStockFromCart();
                    cartItems.clear();
                    refreshCartTable();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Cart has been cleared!");
                    displayData();
                }
                return;
            }

            // ✅ Check for selected row ONLY if needed for quantity or add to cart
            int row = usersTable.getSelectedRow();
            if (source == panelPlus || source == panelMinus || source == addToCartPanel) {
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select an item from the table.");
                    return;
                }

                int itemStock = parseInt(usersTable.getValueAt(row, 3).toString(), 0);
                int stockAvailable = parseInt(stock.getText(), 0);
                int currentQuantity = parseInt(quan.getText(), 1);

                // ✅ Increase Quantity
                if (source == panelPlus) {
                    if (currentQuantity >= itemStock || stockAvailable == 0) {
                        JOptionPane.showMessageDialog(null, "Cannot exceed available stock! (" + itemStock + " items)");
                    } else {
                        quan.setText(String.valueOf(++currentQuantity));
                        updateSubtotalLive();
                    }
                }

                // ✅ Decrease Quantity
                else if (source == panelMinus) {
                    if (currentQuantity <= 1 || stockAvailable == 0) {
                        JOptionPane.showMessageDialog(null, "Not enough stock!");
                    } else {
                        quan.setText(String.valueOf(--currentQuantity));
                        updateSubtotalLive();
                    }
                }

                // ✅ Add to Cart
                else if (source == addToCartPanel) {
                    if (stockAvailable <= 0) {
                        JOptionPane.showMessageDialog(null, "This item is out of stock!");
                        return;
                    }

                    try {
                        int itemID = Integer.parseInt(usersTable.getValueAt(row, 0).toString());
                        String itemName = usersTable.getValueAt(row, 1).toString();
                        double itemPrice = Double.parseDouble(price.getText().replace("₱", ""));
                        int quantity = parseInt(quan.getText(), 1);
                        double subtotal = itemPrice * quantity;

                        if (quantity > stockAvailable) {
                            JOptionPane.showMessageDialog(null, "Not enough stock to add that quantity.");
                            return;
                        }

                        stockAvailable -= quantity;
                        stock.setText(String.valueOf(stockAvailable));
                        usersTable.setValueAt(stockAvailable, row, 3);
                        usersTable.setValueAt(stockAvailable > 0 ? "Available" : "Out of Stock", row, 4);
                        quan.setText(stockAvailable > 0 ? "1" : "0");

                        boolean exists = false;
                        for (CartItem item : cartItems) {
                            if (item.itemID == itemID) {
                                item.quantity += quantity;
                                item.subtotal = item.itemPrice * item.quantity;
                                exists = true;
                                break;
                            }
                        }

                        if (!exists) {
                            cartItems.add(new CartItem(itemID, itemName, itemPrice, quantity));
                        }

                        refreshCartTable();
                        clearFields();
                        usersTable.clearSelection();
                        JOptionPane.showMessageDialog(null, "Item added to cart!");

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid item data.");
                    }
                    
                    displayData();
                }
            }
        }
    };

    panelPlus.addMouseListener(quantityMouseListener);
    panelMinus.addMouseListener(quantityMouseListener);
    clearItemPanel.addMouseListener(quantityMouseListener);
    addToCartPanel.addMouseListener(quantityMouseListener);
    removeItemPanel.addMouseListener(quantityMouseListener);
    placeOrderPanel.addMouseListener(quantityMouseListener);
}


private void restoreStockFromCart() {
    for (CartItem item : cartItems) {
        for (int i = 0; i < usersTable.getRowCount(); i++) {
            int id = Integer.parseInt(usersTable.getValueAt(i, 0).toString());
            if (id == item.itemID) {
                int newStock = parseInt(usersTable.getValueAt(i, 3).toString(), 0) + item.quantity;
                usersTable.setValueAt(newStock, i, 3);
                usersTable.setValueAt(newStock > 0 ? "Available" : "Out of Stock", i, 4);
                stock.setText(String.valueOf(newStock));
                quan.setText(newStock > 0 ? "1" : "0");
                break;
            }
        }
    }
}

//private String generateOrderDetails() {
//    StringBuilder orderDetails = new StringBuilder();
//    double total = 0;
//
//    for (CartItem item : cartItems) {
//        double subtotal = item.itemPrice * item.quantity;
//        total += subtotal;
//        orderDetails.append("Item: ").append(item.itemName).append("\n")
//                     .append("Price: ₱").append(String.format("%.2f", item.itemPrice)).append("\n")
//                     .append("Quantity: ").append(item.quantity).append("\n")
//                     .append("Subtotal: ₱").append(String.format("%.2f", subtotal)).append("\n")
//                     .append("----------------------\n");
//    }
//
//    orderDetails.append("Total: ₱").append(String.format("%.2f", total)).append("\n");
//    return orderDetails.toString();
//}

private void updateSubtotalLive() {
    try {
        double unitPrice = Double.parseDouble(price.getText().replace("₱", ""));
        int quantity = parseInt(quan.getText(), 1);
        subto.setText("₱" + String.format("%.2f", unitPrice * quantity));
    } catch (NumberFormatException ex) {
        subto.setText("₱0.00");
    }
}

private void clearFields() {
    price.setText("");
    quan.setText("");
    stock.setText("");
    subto.setText("");
    name.setText("");
    id.setText("");
    
    
    image.setIcon(null);
    image.setText("");
    path = "";
    destination = "";
    selectedFile = null;
}

// CartItem class remains unchanged
public class CartItem {
    int itemID;
    String itemName;
    double itemPrice;
    int quantity;
    double subtotal;

    public CartItem(int itemID, String itemName, double itemPrice, int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.subtotal = itemPrice * quantity;
    }

    public Object[] toRow() {
        return new Object[]{itemID, itemName, String.format("₱%.2f", itemPrice), quantity, String.format("₱%.2f", subtotal)};
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        juser = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        uid = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idd1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
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
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        clearItemPanel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        removeItemPanel = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        subto = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        stock = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        asdasdasd = new javax.swing.JPanel();
        panelMinus = new javax.swing.JPanel();
        asd = new javax.swing.JLabel();
        panelPlus = new javax.swing.JPanel();
        ss = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        quan = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        addToCartPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        info = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        oha = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        placeOrderPanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        cus = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        add = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        con = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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
        jScrollPane2.setViewportView(usersTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 390, 130));

        jPanel5.setBackground(new java.awt.Color(239, 35, 35));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        uid.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        uid.setForeground(new java.awt.Color(255, 255, 255));
        uid.setText("uid");
        jPanel5.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 30, 20));

        jLabel8.setFont(new java.awt.Font("Gorlock", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("OFS");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/orderflow.png"))); // NOI18N
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 40, 40));

        jLabel5.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Order Flow System");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        idd1.setFont(new java.awt.Font("Gorlock", 1, 8)); // NOI18N
        idd1.setForeground(new java.awt.Color(255, 255, 255));
        idd1.setText("UID:");
        jPanel5.add(idd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 620, 30, 20));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 690));

        jLabel26.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 51, 51));
        jLabel26.setText("Dashboard / Order / Add Order");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 31, 200, 60));

        jPanel6.setBackground(new java.awt.Color(204, 0, 0));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("/");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 50, -1));

        jLabel9.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Home");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

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
        jPanel6.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, -1, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-logout.png"))); // NOI18N
        jPanel6.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 20, 20));

        menuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pngegg.png"))); // NOI18N
        jPanel6.add(menuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 20, 20));

        jLabel13.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User Dashboard");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 11, -1, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 1000, 40));

        jPanel3.setBackground(new java.awt.Color(233, 233, 233));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 980, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 0, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 980, 40));

        jLabel21.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("Dashboard / Order / Add Order");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 31, 200, 60));

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(cartTable);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 390, 140));

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        clearItemPanel.setBackground(new java.awt.Color(51, 51, 51));
        clearItemPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        clearItemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearItemPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearItemPanelMouseExited(evt);
            }
        });
        clearItemPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Clear");
        clearItemPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 40, 30));

        jPanel14.add(clearItemPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 0, 200, 30));

        removeItemPanel.setBackground(new java.awt.Color(51, 51, 51));
        removeItemPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removeItemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeItemPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeItemPanelMouseExited(evt);
            }
        });
        removeItemPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Remove Item");
        removeItemPanel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 89, 30));

        jPanel14.add(removeItemPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 30));

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 520, 390, 30));

        jPanel20.setBackground(new java.awt.Color(252, 252, 252));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel21.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 100));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        jLabel27.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 51, 51));
        jLabel27.setText("Item ID: ");
        jPanel20.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 50, 40));

        id.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        id.setForeground(new java.awt.Color(51, 51, 51));
        id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 60, 20));

        jLabel23.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 51, 51));
        jLabel23.setText("Name:");
        jPanel20.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 50, 30));

        subto.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        subto.setForeground(new java.awt.Color(51, 51, 51));
        subto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(subto, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 275, 70, 20));

        jLabel24.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 51, 51));
        jLabel24.setText("Price:");
        jPanel20.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 40, 30));

        price.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        price.setForeground(new java.awt.Color(51, 51, 51));
        price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 116, 60, 20));

        stock.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        stock.setForeground(new java.awt.Color(51, 51, 51));
        stock.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 135, 60, 20));

        jLabel30.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 51, 51));
        jLabel30.setText("Stock:");
        jPanel20.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 40, 30));

        jPanel12.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel20.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 270, 20));

        jLabel25.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 51, 51));
        jLabel25.setText("Quantity: ");
        jPanel20.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 60, 30));

        asdasdasd.setBackground(new java.awt.Color(51, 51, 51));
        asdasdasd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelMinus.setBackground(new java.awt.Color(51, 51, 51));
        panelMinus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        panelMinus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMinusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMinusMouseExited(evt);
            }
        });

        asd.setFont(new java.awt.Font("Gorlock", 1, 18)); // NOI18N
        asd.setForeground(new java.awt.Color(255, 255, 255));
        asd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        asd.setText("-");

        javax.swing.GroupLayout panelMinusLayout = new javax.swing.GroupLayout(panelMinus);
        panelMinus.setLayout(panelMinusLayout);
        panelMinusLayout.setHorizontalGroup(
            panelMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMinusLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(asd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panelMinusLayout.setVerticalGroup(
            panelMinusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(asd, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        asdasdasd.add(panelMinus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, -1));

        panelPlus.setBackground(new java.awt.Color(51, 51, 51));
        panelPlus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));
        panelPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelPlusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelPlusMouseExited(evt);
            }
        });

        ss.setFont(new java.awt.Font("Gorlock", 1, 18)); // NOI18N
        ss.setForeground(new java.awt.Color(255, 255, 255));
        ss.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ss.setText("+");

        javax.swing.GroupLayout panelPlusLayout = new javax.swing.GroupLayout(panelPlus);
        panelPlus.setLayout(panelPlusLayout);
        panelPlusLayout.setHorizontalGroup(
            panelPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlusLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(ss, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelPlusLayout.setVerticalGroup(
            panelPlusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        asdasdasd.add(panelPlus, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 0, 130, 30));

        jPanel20.add(asdasdasd, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 270, 30));

        name.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        name.setForeground(new java.awt.Color(51, 51, 51));
        name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 185, 60, 20));

        quan.setFont(new java.awt.Font("Gorlock", 1, 10)); // NOI18N
        quan.setForeground(new java.awt.Color(51, 51, 51));
        quan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel20.add(quan, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 215, 60, 20));

        jLabel33.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 51, 51));
        jLabel33.setText("Subtotal:");
        jPanel20.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 60, 30));

        jPanel2.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 270, 320));

        totalLabel.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        totalLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalLabel.setText("₱0.00");
        jPanel2.add(totalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 80, 30));

        jLabel16.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText(" Cart");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 60, 30));

        addToCartPanel.setBackground(new java.awt.Color(51, 51, 51));
        addToCartPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addToCartPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addToCartPanelMouseExited(evt);
            }
        });
        addToCartPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Add Item to Cart");
        addToCartPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 103, 40));

        jLabel22.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Add Item to Cart");
        addToCartPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 160, 30));

        jPanel2.add(addToCartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 530, 280, 30));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 270, 320));

        jLabel18.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel18.setText("Total Payment:");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 110, 50));

        jLabel17.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 18)); // NOI18N
        jLabel17.setText("Add Order / Customer's Order Info");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 460, 60));

        jPanel13.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 610, 210, -1));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 680, 3));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 680, 3));

        info.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        info.setForeground(new java.awt.Color(153, 153, 153));
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/product.png"))); // NOI18N
        jPanel2.add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 30, 30));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 390, 3));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 390, 3));

        jLabel20.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("Customer:");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 560, 60, 40));

        oha.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        oha.setForeground(new java.awt.Color(153, 153, 153));
        oha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shopping-cart.png"))); // NOI18N
        jPanel2.add(oha, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 30, 30));

        jLabel35.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 153, 153));
        jLabel35.setText(" List of Items");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 200, 50));

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 680, 40));

        placeOrderPanel.setBackground(new java.awt.Color(51, 51, 51));
        placeOrderPanel.setBorder(new javax.swing.border.MatteBorder(null));
        placeOrderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                placeOrderPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                placeOrderPanelMouseExited(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Place Order");

        javax.swing.GroupLayout placeOrderPanelLayout = new javax.swing.GroupLayout(placeOrderPanel);
        placeOrderPanel.setLayout(placeOrderPanelLayout);
        placeOrderPanelLayout.setHorizontalGroup(
            placeOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placeOrderPanelLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jLabel29)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        placeOrderPanelLayout.setVerticalGroup(
            placeOrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(placeOrderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 550, 390, 30));

        jLabel31.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(153, 153, 153));
        jLabel31.setText("Item Description");
        jPanel2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 200, 40));

        jLabel36.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(153, 153, 153));
        jLabel36.setText("Address:");
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, 110, 40));

        cus.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cus.setForeground(new java.awt.Color(51, 51, 51));
        cus.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusActionPerformed(evt);
            }
        });
        jPanel2.add(cus, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 570, 200, 20));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 160, 20));

        add.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        add.setForeground(new java.awt.Color(51, 51, 51));
        add.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel2.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 600, 200, 20));

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 600, 160, 20));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 600, 10, 20));

        con.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        con.setForeground(new java.awt.Color(51, 51, 51));
        con.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(255, 51, 51)));
        con.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conActionPerformed(evt);
            }
        });
        jPanel2.add(con, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 600, 200, 20));

        jLabel37.setFont(new java.awt.Font("Gorlock", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("Contact #:");
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 590, 60, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 938, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 647, Short.MAX_VALUE)
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
      order ord = new order();
      ord.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
            session ss = session.getInstance();
     
        if(ss.getUid()== 0){
//            JOptionPane.showMessageDialog(null,"No Account, Login First!");
//            login log = new login();
//            log.setVisible(true);
//            this.dispose();
        } else {
        uid.setText(""+ss.getUid());
        }
    }//GEN-LAST:event_formWindowActivated

    private void usersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersTableMouseClicked
//        int rowIndex = usersTable.getSelectedRow();
//        if(rowIndex < 0){
//            JOptionPane.showMessageDialog(null,"Please Select an Item!");
//        } else {
//
//            try{
//
//                dbConnector db = new dbConnector();
//                TableModel tbl = usersTable.getModel();
//                ResultSet rs = db.getData("SELECT * FROM tbl_item WHERE itemID ='"+tbl.getValueAt(rowIndex,0)+"'");
//                if(rs.next()){
//                    id.setText(""+rs.getString("itemID"));
//
//                    name.setText(""+rs.getString("itemName"));
//                
//
////                    stats.setSelectedItem(""+rs.getString("itemStats"));
////                    add.setEnabled(false);
////                    checkadd = false;
//
//                    //               reg.image.setIcon(reg.ResizeImage(rs.getString("user_image"), null, reg.image));
//                    //               reg.oldpath = rs.getString("user_image");
//                    //               reg.path = rs.getString("user_image");
//                    //               reg.destination = rs.getString("user_image");
//                    //               reg.add.setEnabled(false);
//                    //               reg.upd.setEnabled(true);
//                    //               reg.setVisible(true);
//                    //
//                    //               if(rs.getString("user_image").isEmpty()){
//                        //                   reg.select.setEnabled(true);
//                        //                   reg.remove.setEnabled(false);
//                        //               } else {
//                        //                   reg.select.setEnabled(false);
//                        //                   reg.remove.setEnabled(true);
//                        //               }
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Connection Error!");
//                }
//
//            }catch(SQLException ex){
//                System.out.println(""+ex);
//            }
//        }
    }//GEN-LAST:event_usersTableMouseClicked

    private void cartTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartTableMouseClicked

    private void panelMinusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinusMouseEntered
         panelMinus.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_panelMinusMouseEntered

    private void panelMinusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMinusMouseExited
         panelMinus.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_panelMinusMouseExited

    private void panelPlusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPlusMouseEntered
       panelPlus.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_panelPlusMouseEntered

    private void panelPlusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelPlusMouseExited
          panelPlus.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_panelPlusMouseExited

    private void addToCartPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToCartPanelMouseEntered
          addToCartPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_addToCartPanelMouseEntered

    private void addToCartPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToCartPanelMouseExited
     addToCartPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_addToCartPanelMouseExited

    private void clearItemPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearItemPanelMouseEntered
       clearItemPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_clearItemPanelMouseEntered

    private void clearItemPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearItemPanelMouseExited
        clearItemPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_clearItemPanelMouseExited

    private void removeItemPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeItemPanelMouseEntered
          removeItemPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_removeItemPanelMouseEntered

    private void removeItemPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeItemPanelMouseExited
        removeItemPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_removeItemPanelMouseExited

    private void placeOrderPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_placeOrderPanelMouseEntered
        placeOrderPanel.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_placeOrderPanelMouseEntered

    private void placeOrderPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_placeOrderPanelMouseExited
        placeOrderPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_placeOrderPanelMouseExited

    private void cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void conActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_conActionPerformed

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
            java.util.logging.Logger.getLogger(addOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField add;
    private javax.swing.JPanel addToCartPanel;
    private javax.swing.JLabel asd;
    private javax.swing.JPanel asdasdasd;
    private javax.swing.JLabel back;
    private javax.swing.JTable cartTable;
    private javax.swing.JPanel clearItemPanel;
    public javax.swing.JTextField con;
    public javax.swing.JTextField cus;
    private javax.swing.JLabel id;
    private javax.swing.JLabel idd1;
    public javax.swing.JLabel image;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
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
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel juser;
    private javax.swing.JLabel menuu;
    private javax.swing.JLabel name;
    private javax.swing.JLabel oha;
    private javax.swing.JPanel panelMinus;
    private javax.swing.JPanel panelPlus;
    private javax.swing.JPanel placeOrderPanel;
    private javax.swing.JLabel price;
    private javax.swing.JLabel quan;
    private javax.swing.JPanel removeItemPanel;
    private javax.swing.JLabel ss;
    private javax.swing.JLabel stock;
    private javax.swing.JLabel subto;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel uid;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
