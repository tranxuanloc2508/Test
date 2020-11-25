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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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

    @FXML TextField txtma;
    @FXML TextField txtten;
    @FXML TextField txttacGia;
    @FXML TextField txtmota;
    @FXML TextField txtNXB;
    @FXML TextField txtNgayNhapSach;
    @FXML TextField txtViTri;
    @FXML Button btnLuu;
    @FXML Button btnThoat;
    @FXML TextField txttimkiem;
    @FXML TableView<Book> tbBook;
    @FXML Button btnXoa;
    
    
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
        this.txttimkiem.textProperty().addListener(et->{
             this.tbBook.getItems().clear();
             try {
                 this.tbBook.setItems(
                         FXCollections.observableArrayList(Util.Search(
                                 this.txttimkiem.getText())));
             } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
             }
         });
    }    
   
    @FXML
    public void addBook(ActionEvent event){
        
        Book b= new Book(this.txtma.getText(), this.txtten.getText(), 
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
       
        //TableColumn clid= new TableColumn("id ");
        TableColumn clma= new TableColumn("Mã sách ");
        TableColumn clten= new TableColumn("Tên sách ");
        TableColumn cltg= new TableColumn("Tác giả ");
        TableColumn clmota= new TableColumn("Mô tả ");
        TableColumn clnam= new TableColumn("Năm xuất bản ");
        TableColumn clnhap= new TableColumn("Ngày nhập ");
        TableColumn clvitri= new TableColumn("Vị trí sách ");
        
        
        //.setCellValueFactory(new PropertyValueFactory("id"));
        clma.setCellValueFactory(new PropertyValueFactory("ma"));
        clten.setCellValueFactory(new PropertyValueFactory("tenSach"));
        cltg.setCellValueFactory(new PropertyValueFactory("tacGia"));
        clmota.setCellValueFactory(new PropertyValueFactory("moTa"));
        clnam.setCellValueFactory(new PropertyValueFactory("namXuatBan"));
        clnhap.setCellValueFactory(new PropertyValueFactory("ngayNhap"));
        clvitri.setCellValueFactory(new PropertyValueFactory("viTri"));
//        clContent.setPrefWidth(200);
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(et -> {
            TableCell cell = new TableCell();
            Button btn = new Button("Xóa");
            btn.setOnAction(evt -> {
                // thực hiện sự kiện xóa câu hỏi
                Button b = (Button) evt.getSource();
                TableCell c = (TableCell) b.getParent();
                Book q = (Book) c.getTableRow().getItem();
                
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chắn xóa? ");
                alert.showAndWait().ifPresent(res -> {
                    if (res == ButtonType.OK) {
//                         TableCell c = (TableCell) b.getParent();
//                         Book q = (Book) c.getTableRow().getItem();
                        try {
                          Util.delBook(q.getId());
                          
                          Util.getAlertInfo("Xóa thành công", Alert.AlertType.INFORMATION).show(); 
                                this.loadData("");
                        } catch (SQLException ex) {
                                Util.getAlertInfo("Xóa thất bại: " + ex.getMessage(), Alert.AlertType.INFORMATION).show();
                                //Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);                  
                        }
                    }
                });
                
            });
            
            cell.setGraphic(btn);
            return cell;
        });
        this.tbBook.getColumns().addAll(clma,clten,cltg,clmota,clnam,clnhap,clvitri,colAction);
        this.tbBook.setItems(FXCollections.observableArrayList(Util.getBooks("")));
        
            
                    
    } 
    private void loadData(String kw) throws SQLException {
        tbBook.getItems().clear();
        tbBook.setItems(FXCollections.observableArrayList(Util.getBooks(kw)));
    }
}
