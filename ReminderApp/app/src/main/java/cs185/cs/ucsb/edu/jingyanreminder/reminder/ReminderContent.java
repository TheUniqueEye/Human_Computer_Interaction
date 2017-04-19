package cs185.cs.ucsb.edu.jingyanreminder.reminder;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs185.cs.ucsb.edu.jingyanreminder.ReminderListActivity;

public class ReminderContent {
    public static RecyclerView.Adapter<ReminderListActivity.SimpleItemRecyclerViewAdapter.ViewHolder> adapter;
    public static final List<Reminder> ITEMS = new ArrayList<Reminder>();
    public static final Map<String, Reminder> ITEM_MAP = new HashMap<String, Reminder>();

    /**
     * Add a reminder to the list
     * @param item
     */
    public static void addItem(Reminder item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
        adapter.notifyDataSetChanged();
    }

    /**
     * Remove a reminder from the list
     * @param item
     */
    public static void removeItem(Reminder item) {
        ITEMS.remove(item);
        ITEM_MAP.remove(item.id);
        adapter.notifyDataSetChanged();
    }

    /**
     * The reminder object (add fields as necessary)
     */
    public static class Reminder {
        public final String id;
        public String title;
        public static int count = 0;

        // add detail, time
        public String time;
        public int hour;
        public int minute;
        public String date;
        public String detail;

        public Reminder(String title, String time, int hour, int minute, String date, String detail) {
            id = count++ + "";
            this.title = title;
            this.time = time;
            this.hour = hour;
            this.minute = minute;
            this.date = date;
            this.detail = detail;
        }
    }
}
