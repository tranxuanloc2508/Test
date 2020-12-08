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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.DepthTest;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import pojo.Book;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BorrowBookController implements Initializable {

    @FXML
    private TextField txtIdUser;
    @FXML
    private TableView<Book> tbmuon;
  
    @FXML TextField txtma;
    @FXML TextField txtten;
    @FXML
    private Text IdMember;
    @FXML
    private Text NameBook;
    @FXML
    private HBox member_info;
    @FXML
    private Button btMuon;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // this.member_info.setDepthTest(DepthTest.ENABLE);
        //load ds khach hang
         try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
//         search book/
//         this.tx.textProperty().addListener(et->{
//             this.tbmuon.getItems().clear();
//             try {
//                 this.tbmuon.setItems(
//                         FXCollections.observableArrayList(Util.Search(
//                                 this.txttimkiem.getText())));
//             } catch (SQLException ex) {
//                 System.err.println(ex.getMessage());
//             }
//         });
        tbmuon.setRowFactory(evt->{
            TableRow row = new TableRow();
            row.setOnMouseClicked(et->{
                Book b= tbmuon.getSelectionModel().getSelectedItem();
                txtma.setText(b.getMa());
                txtten.setText(b.getTenSach());
                });
                     
                return row;
            });    
    }  

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
@FXML
    public void mu(ActionEvent event){
        
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
//    @FXML
//    private void muonsach(ActionEvent event) throws SQLException {
////        ResultSet rs = null, rs1, rs2, rs3 = null;
////        PreparedStatement pst = null, pst1, pst2, pst3 = null;
//        JDBCconn connect = new JDBCconn();
//        int idMember = Integer.parseInt(txtIdUser.getText());
//        String maSach = txtma.getText();
//        
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("aaa");
//        alert.setHeaderText(null);
//        alert.setContentText("banj cos muon muon cuon sach nay" );
//        Optional<ButtonType> rs = alert.showAndWait();
//        if(rs.get()==ButtonType.OK)
//        {
//            Util.browBook(maSach, idMember);
//            
////            String sql = "INSERT INTO book_docgia(idbook,iddocgia) VALUES( ("
////                    + "'" + idMember + "',"
////                    + "'" + maSach + "')";
////            String sql1 = "UPDATE book SET isAvail = false WHERE id = '" + maSach + "'";
////            System.out.println(sql + " and " + sql1);
//            
//        }
////        String sql1 = "select *from thedocgia where id = '" + idMember + "'";
////        String sql2 = "select *from book where id = '" + maSach + "'";
////        String sql4 = "select counter from thedocgia where id = '" + idMember + "'";
////        String sql = "insert into book_thedocgia (idbook,idthedocgia)values(?,?)";
////        try {
////            Connection con = JDBCconn.getConnection();
////            pst1 = con.prepareStatement(sql1);
////            rs1 = pst1.executeQuery();
////            pst2 = con.prepareStatement(sql1);
////            rs2 = pst2.executeQuery();
////            if (rs1.next() && rs2.next()) {
////
////                try {
////                    pst = con.prepareStatement(sql);
////                    {
////
////                        pst.setString(1, idMember);
////                        pst.setString(2, maSach);
//////                         Date dNow = new Date();
//////                        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
//////                        String date = ft.format(dNow);
//////                        pst.setString(3, date);
//////                        pst.setString(4, stMajorTxt.getText());
//////                        pst.setString(5, stDepartmentTxt.getText());
////                       
////                        pst.execute();
////                        pst.close();
////                        //JOptionPane.showMessageDialog(null, "Book issued Successfully");
////
////                    }
////
////                } catch (Exception e) {
////                    //JOptionPane.showMessageDialog(null, e);
////                }
////            } else {
////                //JOptionPane.showMessageDialog(null, "Either the book ID or the Student Registration number is incorrect");
////            }
////        } catch (Exception e) {
////            //JOptionPane.showMessageDialog(null, "sasa");
////        }
////        try {
////            Connection con = JDBCconn.getConnection();
////            String sql3 = "DELETE FROM book where id = '"+txtma+"'";
////        pst3 = con.prepareStatement(sql3);
////        pst3.executeUpdate(sql3);
////        pst3.close();
////        }catch(Exception e){
////           // JOptionPane.showMessageDialog(null,e);
////        } finally {
////            try {
////                rs.close();
////                pst.close();
////            } catch (Exception e) {
////                //JOptionPane.showMessageDialog(null, "The book is removed from the shelf");
////            }
////        }
//    }
    @FXML
    private void loadMember(ActionEvent event) throws SQLException {
        
        Connection conn = JDBCconn.getConnection();
        String  id = txtIdUser.getText();
        String sql = "SELECT * FROM thedocgia id = '" + id + "'";
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            IdMember.setText(rs.getString(1));
            NameBook.setText(rs.getString(2));
            rs.close();
                
            }
        else{
            IdMember.setText("Bo such member");
        }  
        }  

    
}
