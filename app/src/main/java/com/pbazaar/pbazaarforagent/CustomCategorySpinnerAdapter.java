package com.pbazaar.pbazaarforagent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.model.SubCategoryModel;

import java.util.ArrayList;

/**
 * Created by Supto on 4/5/2017.
 */

public class CustomCategorySpinnerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SubCategoryModel> categoryModelArrayList;

    public CustomCategorySpinnerAdapter(Context context, ArrayList<SubCategoryModel> categoryModelArrayList) {

        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
    }

    @Override
    public int getCount() {
        return categoryModelArrayList.size();
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
        View row = inflater.inflate(R.layout.item_spinner_dropdown, parent, false);
        TextView make = (TextView) row.findViewById(R.id.spinner_item_text);

        make.setText(categoryModelArrayList.get(position).getName());
        return row;
    }
}
