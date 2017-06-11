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

import java.util.ArrayList;

/**
 * Created by wojtekkurylo on 06.06.2017.
 */

public class SeeFragment extends Fragment {

    private static final int COLOR_COLORS = Color.parseColor("#FFFFFF");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);


        final ArrayList<Gdansk> seeList = new ArrayList<Gdansk>();

        seeList.add(new Gdansk(getString(R.string.name_hall), getString(R.string.address_hall), getString(R.string.description_hall), R.drawable.main_town_hall, getString(R.string.webpage_hall), R.drawable.town_hall, R.raw.gdansk_speech, getString(R.string.location_hall)));
        seeList.add(new Gdansk(getString(R.string.name_museum), getString(R.string.address_museum), getString(R.string.description_museum), R.drawable.museum, getString(R.string.webpage_museum), R.drawable.museum_large, R.raw.gdansk_speech_two, getString(R.string.location_museum)));
        seeList.add(new Gdansk(getString(R.string.name_solidarity), getString(R.string.address_solidarity), getString(R.string.description_solidarity), R.drawable.solidarity, getString(R.string.webpage_solidarity), R.drawable.solidarity_large, R.raw.gdansk_speech_three, getString(R.string.location_solidarity)));
        seeList.add(new Gdansk(getString(R.string.name_crane), getString(R.string.address_crane), getString(R.string.description_crane), R.drawable.crane, getString(R.string.webpage_crane), R.drawable.crane_large, R.raw.gdansk_speech, getString(R.string.location_crane)));
        seeList.add(new Gdansk(getString(R.string.name_amber), getString(R.string.address_amber), getString(R.string.description_amber), R.drawable.amber, getString(R.string.webpage_amber), R.drawable.amber_large, R.raw.gdansk_speech_two, getString(R.string.location_amber)));
        seeList.add(new Gdansk(getString(R.string.name_post), getString(R.string.address_post), getString(R.string.description_post), R.drawable.post, getString(R.string.webpage_post), R.drawable.post_large, R.raw.gdansk_speech_three, getString(R.string.location_post)));

        GdanskAdaper seeAdapter = new GdanskAdaper(getActivity(), seeList, COLOR_COLORS);

        ListView seeListView = (ListView) rootView.findViewById(R.id.list);
        seeListView.setAdapter(seeAdapter);

        seeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the data required in SingleItemWithPlay Class
                Gdansk currentObjectInArray = seeList.get(position);
                String description = currentObjectInArray.getDescription();
                String webpage = currentObjectInArray.getWebpage();
                int imageLargeID = currentObjectInArray.getImageLarge();
                int audioID = currentObjectInArray.getAudioID();
                String location = currentObjectInArray.getLocation();
                Boolean largeImageRequired = currentObjectInArray.checkIfImageLargeRequired();
                Boolean audioRequired = currentObjectInArray.checkIfAudioRequired();


                // Fire up next activity && sending to SingleItemWithPlay Object details
                Intent SingleItem = new Intent(getActivity(), SingleItemWithPlay.class);

                Bundle extras = new Bundle();
                extras.putString("description", description);
                extras.putString("webpage", webpage);
                extras.putInt("imageLargeID", imageLargeID);
                extras.putInt("audioID", audioID);
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
