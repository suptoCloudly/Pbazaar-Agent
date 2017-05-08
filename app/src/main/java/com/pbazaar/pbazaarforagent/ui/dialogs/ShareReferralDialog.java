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
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by supto on 5/8/17.
 */

public class ShareReferralDialog extends DialogFragment implements View.OnClickListener {

    private static final String TAG = ShareReferralDialog.class.getSimpleName();


    @BindView(R.id.text_view_message_share_referral_dialog)
    TextView messageTv;

    @BindView(R.id.text_view_cancel_share_referral_dialog)
    TextView cancelTv;

    @BindView(R.id.text_view_confirm_share_referral_dialog)
    TextView shareTv;

    private OnShareButtonClickListener onShareButtonClickListener;

    public ShareReferralDialog() {
    }

    public static ShareReferralDialog newInstance(@NonNull OnShareButtonClickListener onShareButtonClickListener) {
        ShareReferralDialog shareReferralDialog = new ShareReferralDialog();
        shareReferralDialog.onShareButtonClickListener = onShareButtonClickListener;
        return shareReferralDialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_share_referral, null);
        ButterKnife.bind(this, view);

        cancelTv.setOnClickListener(this);
        shareTv.setOnClickListener(this);
        messageTv.setText("Your referral code is " + PreferenceHelper.getInstance().getCustomerId());


        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false)
                .create();


        return alertDialog;
    }

    @Override
    public void onClick(View view) {
        if (view == shareTv) {
            dismiss();
            onShareButtonClickListener.onShareButtonClicked();
        } else if (view == cancelTv) {
            dismiss();
        }
    }

    public interface OnShareButtonClickListener {
        void onShareButtonClicked();
    }
}