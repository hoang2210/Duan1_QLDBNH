/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.TaiKhoanKHDAO;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Model.TaiKhoanKH;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ADMIN
 */
public class Login_KH extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login_KH() {
        initComponents();
        txt_Password.setEchoChar((char) 0);
        txt_Username.setForeground(Color.GRAY);
        txt_Password.setForeground(Color.GRAY);
        ImageIcon logo = new ImageIcon("src/Logo/LogoNH.png");
        lb_Logo.setIcon(logo);
        ImageIcon mini = new ImageIcon("src/Logo/minimize.png");
        lb_Minimize.setIcon(mini);
        ImageIcon cancel = new ImageIcon("src/Logo/cancel.png");
        lb_Cancel.setIcon(cancel);
 
    }
    TaiKhoanKHDAO tkkh = new TaiKhoanKHDAO();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void Login() {
        String us = txt_Username.getText();
        String ps = new String(txt_Password.getPassword());
        try {
            TaiKhoanKH tk = tkkh.findById(us);
            if (tk != null) {
                String ps1 = tk.getPass();
                if (ps.equals(ps1)) {
                    ShareHelper.USERkh = tk;
                    DialogHelper.alert(this, "Đăng nhập thành công !");
                } else {
                    DialogHelper.alert(this, "Đăng nhập thất bại!");
                }
            } else {
                DialogHelper.alert(this, "Sai tài khoản hoặc mật khẩu!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_Username = new javax.swing.JTextField();
        txt_Password = new javax.swing.JPasswordField();
        cbx_Hienthi = new javax.swing.JCheckBox();
        btn_Dangnhap = new javax.swing.JButton();
        txt_Quenmk = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_DangKi = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lb_Logo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lb_Minimize = new javax.swing.JLabel();
        lb_Cancel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_Username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Username.setText("Tài Khoản");
        txt_Username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_UsernameFocusGained(evt);
            }
        });

        txt_Password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_Password.setText("Mật Khẩu");
        txt_Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_PasswordFocusGained(evt);
            }
        });

        cbx_Hienthi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbx_Hienthi.setText("Hiện mật khẩu");
        cbx_Hienthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_HienthiActionPerformed(evt);
            }
        });

        btn_Dangnhap.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btn_Dangnhap.setText("Đăng nhập");
        btn_Dangnhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangnhapActionPerformed(evt);
            }
        });

        txt_Quenmk.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txt_Quenmk.setText("Quên mật khẩu?");
        txt_Quenmk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_QuenmkMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("hoặc");

        txt_DangKi.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        txt_DangKi.setText("Đăng Kí");
        txt_DangKi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_DangKiMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Đăng Nhập");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lb_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_Username)
                                .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbx_Hienthi, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_Dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_DangKi, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_Quenmk, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_Hienthi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Dangnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_Quenmk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_DangKi)))))
                    .addComponent(lb_Logo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        lb_Minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_MinimizeMouseClicked(evt);
            }
        });

        lb_Cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lb_CancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(462, Short.MAX_VALUE)
                .addComponent(lb_Minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lb_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Minimize, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
            .addComponent(lb_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_UsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_UsernameFocusGained
        txt_Username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Username.getText().equals("Tài Khoản")) {
                    txt_Username.setText("");
                    txt_Username.setForeground(Color.BLACK);
                } else {
                    txt_Username.selectAll();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Username.getText().equals("")) {
                    txt_Username.setText("Tài Khoản");
                    txt_Username.setForeground(Color.GRAY);
                }
            }
        })
        ;

    }//GEN-LAST:event_txt_UsernameFocusGained

    private void txt_PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_PasswordFocusGained
        txt_Password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_Password.getText().equals("Mật Khẩu")) {
                    txt_Password.setEchoChar('*');
                    txt_Password.setText("");
                    txt_Password.setForeground(Color.BLACK);   
                } else{
                    txt_Password.selectAll();
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txt_Password.getText().equals("")) {
                    txt_Password.setText("Mật Khẩu");
                    txt_Password.setEchoChar((char) 0);
                    txt_Password.setForeground(Color.GRAY);
                }
            }
        });

    }//GEN-LAST:event_txt_PasswordFocusGained

    private void cbx_HienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_HienthiActionPerformed
        if (cbx_Hienthi.isSelected()) {
            txt_Password.setEchoChar((char) 0);
        } else {
            txt_Password.setEchoChar('*');
        }
    }//GEN-LAST:event_cbx_HienthiActionPerformed

    private void btn_DangnhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangnhapActionPerformed
        Login();
    }//GEN-LAST:event_btn_DangnhapActionPerformed

    private void txt_QuenmkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_QuenmkMouseClicked
        DialogHelper.alert(this, "Quên mật khẩu");
    }//GEN-LAST:event_txt_QuenmkMouseClicked

    private void txt_DangKiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_DangKiMouseClicked
        DialogHelper.alert(this, "Đăng kí");
    }//GEN-LAST:event_txt_DangKiMouseClicked

    private void lb_MinimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_MinimizeMouseClicked
       this.setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_lb_MinimizeMouseClicked

    private void lb_CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_CancelMouseClicked
      System.exit(0);
    }//GEN-LAST:event_lb_CancelMouseClicked

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
            java.util.logging.Logger.getLogger(Login_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_KH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_KH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Dangnhap;
    private javax.swing.JCheckBox cbx_Hienthi;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_Cancel;
    private javax.swing.JLabel lb_Logo;
    private javax.swing.JLabel lb_Minimize;
    private javax.swing.JLabel txt_DangKi;
    private javax.swing.JPasswordField txt_Password;
    private javax.swing.JLabel txt_Quenmk;
    private javax.swing.JTextField txt_Username;
    // End of variables declaration//GEN-END:variables
}
