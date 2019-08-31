package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import model.Task;
import utility.JsonFileIO;

import java.io.File;
import java.io.IOException;

// Add task view
public class AddTask extends StackPane {
    private static final String FXML = "resources/fxml/AddTask.fxml";
    private File fxmlFile = new File(FXML);
    private Task task;
    
    public AddTask() {
        load();
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

