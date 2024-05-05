package com.melocode.lread;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class PackageController implements Initializable {
    @FXML
    private Text novellasText;
    @FXML
    private TableView<Package> TableView;

    @FXML
    private TableColumn<Package, Integer> idColumn;
    @FXML
    private TableColumn<Package, Integer> idUserColumn;

    @FXML
    private TableColumn<Package, String> titleColumn;

    @FXML
    private TableColumn<Package, String> descriptionColumn;

    @FXML
    private TableColumn<Package, String> categoryColumn;
    @FXML
    private TableColumn<Package, String> priceColumn;
    @FXML
    private TableColumn<Package, String> langueColumn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        novellasText.setOnMouseClicked(event -> {
            try {
                // Charger la nouvelle page FXML
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Fxml/test.fxml")));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Novellas");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Gestion des erreurs de chargement de la nouvelle page FXML
            }
        });
        showBooks();
    }
    public ObservableList<Package> getPackageList(){
        ObservableList<Package> PackageList = FXCollections.observableArrayList();

        DBConnection db =new DBConnection();

        Connection connection = db.getCon();
        String query = "SELECT * FROM packs ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Package Package;
            while(rs.next()) {
                Package = new Package(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getString("price"),
                        rs.getString("langue"));
                PackageList.add(Package);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PackageList;
    }
    public void showBooks() {
        ObservableList<Package> list = getPackageList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Package,Integer>("id"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<Package,Integer>("user_id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Package,String>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Package,String>("description"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Package,String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Package,String>("price"));

        TableView.setItems(list);
    }
}



