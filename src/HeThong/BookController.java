/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.JDBCconn;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pojo.Book;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class BookController implements Initializable {

    @FXML TextField txtid;
    @FXML TextField txtma;
    @FXML TextField txtten;
    @FXML TextField txttacGia;
    @FXML TextField txtmota;
    @FXML TextField txtNXB;
    @FXML TextField txtNgayNhapSach;
    @FXML TextField txtViTri;
    @FXML Button btnLuu;
    @FXML Button btnThoat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addBook(ActionEvent event){
//        String bookId = UUID.randomUUID().toString();
        Book b= new Book(this.txtid.getText(), this.txtma.getText(), this.txtten.getText(), 
                txttacGia.getText(), txtmota.getText(), txtNXB.getText(), 
                txtNgayNhapSach.getText(), txtViTri.getText());
        
        
        try {
            Utils.Util.addBook(b);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add Question succsessful!!!!");
            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add Question failed!!!!" + ex.getMessage());
            alert.show();
        }
    }
            
//        String bookId= txtid.getText();
//        String bookMa= txtma.getText();
//        String bookTen= txtten.getText();
//        String bookTacGia= txttacGia.getText();
//        String bookMota= txtmota.getText();
//        String bookNXB= txtNXB.getText();
//        String bookNgayNhapSach= txtNgayNhapSach.getText();
//        String bookViTri= txtViTri.getText();
//        if(bookId.isEmpty()||bookMa.isEmpty()||bookTen.isEmpty()||
//                bookTacGia.isEmpty()||bookMota.isEmpty()||bookNXB.isEmpty()||
//                bookNgayNhapSach.isEmpty()||bookViTri.isEmpty())
//        {
//            Alert alert= new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Pleas Enter in all fields");
//            alert.showAndWait();
//            return;
//        }
            
//    public void addQuestionHandler(ActionEvent event){
//        String questionId = UUID.randomUUID().toString();
//        
//        
//        ArrayList<Book> choices = new ArrayList<>();
//        choices.add(new Choice(UUID.randomUUID().toString(),this.txtA.getText() , questionId));
//        
//        
//       try{
//            Utils.addQuestion(q, choices);
//            
//            this.tbQuestion.getItems().clear();
//            this.tbQuestion.setItems(FXCollections.observableArrayList(Utils.getQuestion("")));
//            
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setContentText("Add Question succsessful!!!!");
//            alert.show();
//       }catch(SQLException ex){
//           Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Add Question failed!!!!" + ex.getMessage());
//            alert.show();
//       }
//    }
//    
}
