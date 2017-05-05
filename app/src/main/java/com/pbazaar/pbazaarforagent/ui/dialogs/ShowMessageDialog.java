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

public class ShowMessageDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = ShowMessageDialog.class.getSimpleName();

    private static final String ARG_MESSAGE = "arg_message";


    @BindView(R.id.text_view_message_show_message_dialog)
    TextView messageTv;

    @BindView(R.id.text_view_confirm_show_message_dialog)
    TextView confirmTv;

    private String message = "";

    public ShowMessageDialog() {
    }

    public static ShowMessageDialog newInstance(@NonNull String message) {
        ShowMessageDialog showMessageDialog = new ShowMessageDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_MESSAGE, message);
        showMessageDialog.setArguments(bundle);
        return showMessageDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_show_message, null);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);
        }

        messageTv.setText(message);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();

        confirmTv.setOnClickListener(this);

        return alertDialog;
    }

    @Override
    public void onClick(View view) {
        if (view == confirmTv) {
            dismiss();
        }
    }
}
