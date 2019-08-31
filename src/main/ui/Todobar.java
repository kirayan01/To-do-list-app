package ui;

import controller.TodobarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import model.Task;

import java.io.File;
import java.io.IOException;

// Each task is presented in a Todobar
public class Todobar extends VBox {
    private static final String FXML = "resources/fxml/Todobar.fxml";
    private File fxmlFile = new File(FXML);
    private Task task;
    
    public Todobar(Task task) {
        this.task = task;
        load();
    }
    
    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            TodobarController controller = fxmlLoader.<TodobarController>getController();
            controller.setTask(task);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
