package com.leapyear.felipe.leapyear;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class LeapYearActivity extends ActionBarActivity implements OnRequestListenerInterface{

    private ListView mListView;
    private LeapYearArrayAdapter adapter;
    private ArrayList<Year> randArray;
    private Requester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leap_year);
        mListView = (ListView) findViewById(R.id.yearsListView);
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leap_year, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refreshMenuItem){
            if(randArray.get(randArray.size()-1).getLeapYear() == Year.INDETERMINATE) {
                Toast.makeText(this, "Please, wait until you finish the requests of the current list.", Toast.LENGTH_SHORT).show();
                return false;
            }
            refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        Random rand = new Random();
        randArray = new ArrayList(100);
        for(int i = 0; i < 100; i++) {
            int randNumber = rand.nextInt(3001); //because its exclusive for the top number
            randArray.add(new Year(randNumber));
        }
        adapter = new LeapYearArrayAdapter(this, R.layout.leap_year_cell, randArray, mListView);

        mListView.setAdapter(adapter);

        requester = new Requester();
        requester.setDelegate(this);
        requester.execute(randArray);
    }

    @Override
    public void onRequestFinished(Pair<Integer, Boolean> result) {
        int position = result.first;
        boolean leapYear = result.second;
        if(leapYear){
            randArray.set(position, new Year(randArray.get(position).getYear(), Year.LEAPYEAR));
        } else {
            randArray.set(position, new Year(randArray.get(position).getYear(), Year.NOTLEAPYEAR));
        }

        View v = mListView.getChildAt(position -
                mListView.getFirstVisiblePosition());

        if(v == null){
            return;
        }
        TextView resultTextView = (TextView)v.findViewById(R.id.resultImg);
        ProgressBar waitingProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);

        waitingProgressBar.setVisibility(View.INVISIBLE);
        if(leapYear){
            resultTextView.setText("Yes");
            resultTextView.setBackgroundResource(R.drawable.green_background);
        } else {
            resultTextView.setText("No");
            resultTextView.setBackgroundResource(R.drawable.red_background);
        }
        resultTextView.setAlpha(0f);
        resultTextView.setVisibility(View.VISIBLE);
        resultTextView.animate()
                .alpha(1f)
                .setDuration(1500)
                .setListener(null);
    }
}
