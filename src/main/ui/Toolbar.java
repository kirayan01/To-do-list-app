package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// Toolbar appears at the top of the GUI
public class Toolbar extends VBox {
    private static final String FXML = "resources/fxml/Toolbar.fxml";
    private File fxmlFile = new File(FXML);
    
    public Toolbar() {
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