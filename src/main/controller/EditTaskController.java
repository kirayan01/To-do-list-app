package controller;

import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.DueDate;
import model.Status;
import model.Tag;
import model.Task;
import ui.ListView;
import ui.PomoTodoApp;
import utility.JsonFileIO;
import utility.Logger;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

// Controller class for EditTask UI
public class EditTaskController implements Initializable {
    @FXML
    private JFXTextField description;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXTimePicker timePicker;
    @FXML
    private JFXCheckBox isImportantBox;
    @FXML
    private JFXCheckBox isUrgentBox;
    @FXML
    private JFXComboBox statusComboBox;
    @FXML
    private JFXChipView tags;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    private Task task;
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: set task of this and load the UI
    public void setTask(Task task) {

        this.task = task;
        load();
    }
    
    // REQUIRES: task != null
    // EFFECTS: loads the UI
    private void load() {
        Logger.log("EditTaskController", "Load UI");
        displayDescription();
        displayDueDate();
        displayStatus();
        displayPriority();
        displayTags();
    }
    
    // REQUIRES: task != null
    private void displayTags() {
        for (Tag t : task.getTags()) {
            tags.getChips().add(t.getName());
        }
    }
    
    // REQUIRES: task != null
    private void displayStatus() {
        statusComboBox.setValue(task.getStatus());
    }
    
    // REQUIRES: task != null
    private void displayDescription() {
        description.setText(task.getDescription());
    }
    
    // REQUIRES: task != null
    private void displayPriority() {
        if (task.getPriority().isUrgent()) {
            isUrgentBox.setSelected(true);
        }
        if (task.getPriority().isImportant()) {
            isImportantBox.setSelected(true);
        }
    }
    
    // REQUIRES: task != null
    private void displayDueDate() {
        if (task.getDueDate() != Task.NO_DUE_DATE) {
            datePicker.setValue(convertToLocalDate(task.getDueDate().getDate()));
            timePicker.setValue(convertToLocalTime(task.getDueDate().getDate()));
        } else {
            datePicker.setValue(null);
            timePicker.setValue(null);
        }
    }
    
    // REQUIRES: dateToConvert != null
    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
    // REQUIRES: dateToConvert != null
    private LocalTime convertToLocalTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
    }
    
    // REQUIRES: task != null
    // MODIFIES: this
    // EFFECTS: save the updates on UI to task
    @FXML
    public void saveTask() {
        saveDescription();
        saveDueDate();
        saveStatus();
        savePriority();
        saveTags();
        Logger.log("EditTaskController", "Save task:\n" + task);
        JsonFileIO jsonFileIO = new JsonFileIO();
        try {
            jsonFileIO.write(PomoTodoApp.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PomoTodoApp.setScene(new ListView(PomoTodoApp.getTasks()));

    }
    
    // REQUIRES: task != null
    private void saveDescription() {
        Logger.log("EditTaskController", "Save description");
        task.setDescription(description.getText());
    }
    
    // REQUIRES: task != null
    private void saveDueDate() {
        String dateStr = datePicker.getValue() + " " + timePicker.getValue();
        String pattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            date = null;
        }
        if (date == null) {
            Logger.log("EditTaskController", "No due date is given");
            task.setDueDate(Task.NO_DUE_DATE);
        } else {
            Logger.log("EditTaskController", "Save due date");
            task.setDueDate(new DueDate(date));
        }
    }
    
    // REQUIRES: task != null
    private void saveTags() {
        Logger.log("EditTaskController", "Delete the tags in task");
        List<Tag> tagsToBeRemoved = new ArrayList<>(task.getTags());
        for (Tag t : tagsToBeRemoved) {
            task.removeTag(t);
        }
        Logger.log("EditTaskController", "Save the tags to task");
        ObservableList<String> chips = tags.getChips();
        for (String name : chips) {
            task.addTag(name);
        }
    }
    
    // REQUIRES: task != null
    private void saveStatus() {
        Logger.log("EditTaskController", "Save status");
        task.setStatus((Status) statusComboBox.getValue());
    }
    
    // REQUIRES: task != null
    private void savePriority() {
        Logger.log("EditTaskController", "Save priority");
        task.getPriority().setImportant(isImportantBox.isSelected());
        task.getPriority().setUrgent(isUrgentBox.isSelected());
    }
    
    @FXML
    public void cancelEditTask() {
        Logger.log("EditTaskController", "Edit Task cancelled.");
        Logger.log("EditTaskController", "Close application");
        Platform.exit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusComboBox.getItems().addAll((Object[]) Status.values());
    }
}
