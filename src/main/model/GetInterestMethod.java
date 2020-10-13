package model;

public class GetInterestMethod implements CallMethods {

    //EFFECTS: returns the interest level of the company
    public int call(Company company) {
        return company.getInterestLevel();
    }
}
