/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.Util;
import java.awt.Desktop;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Book;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BorrowBookController implements Initializable {

    @FXML
    private TextField txttimkiem;
    @FXML
    private TableView<Book> tbmuon;
    @FXML
    private Button btSearch;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
        
        this.tbmuon.getColumns().addAll(clid,clma,clten,cltg,clmota,clnam,clnhap,clvitri);
        this.tbmuon.setItems(FXCollections.observableArrayList(Util.getBooks("")));
        
    }
    public void Search(ActionEvent event){
        String s = txttimkiem.getText();
        
    }

     
    
}
