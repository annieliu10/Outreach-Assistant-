package persistence;

import model.CompanyList;
import org.json.JSONObject;

public class CompanyListWriter extends Writer {


    public CompanyListWriter(String path) {
        super(path);
    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of CompanyList to file
    public void write(CompanyList lists) {
        JSONObject json = lists.toJson();
        saveToFile(json.toString(INDENTS));
    }
}
