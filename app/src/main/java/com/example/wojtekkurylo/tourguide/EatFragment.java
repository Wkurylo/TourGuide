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
public class EatFragment extends Fragment {

    private static final int COLOR_COLORS = Color.parseColor("#FFFFFF");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        final ArrayList<Gdansk> eatList = new ArrayList<Gdansk>();

        eatList.add(new Gdansk(getString(R.string.name_kubicki), getString(R.string.address_kubicki), getString(R.string.description_kubicki), getString(R.string.webpage_kubicki), getString(R.string.location_kubicki), R.drawable.kubicki));
        eatList.add(new Gdansk(getString(R.string.name_mercato), getString(R.string.address_mercato), getString(R.string.description_mercato), getString(R.string.webpage_mercato), getString(R.string.location_mercato), R.drawable.mercato));
        eatList.add(new Gdansk(getString(R.string.name_brovarnia), getString(R.string.address_brovarnia), getString(R.string.description_brovarnia), getString(R.string.webpage_brovarnia), getString(R.string.location_brovarnia), R.drawable.brovarnia));
        eatList.add(new Gdansk(getString(R.string.name_goldwasser), getString(R.string.address_goldwasser), getString(R.string.description_goldwasser), getString(R.string.webpage_goldwasser), getString(R.string.location_goldwasser), R.drawable.goldwasser));

        GdanskAdaper eatAdapter = new GdanskAdaper(getActivity(), eatList, COLOR_COLORS);

        ListView eatListView = (ListView) rootView.findViewById(R.id.list);
        eatListView.setAdapter(eatAdapter);


        eatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the data required in SingleItemWithPlay Class
                Gdansk currentObjectInArray = eatList.get(position);
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
