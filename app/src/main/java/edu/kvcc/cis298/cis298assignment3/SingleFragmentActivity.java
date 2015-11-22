package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    // Abstract class for creating the fragment that extends this class
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // Creates a new FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // Creates a new Fragment and uses the FragmentManager to find the Container that will hold
        // the new created Fragment
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // Checks to see if the Container is empty, If it is a new Fragment is created in the fragment
        // container.
        if(fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
