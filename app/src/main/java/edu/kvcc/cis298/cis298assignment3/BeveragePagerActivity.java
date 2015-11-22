package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeveragePagerActivity extends FragmentActivity {
    // BeveragePagerActivity that holds the fragment for a single selected Beverage

    // ViewPager Variable to hold the pager layout
    private ViewPager mViewPager;
    // List Variable to hold the List of Beverages
    private List<Beverage> mBeverages;

    // Static Variable to hold a unique String used for Intent calls
    private static final String EXTRA_BEVERAGE_ID = "edu.kvcc.cis298.cis298assignment3.beverage_id";

    // Static method used for intent calls
    public static Intent newIntent(Context context, String beverageId) {
        // Creates a new Intent of this activity
        Intent intent = new Intent(context, BeveragePagerActivity.class);
        // Puts the key in as an extra for the Intent just created
        intent.putExtra(EXTRA_BEVERAGE_ID, beverageId);
        // Returns the Intent so this activity can be called
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_pager);

        // Pulls the extra out that was added when the static method was called to make an Intent for
        // this activity
        String beverageId = getIntent().getStringExtra(EXTRA_BEVERAGE_ID);

        // Sets the ViewPager by finding the pager layout in the project
        mViewPager = (ViewPager) findViewById(R.id.activity_beverage_pager_view_pager);

        // Retrieves the List of Beverages from BeverageLab and stores it in the local mBeverages variable
        mBeverages = BeverageLab.get(this).getBeverages();

        // Creates a FragmentManager and sets it to the SupportFragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Sets the Adapter for the ViewPager
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Beverage beverage = mBeverages.get(i);
                return BeverageFragment.newInstance(beverage.getItemNumber());
            }

            @Override
            public int getCount() {
                return mBeverages.size();
            }
        });

        // Loops through the List of Beverages to find the selected Beverage and then sets the ViewPagers
        // current item to that Beverage
        for (int i = 0; i < mBeverages.size(); i++) {
            if (mBeverages.get(i).getItemNumber().equals(beverageId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
