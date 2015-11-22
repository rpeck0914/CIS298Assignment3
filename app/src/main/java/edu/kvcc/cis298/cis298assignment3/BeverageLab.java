package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeverageLab {
    // Singleton class to hold the arrayList of Beverages

    // Creates a static instance of the class BeverageLab
    private static  BeverageLab sBeverageLab;
    // Creates a new List with type Beverage to store our arrayList of Beverages
    private List<Beverage> mBeverages;

    // Public Constructor that accepts a Context, If an instance of the Singleton doesn't exist then
    // a new one is created with the past over context. If an instance of the class already exists then
    // it will just return the already created one.
    public static BeverageLab get(Context context) {
        if(sBeverageLab == null) {
            sBeverageLab = new BeverageLab(context);
        }
        return sBeverageLab;
    }

    // Private Constructor that is called when a new instance of BeverageLab is created that will instantiate
    // the List as a new array list then call the method to read the data from a file and fill it in
    // the arrayList
    private BeverageLab(Context context) {
        mBeverages = new ArrayList<>();
        loadBeveragesInList(context);

        //        for(int i = 0; i < 100; i++){
//            Beverage beverage = new Beverage();
//            beverage.setItemNumber(i + "");
//            beverage.setItemDescription("Beverage #" + i);
//            beverage.setItemPackSize(i + "");
//            beverage.setItemCasePrice(Double.parseDouble(i + ""));
//            beverage.setActive(i % 2 == 0);
//            mBeverages.add(beverage);
//        }
    }

    // Public method to return the arrayList of Beverages when called
    public List<Beverage> getBeverages() { return mBeverages; }

    // Public Method that accepts a String and then finds that Beverage in the List of Beverages then
    // returns that matching Beverage. If no Beverage matches the String then null is returned since
    // it doesn't exist in the List.
    public Beverage getBeverage(String itemNumber) {
        for(Beverage beverage : mBeverages) {
            if(beverage.getItemNumber().equals(itemNumber)) {
                return beverage;
            }
        }
        return null;
    }

    // Private method to read the data file and fill it into the arrayList of Beverages
    private void loadBeveragesInList(Context context) {

        // Declares a new scanner class variable and sets it to null
        Scanner scanner = null;

        // Try / Catch to catch any errors that may occur in the file reading
        try {
            // Instantiates the scanner variable and sets the data file to it for reading into the app
            scanner = new Scanner(context.getResources().openRawResource(R.raw.beverage_list));

            // While loop to loop through all the data in the file
            while(scanner.hasNextLine()) {
                // String to store a line of data in the file
                String line = scanner.nextLine();
                // String array to hold each data piece in a separate index of the array
                String parts[] = line.split(",");

                // Pulls apart each data and sets them into their own Strings and changes them to the
                // appropriate type of variable they need to be
                String itemNumber = parts[0];
                String itemDescription = parts[1];
                String itemPackSize = parts[2];
                String itemPrice = parts[3];
                String itemActive = parts[4];

                boolean isActive;
                if (itemActive.equals("True")) {
                    isActive = true;
                } else {
                    isActive = false;
                }

                // Creates a new instance of a Beverage with the line of data pulled from the file then
                // adds it to the arrayList
                mBeverages.add(new Beverage(itemNumber, itemDescription, itemPackSize, Double.parseDouble(itemPrice), isActive));
            }

            // Catch to catch any exceptions and then outputs them to see the error
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Finally closes the file after all the file reading is done.
            scanner.close();
        }
    }
}
