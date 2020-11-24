/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.JDBCconn;
import Utils.Util;
import java.awt.Desktop;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        //load ds khach hang
         try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//         search book/
         this.txttimkiem.textProperty().addListener(et->{
             this.tbmuon.getItems().clear();
             try {
                 this.tbmuon.setItems(
                         FXCollections.observableArrayList(Util.Search(
                                 this.txttimkiem.getText())));
             } catch (SQLException ex) {
                 System.err.println(ex.getMessage());
             }
         });
//         seachbook();
            
    }  
//    private void seachbook(){
//        txttimkiem.setOnKeyReleased(e->{
//            if(txttimkiem.getText().equals("")){
//                try {
//                    loadBook();
//                } catch (SQLException ex) {
//                    Logger.getLogger(BorrowBookController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            else{
//            String sql = "select * from book where tensach like'%"+txttimkiem.getText()+"%'";
//            try{
//                Connection conn = JDBCconn.getConnection();
//                PreparedStatement stm = conn.prepareStatement(sql);        
//                ResultSet rs = stm.executeQuery();
//
//                List<Book> books= new ArrayList<>();
//                while(rs.next()){
//                    Book q = new Book(rs.getString("id"),rs.getString("masach"),
//                            rs.getString("tensach"),rs.getString("tacgia"),
//                            rs.getString("motasach"),rs.getString("namxuatban"),
//                            rs.getString("ngaynhapsach"),rs.getString("vitri"));
//                    books.add(q);
//                }
//            
//            }catch(SQLException ex){
//                Logger.getLogger(BorrowBookController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
//        });
//    }
    private void loadBook() throws SQLException {
       
       
        TableColumn clma= new TableColumn("Mã sách ");
        TableColumn clten= new TableColumn("Tên sách ");
        TableColumn cltg= new TableColumn("Tác giả ");
        TableColumn clmota= new TableColumn("Mô tả ");
        TableColumn clnam= new TableColumn("Năm xuất bản ");
        TableColumn clnhap= new TableColumn("Ngày nhập ");
        TableColumn clvitri= new TableColumn("Vị trí sách ");
        
        
        
        clma.setCellValueFactory(new PropertyValueFactory("ma"));
        clten.setCellValueFactory(new PropertyValueFactory("tenSach"));
        cltg.setCellValueFactory(new PropertyValueFactory("tacGia"));
        clmota.setCellValueFactory(new PropertyValueFactory("moTa"));
        clnam.setCellValueFactory(new PropertyValueFactory("namXuatBan"));
        clnhap.setCellValueFactory(new PropertyValueFactory("ngayNhap"));
        clvitri.setCellValueFactory(new PropertyValueFactory("viTri"));
//        clContent.setPrefWidth(200);
        
        this.tbmuon.getColumns().addAll(clma,clten,cltg,clmota,clnam,clnhap,clvitri);
        this.tbmuon.setItems(FXCollections.observableArrayList(Util.getBooks("")));
        
    }
   

     
    
}
