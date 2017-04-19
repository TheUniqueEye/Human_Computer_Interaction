package cs185.cs.ucsb.edu.jingyanreminder;

import android.app.Activity;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import cs185.cs.ucsb.edu.jingyanreminder.reminder.ReminderContent;

import static cs185.cs.ucsb.edu.jingyanreminder.reminder.ReminderContent.removeItem;

public class ReminderDetailFragment extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    public static final String IS_ACTIVITY = "activity";
    private ReminderContent.Reminder mItem;
    private boolean isActivity;
    private OnCloseListener listener;
    CollapsingToolbarLayout appBarLayout;

    public interface OnCloseListener {
        void OnClose();
    }

    public ReminderDetailFragment() {
    }

    public void setOnCloseListener(OnCloseListener listener) {
        this.listener = listener;
    }

    private void close() {
        if (listener != null) {
            listener.OnClose();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = ReminderContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            Activity activity = this.getActivity();

            // soft key board not obscure edit bar
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }
        if (getArguments().containsKey(IS_ACTIVITY)) {
            isActivity = getArguments().getBoolean(IS_ACTIVITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.reminder_detail, container, false);
        if (mItem != null) {
            // Populate the fragment views
            // Call close on delete


            // Set data to XML View
            setView(mItem, rootView);


            Button editButton = (Button) rootView.findViewById(R.id.item_edit);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("edit","You Clicked Edit Button");

                    // Show dialog and add reminder

                    FragmentManager fm = getFragmentManager();
                    InputDialogFragment dialogFragment = new InputDialogFragment();
                    dialogFragment.ifHasValue = true;
                    dialogFragment.mItemInInput = mItem;
                    dialogFragment.appBarLayout_ = appBarLayout;
                    dialogFragment.detailView = rootView;
                    dialogFragment.show(fm, "Sample Fragment");

                }
            });

            Button deleteButton = (Button) rootView.findViewById(R.id.item_delete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("delete","You Clicked Delete Button");
                    ReminderContent.removeItem(mItem);
                    close();
                }
            });

        }
        return rootView;
    }


    // Get information from ReminderContent item
    // through corresponding ID > then Set to XML View
    private void setView(ReminderContent.Reminder rt, View v) {

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
