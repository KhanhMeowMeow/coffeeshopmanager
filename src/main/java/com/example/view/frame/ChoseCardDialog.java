package com.example.view.frame;

import java.util.List;

import com.example.controller.DAO.CardDAO;
import com.example.controller.DAO.DAOImpl.CardDAOImpl;
import com.example.controller.lib.FileSYSTEM;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import com.example.model.Card;

public class ChoseCardDialog extends Dialog {

    private GridPane layoutChoseCardFrame;
    private CardDAO cardDAO;
    private List<Card> listCard;

    public ChoseCardDialog() {

        cardDAO = new CardDAOImpl();
        listCard = cardDAO.findAll();

        layoutChoseCardFrame = new GridPane();
        layoutChoseCardFrame.setHgap(10);
        layoutChoseCardFrame.setVgap(10);

        for (int i = 0; i < listCard.size(); i++) {
            Button cardButton = new Button("Thẻ " + listCard.get(i).getId());
            Card card = listCard.get(i);
            cardButton.setFont(FileSYSTEM.fontApp);
            cardButton.setPrefSize(150, 75);
            cardButton.setStyle(
                    "-fx-background-color: red; -fx-background-radius: 10; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-family: 'Asap Condensed';");
            if (card.getStatus() == 1) {
                cardButton.setDisable(true);
            }
            if (card.getStatus() == 2) {
                cardButton.setStyle(cardButton.getStyle() + "-fx-background-color: white; -fx-text-fill: red;");
                cardButton.setDisable(true);
            }
            cardButton.setOnMouseClicked(e -> {
                if (card.getStatus() == 0) {
                    card.setStatus(1);
                    cardDAO.update(card);
                }
                setResult(card);
                this.close();
            });
            layoutChoseCardFrame.add(cardButton, i % 7, i / 7);
        }

        Font.loadFont(FileSYSTEM.fontApp.getName(), 16);
        this.getDialogPane().setContent(layoutChoseCardFrame);
        this.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setVisible(false);
        this.getDialogPane().lookupButton(ButtonType.CLOSE).setManaged(false);
        this.getDialogPane().setStyle("-fx-background-color: white;");
        this.setResizable(false);
        this.setTitle("Chọn thẻ");
    }
}
