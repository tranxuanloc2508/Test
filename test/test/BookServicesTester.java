/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Utils.BookServices;
import static Utils.BookServices.Search;
import Utils.JDBCconn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.Assert;
import pojo.Book;


/**
 *
 * @author USER
 */
public class BookServicesTester {
    
            private static Connection conn;
    
    @BeforeClass
    public static void setUp() {
        conn = JDBCconn.getConnection();
    }
    
    @AfterClass
    public static void tearDown() {
        try {
           JDBCconn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookServicesTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//        @BeforeClass
//    public static void start() {
//        System.out.println("Start");
//    }
//
//    @Before
//    public void startTest() {
//        System.out.println("Start Test case");
//    }
//
    @Test
    public void testAddSuccessful() throws SQLException {
        Book b = new Book(" ma", "ten", "tacgia", "mota", "2000", "12/07/2000", "A12");
        try {
            boolean kq = BookServices.addBook(b);

            Assert.assertTrue(kq);
            System.out.println("Test case 1");

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    @Test
    public void testIsNull(){
        Book b = new Book(" ma", "ten", "", "mota", "2000", "12/07/2000", "A12");
           try {
            boolean kq = BookServices.addBook(b);

            Assert.assertFalse(kq);
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
    @Test
    public void testSearchSuccesful() throws SQLException{
        List<Book> list= BookServices.Search("b");
        
        Assert.assertEquals(2, list.size());
    }
//
//    @Test
//     public void test2() {
//        System.out.println("Test case 2");
//    }
//
//     @After
//    public void end() {
//        System.out.println("End");
//    }
//    
//    @AfterClass
//    public static void endTest() {
//        System.out.println("End Test case");
//    }
    
    
}
