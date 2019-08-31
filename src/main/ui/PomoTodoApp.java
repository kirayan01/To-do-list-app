package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Task;
import utility.JsonFileIO;

import java.util.ArrayList;
import java.util.List;

// The PomoTODO GUI Application
public class PomoTodoApp extends Application {
    public static final String TITLE = "PomoTODO";
    public static final double WIDTH = 520;
    public static final double HEIGHT = 800;
    private static List<Task> tasks = new ArrayList<>();
    private static Stage primaryStage;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    // EFFECTS: returns the primary Stage
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    
    // REQUIRES: stage != null
    // MODIFIES: this
    // EFFECTS: sets the primary stage
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    // EFFECTS: returns the list of tasks in this PomoTODO APP
    public static List<Task> getTasks() {
        return tasks;
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
        tasks = JsonFileIO.read();
        setScene(new ListView(tasks));
        JsonFileIO.write(tasks);
    }
}
