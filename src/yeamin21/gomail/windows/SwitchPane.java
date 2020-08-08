package yeamin21.gomail.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class SwitchPane {
    public String panelToSwitch;
    public Pane switchOnPanel;
    private FXMLLoader fxmlLoader;

    public SwitchPane(String panelToSwitch, Pane switchOnPanel) {
        this.switchOnPanel = switchOnPanel;
        this.panelToSwitch =panelToSwitch;
        fxmlLoader=new FXMLLoader(getClass().getClassLoader().getResource(panelToSwitch));
    }
    public FXMLLoader getFxmlLoader()
    {
       return this.fxmlLoader;
    }

    @FXML
    public void doSwitch()
    {
        Parent root;
        try {
            root = getFxmlLoader().load();
            this.switchOnPanel.getChildren().setAll(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    }

