package com.pbazaar.pbazaarforagent.ui.dialogs;

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
 * Created by supto on 5/5/17.
 */

public class ForceUpdateDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = ForceUpdateDialog.class.getSimpleName();

    @BindView(R.id.text_view_confirm_update_force_update_dialog)
    TextView confirm;

    @BindView(R.id.text_view_cancel_update_force_update_dialog)
    TextView cancel;

    private OnButtonClicked onButtonClicked;

    public ForceUpdateDialog() {
    }

    public static ForceUpdateDialog newInstance(@NonNull OnButtonClicked onButtonClicked) {
        ForceUpdateDialog forceUpdateDialog = new ForceUpdateDialog();
        forceUpdateDialog.onButtonClicked = onButtonClicked;
        return forceUpdateDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_force_update, null);

        ButterKnife.bind(this, view);


        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return alertDialog;
    }

    @Override
    public void onClick(View view) {
        if (view == confirm) {
            dismiss();
            if (onButtonClicked != null)
                onButtonClicked.onConfirmButtonClicked();
        } else if (view == cancel) {
            dismiss();
            if (onButtonClicked != null)
                onButtonClicked.onCancelButtonClicked();
        }
    }

    public interface OnButtonClicked {
        void onConfirmButtonClicked();

        void onCancelButtonClicked();
    }
}