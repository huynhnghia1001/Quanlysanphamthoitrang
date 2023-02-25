/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.poly.Nghiahtpc01295.Assignment2;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nghiahtpc01295
 */
public class ChiTietSanPham extends javax.swing.JFrame {

    ArrayList<ChiTiet> list = new ArrayList<ChiTiet>();
    int current = 0;
    String userName = "sa";
    String password = "123";
    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=QLTT;encrypt=false;trustServerCertificate=true;";
    String hear[] = {"MaSP", "TenSP", "Mau", "KichThuoc", "ThongTin"};
    DefaultTableModel modell = new DefaultTableModel(hear, 0);

    int a = -1;  
    /**
     * Creates new form ChiTietSanPham
     */
    public ChiTietSanPham() {
        initComponents();
        LoadDataToArray();
        LoadDataToTable();
        lblTen.setEnabled(false);
        txtMaSP.setEditable(false);
        btnUpdate.setEnabled(false);
    }

    public void LoadDataToArray() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, userName, password);
            String sql = "select Products.TenSP, ProductDetails.MaSP, ProductDetails.mau, ProductDetails.KichThuoc, ProductDetails.ThongTin "
                    + "from Products inner join ProductDetails on Products.MaSP = ProductDetails.MaSP";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String MaSP = rs.getString(1);
                String TenSP = rs.getString(2);
                String mau = rs.getString(3);
                String KichThuoc = rs.getString(4);
                String ThongTin = rs.getString(5); 
                ChiTiet sv = new ChiTiet(MaSP, TenSP, mau, KichThuoc, ThongTin);
                list.add(sv);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void LoadDataToTable() {
        try {
            modell.setRowCount(0);
            for (ChiTiet b : list) {
                modell.addRow(new Object[]{
                    b.TenSP, b.MaSP, b.Mau, b.KichThuoc, b.ThongTin
                });
            }
            tblChiTiet.setModel(modell);
        } catch (Exception e) {
        }
    }

    

    public void click() {
        int t = tblChiTiet.getSelectedRow();
        if (t < 0) {
            return;
        }
        Display(t);
    }

    public void Display(int i) {
       ChiTiet sv = list.get(i); 
       txtMaSP.setText(sv.TenSP);
       lblTen.setText(sv.MaSP);
       txtMau.setText(sv.Mau);
       txtKichThuoc.setText(sv.KichThuoc);
       txtThongTin.setText(sv.ThongTin);
       btnUpdate.setEnabled(true);
   }

    public void New() {
        txtMau.setText("");
        txtKichThuoc.setText("");
        txtThongTin.setText("");
        btnUpdate.setEnabled(false);
        tblChiTiet.setSelectionMode(0);
    }
    public void Delete() {
        if (txtMaSP.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Nhap Mã SV!");
            txtMaSP.requestFocus();
            return;
        }
        for (int i = 0; i < list.size(); i++) {
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String sql = "delete from ProductDetails where MaSP = ?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, txtMaSP.getText());
                    st.execute();
                    JOptionPane.showMessageDialog(this, "Delete thành công!!");
                    con.close();
                    Display(current--);
                    LoadDataToArray();
                    tblChiTiet.setSelectionMode(0);
                    LoadDataToTable();
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Error");
                }
                a=1;
                break;
            
        }
        if(a==0){
                JOptionPane.showMessageDialog(this, "khong co ma sinh vien nay");
       }
    }
    
    public void Update() {
        if (txtMaSP.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Nhap Mã SV!");
            txtMaSP.requestFocus();
            return;
        }
        if(Check()){
        for (int i = 0; i < list.size(); i++) {
            try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con = DriverManager.getConnection(url, userName, password);
                    String sql = "update ProductDetails set Mau = ?, KichThuoc = ?, ThongTin = ? where MaSP = ?";
                    PreparedStatement st = con.prepareStatement(sql);
                    st.setString(1, txtMau.getText());
                    st.setString(2, txtKichThuoc.getText());
                    st.setString(3, txtThongTin.getText());
                    st.setString(4, txtMaSP.getText());
                    st.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Update thành công!!!");
                    con.close();
                    LoadDataToArray();
                    tblChiTiet.setSelectionMode(0);
                    LoadDataToTable();
                    New();
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this, "Error");
                }            
                a=1;
                break;
    
        } 
           if(a==0){
                JOptionPane.showMessageDialog(this, "khong co ma sinh vien nay");
            }
    
    }
        }
    public boolean Check(){
        if (txtMaSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dữ liệu");
            txtMaSP.setBackground(Color.yellow);
            return false;
        } else {
            txtMaSP.setBackground(Color.white);
        }
        if (txtMau.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Màu");
            txtMau.setBackground(Color.yellow);
            return false;
        } else {
            txtMau.setBackground(Color.white);
        }
        if (txtKichThuoc.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Kich Thuoc");
            txtKichThuoc.setBackground(Color.yellow);
            return false;
        } else {
            txtKichThuoc.setBackground(Color.white);
        }
        if (txtThongTin.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập Thong Tin");
            txtThongTin.setBackground(Color.yellow);
            return false;
        } else {
            txtThongTin.setBackground(Color.white);
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtMau = new javax.swing.JTextField();
        txtKichThuoc = new javax.swing.JTextField();
        txtThongTin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTiet = new javax.swing.JTable();
        lblTen = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Chi Tiết Sản Phẩm");

        jLabel2.setText("Mã SP :");

        jLabel3.setText("Tên SP :");

        jLabel4.setText("Kích Thước :");

        jLabel5.setText("Màu :");

        jLabel7.setText("Thông Tin :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/New.png"))); // NOI18N
        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/Update.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tblChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChiTiet);

        lblTen.setForeground(new java.awt.Color(0, 0, 204));

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/poly/icon/thoat.jpg"))); // NOI18N
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(176, 176, 176)
                .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                        .addComponent(txtMau)
                        .addComponent(txtKichThuoc)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtMau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietMouseClicked
        // TODO add your handling code here:
        click();
    }//GEN-LAST:event_tblChiTietMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        Delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        New();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        Update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        Login ct = new Login();
        JOptionPane.showMessageDialog(this, "Đăng xuất thành công");
        ct.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChiTietSanPham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTen;
    private javax.swing.JTable tblChiTiet;
    private javax.swing.JTextField txtKichThuoc;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMau;
    private javax.swing.JTextField txtThongTin;
    // End of variables declaration//GEN-END:variables
}
