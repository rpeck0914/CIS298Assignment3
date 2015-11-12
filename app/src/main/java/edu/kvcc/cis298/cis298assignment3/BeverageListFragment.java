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

    private RecyclerView mBeverageRecyclerView;
    private BeverageAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beverage_list, container, false);
        mBeverageRecyclerView = (RecyclerView) v.findViewById(R.id.beverage_recycler_view);
        mBeverageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        BeverageLab beverageLab = BeverageLab.get(getActivity());
        List<Beverage> beverages = beverageLab.getBeverages();
        if(mAdapter == null) {
            mAdapter = new BeverageAdapter(beverages);
            mBeverageRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private class BeverageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBeverageNameTextView;
        private TextView mBeverageItemNumberTextView;
        private TextView mBeveragePriceTextView;

        private Beverage mBeverage;

        public BeverageHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mBeverageNameTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_description);
            mBeverageItemNumberTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_item_number);
            mBeveragePriceTextView = (TextView) itemView.findViewById(R.id.list_item_beverage_price);
        }

        public void bindBeverage(Beverage beverage) {
            mBeverage = beverage;
            mBeverageNameTextView.setText(mBeverage.getItemDescription());
            mBeverageItemNumberTextView.setText(mBeverage.getItemNumber());
            mBeveragePriceTextView.setText("$" + mBeverage.getItemCasePrice());
        }

        @Override
        public void onClick(View view) {
            Intent intent = BeverageActivity.newIntent(getActivity(), mBeverage.getItemNumber());
            startActivity(intent);
        }
    }

    private class BeverageAdapter extends RecyclerView.Adapter<BeverageHolder> {

        private List<Beverage> mBeverages;

        public BeverageAdapter(List<Beverage> beverages) {
            mBeverages = beverages;
        }

        @Override
        public BeverageHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_beverage, viewGroup, false);
            return new BeverageHolder(v);
        }

        @Override
        public void onBindViewHolder(BeverageHolder beverageHolder, int i) {
            Beverage beverage = mBeverages.get(i);
            beverageHolder.bindBeverage(beverage);
        }

        @Override
        public int getItemCount() {
            return mBeverages.size();
        }
    }
}
