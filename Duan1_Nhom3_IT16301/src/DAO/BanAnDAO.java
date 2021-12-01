/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.JdbcHelper;
import Model.BanAn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BanAnDAO {

    private BanAn readFromResultSet(ResultSet rs) throws SQLException {
        BanAn model = new BanAn();
        model.setSoBan(rs.getInt("SoBan"));
        model.setTrangThaiHD(rs.getString("TrangThaiHD"));
        return model;
    }

    private List<BanAn> select(String sql, Object... args) {
        List<BanAn> list = new ArrayList<>();
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

    public List<BanAn> select() {
        String sql = "SELECT * FROM BanAn";
        return select(sql);
    }
        public BanAn findById(int TimeBook) {
        String sql = "	select * from BanAn where SoBan \n"
                + "	not in (Select hd.SoBan from BanAn ba \n"
                + "	join HoaDon hd on ba.SoBan = hd.SoBan\n"
                + "	join PhieuDat pd on hd.MaPD = pd.MaPD\n"
                + "	where pd.TimeBook =? and pd.DateBook = ?) ";
        List<BanAn> list = select(sql, TimeBook);
        return list.size() > 0 ? list.get(0) : null;
    }
        public BanAn findById1(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHD=?";
        List<BanAn> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
}
