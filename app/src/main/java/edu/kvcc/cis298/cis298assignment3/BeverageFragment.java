package edu.kvcc.cis298.cis298assignment3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeverageFragment extends Fragment{
    // BeverageFragment Class which is a fragment that's displayed to show an individual Beverage item

    // Static String to use for creating an argument, that will then be used to create a new intent
    // and then this fragment can use that argument within it's own class
    private static final String ARG_BEVERAGE_ITEM_NUMBER = "beverage_id";

    // Beverage Class variable to be used to make a new Beverage in the BeverageFragment
    private Beverage mBeverage;

    // Layout variables for the BeverageFragment layout
    private TextView mBeverageDescription;
    private EditText mBeverageItemNumber;
    private EditText mBeveragePackSize;
    private EditText mBeveragePrice;
    private CheckBox mBeverageActiveCheckBox;

    // Static method to use when and activity wants to create a new intent for this fragment
    public static BeverageFragment newInstance(String beverageId) {
        // Creates a new bundle to hold the arguments needed to start the fragment
        Bundle args = new Bundle();
        // Adds the argument into the bundle as a string
        args.putString(ARG_BEVERAGE_ITEM_NUMBER, beverageId);

        // Creates a new BeverageFragment so the arguments can be set in it
        BeverageFragment fragment = new BeverageFragment();
        // Sets the arguments to the fragment just created
        fragment.setArguments(args);

        // Returns the fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieves the arguments sent over when the new Intent was created and started
        String beverageId = getArguments().getString(ARG_BEVERAGE_ITEM_NUMBER);
        // Creates a local Beverage and retrieves it's information out of the BeverageLab class to be
        // set as the Beverage selected by the user
        mBeverage = BeverageLab.get(getActivity()).getBeverage(beverageId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Creates a view with the wanted fragment and inflater
        View v = inflater.inflate(R.layout.fragment_beverage, container, false);

        // Finds the correct layout items and sets them to the class variables, then sets each layout
        // item with the appropriate text to be output to the layout.
        mBeverageDescription = (TextView) v.findViewById(R.id.beverage_description);
        mBeverageDescription.setText(mBeverage.getItemDescription());

        mBeverageItemNumber = (EditText) v.findViewById(R.id.beverage_item_number);
        mBeverageItemNumber.setText(mBeverage.getItemNumber());

        mBeveragePackSize = (EditText) v.findViewById(R.id.beverage_pack_size);
        mBeveragePackSize.setText(mBeverage.getItemPackSize());

        mBeveragePrice = (EditText) v.findViewById(R.id.beverage_item_price);
        mBeveragePrice.setText("$" +mBeverage.getItemCasePrice().toString());

        mBeverageActiveCheckBox = (CheckBox) v.findViewById(R.id.beverage_active_check_box);
        mBeverageActiveCheckBox.setChecked(mBeverage.getActive());
        mBeverageActiveCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // Override method to set the checkbox for the item to active or not-active
                mBeverage.setActive(b);
            }
        });

        // Returns the new created view.
        return v;
    }
}
