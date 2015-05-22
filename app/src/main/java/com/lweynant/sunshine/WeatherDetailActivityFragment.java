package com.lweynant.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherDetailActivityFragment extends Fragment {

    private android.support.v7.widget.ShareActionProvider mShareActionProvider;
    private String weatherInfo;

    public WeatherDetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.forecastdetails, menu);
        MenuItem share = menu.findItem(R.id.menu_weather_details_share);
        mShareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(share);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, weatherInfo + " #SunshineApp");
        setShareIntent(shareIntent);
    }
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null){
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        TextView textView = (TextView)rootView.findViewById(R.id.weather_detail_text);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            weatherInfo = intent.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(weatherInfo);
        }

        return rootView;
    }

}
