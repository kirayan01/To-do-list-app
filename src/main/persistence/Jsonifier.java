package persistence;


import model.DueDate;
import model.Priority;
import model.Tag;
import model.Task;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

// Converts model elements to JSON objects
public class Jsonifier {

    // EFFECTS: returns JSON representation of tag
    public static JSONObject tagToJson(Tag tag) {
        JSONObject tagJson = new JSONObject();

        tagJson.put("name", tag.getName());

        return tagJson;
    }

    // EFFECTS: returns JSON representation of priority
    public static JSONObject priorityToJson(Priority priority) {
        JSONObject priorityJson = new JSONObject();

        priorityJson.put("important", priority.isImportant());
        priorityJson.put("urgent", priority.isUrgent());

        return priorityJson;
    }

    // EFFECTS: returns JSON representation of dueDate
    public static JSONObject dueDateToJson(DueDate dueDate) {
        if (dueDate == null) {
            return null;
        } else {
            JSONObject dueDateJson = new JSONObject();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(dueDate.getDate());

            dueDateJson.put("minute", calendar.get(Calendar.MINUTE));
            dueDateJson.put("hour", calendar.get(Calendar.HOUR_OF_DAY));
            dueDateJson.put("day", calendar.get(Calendar.DAY_OF_MONTH));
            dueDateJson.put("month", calendar.get(Calendar.MONTH));
            dueDateJson.put("year", calendar.get(Calendar.YEAR));

            return dueDateJson;
        }
    }

    // EFFECTS: returns JSON representation list of tags
    public static JSONArray tagListToJson(Set<Tag> tags) {
        JSONArray tagArrayJson = new JSONArray();
        for (Tag tag : tags) {
            tagArrayJson.put(tagToJson(tag));
        }
        return tagArrayJson;   // stub
    }

    // EFFECTS: returns JSON representation of task
    public static JSONObject taskToJson(Task task) {
        JSONObject taskJson = new JSONObject();
        taskJson.put("description", task.getDescription());
        if (task.getDueDate() != null) {
            taskJson.put("due-date", dueDateToJson(task.getDueDate()));
        } else {
            taskJson.put("due-date", JSONObject.NULL);
        }
        taskJson.put("priority", priorityToJson(task.getPriority()));
        taskJson.put("tags", tagListToJson(task.getTags()));
        taskJson.put("status", task.getStatus().toString().replace(" ","_"));
        return taskJson;
    }

    // EFFECTS: returns JSON array representing list of tasks
    public static JSONArray taskListToJson(List<Task> tasks) {
        JSONArray taskListJson = new JSONArray();
        for (Task task : tasks) {
            taskListJson.put(taskToJson(task));
        }
        return taskListJson;
    }
}
