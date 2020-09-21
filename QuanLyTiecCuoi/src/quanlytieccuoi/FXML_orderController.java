/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quanlytieccuoi;


import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.Customers;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.OrderDetail;
import quanlytieccuoi.models.Orders;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FXML_orderController implements Initializable {
    String id;
    @FXML
    private TableView<OrderDetail> tbOrder;
    @FXML
    private TableColumn clName;
    @FXML
    private TableColumn clDiscount;
    @FXML
    private TableColumn clQuantity;
    @FXML
    private TableColumn clUnitPrice;
    TableRow row;
    @FXML
    private Label totalM;
    @FXML
    private Label lbDateOfPayment;
    @FXML
    private Label lbDiscount;
    @FXML
    private Label lbFeastDay;
    @FXML
    private Label lbBanquetHall;
    @FXML
    private Label lbTimeOfDay;
    @FXML
    private Label lbName;
    @FXML
    private Label lbPhoneNumber;
    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.factory = HibernateUtil.getSessionFactory();
            //utils.getOrders("");
            loadOrders();
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    public void loadOrders() throws SQLException{
        List<OrderDetail> orders = utils.getOrders("4f7f9cab-9739-4095-9fe2-1ad2c988994a");
        clName.setCellValueFactory(new PropertyValueFactory("fds"));
        clQuantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        clUnitPrice.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        clDiscount.setCellValueFactory(new PropertyValueFactory("discount"));
        
        //this.tbOrder.getItems().addAll(l);
        //this.tbCustomers.getItems().add(cus);
    }
    
    public void setOrders(String id){
        this.id = id;
    }
    
}

