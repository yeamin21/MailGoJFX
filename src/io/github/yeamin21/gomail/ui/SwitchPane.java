package io.github.yeamin21.gomail.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SwitchPane {
    public String panelToSwitch;
    public Pane switchOnPanel;

    public SwitchPane(String panelToSwitch, Pane switchOnPanel) {
        this.switchOnPanel = switchOnPanel;
        this.panelToSwitch =panelToSwitch;
    }

    @FXML
    public void doSwitch()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(this.panelToSwitch));
            this.switchOnPanel.getChildren().setAll(root);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    }

