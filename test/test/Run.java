/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.mysql.cj.xdevapi.Result;
import Utils.JDBCconn;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;


/**
 *
 * @author USER
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        org.junit.runner.Result result = JUnitCore.runClasses(BookServicesTester.class);

        if (!result.wasSuccessful()) {
            result.getFailures().forEach((failure) -> {
                System.err.println(failure);
            });
        } else {
            System.out.println("success");
        }
        // TODO code application logic here

    }
}
