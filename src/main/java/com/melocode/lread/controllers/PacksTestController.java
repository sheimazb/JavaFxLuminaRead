package com.melocode.lread.controllers;

import com.melocode.lread.APICaller;
import com.melocode.lread.models.Pack;
import com.melocode.lread.models.Novella;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class PacksTestController {

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
                            } else {
                                // Créer l'image view
                                ImageView imageView = new ImageView();
                                imageView.setFitWidth(100); // Définissez la largeur souhaitée de l'image
                                imageView.setFitHeight(100); // Définissez la hauteur souhaitée de l'image

                                // Charger l'image à partir du chemin fourni par pack.getImg()
                                Image image = new Image(pack.getImg());
                                imageView.setImage(image);

                                // Créez le texte de la description

                                String description = "Description: " + pack.getDescription();
                                if (description.length() > 50) {
                                    // Si la description est trop longue, la diviser en plusieurs lignes
                                    setText(description.substring(0, 50) + "\n" + description.substring(50));
                                } else {
                                    setText(description);
                                }

                                // Créez le bouton "Show"
                                Button showButton = new Button("Show");
                                showButton.setOnAction(event -> {
                                    Novella novella = fetchNovellaById(pack.getId());
                                    if (novella != null) {
                                        showNovellaDetails(novella);
                                    }
                                });

                                // Créez une VBox pour organiser l'image, le texte et le bouton verticalement
                                VBox vBox = new VBox(10);
                                vBox.getChildren().addAll(imageView, showButton); // Ajoutez l'image, le texte et le bouton à la VBox
                                setGraphic(vBox);
                            }
                        }

                    };
                }
            });


        }
    }



    private void handlePackClick(MouseEvent event) {
        Pack selectedPack = packsListView.getSelectionModel().getSelectedItem();
        if (selectedPack != null) {
            int packId = selectedPack.getId();
            Novella novella = fetchNovellaById(packId);
            if (novella != null) {
                showNovellaDetails(novella);
            }
        }
    }

    private Novella fetchNovellaById(int packId) {
        try {
            URL url = new URL("http://127.0.0.1:8000/api/novellas/" + packId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                //test
                in.close();

                return new Gson().fromJson(response.toString(), Novella.class);
            } else {
                System.out.println("Failed to fetch novella: " + responseCode);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showNovellaDetails(Novella novella) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/Fxml/Novella_details.fxml")));
            Parent parent = loader.load();
            NovellaDetailsController controller = loader.getController();
            controller.setNovella(novella);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Novella Details");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}