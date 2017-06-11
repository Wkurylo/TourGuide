package com.example.wojtekkurylo.tourguide;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SleepFragment extends Fragment {


    private static final int COLOR_COLORS = Color.parseColor("#FFFFFF");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        final ArrayList<Gdansk> sleepList = new ArrayList<Gdansk>();

        sleepList.add(new Gdansk(getString(R.string.name_hilton), getString(R.string.address_hilton), getString(R.string.description_hilton), getString(R.string.webpage_hilton), getString(R.string.location_hilton)));
        sleepList.add(new Gdansk(getString(R.string.name_gotyk), getString(R.string.address_gotyk), getString(R.string.description_gotyk), getString(R.string.webpage_gotyk), getString(R.string.location_gotyk)));
        sleepList.add(new Gdansk(getString(R.string.name_boutique), getString(R.string.address_boutique), getString(R.string.description_boutique), getString(R.string.webpage_boutique), getString(R.string.location_boutique)));
        sleepList.add(new Gdansk(getString(R.string.name_blu), getString(R.string.address_blu), getString(R.string.description_blu), getString(R.string.webpage_blu), getString(R.string.location_blu)));

        GdanskAdaper sleepAdapter = new GdanskAdaper(getActivity(), sleepList, COLOR_COLORS);

        ListView sleepListView = (ListView) rootView.findViewById(R.id.list);
        sleepListView.setAdapter(sleepAdapter);


        sleepListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the data required in SingleItemWithPlay Class
                Gdansk currentObjectInArray = sleepList.get(position);
                String description = currentObjectInArray.getDescription();
                String webpage = currentObjectInArray.getWebpage();
                String location = currentObjectInArray.getLocation();
                Boolean largeImageRequired = currentObjectInArray.checkIfImageLargeRequired();
                Boolean audioRequired = currentObjectInArray.checkIfAudioRequired();


                // Fire up next activity && sending to SingleItemWithPlay Object details
                Intent SingleItem = new Intent(getActivity(), SingleItemWithPlay.class);

                Bundle extras = new Bundle();
                extras.putString("description", description);
                extras.putString("webpage", webpage);
                extras.putString("location", location);
                extras.putBoolean("largeImageRequired", largeImageRequired);
                extras.putBoolean("audioRequired", audioRequired);
                SingleItem.putExtras(extras);

                startActivity(SingleItem);
            }
        });

        return rootView;
    }

}
