package persistence;

import model.CompanyList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Models the sample data persistence demo
//URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a writer that writes JSON representation of a company list to file
public class CompanyListWriter {


    protected static final int INDENTS = 5;
    protected PrintWriter writer;
    protected String path;


    //EFFECTS: constructs a new writer that writes to the specified path
    public CompanyListWriter(String path) {
        this.path = path;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if the file in the path cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }


    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();

    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of CompanyList to file
    public void write(CompanyList lists) {
        JSONObject json = lists.toJson();
        saveToFile(json.toString(INDENTS));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    protected void saveToFile(String json) {
        writer.print(json);
    }


}
