/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.CTHoaDonDAO1;
import DAO.HoaDonDAO;
import DAO.HoaDonDAO1;
import Helper.DateHelper;
import Helper.JdbcHelper;
import static Helper.JdbcHelper.driver;
import Model.CTHoaDon1;
import Model.HoaDon1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

/**
 *
 * @author ADMIN
 */
public class ThongKeHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form ThongKeHoaDon
     */
    private DefaultTableModel model;
    private List<CTHoaDon1> lst;
    private List<HoaDon1> list;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String dburl = "jdbc:sqlserver://localhost;databaseName=QLDatBan";
    String username = "sa";
    String password = "123";

    CTHoaDonDAO1 CTHDDao = new CTHoaDonDAO1();
    HoaDonDAO1 HDDAO = new HoaDonDAO1();

    public ThongKeHoaDon() {
        initComponents();

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        date_chooser.setDate(date);
        model = (DefaultTableModel) tbl_HoaDon.getModel();
        lst = CTHDDao.getAll(10);
        LoadTableTK();
        
    }

    void LoadTable() {
        model.setRowCount(0);
        try {
            String sql = "select *from\n"
                    + "HoaDon inner join PhieuDat on PhieuDat.MaPD=HoaDon.MaPD\n"
                    + "where DateBook=? and TrangThai = N'Đã thanh toán'";
            String a = new SimpleDateFormat("yyyy-MM-dd").format(date_chooser.getDate());
            ResultSet rs = JdbcHelper.executeQuery(sql, a);
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = model.getRowCount() + 1;
                obj[1] = rs.getInt("MaHD");
                obj[2] = rs.getInt("SoBan");
                obj[3] = rs.getString("NguoiTao");
                obj[4] = rs.getInt("TongTien");
                model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadTableTab4() {
      DefaultTableModel model = (DefaultTableModel) tbl_ThongKe1.getModel();
      model.setRowCount(0);
        try {
            String sql = "select Month( NgayAn)as a, Sum(TongTien) as b,COUNT(MaHD) AS c\n"
                    + "from HoaDon INNER JOIN PhieuDat ON PhieuDat.MaPD = HoaDon.MaPD\n"
                    + "where year( NgayAn) =?\n"
                    + "Group by Month( NgayAn)";
            
            ResultSet rs = JdbcHelper.executeQuery(sql, txt_Nam.getText());
            while (rs.next()) {
                Object obj[] = new Object[4];
                obj[0] = model.getRowCount() + 1;
                obj[1] = rs.getInt("a");
                obj[2] = rs.getDouble("b");
                obj[3] = rs.getInt("c");
                model.addRow(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void LoadTableMeNu() {
        DefaultTableModel model = (DefaultTableModel) tbl_Mon.getModel();
        model.setRowCount(0);
        int MaHD = Integer.parseInt(lb_MaHD.getText());
        lst = CTHDDao.getHoaDon(MaHD);
        for (int i = 0; i < lst.size(); i++) {
            CTHoaDon1 s = lst.get(i);
            Object obj[] = new Object[7];
            obj[0] = model.getRowCount() + 1;
            obj[1] = s.getTenMon();
            obj[3] = s.getGiaTien();
            obj[2] = s.getSoLuong();
            obj[4] = Double.parseDouble(s.getSoLuong() + "") * s.getGiaTien();
            model.addRow(obj);

        }
    }

    void LoadTableTK() {
        DefaultTableModel tblmodel = (DefaultTableModel) tbl_ThongKe.getModel();
        tblmodel.setRowCount(0);
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(dburl, username, password);

            String sql = "select year( NgayAn)as a, Sum(TongTien) as b, count(MaHD) AS C\n"
                    + "from HoaDon INNER JOIN PhieuDat ON PhieuDat.MaPD = HoaDon.MaPD\n"
                    + "Group by year( NgayAn)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object obj[] = new Object[5];
                obj[0] = tblmodel.getRowCount() + 1;
                obj[1] = rs.getInt("a");
                obj[2] = rs.getInt("b");
                obj[3] = rs.getString("c");
                tblmodel.addRow(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void TongTien() {
        double tien = 0;
        for (int i = 0; i < tbl_HoaDon.getRowCount(); i++) {
            tien += Double.parseDouble(tbl_HoaDon.getValueAt(i, 4).toString());
            lb_DoanhThu.setText(String.valueOf(tien));
        }
    }

    private void TongTienHD() {
        double tien = 0;
        for (int i = 0; i < tbl_Mon.getRowCount(); i++) {
            tien += Double.parseDouble(tbl_Mon.getValueAt(i, 4).toString());
            lb_TongTien.setText(String.valueOf(tien));
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

        tab = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_HoaDon = new javax.swing.JTable();
        date_chooser = new com.toedter.calendar.JDateChooser();
        btn_Search = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lb_DoanhThu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lb_HoTen = new javax.swing.JLabel();
        lb_SDT = new javax.swing.JLabel();
        lb_SoNguoi = new javax.swing.JLabel();
        lb_NhanVien = new javax.swing.JLabel();
        lb_NgayThang = new javax.swing.JLabel();
        lb_MaHD = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lb_MaPD = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lb_GioRa = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Mon = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        lb_TongTien = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ThongKe1 = new javax.swing.JTable();
        btn_ThuNhap1 = new javax.swing.JButton();
        btn_HoaDon1 = new javax.swing.JButton();
        txt_Nam = new javax.swing.JTextField();
        btn_Tim = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_ThongKe = new javax.swing.JTable();
        btn_ThuNhap = new javax.swing.JButton();
        btn_HoaDon = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Số Bàn", "Người Tạo", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_HoaDon);

        date_chooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                date_chooserMouseClicked(evt);
            }
        });

        btn_Search.setText("Tìm ");
        btn_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SearchActionPerformed(evt);
            }
        });

        jLabel1.setText("Doanh Thu:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(308, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(date_chooser, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btn_Search)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_chooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Search, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_DoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        tab.addTab("Hóa Đơn", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Họ Tên:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Số Điện Thoại:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Nhân Viên: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Số Khách:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Mã Hóa Đơn:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Ngày Tháng:");

        lb_HoTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_SDT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_SoNguoi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_NhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_NgayThang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lb_MaHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Mã Phiếu Đặt:");

        lb_MaPD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Giờ ra:");

        lb_GioRa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lb_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lb_NgayThang, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lb_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lb_GioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_SoNguoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_MaPD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_NgayThang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lb_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lb_GioRa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lb_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_MaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lb_SoNguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_MaPD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        tbl_Mon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Món", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_Mon);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Tổng Tiền:");

        lb_TongTien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lb_TongTien.setText("Tổng Tiền:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_TongTien)
                .addGap(96, 96, 96))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_TongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        tab.addTab("Chi Tiết Hóa Đơn", jPanel3);

        tbl_ThongKe1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tháng", "Tổng thu nhập", "Tổng số Hóa Đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_ThongKe1);

        btn_ThuNhap1.setText("Thu Nhập");
        btn_ThuNhap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThuNhap1ActionPerformed(evt);
            }
        });

        btn_HoaDon1.setText("Hóa Đơn");
        btn_HoaDon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HoaDon1ActionPerformed(evt);
            }
        });

        btn_Tim.setText("Tìm Kiếm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btn_ThuNhap1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btn_HoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThuNhap1)
                    .addComponent(btn_HoaDon1))
                .addGap(21, 21, 21))
        );

        tab.addTab("Thống kê theo năm", jPanel1);

        tbl_ThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Năm", "Tổng thu nhập", "Tổng số Hóa Đơn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbl_ThongKe);

        btn_ThuNhap.setText("Thu Nhập");
        btn_ThuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThuNhapActionPerformed(evt);
            }
        });

        btn_HoaDon.setText("Hóa Đơn");
        btn_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btn_ThuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btn_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThuNhap)
                    .addComponent(btn_HoaDon))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        tab.addTab("Thống Kê Các Năm", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void date_chooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_date_chooserMouseClicked
        LoadTable();
    }//GEN-LAST:event_date_chooserMouseClicked

    private void btn_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SearchActionPerformed
        LoadTable();
        TongTien();
    }//GEN-LAST:event_btn_SearchActionPerformed

    private void btn_ThuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThuNhapActionPerformed
        try {
            String sql = "select year( NgayAn), Sum(TongTien) \n"
                    + "from HoaDon INNER JOIN PhieuDat ON PhieuDat.MaPD = HoaDon.MaPD\n"
                    + "Group by year( NgayAn)";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(JdbcHelper.connect(), sql);
            JFreeChart chart = ChartFactory.createBarChart("Thống kê Thu Nhập theo Năm", "Năm", "Thu Nhập", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer render = null;
            CategoryPlot plot = null;
            render = new BarRenderer();
            ChartFrame frame = new ChartFrame("A", chart);
            frame.setVisible(true);
            frame.setSize(400, 600);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_ThuNhapActionPerformed

    private void btn_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HoaDonActionPerformed
        try {
            String sql = "select year( NgayAn),  count(MaHD)  \n"
                    + "from HoaDon INNER JOIN PhieuDat ON PhieuDat.MaPD = HoaDon.MaPD\n"
                    + "Group by year( NgayAn)";
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(JdbcHelper.connect(), sql);
            JFreeChart chart = ChartFactory.createBarChart("Thống kê Tổng hóa đơn theo Năm", "Năm", "Tổng Hóa Đơn", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer render = null;
            CategoryPlot plot = null;
            render = new BarRenderer();
            ChartFrame frame = new ChartFrame("A", chart);
            frame.setVisible(true);
            frame.setSize(400, 600);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_HoaDonActionPerformed

    private void tbl_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonMouseClicked
        LoadHD();
        tab.setSelectedIndex(1);
    }//GEN-LAST:event_tbl_HoaDonMouseClicked

    private void btn_ThuNhap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThuNhap1ActionPerformed
        try {
            JDBCCategoryDataset dataset = new JDBCCategoryDataset(JdbcHelper.connect());
             int a = 0;
             double b = 0;
        for (int i = 0; i < tbl_ThongKe1.getRowCount(); i++) {
            a = Integer.parseInt(tbl_ThongKe1.getValueAt(i,1).toString());
            b = Double.parseDouble(tbl_ThongKe1.getValueAt(i,2).toString());
              dataset.setValue(new Integer(a), "1", "3");
              dataset.setValue(new Double(b), "1", "2");
            JFreeChart chart = ChartFactory.createBarChart("A ", "B", "C", dataset, PlotOrientation.VERTICAL, false, true, true);
            BarRenderer render = null;
            CategoryPlot plot = null;
            render = new BarRenderer();
            ChartFrame frame = new ChartFrame("A", chart);
            frame.setVisible(true);
            frame.setSize(400, 600);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_ThuNhap1ActionPerformed

    private void btn_HoaDon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HoaDon1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_HoaDon1ActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
       LoadTableTab4();
    }//GEN-LAST:event_btn_TimActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKeHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeHoaDon.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeHoaDon().setVisible(true);
            }
        });
    }

    void LoadHD() {
        int vitri = tbl_HoaDon.getSelectedRow();
        int a = Integer.parseInt(tbl_HoaDon.getValueAt(vitri, 1).toString());
        try {
            String sql = "select *\n"
                    + "from(( HoaDon inner join PhieuDat on PhieuDat.MaPD = HoaDon.MaPD)\n"
                    + "inner join ThongTinKH on ThongTinKH.MaKH = PhieuDat.MaKH)\n"
                    + "where MaHD =?";
            ResultSet rs = JdbcHelper.executeQuery(sql, a);
            while (rs.next()) {
                lb_MaHD.setText(String.valueOf(rs.getInt("MaHD")));
                lb_MaPD.setText(String.valueOf(rs.getInt("MaPD")));
                lb_NgayThang.setText(String.valueOf(rs.getDate("DateBook")));
                lb_NhanVien.setText(rs.getString("NguoiTao"));
                lb_GioRa.setText(String.valueOf(rs.getTime("TimeThanToan")));
                lb_HoTen.setText(rs.getString("HoTen"));
                lb_SDT.setText(rs.getString("SDT"));
                lb_SoNguoi.setText(String.valueOf(rs.getInt("SoNguoi")));
            }
            LoadTableMeNu();
            TongTienHD();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_HoaDon;
    private javax.swing.JButton btn_HoaDon1;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_ThuNhap;
    private javax.swing.JButton btn_ThuNhap1;
    private javax.swing.JButton btn_Tim;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lb_DoanhThu;
    private javax.swing.JLabel lb_GioRa;
    private javax.swing.JLabel lb_HoTen;
    private javax.swing.JLabel lb_MaHD;
    private javax.swing.JLabel lb_MaPD;
    private javax.swing.JLabel lb_NgayThang;
    private javax.swing.JLabel lb_NhanVien;
    private javax.swing.JLabel lb_SDT;
    private javax.swing.JLabel lb_SoNguoi;
    private javax.swing.JLabel lb_TongTien;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tbl_HoaDon;
    private javax.swing.JTable tbl_Mon;
    private javax.swing.JTable tbl_ThongKe;
    private javax.swing.JTable tbl_ThongKe1;
    private javax.swing.JTextField txt_Nam;
    // End of variables declaration//GEN-END:variables
}
