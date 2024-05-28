package com.melocode.lread.controllers;

import com.melocode.lread.models.Novella;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class NovellaDetailsController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox novellasContainer;

    public void setNovellas(List<Novella> novellas) {

        for (Novella novella : novellas) {
            HBox novellaBox = new HBox(10);

            ImageView novellaImageView = new ImageView(new Image(novella.getImg()));
            novellaImageView.setFitWidth(100);
            novellaImageView.setFitHeight(100);

            VBox textContainer = new VBox(5);
            Label titleLabel = new Label("Title: " + novella.getTitle());
            Label descriptionLabel = new Label("Description: " + novella.getDescription());
            Button showContentButton = new Button("Show Content");
            showContentButton.setOnAction(event -> showContent(novella.getContent()));

            textContainer.getChildren().addAll(titleLabel, descriptionLabel, showContentButton);

            novellaBox.getChildren().addAll(novellaImageView, textContainer);
            novellasContainer.getChildren().add(novellaBox);
        }
    }


    private void showContent(String content) {

        Label contentLabel = new Label("Content: " + content);
        contentLabel.setWrapText(true);
        contentLabel.getStyleClass().add("content-label");
        contentLabel.setStyle("-fx-padding: 20px; -fx-background-color: #1b202d;");
        contentLabel.setTextFill(Color.WHITE);

        String[] words = content.split("\\s+"); // Split content into words
        StringBuilder wrappedContent = new StringBuilder();
        int wordCount = 0;

        for (String word : words) {
            wrappedContent.append(word).append(" ");
            wordCount++;

            // Add line break after every 20 words
            if (wordCount % 150 == 0) {
                wrappedContent.append("\n");
            }
        }

        contentLabel.setText("Content:\n" + wrappedContent.toString().trim());
        VBox contentBox = new VBox(5);
        contentBox.getChildren().addAll(contentLabel);

        ScrollPane scrollPane = new ScrollPane(); // Create ScrollPane
        scrollPane.setContent(contentBox); // Set content box as content of ScrollPane
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if necessary


        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Novella Content");
        Scene scene = new Scene(scrollPane, 800, 500); // Set ScrollPane as root of Scene
        scene.getStylesheets().add(getClass().getResource("/Fxml/style/global.css").toExternalForm()); // Add stylesheet
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        scrollPane.setContent(novellasContainer);
    }
}
