/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTest;

import Utils.BookServices;
import Utils.JDBCconn;
import java.security.CryptoPrimitive;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import pojo.Book;

/**
 *
 * @author NGUYEN_NGUYEN
 */
public class BookTest {

    public static Connection conn;

    @BeforeClass
    public static void setUpClass() {
        conn = JDBCconn.getConnection();
    }

    @AfterClass
    public static void tearDownClass() {
        try {
            JDBCconn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Test TÌM KHIẾM

    @Test
    public void testTitleNotNull() {
        try {
            List<Book> list1 = BookServices.getBooks("");
            list1.forEach((b) -> {
                Assert.assertNotNull("A valid in Field", b.getTenSach());
            });
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testAuthorNotNull() {
        try {
            List<Book> list1 = BookServices.getBooks("");
            list1.forEach((b) -> {
                Assert.assertNotNull("A valid in Field", b.getTacGia());
            });
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

////    @Test
////    public void testSearchSpace() {
////        String key = "  q ";
////
////        try {
////            List<Book> list = BookServices.Search(key);
////            for (Book b : list) {
////                Assert.assertTrue(b.getTenSach().contains(key.trim()));
////            }
////        } catch (SQLException ex) {
////            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
////        }
////
////    }
    @Test
    public void testSearchContain() {
//        String key = " Lập ";
        String key = " giao";
        try {
            List<Book> list = BookServices.Search(key);

            for (Book b : list) {
                Assert.assertTrue(b.getTenSach().contains(key.trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testFieldNotNull() {
        try {
            List<Book> list1 = BookServices.getBooks("");
            List<Book> list2 = BookServices.getBooks(null);
            Assert.assertEquals(list1.size(), list2.size());
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testSearchUpper() {
        String key = "     LẬP TRÌNH  ";

        try {
            List<Book> list = BookServices.Search(key);
//            Assert.assertEquals(2, list.size());
            for (Book b : list) {
                Assert.assertTrue(b.getTenSach().toUpperCase().contains(key.trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testSearchLower() {
        String key = "     đồ họa ";

        try {
            List<Book> list = BookServices.Search(key);
//            Assert.assertEquals(2, list.size());
            for (Book b : list) {
                Assert.assertTrue(b.getTenSach().toLowerCase().contains(key.trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testSearch() {
        String key = "Lập trình giao diện";

        try {
            List<Book> list = BookServices.Search(key);
            for (Book b : list) {
                Assert.assertEquals(key, b.getTenSach());

            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testCharater() {
        String key = "Lap + trình";
//        String key = " 1  _   ";

        try {
            List<Book> list = BookServices.Search(key);
            for (Book b : list) {
                Assert.assertFalse(b.getTenSach().contains(key.trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // TEST ADD BOOK
    @Test
    public void testAddFieldIsEmpty() {
        Book b = new Book("KH231", "Mạng máy tính", "Lưu Quang Phương", "123 trang", "2014", "1/9/2014", "A176");
        Book b1 = new Book("", "", "", "", "", "", "");
        try {
                Assert.assertFalse(BookServices.addBook(b1));//Fields Empty
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testAddAllFieldNotEmpty() {
        Book b = new Book("KH231", "Mạng máy tính", "Lưu Quang Phương", "123 trang", "2014", "1/9/2014", "A176");
        try {
            Assert.assertTrue(BookServices.addBook(b));
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//      @Test
//     public  void testAddFieldContainEmpty(){
//       Book b = new Book("KH231", "Mạng máy tính","Lưu Quang Phương","", "2014","1/9/2014","A176");    
//        try {
//            Assert.assertFalse(BookServices.addBook(b));
//            System.out.println("Error");
//        } catch (SQLException ex) {
//            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       
//    }
    //TEST UPDATE BOOK

    @Test
    public void testUpDate() {
        Book b = new Book("KH231", "Mạng máy tính", "Lưu Quang Phương", "123 trang", "2014", "1/9/2014", "A176");
        Book b1 = new Book("KH231", "Mạng máy tínhhhh", "Lưu Quang Phương", "123 trang", "2014", "1/9/2014", "A176");
//         List<Book> list = BookServices.updateBook(b1);
        try {
            boolean list = BookServices.updateBook(b);
            Assert.assertTrue(BookServices.updateBook(b1));
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testDeleteBook() {
        int a = 74;
        try {
            boolean k = BookServices.delBook(a);
            assertTrue(k);
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
//        

    }
}
