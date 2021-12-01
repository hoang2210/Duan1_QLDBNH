/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Helper.DialogHelper;
import Helper.ShareHelper;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Dieu Le
 */
public class HomeNhaHang extends javax.swing.JFrame {

    /**
     * Creates new form HomeNhaHang
     */
    public HomeNhaHang() {
        initComponents();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        ImageIcon user = new ImageIcon("src/Logo/2.jpg");
        lblogo.setIcon(user);
        ImageIcon dx = new ImageIcon("src/Logo/dangxuat1.png");
        mniDangxuat.setIcon(dx);
        ImageIcon dmk = new ImageIcon("src/Logo/Refresh.png");
        mniDoiMK.setIcon(dmk);
        ImageIcon thoat = new ImageIcon("src/Logo/off.png");
        mniThoat.setIcon(thoat);
        ImageIcon dondat = new ImageIcon("src/Logo/phieudat1.png");
        mniPhieuDat.setIcon(dondat);
        ImageIcon ttb = new ImageIcon("src/Logo/datban1.png");
        mniTrangThaiBan.setIcon(ttb);
        ImageIcon hoadon = new ImageIcon("src/Logo/bill.png");
        mniHoaDon.setIcon(hoadon);
        ImageIcon taikhoan = new ImageIcon("src/Logo/lock.png");
        mniTaikhoan.setIcon(taikhoan);
        ImageIcon nhanvien = new ImageIcon("src/Logo/add-user.png");
        mniNhanVien.setIcon(nhanvien);
        ImageIcon menu = new ImageIcon("src/Logo/menu.png");
        mniThucDon.setIcon(menu);
        ImageIcon dx1 = new ImageIcon("src/Logo/dangxuat1.png");
        btnDXuat.setIcon(dx1);
        ImageIcon dondat1 = new ImageIcon("src/Logo/phieudat1.png");
        btnPhieuDat.setIcon(dondat1);
        ImageIcon ttb1 = new ImageIcon("src/Logo/datban1.png");
        btnTTB.setIcon(ttb1);
        ImageIcon hoadon1 = new ImageIcon("src/Logo/bill.png");
        btnHoaDon.setIcon(hoadon1);
        ImageIcon taikhoan1 = new ImageIcon("src/Logo/lock.png");
        btnTaiKhoan.setIcon(taikhoan1);
        ImageIcon nhanvien1 = new ImageIcon("src/Logo/add-user.png");
        btnNhanVien.setIcon(nhanvien1);
//        ImageIcon ngay = new ImageIcon("src/Logo/lich1.png");
//        lblNgay.setIcon(ngay);
//        ImageIcon gio = new ImageIcon("src/Logo/dongho2.png");
//        lblGio.setIcon(gio);
//        ImageIcon logo = new ImageIcon("src/Logo/LogoNH1.png");
//        lb3.setIcon(logo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator4 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jToolBar1 = new javax.swing.JToolBar();
        btnDXuat = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnPhieuDat = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnTTB = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jSeparator12 = new javax.swing.JToolBar.Separator();
        btnHoaDon = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnTaiKhoan = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jSeparator11 = new javax.swing.JToolBar.Separator();
        btnNhanVien = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblNgay = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();
        lb3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblogo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniDangxuat = new javax.swing.JCheckBoxMenuItem();
        mniDoiMK = new javax.swing.JCheckBoxMenuItem();
        mniThoat = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mniPhieuDat = new javax.swing.JCheckBoxMenuItem();
        mniTrangThaiBan = new javax.swing.JCheckBoxMenuItem();
        mniHoaDon = new javax.swing.JCheckBoxMenuItem();
        mniTaikhoan = new javax.swing.JCheckBoxMenuItem();
        mniNhanVien = new javax.swing.JCheckBoxMenuItem();
        mniThucDon = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        btnDXuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDXuat.setText("Đăng xuất");
        btnDXuat.setFocusable(false);
        btnDXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDXuat);
        jToolBar1.add(jSeparator1);

