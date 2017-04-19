package cs185.cs.ucsb.edu.jingyanreminder;


import android.app.DialogFragment;
import android.os.Bundle;


import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import cs185.cs.ucsb.edu.jingyanreminder.reminder.ReminderContent;

/**
 * Created by EYE on 13/02/2017.
 */

public class InputDialogFragment extends DialogFragment {

    View rootView;
    boolean ifHasValue =  false;
    ReminderContent.Reminder mItemInInput;
    View detailView;
    CollapsingToolbarLayout appBarLayout_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dialogue, container, false);
        getDialog().setTitle("Simple Reminder");

        // soft key board not obscure edit bar
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Store information after clicking ADD button
        Button button = (Button) rootView.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(ifHasValue) {
                    collectValue(mItemInInput,rootView);
                    setDetailView(mItemInInput,detailView);
                    if (appBarLayout_ != null) {
                        appBarLayout_.setTitle(mItemInInput.title);
                    }
                }else {
                    ReminderContent.Reminder reminder = new ReminderContent.Reminder("","",0,0,"","");
                    collectValue(reminder,rootView);
                    ReminderContent.addItem(reminder);
                }
                // close the dialogue after completion
                dismiss();
            }
        });

        if(ifHasValue) {
            setDialogueView(mItemInInput, rootView);

        }

        return rootView;
    }

    public void collectValue(ReminderContent.Reminder rt, View v){

        // Define Views
        TextView texv = (TextView) v.findViewById(R.id.editText_title);
        TextView texvD = (TextView) v.findViewById(R.id.editText_detail);
        TimePicker tp = (TimePicker) v.findViewById(R.id.timePicker);
        CheckBox c1 = (CheckBox) v.findViewById(R.id.checkbox_Mon);
        CheckBox c2 = (CheckBox) v.findViewById(R.id.checkbox_Tue);
        CheckBox c3 = (CheckBox) v.findViewById(R.id.checkbox_Wed);
        CheckBox c4 = (CheckBox) v.findViewById(R.id.checkbox_Tru);
        CheckBox c5 = (CheckBox) v.findViewById(R.id.checkbox_Fri);
        CheckBox c6 = (CheckBox) v.findViewById(R.id.checkbox_Sat);
        CheckBox c7 = (CheckBox) v.findViewById(R.id.checkbox_Sun);

        rt.title = texv.getText().toString();
        rt.hour = tp.getHour();
        rt.minute = tp.getMinute();
        rt.time = rt.hour + ":" + (rt.minute<10 ? "0" : "") + rt.minute + (rt.hour<12 ? " AM" : " PM");


        int mon = c1.isChecked() ? 1 : 0;
        int tue = c2.isChecked() ? 1 : 0;
        int wed = c3.isChecked() ? 1 : 0;
        int thr = c4.isChecked() ? 1 : 0;
        int fri = c5.isChecked() ? 1 : 0;
        int sat = c6.isChecked() ? 1 : 0;
        int sun = c7.isChecked() ? 1 : 0;
        rt.date = "" + mon + tue + wed + thr + fri + sat + sun;
        rt.detail = texvD.getText().toString();


        Log.d("XXxxxxxxXX", "time "+ rt.time + " date "+ rt.date +" detail "+ rt.detail);

    }

    // Get information from ReminderContent item
    // through corresponding ID > then Set to XML View
    public void setDialogueView(ReminderContent.Reminder rt, View v) {

        Button edit = (Button) v.findViewById(R.id.button_add);
        edit.setText("EDIT");

        // Set to XML view
        TextView texv_ = (TextView) v.findViewById(R.id.editText_title);
        texv_.setText(rt.title);


        TextView texvD_ = (TextView) v.findViewById(R.id.editText_detail);
        texvD_.setText(rt.detail);

        TimePicker tp_ = (TimePicker) v.findViewById(R.id.timePicker);
        tp_.setHour(rt.hour);
        tp_.setMinute(rt.minute);

        CheckBox c1_ = (CheckBox) v.findViewById(R.id.checkbox_Mon);
        c1_.setChecked(rt.date.charAt(0) == '1');
        CheckBox c2_ = (CheckBox) v.findViewById(R.id.checkbox_Tue);
        c2_.setChecked(rt.date.charAt(1) == '1');
        CheckBox c3_ = (CheckBox) v.findViewById(R.id.checkbox_Wed);
        c3_.setChecked(rt.date.charAt(2) == '1');
        CheckBox c4_ = (CheckBox) v.findViewById(R.id.checkbox_Tru);
        c4_.setChecked(rt.date.charAt(3) == '1');
        CheckBox c5_ = (CheckBox) v.findViewById(R.id.checkbox_Fri);
        c5_.setChecked(rt.date.charAt(4) == '1');
        CheckBox c6_ = (CheckBox) v.findViewById(R.id.checkbox_Sat);
        c6_.setChecked(rt.date.charAt(5) == '1');
        CheckBox c7_ = (CheckBox) v.findViewById(R.id.checkbox_Sun);
        c7_.setChecked(rt.date.charAt(6) == '1');



    }


    void setDetailView(ReminderContent.Reminder rt, View v) {

        // Set to XML view
        TextView tx_title = (TextView) v.findViewById(R.id.item_title);
        tx_title.setText(rt.title);

        TextView tx_detail = (TextView) v.findViewById(R.id.item_detail);
        tx_detail.setText(rt.detail);

        TextView tx_time = (TextView) v.findViewById(R.id.item_time);
        tx_time.setText(rt.time);

        CheckBox c_mon = (CheckBox) v.findViewById(R.id.checkbox_Mon);
        c_mon.setChecked(rt.date.charAt(0) == '1');
        CheckBox c_tue = (CheckBox) v.findViewById(R.id.checkbox_Tue);
        c_tue.setChecked(rt.date.charAt(1) == '1');
        CheckBox c_wed = (CheckBox) v.findViewById(R.id.checkbox_Wed);
        c_wed.setChecked(rt.date.charAt(2) == '1');
        CheckBox c_tru = (CheckBox) v.findViewById(R.id.checkbox_Tru);
        c_tru.setChecked(rt.date.charAt(3) == '1');
        CheckBox c_fri = (CheckBox) v.findViewById(R.id.checkbox_Fri);
        c_fri.setChecked(rt.date.charAt(4) == '1');
        CheckBox c_sat = (CheckBox) v.findViewById(R.id.checkbox_Sat);
        c_sat.setChecked(rt.date.charAt(5) == '1');
        CheckBox c_sun = (CheckBox) v.findViewById(R.id.checkbox_Sun);
        c_sun.setChecked(rt.date.charAt(6) == '1');

    }

}


