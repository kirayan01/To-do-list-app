package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Task;
import utility.JsonFileIO;

import java.util.ArrayList;
import java.util.List;

// Edit Task Demo
public class EditTaskDemo extends Application {
    public static final String TITLE = "Edit Task Demo!";
    public static final double WIDTH = 520;
    public static final double HEIGHT = 800;
    private static Task task = new Task("Buy milk ## today; important; grocery");
    private static Stage primaryStage;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // REQUIRES: stage != null
    // MODIFIES: this
    // EFFECTS: sets the primary stage
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    // REQUIRES: primaryStage != null AND root != null
    public static void setScene(Parent root) {
        try {
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            primaryStage.setTitle(TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Failed to load new Scene!");
        }
    }
    
    // EFFECTS: Application starts here!
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        setScene(new EditTask(task));
    }
}
