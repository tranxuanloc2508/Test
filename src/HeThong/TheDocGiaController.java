/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.gioiTinh;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pojo.ComBoBox;
import pojo.Member;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class TheDocGiaController implements Initializable {

    @FXML GridPane grPand;
    @FXML TextField txtMa;
    @FXML TextField txtHoten;
//    @FXML TextField txtGioiTinh;
    @FXML TextField txtNgaySinh;
    @FXML TextField txtDoiTuong;
    @FXML TextField txtBoPhan;
    @FXML TextField txtHanThe;
    @FXML TextField txtEmail;
    @FXML TextField txtDiaChi;
    @FXML TextField txtSdt;
    @FXML Button btnSave;
    @FXML ComboBox<ComBoBox> cbGioiTinh;
    
    //ObservableList<String> list = FXCollections.observableArrayList("Male","Femail","Orther");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //cbGioiTinh.setItems(list);
        this.cbGioiTinh.prefWidthProperty().bind(this.grPand.widthProperty());
         try {
            // TODO
            cbGioiTinh.getItems().addAll(gioiTinh.getGioiTinhs());
//            loadQuestions();
//            loadData(""
                    } catch (SQLException ex) {
            Logger.getLogger(TheDocGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void addMember(ActionEvent event){

//        String mId = UUID.randomUUID().toString();
        Member b= new Member(this.txtMa.getText(), this.txtHoten.getText(),
                cbGioiTinh.getSelectionModel().getSelectedItem().getId(),
                txtNgaySinh.getText(), txtDoiTuong.getText(), txtBoPhan.getText(),
                txtHanThe.getText(), txtDiaChi.getText(), txtDiaChi.getText(),
                txtSdt.getText());
        
//        cbGioiTinh.getSelectionModel().getSelectedItem()
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
//      private void loadData(String kw) throws SQLException {
//        tbQuestions.getItems().clear();
//        tbQuestions.setItems(FXCollections.observableArrayList(QuestionServices.getQuestions(kw)));
//    }

}
