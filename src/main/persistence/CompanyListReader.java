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

// Represents a reader that reads company lists from JSON data stored in file and then retrieve the old data
// to work with
public class CompanyListReader {
    private String path;


    //EFFECTS: constructs a new reader that reads from the specified path
    public CompanyListReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads the company Lists from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CompanyList read() throws IOException {
        String jsonData = readFile(path);
        JSONObject jsonObject = new JSONObject(jsonData.trim());
        return parseCompanyList(jsonObject);
    }

    // EFFECTS: reads the data in the file as strings and returns it
    protected String readFile(String path) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses the company list from JSONObject and returns it
    protected CompanyList parseCompanyList(JSONObject jsonObject) {
        CompanyList list1 = new CompanyList();
        addCompanies(list1, jsonObject);
        return list1;

    }

    // MODIFIES: CompanyList
    // EFFECTS: parses the companies in the 3 sub lists within the JSONObject that contays sub JSONArrays
    // and adds them back to CompanyList
    private void addCompanies(CompanyList list, JSONObject jsonObject) {
        JSONArray uncontacted = jsonObject.getJSONArray("Companies which have not been contacted");
        JSONArray contacted = jsonObject.getJSONArray("Companies which have ben contacted");
        JSONArray followedup = jsonObject.getJSONArray("Companies which have been followed-up");
        addCompanies1(list, uncontacted);
        addCompanies1(list, contacted);
        addCompanies1(list, followedup);
    }

    //MODIFIES: CompanyList
    // EFFECTS: parses the companies from a JSONObject in each sub list  and adds them back to the CompanyList
    private void addCompanies1(CompanyList list, JSONArray array1) {
        for (Object next : array1) {
            JSONObject company = (JSONObject) next;
            addCompany(list, company);
        }
    }

    // MODIFIES: CompanyList
    // EFFECTS: parses a company from JSONObject
    private void addCompany(CompanyList list, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int size = jsonObject.getInt("size");
        String industry = jsonObject.getString("industry");
        String employerName = jsonObject.getString("employer name");
        int interestLevel = jsonObject.getInt("interest level");
        boolean contactStatus = jsonObject.getBoolean("contact status");
        boolean followUpStatus = jsonObject.getBoolean("follow-up status");
        Company company = new Company(size, industry, name, employerName);
        reconstructAnddetermineWhichListToPutIn(company, list, interestLevel, contactStatus, followUpStatus);

    }


    //MODIFIES: CompanyList
    //EFFECTS: reconstructs a company based on the old data stored and retrieve it back by
    // adding them back to the sub-lists in CompanyList once again
    private void reconstructAnddetermineWhichListToPutIn(Company company, CompanyList list, int interest,
                                                         boolean contact, boolean followup) {
        if (contact) {
            company.contacted(interest);
        }
        if (followup) {
            company.followedUp();
        }

        list.reAddCompanies(company);

    }

}
