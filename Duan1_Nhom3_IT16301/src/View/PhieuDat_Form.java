/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.BanAnDAO;
import DAO.HoaDonDAO;
import DAO.PhieuDatDAO;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.ShareHelper;
import Model.BanAn;
import Model.HoaDon;
import Model.PhieuDat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class PhieuDat_Form extends javax.swing.JFrame {

    /**
     * Creates new form PhieuDat
     */
    PhieuDatDAO dao = new PhieuDatDAO();
    int index = 0;
    BanAnDAO BADao = new BanAnDAO();
    HoaDonDAO hdDao = new HoaDonDAO();

    public PhieuDat_Form() {
        initComponents();
        LoadTable();
        fillComboBox();
        new Timer(1000, new ActionListener() {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");

            public void actionPerformed(ActionEvent e) {
                lb_Clock.setText(format.format(new Date()));
            }
        }).start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void LoadText() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLDatBan2";
        String user = "sa";
        String password = "123";
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "select Hoten,SDT,DateBook,TimeBook,SoNguoi,GhiChu,MaPD\n"
                    + "from PhieuDat inner join ThongTinKH on PhieuDat.MaKH=ThongTinKH.MaKH\n"
                    + "where MaPD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lb_MaPhieu.getText());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lb_HoTen.setText(rs.getString("Hoten"));
                lb_SDT.setText(rs.getString("SDT"));
                txt_GhiChu.setText(rs.getString("GhiChu"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void edit() {
        try {
            int a = Integer.parseInt(tbl_PhieuDat.getValueAt(index, 0) + "");
            PhieuDat model = dao.findById(a);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
                LoadText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setStatus(boolean insertable) {
        boolean first = this.index > 0;
        boolean last = this.index < tbl_PhieuDat.getRowCount() - 1;

    }

    void setModel(PhieuDat model) {
        lb_MaPhieu.setText(String.valueOf(model.getMaPD()));
        date_chooser.setDate(model.getDateBook());
        txt_GioDat.setText(String.valueOf(model.getTimeBook()));
        txt_SoNguoi.setText(String.valueOf(model.getSoNguoi()));
        txt_GhiChu.setText(model.getGhiChu());
        lb_TimeXacNhan.setText(String.valueOf(model.getTimeXacNhan()));
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb_BanAn.getModel();
        model.removeAllElements();
        try {
            List<BanAn> list = BADao.select();
            for (BanAn cd : list) {
                model.addElement(cd.getSoBan());
            }
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    HoaDon GetModel() {
        HoaDon model = new HoaDon();
        model.setMaPD(Integer.parseInt(lb_MaPhieu.getText()));
        model.setSoBan(Integer.parseInt(cbb_BanAn.getSelectedItem() + ""));
        model.setNgayAn(date_chooser.getDate());
        model.setGhiChu(txt_GhiChu.getText());
        model.setNguoiTao(String.valueOf(ShareHelper.USER));
        return model;
    }

    PhieuDat getmodel1() {
        PhieuDat model = new PhieuDat();
        model.setTrangThai("Đã Xác Nhận");
        model.setMaPD(Integer.parseInt(lb_MaPhieu.getText()));
        model.setDateBook(date_chooser.getDate());
        model.setTimeBook(Time.valueOf(txt_GioDat.getText()));
        model.setGhiChu(txt_GhiChu.getText());
        model.setSoNguoi(Integer.parseInt(txt_SoNguoi.getText()));
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
            java.util.Date d1 = (java.util.Date) format.parse(lb_Clock.getText());
            java.sql.Time ppstime = new java.sql.Time(d1.getTime());
            model.setTimeXacNhan(ppstime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    PhieuDat getmodelPD() {
        PhieuDat model = new PhieuDat();
//        String a =String.valueOf(tbl_PhieuDat.getValueAt(index, 7));
//        model.setTrangThai(a);
        model.setMaPD(Integer.parseInt(lb_MaPhieu.getText()));
        model.setDateBook(date_chooser.getDate());
        model.setTimeBook(Time.valueOf(txt_GioDat.getText()));
        model.setGhiChu(txt_GhiChu.getText());
        model.setSoNguoi(Integer.parseInt(txt_SoNguoi.getText()));
//        model.setTimeXacNhan(Time.valueOf(lb_TimeXacNhan.getText()));
        return model;
    }

    void UpdateHD() {
        PhieuDat model = getmodel1();
        try {
            dao.update1(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void UpdatePD() {
        PhieuDat model = getmodelPD();
        try {
            dao.update(model);
            DialogHelper.alert(this, "Thay đổi thành công");
            LoadText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void Insert() {
        HoaDon model = GetModel();
        try {
            hdDao.insert(model);
            DialogHelper.alert(this, " Thành Công");
            UpdateHD();
            Clear();
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Thất Bại");
        }
    }

    void Delete() {
        int id = Integer.parseInt(lb_MaPhieu.getText());
        try {
            dao.delete(id);
            DialogHelper.alert(this, "Xóa thành công");
            Clear();
            LoadTable();
            LoadText();
        } catch (Exception e) {
            e.printStackTrace();
            DialogHelper.alert(this, "Xóa Thất Bại");
        }
    }

    void Clear() {
        lb_HoTen.setText("");
        lb_SDT.setText("");
        setModel(new PhieuDat());
        txt_GioDat.setText("");
        lb_MaPhieu.setText("");
        txt_SoNguoi.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_PhieuDat = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbb_BanAn = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txt_SoNguoi = new javax.swing.JTextField();
        txt_GioDat = new javax.swing.JTextField();
        lb_HoTen = new javax.swing.JLabel();
        lb_SDT = new javax.swing.JLabel();
        lb_MaPhieu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_GhiChu = new javax.swing.JTextArea();
        date_chooser = new com.toedter.calendar.JDateChooser();
        lb_Clock = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbb_TrangThai = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        lb_TimeXacNhan = new javax.swing.JLabel();
        btn_XacNhan = new javax.swing.JButton();
        btn_ThayDoi = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        btn_Hd = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_PhieuDat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phiếu Đặt", "Mã Khách Hàng", "Đặt Ngày", "Thời gian", "Số Người", "Time Xác Nhận", "Ghi Chú", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_PhieuDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_PhieuDatMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_PhieuDat);
        if (tbl_PhieuDat.getColumnModel().getColumnCount() > 0) {
            tbl_PhieuDat.getColumnModel().getColumn(5).setHeaderValue("Time Xác Nhận");
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Họ Tên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Ngày Đặt:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Mã Phiếu Đặt:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Số Người đặt");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Giờ Đặt");

        cbb_BanAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Số Bàn:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Ghi Chú:");

        txt_GhiChu.setColumns(20);
        txt_GhiChu.setRows(5);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Trạng Thái:");

        cbb_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chờ Xác Nhận", "Đã Xác Nhận", "Đã Thanh Toán", " " }));
        cbb_TrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TrangThaiActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Thời gian Xác Nhận:");

        lb_TimeXacNhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_GhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(txt_GioDat, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_SoNguoi)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_TimeXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lb_MaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbb_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbb_BanAn, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_Clock, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Clock, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_GioDat))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
                            .addComponent(txt_GhiChu)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_MaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_BanAn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_TimeXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_XacNhan.setText("Xác Nhận");
        btn_XacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XacNhanActionPerformed(evt);
            }
        });

        btn_ThayDoi.setText("Thay Đổi");
        btn_ThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThayDoiActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_Hd.setText("Hóa Đơn");
        btn_Hd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btn_Hd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btn_ThayDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_XacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ThayDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Hd))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_PhieuDatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_PhieuDatMouseClicked

        if (evt.getClickCount() == 1) {
            this.index = tbl_PhieuDat.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                LoadText();
                if (lb_TimeXacNhan.equals("null")) {
                    lb_TimeXacNhan.setText("");
                }
            }
        }
    }//GEN-LAST:event_tbl_PhieuDatMouseClicked

    private void btn_XacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XacNhanActionPerformed
        Insert();
        Cbb();
    }//GEN-LAST:event_btn_XacNhanActionPerformed

    private void btn_ThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThayDoiActionPerformed
        UpdatePD();
        Cbb();
    }//GEN-LAST:event_btn_ThayDoiActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        Delete();
        Cbb();
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        Clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void cbb_TrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TrangThaiActionPerformed
//        int a = cbb_TrangThai.getSelectedIndex();
//        if (a == 0) {
//            LoadTable();
//               btn_Xoa.setEnabled(true);
//        }
//        if (a == 1) {
//            LoadTableDPH();
//            btn_Xoa.setEnabled(false);
//        }
//        if (a == 2) {
//            LoadTableDTT();
//            btn_Xoa.setEnabled(false);
//        }
        Cbb();
    }//GEN-LAST:event_cbb_TrangThaiActionPerformed

    private void btn_HdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HdActionPerformed
    HoaDonKH a = new HoaDonKH();
    a.setVisible(true);
    dispose();
    }//GEN-LAST:event_btn_HdActionPerformed

    /**
     * @param args the command line arguments
     */
    void LoadTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_PhieuDat.getModel();
        model.setRowCount(0);
        try {
            List<PhieuDat> list = dao.select();
            for (PhieuDat cd : list) {
                Object[] obj = {
                    cd.getMaPD(),
                    cd.getMaKH(),
                    cd.getDateBook(),
                    cd.getTimeBook(),
                    cd.getSoNguoi(),
                    cd.getTimeXacNhan(),
                    cd.getGhiChu(),
                    cd.getTrangThai()
                };
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadTableDPH() {
        DefaultTableModel model = (DefaultTableModel) tbl_PhieuDat.getModel();
        model.setRowCount(0);
        try {
            List<PhieuDat> list = dao.selectDPH();
            for (PhieuDat cd : list) {
                Object[] obj = {
                    cd.getMaPD(),
                    cd.getMaKH(),
                    cd.getDateBook(),
                    cd.getTimeBook(),
                    cd.getSoNguoi(),
                    cd.getTimeXacNhan(),
                    cd.getGhiChu(),
                    cd.getTrangThai()
                };
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadTableDTT() {
        DefaultTableModel model = (DefaultTableModel) tbl_PhieuDat.getModel();
        model.setRowCount(0);
        try {
            List<PhieuDat> list = dao.selectDTT();
            for (PhieuDat cd : list) {
                Object[] obj = {
                    cd.getMaPD(),
                    cd.getMaKH(),
                    cd.getDateBook(),
                    cd.getTimeBook(),
                    cd.getSoNguoi(),
                    cd.getTimeXacNhan(),
                    cd.getGhiChu(),
                    cd.getTrangThai()
                };
                model.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void Cbb() {
        int a = cbb_TrangThai.getSelectedIndex();
        if (a == 0) {
            LoadTable();
            btn_Xoa.setEnabled(true);
            Clear();
        }
        if (a == 1) {
            LoadTableDPH();
            btn_Xoa.setEnabled(false);
             Clear();
        }
        if (a == 2) {
            LoadTableDTT();
            btn_Xoa.setEnabled(false);
             Clear();
        }
    }

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
            java.util.logging.Logger.getLogger(PhieuDat_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhieuDat_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhieuDat_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhieuDat_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new PhieuDat_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Hd;
    private javax.swing.JButton btn_ThayDoi;
    private javax.swing.JButton btn_XacNhan;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbb_BanAn;
    private javax.swing.JComboBox<String> cbb_TrangThai;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Clock;
    private javax.swing.JLabel lb_HoTen;
    private javax.swing.JLabel lb_MaPhieu;
    private javax.swing.JLabel lb_SDT;
    private javax.swing.JLabel lb_TimeXacNhan;
    private javax.swing.JTable tbl_PhieuDat;
    private javax.swing.JTextArea txt_GhiChu;
    private javax.swing.JTextField txt_GioDat;
    private javax.swing.JTextField txt_SoNguoi;
    // End of variables declaration//GEN-END:variables
}