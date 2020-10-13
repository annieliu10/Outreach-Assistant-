package model;

// purpose of this interface is to reduce code duplication in the CompanyList class. It is used to pass a method call
// as a param in a few prioritization methods the CompanyList class.
public interface CallMethods {

    //EFFECTS: call one of the methods owned by company
    int call(Company company);
}
