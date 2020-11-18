/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class UserController implements Initializable {

    @FXML Label lb_close;
    @FXML TextField txtUsername;
    @FXML TextField txtPassword;
    @FXML Button btnSignIn;
    
    public void handleButtonAction(MouseEvent event){
        if(event.getSource()==lb_close){
            System.exit(0);
        }
        if(event.getSource()==btnSignIn){
        
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //-- 
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet =null;
    private void logIn(){
    
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        
        String sql = "SELECT * FROM users WHERE username  = ? and password = ? ";
    }
}
