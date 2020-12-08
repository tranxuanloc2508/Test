/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HeThong;

import Utils.BookServices;
import Utils.BorrowServices;
import Utils.JDBCconn;
import Utils.Util;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
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
import pojo.Borrow;

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

    @FXML
    TextField txtma;
    @FXML
    TextField txtten;
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
        tbmuon.setRowFactory(evt -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(et -> {
                Book b = tbmuon.getSelectionModel().getSelectedItem();
                txtma.setText((String.valueOf(b.getId())));
                txtten.setText(b.getTenSach());
            });

            return row;
        });
    }

    private void loadBook() throws SQLException {

        TableColumn clma = new TableColumn("Mã sách ");
        TableColumn clten = new TableColumn("Tên sách ");
        TableColumn cltg = new TableColumn("Tác giả ");
        TableColumn clmota = new TableColumn("Mô tả ");
        TableColumn clnam = new TableColumn("Năm xuất bản ");
        TableColumn clnhap = new TableColumn("Ngày nhập ");
        TableColumn clvitri = new TableColumn("Vị trí sách ");

        clma.setCellValueFactory(new PropertyValueFactory("ma"));
        clten.setCellValueFactory(new PropertyValueFactory("tenSach"));
        cltg.setCellValueFactory(new PropertyValueFactory("tacGia"));
        clmota.setCellValueFactory(new PropertyValueFactory("moTa"));
        clnam.setCellValueFactory(new PropertyValueFactory("namXuatBan"));
        clnhap.setCellValueFactory(new PropertyValueFactory("ngayNhap"));
        clvitri.setCellValueFactory(new PropertyValueFactory("viTri"));
//        clContent.setPrefWidth(200);

        this.tbmuon.getColumns().addAll(clma, clten, cltg, clmota, clnam, clnhap, clvitri);
        this.tbmuon.setItems(FXCollections.observableArrayList(BookServices.getBooks("")));

    }


    @FXML
    public void muonSach(ActionEvent event) throws ParseException {
        Random so = new Random();
        
        int id = so.nextInt(31);
        
        
        Borrow b = new Borrow(Integer.parseInt(txtIdUser.getText()), id, Integer.parseInt(txtma.getText()));

        try {
            BorrowServices.addBorrow(b);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Add successful");
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add unsuccessful" + ex.getMessage());
                    alert.showAndWait();

        }
    }

}
