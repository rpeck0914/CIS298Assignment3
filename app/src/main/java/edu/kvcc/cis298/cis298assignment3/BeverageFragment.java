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

    private static final String ARG_BEVERAGE_ITEM_NUMBER = "beverage_id";

    private Beverage mBeverage;

    private TextView mBeverageDescription;
    private EditText mBeverageItemNumber;
    private EditText mBeveragePackSize;
    private EditText mBeveragePrice;
    private CheckBox mBeverageActiveCheckBox;

    public static BeverageFragment newInstance(String beverageId) {
        Bundle args = new Bundle();
        args.putString(ARG_BEVERAGE_ITEM_NUMBER, beverageId);

        BeverageFragment fragment = new BeverageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String beverageId = getArguments().getString(ARG_BEVERAGE_ITEM_NUMBER);

        mBeverage = BeverageLab.get(getActivity()).getBeverage(beverageId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beverage, container, false);

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
                mBeverage.setActive(b);
            }
        });

        return v;
    }
}
