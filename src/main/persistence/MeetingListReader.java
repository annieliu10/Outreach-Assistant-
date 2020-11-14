package persistence;

import model.Company;
import model.Meeting;
import model.SalesMeetings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;


//Models the sample data persistence demo
//URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads sales meetings list from JSON data stored in file and then retrieve the old data
// to work with


public class MeetingListReader {
    private String path;


    //EFFECTS: constructs a new reader that reads from the specified path
    public MeetingListReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads the list of sales meetings from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SalesMeetings read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData.trim());
        return parseMeetingsList(jsonObject);
    }


    // EFFECTS: reads the data in the file as strings and returns it
    private String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();

    }

    // EFFECTS: parses the sales meeting list from JSONObject and returns it
    private SalesMeetings parseMeetingsList(JSONObject jsonObject) {
        SalesMeetings meetings = new SalesMeetings();
        addMeetingsBack(meetings, jsonObject);
        return meetings;
    }

    // MODIFIES: meetings
    // EFFECTS: parses the meetings within the JSONObject that contains JSONArrays
    // and adds them back to SalesMeetings
    private void addMeetingsBack(SalesMeetings meetings, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of meetings");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addMeeting(next, meetings);
        }

    }


    // MODIFIES: meetings
    // EFFECTS: parses a meeting from a JSONObject
    private void addMeeting(JSONObject json, SalesMeetings meetings) {
        String companyName = json.getString("name");
        int size = json.getInt("size");
        String industry = json.getString("industry");
        String employerName = json.getString("employer name");
        int interestLevel = json.getInt("interest level");
        boolean contactStatus = json.getBoolean("contact status");
        boolean followUpStatus = json.getBoolean("follow-up status");
        int year = json.getInt("year");
        int month = json.getInt("month");
        int date = json.getInt("date");
        Company company = new Company(size, industry, companyName, employerName);
        if (contactStatus) {
            company.contacted(interestLevel);
        }
        if (followUpStatus) {
            company.followedUp();
        }

        String inputtedMonth = convertFromNumToCalendarMonth(month);
        Meeting meeting = new Meeting(company, year, inputtedMonth, date);
        meetings.reAddMeetingsBack(meeting);

    }


    //EFFECTS: converts from a numerical month to a string month
    private String convertFromNumToCalendarMonth(int month) {
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        return months.get(month);
    }
}
