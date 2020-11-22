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
    @FXML TableColumn tc1;
    @FXML TableColumn tc2;
    @FXML TableColumn tc3;
    @FXML TableColumn tc4;
    @FXML TableColumn tc5;
    @FXML TableColumn tc6;
    @FXML TableColumn tc7;
    @FXML TableColumn tc8;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
            //load books
        try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
    }    
    
//    @FXML
//    private void onOpen(ActionEvent event) {
//        try {
//            Connection conn=JDBCconn.getConnection();
//            data=FXCollections.observableArrayList();
//            ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM book");
//            while (rs.next()) {                
//                data.add(new Book(rs.getString(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4), rs.getString(5), 
//                        rs.getString(6), rs.getString(7), rs.getString(8)));
//            }
//        } catch (SQLException ex) {
//            System.err.println("Error"+ex);
//        }
//       tc1.setCellValueFactory(new PropertyValueFactory("id"));
//       tc2.setCellValueFactory(new PropertyValueFactory("masach"));
//       tc3.setCellValueFactory(new PropertyValueFactory("tensach"));
//       tc4.setCellValueFactory(new PropertyValueFactory("tacgia"));
//       tc5.setCellValueFactory(new PropertyValueFactory("mota"));
//       tc6.setCellValueFactory(new PropertyValueFactory("namxuatban"));
//       tc7.setCellValueFactory(new PropertyValueFactory("ngaynhapsach"));
//       tc8.setCellValueFactory(new PropertyValueFactory("vitri"));
//       
//      tbBook.setItems(null);
//      tbBook.setItems(data);
//    }
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
