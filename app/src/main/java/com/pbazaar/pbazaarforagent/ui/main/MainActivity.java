package com.pbazaar.pbazaarforagent.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.pbazaar.pbazaarforagent.R;
import com.pbazaar.pbazaarforagent.helper.PreferenceHelper;
import com.pbazaar.pbazaarforagent.ui.login.LoginActivity;
import com.pbazaar.pbazaarforagent.ui.main.product.ProductPostingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.fragment_container_main_activity)
    FrameLayout fragmentContainer;


    private ProductPostingFragment productPostingFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportActionBar().setElevation(0);

        productPostingFragment = (ProductPostingFragment) getSupportFragmentManager().findFragmentByTag(ProductPostingFragment.class.getSimpleName());

        if (productPostingFragment == null) {
            productPostingFragment = ProductPostingFragment.getInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(fragmentContainer.getId(), productPostingFragment, ProductPostingFragment.class.getSimpleName()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.log_out_menu_button) {
            logout();
        }

        return true;
    }

    private void logout() {
        // clear the customerId saved in preference and start login activity

        PreferenceHelper.getInstance().setCustomerId(PreferenceHelper.CUSTOMER_ID_DEFAULT_VALUE);

        finish();
        Intent logInActivityIntent = new Intent(this, LoginActivity.class);
        startActivity(logInActivityIntent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }
}
