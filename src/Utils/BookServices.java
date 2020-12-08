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
import pojo.Book;
import pojo.Member;

/**
 *
 * @author USER
 */
public class BookServices {

    public static void addorUpdateBook(Book book, String sql) throws SQLException {
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

    public static void addBook(Book book) throws SQLException {

        String sql = "INSERT INTO book(masach,tensach,tacgia,motasach,"
                + "namxuatban,ngaynhapsach,vitri) VALUES(?,?,?,?,?,?,?)";
        addorUpdateBook(book, sql);
    }

    public static void addB(Book book, String sql) throws SQLException {
        Connection conn = JDBCconn.getConnection();
        conn.setAutoCommit(false);
        //add Question
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setString(1, book.getMa());
        stm.setString(2, book.getTenSach());
        stm.setString(3, book.getTacGia());
        stm.setString(4, book.getMoTa());
        stm.setString(5, book.getNamXuatBan());
        stm.setString(6, book.getNgayNhap());
        stm.setString(7, book.getViTri());

        stm.executeUpdate();

    }

    public static void browBook(Book b, Member m) throws SQLException {
//        String sql1 = "SELECT * FROM book";
//        String sql2 = "SELECT * FROM thedocgia ";
        String sql = "INSERT INTO book-docgia(id,idbook,iddocgia) VALUES(?,?,?)";
         addB(b,sql);
    }

    public static List<Book> getBooks(String keyword) throws SQLException {
        String sql = "SELECT * FROM book ";
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();

        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book q = new Book(rs.getInt("id"), rs.getString("masach"),
                    rs.getString("tensach"), rs.getString("tacgia"),
                    rs.getString("motasach"), rs.getString("namxuatban"),
                    rs.getString("ngaynhapsach"), rs.getString("vitri"));
            books.add(q);
        }

        return books;

    }

    public static List<Book> Search(String keyword) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book ";
        if (keyword != null && !keyword.isEmpty()) {
            sql += "WHERE masach like?" + "or tensach like?" + "or tacgia like?" + "or namxuatban like?" + "or vitri like?";
        }
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        if (keyword != null && !keyword.trim().isEmpty())
            
           try {
            stm.setString(1, String.format("%%%s%%", keyword.trim()));
            stm.setString(2, String.format("%%%s%%", keyword.trim()));
            stm.setString(3, String.format("%%%s%%", keyword.trim()));
            stm.setString(4, String.format("%%%s%%", keyword.trim()));
            stm.setString(5, String.format("%%%s%%", keyword.trim()));
        } catch (SQLException sQLException) {
        }

        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            Book q = new Book(rs.getInt("id"), rs.getString("masach"),
                    rs.getString("tensach"), rs.getString("tacgia"),
                    rs.getString("motasach"), rs.getString("namxuatban"),
                    rs.getString("ngaynhapsach"), rs.getString("vitri"));
            books.add(q);
        }
        return books;
    }
}
