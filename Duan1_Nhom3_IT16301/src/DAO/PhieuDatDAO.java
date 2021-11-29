package DAO;

import Helper.JdbcHelper;
import Model.PhieuDat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhieuDatDAO {

    private PhieuDat readFromResultSet(ResultSet rs) throws SQLException {
        PhieuDat model = new PhieuDat();
        model.setMaPD(rs.getInt("MaPD"));
        model.setMaKH(rs.getInt("MaKH"));
        model.setDateBook(rs.getDate("DateBook"));
        model.setTimeBook(rs.getTime("TimeBook"));
        model.setSoNguoi(rs.getInt("SoNguoi"));
        model.setTimeXacNhan(rs.getTime("TimeXacNhan"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setTrangThai(rs.getString("TrangThai"));

        return model;
    }

    private List<PhieuDat> select(String sql, Object... args) {
        List<PhieuDat> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * Thêm mới thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public void insert(PhieuDat entity) {
        String sql = "INSERT INTO PhieuDat (MaKH, DateBook, TimeBook, SoNguoi, TimeXacNhan, GhiChu, TrangThai) VALUES (?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaKH(),
                entity.getDateBook(),
                entity.getTimeBook(),
                entity.getSoNguoi(),
                entity.getTimeXacNhan(),
                entity.getGhiChu(),
                entity.getTrangThai());
    }

    /**
     * Cập nhật thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update1(PhieuDat entity) {
        String sql = "UPDATE PhieuDat SET DateBook=?, TimeBook=?, SoNguoi=?, GhiChu=?, TimeXacNhan=? ,TrangThai=? WHERE MaPD=?";
        JdbcHelper.executeUpdate(sql,
                entity.getDateBook(),
                entity.getTimeBook(),
                entity.getSoNguoi(),
                entity.getGhiChu(),
                entity.getTimeXacNhan(),
                entity.getTrangThai(),
                entity.getMaPD());
    }
    public void update(PhieuDat entity) {
        String sql = "UPDATE PhieuDat SET DateBook=?, TimeBook=?, SoNguoi=?, GhiChu=? WHERE MaPD=?";
        JdbcHelper.executeUpdate(sql,
                entity.getDateBook(),
                entity.getTimeBook(),
                entity.getSoNguoi(),
                entity.getGhiChu(),
                entity.getMaPD());
    }

    /**
     * Xóa bản ghi khỏi CSDL
     *
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(int id) {
        String sql = "DELETE FROM PhieuDat WHERE MaPD=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    /**
     * Truy vấn tất cả các các thực thể
     *
     * @return list danh sách các thực thể
     */
    public List<PhieuDat> select() {
        String sql = "SELECT * FROM PhieuDat\n"
                + "WHERE TrangThai =N'Chờ Xác Nhận'";
        return select(sql);
    }
        public List<PhieuDat> selectDPH() {
        String sql = "SELECT * FROM PhieuDat\n"
                + "WHERE TrangThai =N'Đã Xác Nhận '";
        return select(sql);
    }
            public List<PhieuDat> selectDTT() {
        String sql = "SELECT * FROM PhieuDat\n"
                + "WHERE TrangThai =N'Đã Thanh Toán'";
        return select(sql);
    }
    public List<PhieuDat> select(int id) {
        String sql = "SELECT * FROM PhieuDat WHERE MaKH=?";
        return select(sql, id);
    }
    /**
     * Truy vấn thực thể theo mã id
     *
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public PhieuDat findById(int id) {
        String sql = "SELECT * FROM PhieuDat WHERE MaPD=?";
        List<PhieuDat> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

//    public PhieuDat findByUser(String user) {
//        String sql = "SELECT * FROM PhieuDat WHERE Username=?";
//        List<PhieuDat> list = select(sql, user);
//        return list.size() > 0 ? list.get(0) : null;
//    }
}
