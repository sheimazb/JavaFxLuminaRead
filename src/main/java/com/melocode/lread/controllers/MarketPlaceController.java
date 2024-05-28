package com.melocode.lread.controllers;

import com.melocode.lread.APICaller;
import com.melocode.lread.models.Pack;
import com.melocode.lread.models.Novella;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class MarketPlaceController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button marketButton;


    @FXML
    private ListView<Pack> packsListView;
    @FXML
    private ImageView headerImage;

    @FXML
    public void initialize() {
        List<Pack> packs = APICaller.fetchPacksFromAPI();
        headerImage.setImage(new Image("https://i.gyazo.com/706a645ba061f1a126897be47f21667c.png"));
        headerImage.setFitWidth(850);
        headerImage.setFitHeight(150);

        if (packs != null) {
            packsListView.getItems().addAll(packs);
            packsListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Pack> call(ListView<Pack> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Pack pack, boolean empty) {
                            super.updateItem(pack, empty);
                            if (empty || pack == null) {
                                setGraphic(null);
                                setText(null);
                                setStyle("-fx-background-color: transparent;");
                            } else {
                                ImageView imageView = new ImageView();
                                imageView.setFitWidth(100);
                                imageView.setFitHeight(100);

                                Image image = new Image(pack.getImg());
                                imageView.setImage(image);

                                String description = "Description: " + pack.getDescription();
                                setText(description.length() > 50 ? description.substring(0, 50) + "\n" + description.substring(50) : description);

                                Button showButton = new Button("Show");
                                showButton.setOnAction(event -> {
                                    List<Novella> novellas = fetchNovellasByPackId(pack.getId());
                                    if (novellas != null && !novellas.isEmpty()) {
                                        showNovellaDetails(novellas);
                                    }
                                });

                                VBox vBox = new VBox(10);
                                vBox.getChildren().addAll(imageView, showButton);
                                setGraphic(vBox);

                                setStyle("-fx-background-color: #1b202d; -fx-padding:10;");
                                setTextFill(javafx.scene.paint.Color.WHITE);
                            }
                        }
                    };
                }
            });
        }
    }

    private List<Novella> fetchNovellasByPackId(int packId) {
        try {
            URL url = new URL("http://127.0.0.1:8000/api/novel/" + packId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Type listType = new TypeToken<List<Novella>>() {}.getType();
                return new Gson().fromJson(response.toString(), listType);
            } else {
                System.out.println("Failed to fetch novellas: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showNovellaDetails(List<Novella> novellas) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/Novella_details.fxml")));
            Parent parent = loader.load();
            NovellaDetailsController controller = loader.getController();
            controller.setNovellas(novellas);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Novella Details");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Home.fxml"));
        Stage stage = (Stage) homeButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/Login.fxml"));
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/SignUp.fxml"));
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }
    @FXML
    private void handleMarket(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Fxml/market.fxml"));
        Stage stage = (Stage) marketButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

}
