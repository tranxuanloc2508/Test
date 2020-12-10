

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
import java.util.UUID;
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
private static Connection conn;
    
    @BeforeClass
    public static void start() {
        conn = JDBCconn.getConnection();
    }

   

    @AfterClass
    public static void end() {
         try {
            JDBCconn.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    @Test
    public void testa(){
        String id = "";
        
    }
    @Test
    public void test() {
        try {
            List<Book> cats = BookServices.getBooks("");
            
            for (Book c: cats) {
                Assert.assertNotNull(c.getTenSach());
                Assert.assertNotEquals(" ", c.getTenSach().trim());
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void test2() {
        try {
            List<Book> cats = BookServices.getBooks("");
            Assert.assertTrue(cats.size() >= 5);
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testNoFilters2() {
        try {
            List<Book> kq1 = BookServices.getBooks(null);
            List<Book> kq2 = BookServices.getBooks("   ");
            
            Assert.assertEquals(kq1.size(), kq2.size());
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @Test
    public void testNoFilters() {
        try {
            List<Book> kq1 = BookServices.getBooks(null);
            List<Book> kq2 = BookServices.getBooks("");
            
            Assert.assertEquals(kq1.size(), kq2.size());
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testFilterQuestion() {
        String kw = "  a  ";
        try {
            
            List<Book> q = BookServices.Search(kw);
            
            Assert.assertEquals(q.size(), q.size());
            for (Book que: q){
                Assert.assertTrue(que.getTenSach().contains(kw.trim()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
//    @Test(expected = NullPointerException.class)
//    public void testQuestionContestIsEmpty() {
//        Book q = new Book(UUID.randomUUID().toString(), "", new Category(1, "abc"));
//        QuestionServices.addQuestion(q, null);
//    }
}
