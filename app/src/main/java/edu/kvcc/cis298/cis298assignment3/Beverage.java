package edu.kvcc.cis298.cis298assignment3;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class Beverage {
    private String mItemNumber;
    private String mItemDescription;
    private String mItemPackSize;
    private Double mItemCasePrice;
    private Boolean mActive;

    public Beverage() {}

    public Beverage(String itemNumber, String description, String packSize, Double price, Boolean active) {
        mItemNumber = itemNumber;
        mItemDescription = description;
        mItemPackSize = packSize;
        mItemCasePrice = price;
        mActive = active;
    }

    public String getItemNumber() {
        return mItemNumber;
    }

    public void setItemNumber(String itemNumber) {
        mItemNumber = itemNumber;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String itemDescription) {
        mItemDescription = itemDescription;
    }

    public String getItemPackSize() {
        return mItemPackSize;
    }

    public void setItemPackSize(String itemPackSize) {
        mItemPackSize = itemPackSize;
    }

    public Double getItemCasePrice() {
        return mItemCasePrice;
    }

    public void setItemCasePrice(Double itemCasePrice) {
        mItemCasePrice = itemCasePrice;
    }

    public Boolean getActive() {
        return mActive;
    }

    public void setActive(Boolean active) {
        mActive = active;
    }
}
