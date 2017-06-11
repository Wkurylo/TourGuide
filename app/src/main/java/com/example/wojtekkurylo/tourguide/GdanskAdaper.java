package com.example.wojtekkurylo.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wojtekkurylo on 06.06.2017.
 */

public class GdanskAdaper extends ArrayAdapter<Gdansk> {

    /**
     * Resource ID for the background color of word container (with TextView)
     */

    private int mColorTextViewId;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context       The current context.
     * @param listToDisplay A List of  objects to display in a list
     */
    public GdanskAdaper(Context context, ArrayList<Gdansk> listToDisplay, int colorTextViewId) {

        super(context, 0, listToDisplay);
        mColorTextViewId = colorTextViewId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation/tworzenie nowych Obiektow.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View anotherListItemView = convertView;
        if (anotherListItemView == null) {
            anotherListItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_instructions, parent, false);

        }

        // Get the {@link Place} object located at this position in the list
        Gdansk currentPlace = getItem(position);

        // Find the TextView and set the short description
        TextView addressTextView = (TextView) anotherListItemView.findViewById(R.id.address);
        addressTextView.setText(currentPlace.getAddress());

        // Find the TextView and set the name
        TextView nameTextView = (TextView) anotherListItemView.findViewById(R.id.name);
        nameTextView.setText(currentPlace.getName());

        // Find the ImageView and set the image if required (small image in ListView)
        ImageView imageView = (ImageView) anotherListItemView.findViewById(R.id.icon);
        if (currentPlace.checkIfImageRequired()) {
            imageView.setImageResource(currentPlace.getImage());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        /**
         * Setting color of view with text in English and Miwok
         */

        View textViewContainer = anotherListItemView.findViewById(R.id.textViewContainer);
        textViewContainer.setBackgroundColor(mColorTextViewId);

        return anotherListItemView;
    }
}
