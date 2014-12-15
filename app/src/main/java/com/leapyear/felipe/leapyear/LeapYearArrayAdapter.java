package com.leapyear.felipe.leapyear;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Felipe on 09/12/2014.
 */
public class LeapYearArrayAdapter extends ArrayAdapter<Year> {

    private final int mLayoutViewResourceId;

    public LeapYearArrayAdapter(Context context, int resource, List<Year> objects, ListView listView) {
        super(context, resource, objects);
        mLayoutViewResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Year year = getItem(position);
        View view;
        view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(mLayoutViewResourceId, null);
        }

        TextView yearTextView = (TextView)view.findViewById(R.id.yearTextView);
        TextView resultTextView = (TextView)view.findViewById(R.id.resultImg);
        ProgressBar waitingProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        yearTextView.setText(String.valueOf(year.getYear()));
        if(year.getLeapYear() == Year.INDETERMINATE) {
            waitingProgressBar.setVisibility(View.VISIBLE);
            resultTextView.setVisibility(View.INVISIBLE);
        } else if(year.getLeapYear() == Year.LEAPYEAR) {
            waitingProgressBar.setVisibility(View.INVISIBLE);
            resultTextView.setText("Yes");
            resultTextView.setBackgroundResource(R.drawable.green_background);
            resultTextView.setVisibility(View.VISIBLE);
        }
        else if(year.getLeapYear() == Year.NOTLEAPYEAR) {
            waitingProgressBar.setVisibility(View.INVISIBLE);
            resultTextView.setText("No");
            resultTextView.setBackgroundResource(R.drawable.red_background);
            resultTextView.setVisibility(View.VISIBLE);
        }
        return view;
    }

}
