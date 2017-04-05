package com.pbazaar.pbazaarforagent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.model.LocationSpinnerDataModel;

import java.util.ArrayList;

/**
 * Created by supto on 4/2/17.
 */

public class CustomAreaSpinnerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LocationSpinnerDataModel> itemList;

    public CustomAreaSpinnerAdapter(Context context, ArrayList<LocationSpinnerDataModel> itemList) {

        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.item_spinner_dropdown, parent,
                false);
        TextView make = (TextView) row.findViewById(R.id.spinner_item_text);

        make.setText(itemList.get(position).getLocationName());
        return row;
    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View row = inflater.inflate(R.layout.item_spinner_dropdown, parent,
//                false);
//        TextView make = (TextView) row.findViewById(R.id.spinner_item_text);
//
//        make.setText(itemList.get(position).getLocationName());
//        return row;
//    }
//
//
//    public View getDropDownView(int position, View convertView, ViewGroup parent) {
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View row = inflater.inflate(R.layout.item_spinner_dropdown, parent,
//                false);
//        TextView make = (TextView) row.findViewById(R.id.spinner_item_text);
//
//        make.setText(itemList.get(position).getLocationName());
//        return row;
//    }
//
//    @Override
//    public int getCount() {
//        return super.getCount();
//    }
}
