package persistence;


//Models the sample data persistence demo
//URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.Company;
import model.CompanyList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads company lists from JSON data stored in file and then add add the old data
// back to work with it
public class Reader {
    private String path;


    //EFFECTS: constructs a new reader that reads from the specified path
    public Reader(String path) {
        this.path = path;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CompanyList read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    private String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private CompanyList parseWorkRoom(JSONObject jsonObject) {
        CompanyList list1 = new CompanyList();
        addCompanies(list1, jsonObject);
        return list1;

    }

    private void addCompanies(CompanyList list, JSONObject jsonObject) {
        JSONArray uncontacted = jsonObject.getJSONArray("Companies which have not been contacted");
        JSONArray contacted = jsonObject.getJSONArray("Companies which have ben contacted");
        JSONArray followedup = jsonObject.getJSONArray("Companies which have been followed-up");

    }

}
