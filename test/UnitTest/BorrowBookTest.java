/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTest;

import static UnitTest.BookTest.conn;
import Utils.BookServices;
import Utils.BorrowServices;
import Utils.JDBCconn;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo.Borrow;

/**
 *
 * @author LocNe
 */
public class BorrowBookTest {

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

    @Test
    public void borrowBook() throws ParseException {
        Borrow b = new Borrow(2, 2);
        try {
            Assert.assertTrue(BorrowServices.addBorrow(b));
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void returnBook() throws ParseException, SQLException {
        
        Borrow b1 = new Borrow("14/12/2020","20/01/2021",2,106,2 );
//        Borrow b1 = new Borrow("14/12/2020","01/01/2021",2,102,2 );
        
        try {
//            boolean list = BorrowServices.returnB(b, b.getNgaymuon(), b.getNgaytra());
                Assert.assertTrue(BorrowServices.returnB(b1, b1.getNgaytra(), b1.getNgaymuon()));
            
        } catch (SQLException ex) {
            Logger.getLogger(BookTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
