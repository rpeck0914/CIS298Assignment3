package edu.kvcc.cis298.cis298assignment3;

import android.support.v4.app.Fragment;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeverageListActivity extends SingleFragmentActivity {

    // BeverageListActivity class that inherits from the SingleFragmentActivity class
    protected Fragment createFragment() {
        return new BeverageListFragment();
    }
}
