/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Helper.JdbcHelper;
import Model.KhachHang;
import Model.LoaiMon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class LoaiMonDAO {
    private LoaiMon readFromResultSet(ResultSet rs) throws SQLException {
        LoaiMon model = new LoaiMon();
        model.setMaLoaiMon(rs.getString("MaLoaiMon"));
        model.setTenLoaiMon(rs.getString("TenLoaiMon"));

        return model;
    }

    private List<LoaiMon> select(String sql, Object... args) {
        List<LoaiMon> list = new ArrayList<>();
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
    public List<LoaiMon> select() {
        String sql = "SELECT * FROM LoaiMon";
        return select(sql);
    }
}
