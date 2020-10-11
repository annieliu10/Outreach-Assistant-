package model;

public class GetInterestMethod implements CallMethods {

    public int call(Company company) {
        return company.getInterestLevel();
    }
}
