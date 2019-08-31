package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

// The add button at the bottom of them GUI
public class AddButton extends StackPane {
    private static final String FXML = "resources/fxml/AddButton.fxml";
    private File fxmlFile = new File(FXML);
    
    public AddButton() {
        this.load();
    }
    
    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
