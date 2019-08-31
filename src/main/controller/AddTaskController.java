package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import model.Task;
import ui.ListView;
import ui.PomoTodoApp;
import utility.JsonFileIO;
import utility.Logger;

import java.io.IOException;

// Controller class for AddTask UI
public class AddTaskController {
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    private Task task;
    
    // EFFECTS: try to create a new task from the given description
    //          add the new task to the list of tasks in PomoTodoApp
    //          return to the list view UI
    @FXML
    public void saveTask() {
        Logger.log("AddTaskController", "Add new Task with description " + description.getText());
        try {
            Task task = new Task(description.getText());
            PomoTodoApp.getTasks().add(task);
            JsonFileIO jsonFileIO = new JsonFileIO();
            try {
                jsonFileIO.write(PomoTodoApp.getTasks());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException e) {
            Logger.log("AddTaskController", "Failed to create a new task from description " + description.getText());
        } finally {
            returnToListView();
        }
    }
    
    // EFFECTS: return to the list view UI
    @FXML
    public void cancelNewTask() {
        Logger.log("AddTaskController", "Edit Task cancelled.");
        returnToListView();
    }
    
    // EFFECTS: return to the list view UI
    private void returnToListView() {
        Logger.log("AddTaskController", "Return to the list view UI.");
        PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));
    }
}
