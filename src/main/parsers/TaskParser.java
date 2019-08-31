package parsers;

import model.DueDate;
import model.Priority;
import model.Tag;
import model.Status;
import model.Task;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.*;


// Represents Task parser
public class TaskParser {
    private Status status;
    private Tag tag;

    // EFFECTS: iterates over every JSONObject in the JSONArray represented by the input
    // string and parses it as a task; each parsed task is added to the list of tasks.
    // Any task that cannot be parsed due to malformed JSON data is not added to the
    // list of tasks.
    // Note: input is a string representation of a JSONArray
    public List<Task> parse(String input) {
        List<Task> listTask = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(input);
        for (Object o : jsonArray) {
            try {
                JSONObject j = (JSONObject) o;
                String description = jsonToDescription(j);
                DueDate dueDate = jsonToNullDueDate(j);
                Priority priority = jsonToPriority(j);
                Status status = jsonToStatus(j);
                List<Tag> listOfTag = jsonToTags(j);
                addToTask(listTask, listOfTag,j,description, dueDate,priority,status);

            } catch (JSONException e) {
                continue;
            }
        }
        return listTask;
    }

    public void addToTask(List<Task> listTask,List<Tag> listOfTag, JSONObject j,
                          String description, DueDate dueDate,
                          Priority priority, Status status) {
        Task task = new Task(description);
        task.setDueDate(dueDate);
        task.setPriority(priority);
        task.setStatus(status);
        for (Tag t : listOfTag) {
            task.addTag(t);
        }
        listTask.add(task);
    }




    public String jsonToDescription(JSONObject j) throws JSONException {
        try {

            String description = j.getString("description");
            return description;

        } catch (JSONException e) {
            throw new JSONException("Parsing description error");
        }
    }

    public DueDate jsonToNullDueDate(JSONObject j) throws JSONException {
        Object dueDateJson = j.get("due-date");
        if (dueDateJson == JSONObject.NULL) {
            return null;
        } else {
            return jsonToDueDate(j);
        }

    }

    public DueDate jsonToDueDate(JSONObject j) throws JSONException {
        DueDate dueDate = new DueDate();
        JSONObject j1 = j.getJSONObject("due-date");
        Date date = new GregorianCalendar(j1.getInt("minute"), j1.getInt("hour"),
                j1.getInt("day"), j1.getInt("month"),
                j1.getInt("year")).getTime();
        dueDate.setDueDate(date);
        if (!(j1.get("minute") instanceof Integer)
                || !(j1.get("hour") instanceof Integer)
                || !(j1.get("day") instanceof Integer)
                || !(j1.get("month") instanceof Integer)
                || !(j1.get("year") instanceof Integer)) {
            throw new JSONException("Parsing due date error");
        }

        return dueDate;
    }

    public Priority jsonToPriority(JSONObject j) throws JSONException {
        try {
            Priority priority = new Priority();
            JSONObject priorityJson = j.getJSONObject("priority");
            Object priorityLevel1 = priorityJson.get("important");
            Object priorityLevel2 = priorityJson.get("urgent");

            if (priorityLevel1 instanceof Boolean
                    && priorityLevel2 instanceof Boolean) {
                priority.setUrgent((Boolean) priorityLevel2);
                priority.setImportant((Boolean) priorityLevel1);
                return priority;
            } else {
                throw new JSONException("Parsing priority error");
            }
        } catch (JSONException e) {
            throw new JSONException("Parsing priority error");
        }
    }


    public Status jsonToStatus(JSONObject j) throws JSONException {
        try {
            String statusName = j.getString("status");
            if (statusName.equals("TODO")) {
                status = Status.TODO;
            } else if (statusName.equals("UP_NEXT")) {
                status = Status.UP_NEXT;
            } else if (statusName.equals("IN_PROGRESS")) {
                status = Status.IN_PROGRESS;
            } else if (statusName.equals("DONE")) {
                status = Status.DONE;
            } else {
                throw new JSONException("Parsing status error");
            }
            return status;
        } catch (JSONException e) {
            throw new JSONException("Parsing status error");
        }
    }


    public List<Tag> jsonToTags(JSONObject j) throws JSONException {
        try {
            List<Tag> listTags = new ArrayList<>();
            JSONArray tags = j.getJSONArray("tags");

            for (Object tag : tags) {

                JSONObject tagJson = (JSONObject) tag;
                String tagName = tagJson.getString("name");
                listTags.add(new Tag(tagName));

            }
            return listTags;
        } catch (JSONException e) {
            throw new JSONException("Parsing list of tags error");
        }
    }




}
