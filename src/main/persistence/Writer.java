package persistence;


//Models the sample data persistence demo
//URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.CompanyList;
import org.json.JSONObject;

import javax.print.attribute.standard.PrinterName;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of lists of companies to file
public class Writer {
    private static final int INDENTS = 5;
    private PrintWriter writer;
    private String path;


    //EFFECTS: constructs a new writer that writes to the specified path
    public Writer(String path) {
        this.path = path;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if the file in the path cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of CompanyList to file
    public void write(CompanyList lists) {
        JSONObject json = lists.toJson();
        saveToFile(json.toString(INDENTS));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();

    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
