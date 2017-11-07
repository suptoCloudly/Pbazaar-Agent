package com.pbazaar.pbazaarforagent.ui.main.new_product;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pbazaar.pbazaarforagent.R;

import butterknife.ButterKnife;


public class NewProductPostingFragment extends Fragment {

    public static final String TAG = NewProductPostingFragment.class.getSimpleName();


    public NewProductPostingFragment() {
        // Required empty public constructor
    }


    public static NewProductPostingFragment newInstance() {
        NewProductPostingFragment fragment = new NewProductPostingFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_product_postmg, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
