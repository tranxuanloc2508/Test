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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import pojo.Book;
import pojo.Borrow;

/**
 * FXML Controller class
 *
 * @author LocNe
 */
public class BookController implements Initializable {

    private ObservableList<Book> data;
    private static final int MAX_BOOK_TO_ISSUE = 3;
    @FXML
    TextField txtma;
    @FXML
    TextField txtten;
    @FXML
    TextField txttacGia;
    @FXML
    TextField txtmota;
    @FXML
    TextField txtNXB;
    @FXML
    TextField txtNgayNhapSach;
    @FXML
    TextField txtViTri;
    @FXML
    Button btnLuu;
    @FXML
    Button btnThoat;
    @FXML
    TextField txttimkiem;
    @FXML
    TableView<Book> tbBook;
    Button btnXoa;

    @FXML
    TableView<Book> tbmuon;
    @FXML
    TextField txtma1;
    @FXML
    TextField txtten1;
    @FXML
    TextField txtIdUser;
    @FXML
    private TableView<Borrow> tbmuon1;
    @FXML
    private DatePicker dateReturn;
    @FXML
    private TextField txtTongTien;
    @FXML
    private ListView<String> listViewData;
    @FXML
    private ListView<String> listViewData1;
    @FXML
    private TextField txtLoadMember;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load books
        try {
            this.loadBook();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        try {
            this.loadBook1();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //Search bôok

        this.txttimkiem.textProperty().addListener(et -> {
            this.tbBook.getItems().clear();
            try {
                this.tbBook.setItems(
                        FXCollections.observableArrayList(BookServices.Search(
                                this.txttimkiem.getText())));

            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }

        });
        tbmuon.setRowFactory(evt -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(et -> {
                Book b = tbmuon.getSelectionModel().getSelectedItem();
                txtma1.setText((String.valueOf(b.getId())));
                txtten1.setText(b.getTenSach());
            });

            return row;
        });

//           int sum = 0;
//
//                for (int i = 0; i < tbmuon1.getItems().size(); i++) {
//                    sum = sum + Integer.parseInt(tbmuon1.getValueAt(i, 1).toString());
//                }
//
//                txtTongTien.setText(String.valueOf(sum));
    }
//   public void totalB(ActionEvent event){
//   
//       String a = tbmuon1.getRowFactory().toString();

    @FXML
    public void addBook(ActionEvent event) {

        Book b = new Book(this.txtma.getText(), this.txtten.getText(),
                txttacGia.getText(), txtmota.getText(), txtNXB.getText(),
                txtNgayNhapSach.getText(), txtViTri.getText());

        try {
            Utils.BookServices.addBook(b);
            this.tbBook.getColumns().clear();
            this.tbBook.setItems(FXCollections.observableArrayList(BookServices.getBooks("")));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Add Book succsessful!!!!");
            alert.show();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Add Book failed!!!!" + ex.getMessage());
            alert.show();
        }
    }

    @FXML
    public void loadFull(ActionEvent ev) throws SQLException {

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
        this.tbmuon.setVisible(true);
        this.tbmuon1.setVisible(false);

    }

    private void loadBook1() throws SQLException {

        this.tbmuon1.getItems().clear();

        TableColumn clma = new TableColumn("ID");
        TableColumn clten = new TableColumn("IDbook");
        TableColumn cltg = new TableColumn("IDMember");
        TableColumn clmota = new TableColumn("Ngày mượn");
        TableColumn clnam = new TableColumn("Ngày trả");
        TableColumn cltien = new TableColumn("Tiền phạt");

        clma.setCellValueFactory(new PropertyValueFactory("id"));
        clten.setCellValueFactory(new PropertyValueFactory("idbook"));
        cltg.setCellValueFactory(new PropertyValueFactory("iddocgia"));
        clmota.setCellValueFactory(new PropertyValueFactory("ngaymuon"));
        clnam.setCellValueFactory(new PropertyValueFactory("ngaytra"));
        cltien.setCellValueFactory(new PropertyValueFactory("tienphat"));
        this.tbmuon1.getColumns().clear();
        this.tbmuon1.getColumns().addAll(clma, clten, cltg, clmota, clnam, cltien);
        this.tbmuon1.setItems(FXCollections.observableArrayList(BorrowServices.getBorrow("")));
        this.tbmuon.setVisible(false);

    }

