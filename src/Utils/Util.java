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
import pojo.Book;
import pojo.ComBoBox;
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
            int gioitinh = rs.getInt("gioitinh");
            String ngaysinh = rs.getString("ngaysinh");
            String doituong = rs.getString("doituong");
            String bophan = rs.getString("bophan");
            String hanthe = rs.getString("hanthe");
            String email = rs.getString("email");
            String diachi = rs.getString("diachi");
            String sdt = rs.getString("sdt");
            ComBoBox c = new ComBoBox(gioitinh);
            
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
         stm.setInt(3, b.getGioitinhID());
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
                       + "doituong,bophan,hanthe,email,diachi,sdt) VALUES(?,?,?,?,?,?,?,?,?,?)";
            addM(m, sql);
    }
//    
    public static boolean deleteQuestion(String questionId) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        String sql = "DELETE FROM book WHERE id=?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1, questionId);
        
        int kq = stm.executeUpdate();
        
        return kq > 0;
    }
    public static List<Book> Search (String keyword) throws SQLException{
        String sql = "SELECT * FROM book ";
        if(!keyword.isEmpty())
            sql+="WHERE tensach like?";
        
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        if(!keyword.isEmpty())
//           stm.setString(1, keyword);
            stm.setString(1, String.format("%%%s%%", keyword));
        
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
     
}
