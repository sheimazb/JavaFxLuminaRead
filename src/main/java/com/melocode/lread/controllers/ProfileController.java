package com.melocode.lread.controllers;

import com.melocode.lread.DBConnection;
import com.melocode.lread.models.Pack;
import com.melocode.lread.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.ScrollPane;

import java.util.List;

public class ProfileController {

    @FXML
    private ImageView userImage;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Label userName;

    @FXML
    private Label userDescription;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox packListContainer;

    public void initialize() {
        // Set the background image
        backgroundImage.setImage(new Image("https://i.gyazo.com/706a645ba061f1a126897be47f21667c.png"));

        DBConnection dbConnection = new DBConnection();
        User user = dbConnection.getUserProfile(6);
        if (user != null) {
            userName.setText(user.getName());
            userDescription.setText(user.getDescription());
            if (user.getImg() != null && !user.getImg().isEmpty()) {
                userImage.setImage(new Image(user.getImg()));
            }
        }

        List<Pack> packs = dbConnection.getPacksForUser(6);
        if (packs != null && !packs.isEmpty()) {
            int packsPerRow = 3;

            packListContainer.setSpacing(20);

            for (int i = 0; i < packs.size(); i += packsPerRow) {
                HBox row = new HBox();
                row.setSpacing(20);
                row.getStyleClass().add("pack-row");

                for (int j = 0; j < packsPerRow && (i + j) < packs.size(); j++) {
                    Pack pack = packs.get(i + j);
                    if (pack != null) {
                        VBox packContainer = new VBox();
                        packContainer.getStyleClass().add("pack-container");
                        packContainer.setSpacing(10);

                        Label packTitle = new Label(pack.getTitle());
                        packTitle.getStyleClass().add("pack-title");

                        Label packDescription = new Label(pack.getDescription());
                        packDescription.getStyleClass().add("pack-description");

                        Label packCategory = new Label("Category: " + pack.getCategory());
                        packCategory.getStyleClass().add("pack-category");

                        Label packPrice = new Label("Price: " + pack.getPrice());
                        packPrice.getStyleClass().add("pack-price");

                        Label packLanguage = new Label("Language: " + pack.getLangue());
                        packLanguage.getStyleClass().add("pack-language");

                        ImageView packImage = null;
                        try {
                            packImage = new ImageView(new Image(pack.getImg()));
                            packImage.setFitWidth(100);
                            packImage.setFitHeight(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        packContainer.getChildren().addAll(packImage, packTitle, packDescription, packCategory, packPrice, packLanguage);
                        row.getChildren().add(packContainer);
                    }
                }
                packListContainer.getChildren().add(row);
            }

            scrollPane.setContent(packListContainer);
        }
    }
}
