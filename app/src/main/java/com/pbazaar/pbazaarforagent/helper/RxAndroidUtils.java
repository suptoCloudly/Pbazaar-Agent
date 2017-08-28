package com.pbazaar.pbazaarforagent.helper;

import io.reactivex.disposables.Disposable;

/**
 * Created by supto on 7/31/17.
 */

public class RxAndroidUtils {


//    public static Observable<String> getEditTextObservable(@NonNull EditText editText) {
//        // method that takes a editText as input and returns an observable
//
//        return RxTextView.textChanges(editText)
//
//                .map(new Function<CharSequence, String>() {
//                    @Override
//                    public String apply(@NonNull CharSequence charSequence) throws Exception {
//                        return charSequence.toString();
//                    }
//                });
//    }


    public static void disposeDisposable(Disposable disposable) {
        // dispose a disposable if not disposed

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public static void disposeDisposable(Disposable... disposables) {
        // dispose a disposable if not disposed

        for (Disposable disposable : disposables) {

            if (disposable != null && !disposable.isDisposed()) {
                disposable.dispose();
            }
        }

    }
}
