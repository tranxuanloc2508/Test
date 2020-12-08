/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.sun.javafx.binding.StringFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import pojo.Borrow;

/**
 *
 * @author USER
 */
public class BorrowServices {

    public static void addBorrow(Borrow b) throws SQLException, ParseException {
        String sql = "INSERT INTO bookdocgia(id,idbook,iddocgia,ngaymuon,ngaytra) VALUES (?,?,?,?,?)";
        Connection conn = JDBCconn.getConnection();
        
        String c= getDateNow();
        String d= getDateNow();
        Random so = new Random();
        
        int rd = so.nextInt(35);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(c));
        cal.add(Calendar.DATE, rd);
        d = sdf.format(cal.getTime());  // dt is now the new date
        
//        double fee= Fee(datec, d);


        conn.setAutoCommit(false);
        //add Question
        PreparedStatement stm = conn.prepareStatement(sql);

        stm.setInt(1, b.getId());
        stm.setInt(2, b.getIdbook());
        stm.setInt(3, b.getIddocgia());
        stm.setString(4, c);
        stm.setString(5, d);

        stm.executeUpdate();
        
        conn.commit();
    }

//    public static void browBook(Book b, Member m) throws SQLException {
//////        String sql1 = "SELECT * FROM b";
//////        String sql2 = "SELECT * FROM thedocgia ";
////         addB(b,sql);
////    }
    public static String getDateNow(){
        Date date= new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        
        return format.format(date);
        
    }
    public static double Fee(Date a, Date b){
        int c= (int) a.getTime();
        int d= (int) b.getTime();
        
        int chenhlech= d-c;
        return chenhlech;
        
        
    }
}
