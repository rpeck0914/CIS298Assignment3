package edu.kvcc.cis298.cis298assignment3;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BeverageActivity extends SingleFragmentActivity {

    private static final String EXTRA_BEVERAGE_ID = "edu.kvcc.cis298.cis298assignment3.beverage_id";

    public static Intent newIntent(Context packageContext, String beverageId) {
        Intent intent = new Intent(packageContext, BeverageActivity.class);
        intent.putExtra(EXTRA_BEVERAGE_ID, beverageId);
        return intent;
    }

    protected Fragment createFragment() {
        String beverageId = getIntent().getStringExtra(EXTRA_BEVERAGE_ID);
        return new BeverageFragment().newInstance(beverageId);
    }
}
