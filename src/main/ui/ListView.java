package ui;

import controller.ListViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

// List View: Tasks are listed (in no particular order)
public class ListView extends StackPane {
    private static final String FXML = "resources/fxml/ListView.fxml";
    private File fxmlFile = new File(FXML);
    private List<Task> tasks;
    
    // REQUIRES: task != null
    // MODIFIES: this
    public ListView(List<Task> tasks) {
        this.tasks = tasks;
        this.load();
    }
    
    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ListViewController controller = fxmlLoader.<ListViewController>getController();
            controller.setData(tasks);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}