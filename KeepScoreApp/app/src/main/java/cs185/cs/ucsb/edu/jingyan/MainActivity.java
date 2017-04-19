package cs185.cs.ucsb.edu.jingyan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {
    private DatePicker dp;
    private TeamScore teamScore1;
    private TeamScore teamScore2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // set initial date for DatePicker
        dp =  (DatePicker)this.findViewById(R.id.datePicker);
        dp.init(getResources().getInteger(R.integer.init_year),
                getResources().getInteger(R.integer.init_month),
                getResources().getInteger(R.integer.init_day), null);

        teamScore1= (TeamScore) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        teamScore1.setHint(getResources().getString(R.string.auto_hint1),getResources().getString(R.string.hint));

        teamScore2= (TeamScore) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        teamScore2.setHint(getResources().getString(R.string.auto_hint2),getResources().getString(R.string.hint));

    }

    // set activity for button
    public void clearInput(View view) {
        // clear AutoCompleteTextView
        teamScore1.clearText();
        teamScore2.clearText();

        // clear DatePicker
        dp = (DatePicker)findViewById(R.id.datePicker);
        dp.init(getResources().getInteger(R.integer.init_year),
                getResources().getInteger(R.integer.init_month),
                getResources().getInteger(R.integer.init_day), null);
    }
}
