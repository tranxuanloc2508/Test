/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.ComBoBox;

/**
 *
 * @author LocNe
 */
public class gioiTinh {
    public static ComBoBox getGioiTinhBysId(int id) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        String q = "SELECT * FROM gioitinh WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(q);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        while (rs.next())
            return new ComBoBox(rs.getInt("id"), rs.getString("name"));
        
        return null;
    }
    public static List<ComBoBox> getGioiTinhs() throws SQLException {
        Connection conn = JDBCconn.getConnection();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM gioitinh");
        
        List<ComBoBox> kq = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            ComBoBox c = new ComBoBox(id, name);
            
            kq.add(c);
        }
        
        return kq;
    }
}
