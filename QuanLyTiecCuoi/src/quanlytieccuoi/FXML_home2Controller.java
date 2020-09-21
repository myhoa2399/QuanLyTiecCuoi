/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlytieccuoi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import quanlytieccuoi.models.Admin;
import quanlytieccuoi.models.BanquetHall;
import quanlytieccuoi.models.FoodDrinkService;
import quanlytieccuoi.models.MenuGroup;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class FXML_home2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private Menu menu;
    @FXML
    private Menu banquetHall;
    @FXML
    private ImageView login;
    @FXML
    private ImageView imageOrders;
    @FXML
    private ImageView imageHome;
    @FXML
    private Menu menuhome;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnOrder;
    @FXML
    private Label lbTen;
    SessionFactory factory;
    UtilsDao utils = new UtilsDao();
    Stage stage;
    EventHandler<ActionEvent> event = eventLoad();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.factory = HibernateUtil.getSessionFactory();
        scrPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        // load các menuitem
        loadHome();
        loadMenu();
        loadBanquetHall();
        
        //menuhome.setOnShowing(e -> { utils.alertInformationHandler("dfdf", "dfsdfd");});
        //menuhome.setOnAction(null);
        
        imageOrders.setOnMouseClicked(e ->{
            utils.changeSceneHandler("FXML_order.fxml", e);
        });
        
        btnSignIn.setOnAction(e->{
            utils.changeSceneHandler("FXML_login.fxml", e);
        });
        
        imageHome.setOnMouseClicked(e->{
            utils.changeSceneHandler("FXML_home.fxml", e);
        });
        
        btnOrder.setOnAction(e -> {
            utils.changeSceneHandler("FXML_choosecustomer.fxml", e);
        });
        
    } 
    // xử lý sự kiện trên menu item
    public EventHandler<ActionEvent> eventLoad() {
        return new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                MenuItem mItem = (MenuItem) event.getSource();
                String side = mItem.getText();
                if ("food".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadFoodDrinkServiceToApp("Food");
                }
                if ("Drink".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadFoodDrinkServiceToApp("Drink");
                }
                if ("Service".equalsIgnoreCase(side)) {
                   flowPane.getChildren().clear();
                    loadFoodDrinkServiceToApp("Service");
                }
                if ("Hall B".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Hall B");
                }
                if ("Hall E".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Hall E");
                }
                if ("Hall C".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Hall C");
                }if ("Hall A".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Hall A");
                }
                if ("Hall D".equalsIgnoreCase(side)) {
                    flowPane.getChildren().clear();
                    loadBanquetHall("Hall D");
                }                
            }
        };
    }
    
    public void setTenAdmin(Admin ad){
        if(ad != null)
            lbTen.setText(ad.getFirstName());
        
    }
    
    public void loadFoodDrinkServiceToApp(String p){
        List<FoodDrinkService> l = utils.getFoodDrinkService();
        
        for(FoodDrinkService f : l){
            if(f.getIDType().getmName().equalsIgnoreCase(p)){
                VBox v = new VBox(3);

                String image = "quanlytieccuoi/Images/" + f.getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(237);
                imageView.setFitHeight(202);

                Text txt = new Text(f.getName());
                txt.setWrappingWidth(237);
                txt.setFont(new Font("Arial", 22));
                txt.setStyle("-fx-text-alignment: CENTER;");

                Text price = new Text(String.format("Giá: %s VNĐ", f.getPrice()));
                price.setWrappingWidth(237);
                price.setFill(Color.RED);
                price.setFont(new Font("Arial", 18));
                price.setStyle("-fx-text-alignment: CENTER;");
                
                Button btn = new Button("Thêm");
                
                btn.setFont(new Font("Arial", 18));
                btn.setStyle("-fx-alignment: CENTER;");
                
                v.getChildren().addAll(imageView, txt, price, btn);
                flowPane.getChildren().add(v);
            }
                
        }   
    
    }
    
    public void loadBanquetHall(String p){
        List<BanquetHall> l = utils.getBanquetHall();
        
        for(BanquetHall f : l){
            if(f.getName().equalsIgnoreCase(p)){
                VBox v = new VBox(3);
                
                String image = "quanlytieccuoi/Images/" + f.getImage();
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(237);
                imageView.setFitHeight(202);

                Text txt = new Text(f.getName());
                txt.setWrappingWidth(237);
                txt.setFont(new Font("Arial", 22));
                txt.setStyle("-fx-text-alignment: CENTER;");
                
                Text txtAcrege = new Text(String.format("Giá: %s m2", f.getAcreage()));
                txtAcrege.setWrappingWidth(237);
                txtAcrege.setFont(new Font("Arial", 18));
                txtAcrege.setStyle("-fx-text-alignment: CENTER;");
                
                Text txtMaxPeople = new Text(String.format("Giá: %s người", f.getMaxPeople()));
                txtMaxPeople.setWrappingWidth(237);
                txtMaxPeople.setFont(new Font("Arial", 18));
                txtMaxPeople.setStyle("-fx-text-alignment: CENTER;");
                
                Text price = new Text(String.format("Giá: %s VNĐ", f.getPrice()));
                price.setWrappingWidth(237);
                price.setFill(Color.RED);
                price.setFont(new Font("Arial", 18));
                price.setStyle("-fx-text-alignment: CENTER;");

                v.getChildren().addAll(imageView, txt,txtAcrege, txtMaxPeople, price);
                flowPane.getChildren().add(v);
            }      
        } 
    }
    
    public void loadHome(){
        List<String> ds  = new ArrayList<>();
        ds.add("BanQuetHAll");
        ds.add("Food");
        ds.add("Service");
        ds.add("Drink");
        int j = 1;
        for (String str : ds){

            VBox v = new VBox(5);
            HBox h = new HBox(3);


            Line l = new Line(-100,0,230.5,0);
            l.setStrokeWidth(2);
            l.setStroke(Color.web("f8b4a8"));
            Line l1 = new Line(-100,0,230.5,0);
            l1.setStrokeWidth(2);
            l1.setStroke(Color.web("f8b4a8"));

            Text t = new Text(str);
            t.setFont(new Font("System", 24));
            t.setStyle("-fx-font-weight: bold");

            String image = "quanlytieccuoi/Images/f" + j + ".png";
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(1280);
            imageView.setFitHeight(300);
            imageView.setTranslateX(-15);
            j++;

            h.setStyle("-fx-alignment: CENTER;");
            h.getChildren().addAll(l, t, l1);
            v.getChildren().addAll(h, imageView);

            flowPane.getChildren().add(v);
        }
    }
    
    public void loadMenu(){
        List<MenuGroup> mArr = utils.getMenuGroup();
        for(MenuGroup m : mArr){
            MenuItem menuItem = new MenuItem(m.getmName());
            menuItem.setOnAction(event);
            menu.getItems().add(menuItem);
            
        }
//        ImageView im = new ImageView("quanlytieccuoi/Images/home.png");
//        im.setFitWidth(30);
//        im.setFitHeight(30);
//        im.setStyle("-fx-spacing: 20;");
//        menuhome.setGraphic(im);
        
    }
    
    public void loadBanquetHall(){
        List<BanquetHall> mArr = utils.getBanquetHall();
        for(BanquetHall m : mArr){
            MenuItem menuItem = new MenuItem(m.getName());
            menuItem.setOnAction(event);
            banquetHall.getItems().add(menuItem);
        }
        
    }
    
    public void clickHome(){
        System.out.println("HIHIHIHIHI");
    }
    
}
