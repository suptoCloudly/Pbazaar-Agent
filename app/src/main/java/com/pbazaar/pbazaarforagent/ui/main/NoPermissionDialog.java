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

/**
 * Created by Pathshala on 04-Mar-17.
 */

public class NoPermissionDialog extends DialogFragment {

    private TextView okButtonText;

    private OnConfirmButtonmClickListener onConfirmButtonmClickListener;

    public NoPermissionDialog() {
    }

    public void setOnConfirmButtonmClickListener(OnConfirmButtonmClickListener onConfirmButtonmClickListener) {
        this.onConfirmButtonmClickListener = onConfirmButtonmClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_dialog_permission_required, null);

        okButtonText = (TextView) view.findViewById(R.id.no_permission_description_button);

        okButtonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (onConfirmButtonmClickListener != null) {
                    onConfirmButtonmClickListener.onConfirmButtonClicked();
                }
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        return alertDialog;
    }

    public interface OnConfirmButtonmClickListener {
        void onConfirmButtonClicked();
    }
}
