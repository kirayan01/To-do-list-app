package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import javafx.fxml.FXML;
import ui.AddTask;
import ui.PomoTodoApp;
import utility.Logger;

// Controller class for AddButton UI
public class AddButtonController {
    @FXML
    private JFXNodesList nodesList;
    @FXML
    private JFXButton newButton;
    @FXML
    private JFXButton addTaskButton;
    @FXML
    private JFXButton addProjectButton;
    
    // EFFECTS: Open the "Add new task" UI
    @FXML
    public void onNewTask() {
        Logger.log("AddButtonController", "Add new task.");
        closeNodeList();
        PomoTodoApp.setScene(new AddTask());
    }
    
    // EFFECTS: Open the "Add new project" UI
    @FXML
    public void onNewProject() {
        Logger.log("AddButtonController", "Adding project is not supported in this version.");
        closeNodeList();
    }
    
    // EFFECTS: closes the node list
    private void closeNodeList() {
        nodesList.animateList(false);
    }
}
