/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pojo.Book;
import pojo.Member;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class TheDocGiaController implements Initializable {

    @FXML TextField txtMa;
    @FXML TextField txtHoten;
    @FXML TextField txtGioiTinh;
    @FXML TextField txtNgaySinh;
    @FXML TextField txtDoiTuong;
    @FXML TextField txtBoPhan;
    @FXML TextField txtHanThe;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
    @FXML TextField txtSdt;
    @FXML Button btnSave;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void addMember(ActionEvent event){
//        String bookId = UUID.randomUUID().toString();
        Member b= new Member(this.txtMa.getText(), this.txtHoten.getText(), this.txtGioiTinh.getText(), 
                txtNgaySinh.getText(), txtDoiTuong.getText(), txtBoPhan.getText(), 
                txtHanThe.getText(), txtDiaChi.getText(), txtDiaChi.getText(),txtSdt.getText());
        
        
        try {
            Utils.Util.addMember(b);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add member succsessful!!!!");
            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add member failed!!!!" + ex.getMessage());
            alert.show();
        }
    }

}
