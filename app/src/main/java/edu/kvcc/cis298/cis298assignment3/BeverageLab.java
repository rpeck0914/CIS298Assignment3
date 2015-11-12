package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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

        for(int i = 0; i < 100; i++){
            Beverage beverage = new Beverage();
            beverage.setItemNumber(i + "");
            beverage.setItemDescription("Beverage #" + i);
            beverage.setItemPackSize(i + "");
            beverage.setItemCasePrice(Double.parseDouble(i + ""));
            beverage.setActive(i % 2 == 0);
            mBeverages.add(beverage);
        }

        ////TODO Read File In Here!!
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
}
