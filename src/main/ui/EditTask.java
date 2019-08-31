package ui;

import controller.EditTaskController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import model.Task;

import java.io.File;
import java.io.IOException;

// Edit task UI
public class EditTask extends StackPane {
    private static final String FXML = "resources/fxml/EditTask.fxml";
    private File fxmlFile = new File(FXML);
    private Task task;
    
    public EditTask(Task task) {
        this.task = task;
        this.load();
    }
    
    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            EditTaskController controller = fxmlLoader.<EditTaskController>getController();
            controller.setTask(task);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}