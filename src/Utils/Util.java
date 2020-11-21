/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import pojo.Book;

/**
 *
 * @author LocNe
 */
public class Util {
    public static void addorUpdateBook(Book book,String sql) throws SQLException{
     Connection conn = JDBCconn.getConnection();
        
         
         conn.setAutoCommit(false);
         
         //add Question
         PreparedStatement stm = conn.prepareStatement(sql);
         stm.setString(1, book.getId());
         stm.setString(2, book.getMa());
         stm.setString(3, book.getTenSach());
         stm.setString(4, book.getTacGia());
         stm.setString(5, book.getMoTa());
         stm.setString(6, book.getNamXuatBan());
         stm.setString(7, book.getNgayNhap());
         stm.setString(8, book.getViTri());
         
         
         stm.executeUpdate();
         
         
         conn.commit();
         }
     public static void addBook(Book book) throws SQLException{
         
            String sql=  "INSERT INTO book(id,masach,tensach,tacgia,motasach,"
                       + "namxuatban,ngaynhapsach,vitri) VALUES(?,?,?,?,?,?,?,?)";
            addorUpdateBook(book, sql);
     }
}
