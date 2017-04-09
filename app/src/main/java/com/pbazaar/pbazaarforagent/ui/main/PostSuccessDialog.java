package com.pbazaar.pbazaarforagent.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pbazaar.pbazaarforagent.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by supto on 4/9/17.
 */

public class PostSuccessDialog extends DialogFragment implements View.OnClickListener {

    @BindView(R.id.tv_positive_post_success_dialog_button)
    TextView positiveButtonTv;

    @BindView(R.id.tv_negative_post_success_dialog_button)
    TextView negativeButtonTv;

    private PostSuccessDialog.OnButtonClicked onButtonClicked;

    public PostSuccessDialog() {
    }


    public static PostSuccessDialog getInstance(PostSuccessDialog.OnButtonClicked onButtonClicked) {
        PostSuccessDialog postSuccessDialog = new PostSuccessDialog();
        postSuccessDialog.onButtonClicked = onButtonClicked;
        return postSuccessDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_dialog_on_post_success, null);

        ButterKnife.bind(this, view);

        positiveButtonTv.setOnClickListener(this);
        negativeButtonTv.setOnClickListener(this);


        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        if (v == positiveButtonTv) {
            dismiss();
            if (onButtonClicked != null)
                onButtonClicked.onPositiveButtonClicked();
        } else if (v == negativeButtonTv) {
            dismiss();
            if (onButtonClicked != null)
                onButtonClicked.onNegativeButtonClicked();
        }
    }

    public interface OnButtonClicked {
        void onPositiveButtonClicked();

        void onNegativeButtonClicked();
    }
}