package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeverageLab {

    private static  BeverageLab sBeverageLab;
    private List<Beverage> mBeverages;

    public static BeverageLab get(Context context) {
        if(sBeverageLab == null) {
            sBeverageLab = new BeverageLab(context);
        }
        return sBeverageLab;
    }

    private BeverageLab(Context context) {
        mBeverages = new ArrayList<>();
//        for(int i = 0; i < 100; i++){
//            Beverage beverage = new Beverage();
//            beverage.setItemNumber(i + "");
//            beverage.setItemDescription("Beverage #" + i);
//            beverage.setItemPackSize(i + "");
//            beverage.setItemCasePrice(Double.parseDouble(i + ""));
//            beverage.setActive(i % 2 == 0);
//            mBeverages.add(beverage);
//        }
        loadBeveragesInList(context);
    }

    public List<Beverage> getBeverages() { return mBeverages; }

    public Beverage getBeverage(String itemNumber) {
        for(Beverage beverage : mBeverages) {
            if(beverage.getItemNumber().equals(itemNumber)) {
                return beverage;
            }
        }
        return null;
    }

    private void loadBeveragesInList(Context context) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(context.getResources().openRawResource(R.raw.beverage_list));

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String parts[] = line.split(",");

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

                mBeverages.add(new Beverage(itemNumber, itemDescription, itemPackSize, Double.parseDouble(itemPrice), isActive));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
