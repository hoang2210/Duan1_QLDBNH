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
import java.util.Date;
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

    public List<BanAn> selectSoBan(String datebook, String timebook) {
        String sql = "select SoBan from BanAn where SoBan\n"
                + "    not in (Select ba.SoBan from BanAn ba join PhieuDat pd on ba.SoBan = pd.SoBan\n"
                + "			where pd.DateBook = ? and pd.TimeBook = ?)";
        return select(sql, datebook, timebook);
    }
}
