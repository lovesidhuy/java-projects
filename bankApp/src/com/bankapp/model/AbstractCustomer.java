package com.bankapp.model;

public abstract class AbstractCustomer {
    private String firstName;
    private String lastName;
    private Integer sin;

    public AbstractCustomer(String firstName, String lastName, Integer sin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sin = sin;
    }

    public void accountDetails() {
        System.out.println("Account Details: " + firstName + " " + lastName + " SIN: " + sin);
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public Integer getSin() { return sin; }
    public void setSin(Integer sin) { this.sin = sin; }

    public abstract void saveToDatabase();
}
