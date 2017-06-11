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
public class ShopFragment extends Fragment {


    private static final int COLOR_COLORS = Color.parseColor("#FFFFFF");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        final ArrayList<Gdansk> shopList = new ArrayList<Gdansk>();

        shopList.add(new Gdansk(getString(R.string.name_baltycka), getString(R.string.address_baltycka), getString(R.string.description_baltycka), getString(R.string.webpage_baltycka), getString(R.string.location_baltycka)));
        shopList.add(new Gdansk(getString(R.string.name_kaszubska), getString(R.string.address_kaszubska), getString(R.string.description_kaszubska), getString(R.string.webpage_kaszubska), getString(R.string.location_kaszubska)));
        shopList.add(new Gdansk(getString(R.string.name_szafa), getString(R.string.address_szafa), getString(R.string.description_szafa), getString(R.string.webpage_szafa), getString(R.string.location_szafa)));

        GdanskAdaper shopAdapter = new GdanskAdaper(getActivity(), shopList, COLOR_COLORS);

        ListView shopListView = (ListView) rootView.findViewById(R.id.list);
        shopListView.setAdapter(shopAdapter);


        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Get the data required in SingleItemWithPlay Class
                Gdansk currentObjectInArray = shopList.get(position);
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
