package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    private ListView listView;
    private String[] Monday;
    private String[] Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        setupUIViews();
        setupListView();
    }

    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.day_listview);
    }

    private void setupListView() {
        Time = getResources().getStringArray(R.array.time);
        Monday = getResources().getStringArray(R.array.Monday);

        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);

        if(selected_day.equalsIgnoreCase("Monday")) {

        }

        DayAdapter dayAdapter = new DayAdapter(this, Monday, Time);
        listView.setAdapter(dayAdapter);
    }

    public class DayAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView lesson_name, lesson_time;
        private String[] subjectArray;
        private String[] timeArray;


        public DayAdapter(Context context, String[] subjectArray, String[] timeArray) {
            mContext = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.day_single, null);
            }

            lesson_name = (TextView)convertView.findViewById(R.id.lesson_name);
            lesson_time = (TextView)convertView.findViewById(R.id.lesson_time);

            lesson_name.setText(subjectArray[position]);
            lesson_time.setText(timeArray[position]);

            return convertView;
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
