package edu.kvcc.cis298.cis298assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Robert Peck on 11/11/2015.
 */
public class BeverageListFragment extends Fragment {
    //BeverageListFragment Class To Hold The Recycler View That Will Populate The Fragement With All
    //The Beverage Objects From The Data File

    // Variable For The RecyclerView To Link To The Layout View For A RecyclerView
    private RecyclerView mBeverageRecyclerView;
    // An Adapter To Set For The RecyclerView
    private BeverageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // onCreateView Inflates An Instance Of The RecyclerView
        View v = inflater.inflate(R.layout.fragment_beverage_list, container, false);
        // Links The RecyclerView Variable To The Layout Created For The RecyclerView
        mBeverageRecyclerView = (RecyclerView) v.findViewById(R.id.beverage_recycler_view);
        // Sets The LayoutManager For The RecyclerView As A LinearLayout
        mBeverageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Calls The UpdateUI Method To Update The Interface
        updateUI();
        // Returns The Newly Created View
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        // onResume Override Method Calls The UpdateUI So That Any Changed Data Will Update In The
        // RecyclerView
        updateUI();
    }

    private void updateUI() {
        // Class Variable Created To Retrieve The List From Our Created Singleton. If The List Doesn't
        // Exist It Will Be Created When Creating This Class Variable
        BeverageLab beverageLab = BeverageLab.get(getActivity());
        // Creates A New Local List And Populates It With The List From Our Singleton
        List<Beverage> beverages = beverageLab.getBeverages();
        // Check To See If The Adapter Is Null
        if(mAdapter == null) {
            // If The Adapter Is Null A New One Will Be Created And Then Used To Set The RecyclerView
            mAdapter = new BeverageAdapter(beverages);
            mBeverageRecyclerView.setAdapter(mAdapter);
        } else {
            // If The Adapter Already Exists This Method Call Will Tell The Program That Data Has Changed
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BeverageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // BeverageHolder Class Holds Each Instance Of A Beverage That Will Be Used In The RecyclerView

        // Layout Variables For The Instance Of Each Beverage
        private TextView mBeverageNameTextView;
        private TextView mBeverageItemNumberTextView;
        private TextView mBeveragePriceTextView;

        // Class Variable For A Beverage
        private Beverage mBeverage;

        // Constructor For BeverageHolder That Accepts A View
        public BeverageHolder(View itemView) {
            super(itemView);
            // Sets An OnClickListener For Each Created Beverage
            itemView.setOnClickListener(this);

            // Links The Layout Variables To The Layout
            mBeverageNameTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_description);
            mBeverageItemNumberTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_item_number);
            mBeveragePriceTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_price);
        }

        public void bindBeverage(Beverage beverage) {
            // Public Method bindBeverage Sets The Layout Outputs For Each Beverage

            mBeverage = beverage;
            mBeverageNameTextView.setText(mBeverage.getItemDescription());
            mBeverageItemNumberTextView.setText(mBeverage.getItemNumber());
            mBeveragePriceTextView.setText("$" + mBeverage.getItemCasePrice());
        }

        @Override
        public void onClick(View view) {
            // When A Beverage From The List Is Selected, This Override Method Will Be Called And
            // And An Intent Will Be Created For Opening The BeveragePagerActivity Class And The Selected
            // Beverage Item Number Will Be Sent Over To Set The Selected Beverage
            Intent intent = BeveragePagerActivity.newIntent(getActivity(), mBeverage.getItemNumber());
            // Starts The Activity With The Created Intent
            startActivity(intent);
        }
    }

    private class BeverageAdapter extends RecyclerView.Adapter<BeverageHolder> {
        // BeverageAdapter Is The Adapter That Will Be Created To Create The RecyclerView

        // Class Variable To Hold The List Of Beverages
        private List<Beverage> mBeverages;

        // Constructor To Set The Class Variable List From The Sent Over List
        public BeverageAdapter(List<Beverage> beverages) {
            mBeverages = beverages;
        }

        @Override
        public BeverageHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // Creates A View For Each Beverage In The List
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_beverage, viewGroup, false);
            // Returns The View To The Beverage Holder
            return new BeverageHolder(v);
        }

        @Override
        public void onBindViewHolder(BeverageHolder beverageHolder, int i) {
            // Method To Get The Position In The Beverage List And Binds The Holder To That Beverage
            Beverage beverage = mBeverages.get(i);
            beverageHolder.bindBeverage(beverage);
        }

        @Override
        public int getItemCount() {
            // Returns The Size Of The List For The Beverages
            return mBeverages.size();
        }
    }
}
