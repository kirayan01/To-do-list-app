package utility;

import model.Task;
import org.json.JSONArray;
import parsers.TaskParser;
import persistence.Jsonifier;
import java.io.*;
import java.util.List;

// File input/output operations
public class JsonFileIO {
    public static final File jsonDataFile = new File("./resources/json/tasks.json");
    private StringBuilder stringBuilder = new StringBuilder();
    private String input = null;


    // EFFECTS: attempts to read jsonDataFile and parse it
    //           returns a list of tasks from the content of jsonDataFile
    public static List<Task> read() throws IOException {
        InputStream inputStream = new FileInputStream(jsonDataFile);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String input = null;

        while ((input = bufferedReader.readLine()) != null) {
            stringBuilder.append(input);
        }

        TaskParser taskParser = new TaskParser();

        return taskParser.parse(stringBuilder.toString());

    }
    
    // EFFECTS: saves the tasks to jsonDataFile
    public static void write(List<Task> tasks) throws IOException {
        Jsonifier jsonifier = new Jsonifier();
        FileWriter fileWriter = new FileWriter(jsonDataFile);
        fileWriter.write(jsonifier.taskListToJson(tasks).toString());
        fileWriter.close();

    }
}
