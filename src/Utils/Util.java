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
import javafx.scene.control.Alert;
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
//            sql+="WHERE tensach like?";
            
        
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
//        if(!keyword.isEmpty())
//           stm.setString(1, keyword);
//            stm.setString(1, String.format("%%%s%%", keyword));
            
        
        ResultSet rs = stm.executeQuery();
        
        List<Book> books= new ArrayList<>();
        while(rs.next()){
            Book q = new Book(rs.getInt("id"),rs.getString("masach"),
                    rs.getString("tensach"),rs.getString("tacgia"),
                    rs.getString("motasach"),rs.getString("namxuatban"),
                    rs.getString("ngaynhapsach"),rs.getString("vitri"));
            books.add(q);
        }
        
        return books;
        
    }
     public static List<Member> getMembers (String keyword) throws SQLException{
        String sql = "SELECT * FROM thedocgia ";
//        if(!keyword.isEmpty())
//            sql+="WHERE tensach like?";
            
        
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
//        if(!keyword.isEmpty())
//           stm.setString(1, keyword);
//            stm.setString(1, String.format("%%%s%%", keyword));
            
        
        ResultSet rs = stm.executeQuery();
        
        List<Member> books= new ArrayList<>();
        while(rs.next()){
            String id = rs.getString("id");
            String ma = rs.getString("madocgia");
            String hoten = rs.getString("hoten");
            String gioitinh = rs.getString("gioitinh");
            String ngaysinh = rs.getString("ngaysinh");
            String doituong = rs.getString("doituong");
            String bophan = rs.getString("bophan");
            String hanthe = rs.getString("hanthe");
            String email = rs.getString("email");
            String diachi = rs.getString("diachi");
            String sdt = rs.getString("sdt");
            
            Member q = new Member(ma, hoten, gioitinh, ngaysinh, doituong, bophan, hanthe, email, diachi, sdt);
            
            books.add(q);
            
            
            
        }
        
        return books;
        
    }
    public static void addorUpdateBook(Book book,String sql) throws SQLException{
     Connection conn = JDBCconn.getConnection();
        
         
         conn.setAutoCommit(false);
         
         //add Question
         PreparedStatement stm = conn.prepareStatement(sql);
         //stm.setString(1, book.getId()));
         stm.setString(1, book.getMa());
         stm.setString(2, book.getTenSach());
         stm.setString(3, book.getTacGia());
         stm.setString(4, book.getMoTa());
         stm.setString(5, book.getNamXuatBan());
         stm.setString(6, book.getNgayNhap());
         stm.setString(7, book.getViTri());
         
         
         stm.executeUpdate();
         
         
         conn.commit();
         }
     public static void addBook(Book book) throws SQLException{
         
            String sql=  "INSERT INTO book(masach,tensach,tacgia,motasach,"
                       + "namxuatban,ngaynhapsach,vitri) VALUES(?,?,?,?,?,?,?)";
            addorUpdateBook(book, sql);
     }
     
     public static void addM(Member b, String s) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        
         
         conn.setAutoCommit(false);
         
     
         PreparedStatement stm = conn.prepareStatement(s);
         stm.setString(1, b.getMa());
         stm.setString(2, b.getHoten());
         stm.setString(3, b.getGioitinh());
         stm.setString(4, b.getNgaysinh());
         stm.setString(5, b.getDoituong());
         stm.setString(6, b.getBophan());
         stm.setString(7, b.getHanthe());
         stm.setString(8, b.getDiachi());
         stm.setString(9, b.getEmail());
         stm.setString(10, b.getSdt());

         stm.executeUpdate();
                  
         conn.commit();
         
    }
    public static void addMember(Member m) throws SQLException{
          String sql=  "INSERT INTO thedocgia(madocgia,hoten,gioitinh,ngaysinh,"
                       + "doituong,bophan,hanthe,email,diachi,sdt)"
                  + " VALUES(?,?,?,?,?,?,?,?,?,?)";
            addM(m, sql);
    }
//    
    public static boolean delBook(int bookId) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        String sql = "DELETE FROM book WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, bookId);
        
        int kq = stm.executeUpdate();
        
        return kq > 0;
    }
    public static List<Book> Search (String keyword) throws SQLException{
        List<Book> books= new ArrayList<>();
        String sql = "SELECT * FROM book ";
        if(keyword != null && !keyword.isEmpty())
            {
                sql +="WHERE masach like?" +"or tensach like?"+"or tacgia like?"+"or namxuatban like?"+"or vitri like?";  
            }
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        if(keyword != null && !keyword.trim().isEmpty())
            
           try {
            stm.setString(1, String.format("%%%s%%", keyword.trim()));
            stm.setString(2, String.format("%%%s%%", keyword.trim()));
            stm.setString(3, String.format("%%%s%%", keyword.trim()));
            stm.setString(4, String.format("%%%s%%", keyword.trim()));
            stm.setString(5, String.format("%%%s%%", keyword.trim()));
        } catch (SQLException sQLException) {}
       
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            Book q = new Book(rs.getInt("id"),rs.getString("masach"),
                    rs.getString("tensach"),rs.getString("tacgia"),
                    rs.getString("motasach"),rs.getString("namxuatban"),
                    rs.getString("ngaynhapsach"),rs.getString("vitri"));
            books.add(q);
        }   
        return books;
    }
    public static Alert getAlertInfo(String content, Alert.AlertType type){
         Alert a = new Alert(type);
         a.setContentText(content);
         
         return a;
     }
     
}
