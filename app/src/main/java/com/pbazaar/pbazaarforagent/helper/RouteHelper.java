package com.pbazaar.pbazaarforagent.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.pbazaar.pbazaarforagent.R;


/**
 * Created by supto on 8/26/17.
 */

public class RouteHelper {


    public static void startActivity(@NonNull Context context, @NonNull Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public static void startActivity(@NonNull Context context, @NonNull Class<?> destinationClass, Bundle bundle) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }


    public static void startActivityWithFinish(@NonNull Context context, @NonNull Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        ((Activity) context).finish();
    }

    public static void startActivityWithFinish(@NonNull Context context, @NonNull Class<?> destinationClass, Bundle bundle) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        ((Activity) context).finish();
    }

    public static void startActivityWithClearBackStack(@NonNull Context context, @NonNull Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        ((Activity) context).finish();
    }
}
