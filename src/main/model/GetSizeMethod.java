package model;

public class GetSizeMethod implements CallMethods {

    public int call(Company company) {
        return company.getSize();

    }
}
