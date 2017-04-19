package cs185.cs.ucsb.edu.jingyanreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.WindowManager;


public class ReminderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ReminderDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ReminderDetailFragment.ARG_ITEM_ID));
            arguments.putBoolean(ReminderDetailFragment.IS_ACTIVITY, true);
            ReminderDetailFragment fragment = new ReminderDetailFragment();
            fragment.setOnCloseListener(new ReminderDetailFragment.OnCloseListener() {
                @Override
                public void OnClose() {
                    onBackPressed();
                }
            });
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.reminder_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ReminderListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
