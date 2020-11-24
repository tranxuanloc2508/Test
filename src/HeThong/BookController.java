/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.JDBCconn;
import Utils.Util;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Book;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class BookController implements Initializable {
    private ObservableList<Book>data;
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
    @FXML TextField txtkeyword;
    @FXML TableView<Book> tbBook;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
            //load books
        try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //Search bôk
        
    }    
    
    @FXML
    public void addBook(ActionEvent event){
//        String bookId = UUID.randomUUID().toString();
        Book b= new Book(this.txtid.getText(), this.txtma.getText(), this.txtten.getText(), 
                txttacGia.getText(), txtmota.getText(), txtNXB.getText(), 
                txtNgayNhapSach.getText(), txtViTri.getText());
        
        
        try {
            Utils.Util.addBook(b);
            this.tbBook.getItems().clear();
            this.tbBook.setItems(FXCollections.observableArrayList(Util.getBooks("")));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add Book succsessful!!!!");
            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add Book failed!!!!" + ex.getMessage());
            alert.show();
        }
    }
    private void loadBook() throws SQLException {
       
        TableColumn clid= new TableColumn("id ");
        TableColumn clma= new TableColumn("masach ");
        TableColumn clten= new TableColumn("ten ");
        TableColumn cltg= new TableColumn("tac gia ");
        TableColumn clmota= new TableColumn("mo ta ");
        TableColumn clnam= new TableColumn("nam xua ban ");
        TableColumn clnhap= new TableColumn("ngay nhap ");
        TableColumn clvitri= new TableColumn("vi tri ");
        
        
        clid.setCellValueFactory(new PropertyValueFactory("id"));
        clma.setCellValueFactory(new PropertyValueFactory("ma"));
        clten.setCellValueFactory(new PropertyValueFactory("tenSach"));
        cltg.setCellValueFactory(new PropertyValueFactory("tacGia"));
        clmota.setCellValueFactory(new PropertyValueFactory("moTa"));
        clnam.setCellValueFactory(new PropertyValueFactory("namXuatBan"));
        clnhap.setCellValueFactory(new PropertyValueFactory("ngayNhap"));
        clvitri.setCellValueFactory(new PropertyValueFactory("viTri"));
//        clContent.setPrefWidth(200);
        
        this.tbBook.getColumns().addAll(clid,clma,clten,cltg,clmota,clnam,clnhap,clvitri);
        this.tbBook.setItems(FXCollections.observableArrayList(Util.getBooks("")));
        
            
                    
    } 
}