    private void loadBook() throws SQLException {

        //TableColumn clid= new TableColumn("id ");
        TableColumn clma = new TableColumn("Mã sách ");
        TableColumn clten = new TableColumn("Tên sách ");
        TableColumn cltg = new TableColumn("Tác giả ");
        TableColumn clmota = new TableColumn("Mô tả ");
        TableColumn clnam = new TableColumn("Năm xuất bản ");
        TableColumn clnhap = new TableColumn("Ngày nhập ");
        TableColumn clvitri = new TableColumn("Vị trí sách ");

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
        this.tbBook.getColumns().addAll(clma, clten, cltg, clmota, clnam, clnhap, clvitri, colAction);
        this.tbBook.setItems(FXCollections.observableArrayList(BookServices.getBooks("")));
    }

    @FXML
    public void muonSach(ActionEvent event) throws ParseException {
//        Random so = new Random();
//        
//        int id = so.nextInt(31);

        Borrow b = new Borrow(Integer.parseInt(txtIdUser.getText()), Integer.parseInt(txtma1.getText()));

        try {
            BorrowServices.addBorrow(b);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Borrow Book successful");
            this.loadBook1();
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Borrow Book unsuccessful" + ex.getMessage());
            alert.showAndWait();

        }
        this.tbmuon1.setVisible(true);
        this.tbmuon.setVisible(false);
    }

    private void loadData(String kw) throws SQLException {
        tbBook.getColumns().clear();
        tbBook.setItems(FXCollections.observableArrayList(BookServices.getBooks(kw)));
    }

    @FXML
    private void traSach(ActionEvent event) throws ParseException {
        Borrow b = tbmuon1.getSelectionModel().getSelectedItem();
        String ngaymuon = b.getNgaymuon();
        String ngaytra = dateReturn.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));;

        Borrow bor = new Borrow(ngaymuon, ngaytra, b.getIdbook(), b.getId(), b.getIddocgia(), b.getTienphat());

        try {
            BorrowServices.returnB(b, ngaytra, ngaymuon);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Update successful");
            this.loadBook1();
            alert.showAndWait();

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Update failed" + ex.getMessage());
            alert.showAndWait();

        }

    }

    @FXML
    private void ChangeDate(ActionEvent event) {
    }

    @FXML
    private void tongTien(ActionEvent event) throws SQLException {

    }

    @FXML
    private void Loaddata(Event event) throws SQLException {
        ObservableList<String> issueData = FXCollections.observableArrayList();
        String sql = "Select sum(tienphat) from bookdocgia";
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            String a = rs.getString("sum(tienphat)");
            issueData.add("Tổng tiền phạt đã nhận là :" + a);
        }

        String sql1 = "Select count(id) from bookdocgia";
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        ResultSet rs1 = stm1.executeQuery();
        if (rs1.next()) {
            String a = rs1.getString("count(id)");
            issueData.add("Số quyển sách đã mượn là :" + a);
        }
        listViewData.getItems().setAll(issueData);
    }

    @FXML
    private void loadmember(ActionEvent event) throws SQLException {
        ObservableList<String> issueData = FXCollections.observableArrayList();
        String id = txtLoadMember.getText();
        String sql = "Select * from thedocgia where id='" + id + "'";
        Connection conn = JDBCconn.getConnection();
        PreparedStatement stm = null;
        ResultSet rs = null;
        stm = conn.prepareStatement(sql);
        rs = stm.executeQuery();

        try {
            while (rs.next()) {
                String a = id;
                String a1 = rs.getString("madocgia");
                String a2 = rs.getString("hoten");
                String a3 = rs.getString("gioitinh");
                String a4 = rs.getString("ngaysinh");
                String a5 = rs.getString("hanthe");
                
                issueData.add("ID là :" + a);
                issueData.add("Mã Độc giả:" + a1);
                issueData.add("Họ Tên :" + a2);
                issueData.add("Giới Tính :" + a3);
                issueData.add("Ngày Sinh:" + a4);
                issueData.add(" Hạn Thẻ:" + a5);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listViewData1.getItems().setAll(issueData);
    }
}
