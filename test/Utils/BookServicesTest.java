/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pojo.Book;

/**
 *
 * @author LocNe
 */
public class BookServicesTest {
    
    public BookServicesTest() {
    }
    
    private static Connection conn;
    
    @BeforeAll
    public static void setUp() {
        conn = JDBCconn.getConnection();
    }
    
    @AfterAll
    public static void tearDown() {
        try {
            JDBCconn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(BookServicesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//     @Test
//    public void testDeleteSuccessful() {
//        int questionId = 64;
//        
//        try {
//            boolean kq = BookServices.delBook(questionId);
//            Assert.assertTrue(kq);
//            
//            Book b = BookServices.getBooks(" ");
//            Assert.assertTrue(b);
//            
//            List<Book> choices = BookServices.getBooks("");
//            Assert.assertEquals(3, choices.size());
//        } catch (SQLException ex) {
//            Logger.getLogger(BookServicesTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
}
