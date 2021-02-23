package com.example.assignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        setupListView();
    }

    private void setupUIViews() {
        listView = (ListView)findViewById(R.id.listview);
        sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE);
    }


    private void setupListView() {
        String[] weekArray = getResources().getStringArray(R.array.week);

        WeekAdapter adapter = new WeekAdapter(this, R.layout.week_single, weekArray);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                        case 0: {
                            startActivity(new Intent(MainActivity.this, DayActivity.class));
                            sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
                            break;
                        }
                        case 1: {
                            startActivity(new Intent(MainActivity.this, DayActivity.class));
                            sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
                            break;
                        }
                        case 2: {
                            startActivity(new Intent(MainActivity.this, DayActivity.class));
                            sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
                            break;
                        }
                        case 3: {
                            startActivity(new Intent(MainActivity.this, DayActivity.class));
                            sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
                            break;
                        }
                        case 4: {
                            startActivity(new Intent(MainActivity.this, DayActivity.class));
                            sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
                            break;
                        }
                        default: break;
                }
            }
        });
    }

    public class WeekAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] weekArray = new String[]{};

        public WeekAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.weekArray = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                viewHolder.weekDay = (TextView)convertView.findViewById(R.id.week_day);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder)convertView.getTag();
            }

            viewHolder.weekDay.setText(weekArray[position]);

            return convertView;
        }

        class ViewHolder {
            private TextView weekDay;

        }
    }

}