        btnPhieuDat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPhieuDat.setText("Đơn đặt");
        btnPhieuDat.setFocusable(false);
        btnPhieuDat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhieuDat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPhieuDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuDatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPhieuDat);

        jSeparator3.setForeground(new java.awt.Color(204, 153, 255));
        jToolBar1.add(jSeparator3);

        btnTTB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTTB.setText("Món ăn");
        btnTTB.setFocusable(false);
        btnTTB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTTB.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTTBActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTTB);
        jToolBar1.add(jSeparator5);
        jToolBar1.add(jSeparator12);

        btnHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setFocusable(false);
        btnHoaDon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnHoaDon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        jToolBar1.add(btnHoaDon);
        jToolBar1.add(jSeparator6);

        btnTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTaiKhoan.setText("Tài khoản");
        btnTaiKhoan.setFocusable(false);
        btnTaiKhoan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTaiKhoan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaiKhoanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTaiKhoan);
        jToolBar1.add(jSeparator8);
        jToolBar1.add(jSeparator11);

        btnNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setFocusable(false);
        btnNhanVien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNhanVien);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lblNgay.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        lblGio.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGio)
                    .addComponent(lblNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNgay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblGio)))
                .addContainerGap())
        );

        jToolBar1.add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );

        jMenu1.setText("Hệ thống");

        mniDangxuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniDangxuat.setText("Đăng xuất");
        mniDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangxuatActionPerformed(evt);
            }
        });
        jMenu1.add(mniDangxuat);

        mniDoiMK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniDoiMK.setText("Đổi mật khẩu");
        jMenu1.add(mniDoiMK);

        mniThoat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniThoat.setText("Thoát");
        mniThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThoatActionPerformed(evt);
            }
        });
        jMenu1.add(mniThoat);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản lý");

        mniPhieuDat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniPhieuDat.setText("Đơn đặt");
        mniPhieuDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPhieuDatActionPerformed(evt);
            }
        });
        jMenu2.add(mniPhieuDat);

        mniTrangThaiBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniTrangThaiBan.setText("Trạng thái bàn ");
        mniTrangThaiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTrangThaiBanActionPerformed(evt);
            }
        });
        jMenu2.add(mniTrangThaiBan);

        mniHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniHoaDon.setText("Hóa đơn ");
        mniHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHoaDonActionPerformed(evt);
            }
        });
        jMenu2.add(mniHoaDon);

        mniTaikhoan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniTaikhoan.setText("Tài khoản ");
        mniTaikhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTaikhoanActionPerformed(evt);
            }
        });
        jMenu2.add(mniTaikhoan);

        mniNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniNhanVien.setText("Nhân viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(mniNhanVien);

        mniThucDon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mniThucDon.setText("Thực đơn");
        mniThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniThucDonActionPerformed(evt);
            }
        });
        jMenu2.add(mniThucDon);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed
        new QuanLiNhanVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        new HoaDonKH().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaiKhoanActionPerformed
        new Accout().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTaiKhoanActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        new QuanLiNhanVien().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void mniTrangThaiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTrangThaiBanActionPerformed
        
    }//GEN-LAST:event_mniTrangThaiBanActionPerformed

    private void mniTaikhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTaikhoanActionPerformed
        new Accout().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniTaikhoanActionPerformed

    private void mniHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHoaDonActionPerformed
        new HoaDonKH().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniHoaDonActionPerformed

    private void btnTTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTTBActionPerformed
        new QLMonAn().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTTBActionPerformed

    private void btnPhieuDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuDatActionPerformed
        new PhieuDat_Form().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPhieuDatActionPerformed

    private void mniDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangxuatActionPerformed
        ShareHelper.logoff();
        new Login_NV().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniDangxuatActionPerformed

    private void btnDXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDXuatActionPerformed
        ShareHelper.logoff();
        new Login_NV().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDXuatActionPerformed

    private void mniThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThoatActionPerformed
        if (DialogHelper.confirm(this, "Bạn có muốn thoát chương trình không?")) {
            System.exit(0);
        }
    }//GEN-LAST:event_mniThoatActionPerformed

    private void mniPhieuDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPhieuDatActionPerformed
        new PhieuDat_Form().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniPhieuDatActionPerformed

    private void mniThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniThucDonActionPerformed
        new QLLoaiMon().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mniThucDonActionPerformed

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
            java.util.logging.Logger.getLogger(HomeNhaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeNhaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeNhaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeNhaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new HomeNhaHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDXuat;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnPhieuDat;
    private javax.swing.JButton btnTTB;
    private javax.swing.JButton btnTaiKhoan;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JToolBar.Separator jSeparator11;
    private javax.swing.JToolBar.Separator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblNgay;
    private javax.swing.JLabel lblogo;
    private javax.swing.JCheckBoxMenuItem mniDangxuat;
    private javax.swing.JCheckBoxMenuItem mniDoiMK;
    private javax.swing.JCheckBoxMenuItem mniHoaDon;
    private javax.swing.JCheckBoxMenuItem mniNhanVien;
    private javax.swing.JCheckBoxMenuItem mniPhieuDat;
    private javax.swing.JCheckBoxMenuItem mniTaikhoan;
    private javax.swing.JCheckBoxMenuItem mniThoat;
    private javax.swing.JCheckBoxMenuItem mniThucDon;
    private javax.swing.JCheckBoxMenuItem mniTrangThaiBan;
    // End of variables declaration//GEN-END:variables
}
