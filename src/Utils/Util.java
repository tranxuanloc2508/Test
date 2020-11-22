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
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import pojo.Book;
import pojo.Member;

/**
 *
 * @author LocNe
 */
public class Util {
    
    public static List<Book> getBooks (String keyword) throws SQLException{
        String sql = "SELECT * FROM book ";
//        if(!keyword.isEmpty())
//            sql+="WHERE id like?";Where id=?,masach=?,tensach=?,tacgia=?,motasach=?,namxuatban=?,ngaynhapsach=?,vitri=?
        
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
//        if(!keyword.isEmpty())
//           stm.setString(1, keyword);
//            stm.setString(2, String.format("%%%$%%", keyword));
        
        ResultSet rs = stm.executeQuery();
        
        List<Book> books= new ArrayList<>();
        while(rs.next()){
            Book q = new Book(rs.getString("id"),rs.getString("masach"),
                    rs.getString("tensach"),rs.getString("tacgia"),
                    rs.getString("motasach"),rs.getString("namxuatban"),
                    rs.getString("ngaynhapsach"),rs.getString("vitri"));
            books.add(q);
        }
        
        return books;
        
    }
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

    public static void addM(Member b, String s) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        
         
         conn.setAutoCommit(false);
         
         //add Question
         PreparedStatement stm = conn.prepareStatement(s);
         stm.setString(1, b.getMa());
         stm.setString(2, b.getHoten());
         stm.setString(3, b.getGioitinh());
         stm.setString(4, b.getNgaysinh());
         stm.setString(5, b.getDoituong());
         stm.setString(6, b.getBophan());
         stm.setString(7, b.getHanthe());
         stm.setString(8, b.getEmail());
         stm.setString(9, b.getDiachi());
         stm.setString(10, b.getSdt());

         stm.executeUpdate();
                  
         conn.commit();
         
    }
    public static void addMember(Member m) throws SQLException{
          String sql=  "INSERT INTO member(id,madocgia,hoten,gioitinh,ngaysinh,"
                       + "doituong,bophan,hanthe,email) VALUES(?,?,?,?,?,?,?,?,?,?)";
            addM(m, sql);
    }
//    
    public static void delB(Book book , String sql) throws SQLException{
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
//     public static void delBook(Book book) throws SQLException{
//         
//            String sql=  "DELETE FROM book WHERE Id=id,masach,tensach,tacgia,motasach,"
//                       + "namxuatban,ngaynhapsach,vitri) ";
//            delB(book, sql);
//     }
     
}
