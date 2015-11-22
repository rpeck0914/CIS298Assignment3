package edu.kvcc.cis298.cis298assignment3;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class Beverage {
    // Class to hold the item of a beverage

    // Private variables for the class Beverage
    private String mItemNumber;
    private String mItemDescription;
    private String mItemPackSize;
    private Double mItemCasePrice;
    private Boolean mActive;

    // Empty Constructor for the Beverage Class
    public Beverage() {}

    // Constructor for the Class Beverage to make a new Beverage with all it's variables
    public Beverage(String itemNumber, String description, String packSize, Double price, Boolean active) {
        mItemNumber = itemNumber;
        mItemDescription = description;
        mItemPackSize = packSize;
        mItemCasePrice = price;
        mActive = active;
    }

    // Getters and Setters for the Class Beverage
    public String getItemNumber() {
        return mItemNumber;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public String getItemPackSize() {
        return mItemPackSize;
    }

    public Double getItemCasePrice() {
        return mItemCasePrice;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }
}
