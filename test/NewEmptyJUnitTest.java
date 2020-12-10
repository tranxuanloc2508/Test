/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Utils.BookServices;
import Utils.JDBCconn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;
import pojo.Book;

/**
 *
 * @author LocNe
 */
public class NewEmptyJUnitTest {
private static Connection connect;
    
    @BeforeEach
    public static void setUp() {
        connect = JDBCconn.getConnection();
    }
    
    @AfterEach
    public static void tearDown() {
        try {
            JDBCconn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowInforTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetListBook(){
        try {
            List<Book> list = BookServices.getBook();
            
            Assert.assertEquals(2, list.size());
            System.err.println("Test get list book successfully!");
            
        } catch (SQLException ex) {
            System.err.println("Test get list book unsuccessfully!");
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testCheckBookByName() {
        try {
            String name = BookServices.checkBook("Ma´t Biê´c");
            Assert.assertNotEquals("", name);
            Assert.assertNotNull(name);
            
            System.err.println("Check book by name successfully!");
        } catch (SQLException ex) {
            System.err.println("Check book by name unsuccessfully!");
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Test    
    public void testChangeState() {
        String state = null;
        try {
            BookServices.addorUpdateBook("123", "Borrowed");
            List<Book> list = BookServices.getBooks();
            for (Book b : list) {
                if (b.getId().equals("123")) {
                    state = b.getState();
                }
            }
            
            Assert.assertEquals("Borrowed", state);
            System.err.println("Change state by id book successfully!");
        } catch (SQLException ex) {
            System.err.println("Change state by id book unsuccessfully!");
            Logger.getLogger(BookTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    @BeforeAll
//    public static void setUpClass() {
//        System.out.println("Gọi trước tất cả TC.");
//    }
//
//    @AfterAll
//
//    public static void tearDownClass() {
//        System.out.println("Gọi sau tất cả TC.");
//    }
//
//    @BeforeEach
//    public void setUp() {
//        System.out.println("Gọi trước mỗi TC.");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        System.out.println("Gọi sau mỗi TC.");
//    }
//
    @BeforeClass
    public static void start() {
        System.out.println("Start");
    }

    @Before
    public void startTest() {
        System.out.println("Start Test case");
    }

    @After
    public void endTest() {
        System.out.println("End Test case");
    }

    @AfterClass
    public static void end() {
        System.out.println("End");
    }

    @Test
    public void test1() {
        System.out.println("Test case 01");
    }

    @Test
    public void test2() {
        System.out.println("Test case 02");
    }
}
