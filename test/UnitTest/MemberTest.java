/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnitTest;
import Utils.BookServices;
import Utils.JDBCconn;
import Utils.MemberServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.Book;
import pojo.Member;

/**
 *
 * @author NGUYEN_NGUYEN
 */
public class MemberTest {
    
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
          Logger.getLogger(MemberTest.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Test
    public void testFieldNotNull() {
       
        try {
            List<Member> expResult = MemberServices.getMembers(null);
            List<Member> result = MemberServices.getMembers("");
            Assert.assertEquals(expResult.size(), result.size());
           
        } catch (SQLException ex) {
            Logger.getLogger(MemberTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
       @Test
       public void testSearch(){
           String s = "  qq ";
      try {
          List<Member> list = MemberServices.Search(s);
//          assertEquals(1, list.size());
          for(Member m : list){
            
          }
      } catch (SQLException ex) {
          Logger.getLogger(MemberTest.class.getName()).log(Level.SEVERE, null, ex);
      }
       }
       @Test
       public void addM(){
           Member m = new Member("M01", "a", "Man", "14/02/2000", "Sinh viên", "IT", "email", "A", "00");
           try{
               Assert.assertTrue(MemberServices.addMember(m));
           }catch (SQLException ex) {
          Logger.getLogger(MemberTest.class.getName()).log(Level.SEVERE, null, ex);
      }
       }
   
   
   
        
    

 
    
}
