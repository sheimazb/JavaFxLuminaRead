package com.melocode.lread.controllers;

import com.melocode.lread.models.Novella;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NovellaDetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label summaryTextArea;

    @FXML
    private ImageView novellaImageView;

    public void setNovella(Novella novella) {
        titleLabel.setText(novella.getTitle());
        authorLabel.setText(novella.getDescription());
        summaryTextArea.setText(novella.getContent());
        novellaImageView.setImage(new Image(novella.getImg()));
    }
}
