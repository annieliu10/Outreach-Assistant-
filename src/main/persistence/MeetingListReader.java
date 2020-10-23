package persistence;

import model.Company;
import model.Meeting;
import model.SalesMeetings;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.stream.Stream;

public class MeetingListReader {
    private String path;


    //EFFECTS: constructs a new reader that reads from the specified path
    public MeetingListReader(String path) {
        this.path = path;
    }

    public SalesMeetings read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData.trim());
        return parseMeetingsList(jsonObject);
    }


    private String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();

    }

    private SalesMeetings parseMeetingsList(JSONObject jsonObject) {
        SalesMeetings meetings = new SalesMeetings();
        addMeetingsBack(meetings, jsonObject);
        return meetings;
    }

    private void addMeetingsBack(SalesMeetings meetings, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list of meetings");
        for (Object json : jsonArray) {
            JSONObject next = (JSONObject) json;
            addMeeting(next, meetings);
        }

    }

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
        String agenda = json.getString("agenda");
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


    private String convertFromNumToCalendarMonth(int month) {
        if (month == 0) {
            return "January";
        } else if (month == 1) {
            return "February";
        } else if (month == 2) {
            return "March";
        } else if (month == 3) {
            return "April";
        } else if (month == 4) {
            return "May";
        } else if (month == 5) {
            return "June";
        } else if (month == 6) {
            return "July";
        } else if (month == 7) {
            return "August";
        } else if (month == 8) {
            return "September";
        } else if (month == 9) {
            return "October";
        } else if (month == 10) {
            return "November";
        } else {
            return "December";
        }
    }
}
