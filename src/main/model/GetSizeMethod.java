package model;

public class GetSizeMethod implements CallMethods {

    //EFFECTS: returns the size of the company
    public int call(Company company) {
        return company.getSize();

    }
}
