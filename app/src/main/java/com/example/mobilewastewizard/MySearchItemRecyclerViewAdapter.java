package com.example.mobilewastewizard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobilewastewizard.SearchItemFragment.OnListFragmentInteractionListener;
import com.example.mobilewastewizard.backend.Constants;
import com.example.mobilewastewizard.backend.Database.TrashItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TrashItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySearchItemRecyclerViewAdapter extends RecyclerView.Adapter<MySearchItemRecyclerViewAdapter.ViewHolder> {

    private final List<TrashItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Map<Constants.Categories, String> categoryToText;

    public MySearchItemRecyclerViewAdapter(List<TrashItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        this.categoryToText = new HashMap<>();
        this.categoryToText.put(Constants.Categories.BLUE_BIN, "Blue bin");
        this.categoryToText.put(Constants.Categories.BRING_TO_TRANSFER_STATION_OR_WASTE_DEPOT, "Transfer station");
        this.categoryToText.put(Constants.Categories.E_WASTE, "E-Waste");
        this.categoryToText.put(Constants.Categories.GREEN_BIN, "Green bin");
        this.categoryToText.put(Constants.Categories.GREY_BIN, "Gray bin");
        this.categoryToText.put(Constants.Categories.HOUSEHOLD_HAZARDOUS_WASTE, "HHW");
        this.categoryToText.put(Constants.Categories.OVERSIZED_WASTE, "Oversized Waste");
        this.categoryToText.put(Constants.Categories.PROHIBITED_WASTE, "Prohobitied Waste");
        this.categoryToText.put(Constants.Categories.SCRAP_METAL, "Scrap Metal");
        this.categoryToText.put(Constants.Categories.YARD_WASTE, "Yard Waste");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_searchitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public TrashItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
