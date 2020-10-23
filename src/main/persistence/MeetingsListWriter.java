package persistence;

import model.SalesMeetings;
import org.json.JSONObject;

public class MeetingsListWriter extends Writer {
    public MeetingsListWriter(String path) {
        super(path);
    }

    public void write(SalesMeetings meetings) {
        JSONObject json = meetings.toJson();
        saveToFile(json.toString(INDENTS));
    }
}
