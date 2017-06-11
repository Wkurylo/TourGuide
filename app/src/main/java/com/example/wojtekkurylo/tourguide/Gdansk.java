package com.example.wojtekkurylo.tourguide;


/**
 * {@link Gdansk} represent the activity/place that the user can visit to learn more about Gdansk / enjoy time.
 * It contains a name && short description && image of place(if applicable)
 */

public class Gdansk {

    // instance variables / state
    /**
     * Static - associated with Class, final - value cannot change. Constant Variable
     */
    private static final int NO_IMAGE = -1;
    /**
     * Static - associated with Class, final - value cannot change. Constant Variable
     */
    private static final int NO_AUDIO = -1;
    /**
     * Name of activity/place
     */
    private String mName;
    /**
     * Address of activity/place
     */
    private String mAddress;
    /**
     * Description of activity/place
     */
    private String mDescription;
    /**
     * Address of activity/place
     */
    private String mLocation;
    /**
     * Address of activity/place
     */
    private String mWebpage;
    /**
     * Drawable resource to image assiociated with Object
     */
    private int mImageResourceID = NO_IMAGE;
    /**
     * Drawable resource to image assiociated with Object - To display on Place Details Page (Large)
     */
    private int mImageResourceLargeID = NO_IMAGE;

    /**
     * Raw resource to audio assiociated with Object
     */
    private int mAudioResourceID = NO_IMAGE;

    /**
     * Constructor for objects of class Gdanks
     * <p>
     * Create a new Gdansk Object
     * We are assigning some value to mImage that i why will return false in checkIfImageRequired method in Class
     *
     * @param name                 is the name of listed item
     * @param address              is the address
     * @param description          is the short description
     * @param imageResourceID      is the drawable resource ID for the image associated with current item
     * @param webpage              is a site URL of place
     * @param imageResourceLargeID is the drawable resource ID for the image associated with current item in Detail Page
     * @param audioResourceID      is the raw resource ID for audio associated with Object
     * @param location             is a WSG-84 location coordinates of place
     */
    public Gdansk(String name, String address, String description, int imageResourceID, String webpage, int imageResourceLargeID, int audioResourceID, String location) {
        mName = name;
        mAddress = address;
        mDescription = description;
        mImageResourceID = imageResourceID;
        mWebpage = webpage;
        mImageResourceLargeID = imageResourceLargeID;
        mAudioResourceID = audioResourceID;
        mLocation = location;
    }

    /**
     * Constructor for objects of class Gdanks (without image)
     * <p>
     * Create a new Gdansk Object
     *
     * @param name            is the name of listed item
     * @param description     is the description
     * @param address         is the address
     * @param location        is a WSG-84 location coordinates of place
     * @param webpage         is a site URL of place
     * @param imageResourceID is the drawable resource ID for the image associated with current item
     */
    public Gdansk(String name, String address, String description, String webpage, String location, int imageResourceID) {
        mName = name;
        mAddress = address;
        mDescription = description;
        mWebpage = webpage;
        mLocation = location;
        mImageResourceID = imageResourceID;
    }

    /**
     * Constructor for objects of class Gdanks (without image)
     * <p>
     * Create a new Gdansk Object
     *
     * @param name        is the name of listed item
     * @param description is the description
     * @param address     is the address
     * @param location    is a WSG-84 location coordinates of place
     * @param webpage     is a site URL of place
     */
    public Gdansk(String name, String address, String description, String webpage, String location) {
        mName = name;
        mAddress = address;
        mDescription = description;
        mWebpage = webpage;
        mLocation = location;
    }

    /**
     * Return the name of Object.
     */
    public String getName() {
        return mName;
    }

    /**
     * Return the address of Object.
     */
    public String getAddress() {
        return mAddress;
    }

    /**
     * Return the short description of Object.
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Return the image resource ID of the location.
     */
    public int getImage() {
        return mImageResourceID;
    }

    /**
     * Return the image resource ID of the location - To display on Place detail page.
     */
    public int getImageLarge() {
        return mImageResourceLargeID;
    }

    /**
     * Method to check if in Class Gdansk has been assigned value to checkIfImageRequired variable (icon image in ListView)
     * This method is used in GdanskAdapter Class
     *
     * @return true If value of mImageResourceID is NOT equal to constant variable NO_IMAGE = -1;
     */
    public boolean checkIfImageRequired() {
        return mImageResourceID != NO_IMAGE;
    }

    /**
     * Method to check if in Class Gdansk has been assigned value to mImageResourceLargeID variable (Large image in SingleItemWIthPlay)
     * This method is used in GdanskAdapter Class
     *
     * @return true If value of mImageResourceLargeID is NOT equal to constant variable NO_IMAGE = -1;
     */
    public boolean checkIfImageLargeRequired() {
        return mImageResourceLargeID != NO_IMAGE;
    }

    /**
     * Return the webpage address
     */
    public String getWebpage() {
        return mWebpage;
    }

    /**
     * Return the location of Object.
     */
    public String getLocation() {
        return mLocation;
    }

    /**
     * Return the audio resource ID
     */
    public int getAudioID() {
        return mAudioResourceID;
    }

    /**
     * Method to check if in Class Gdansk has been assigned value to mAudioResourceID variable
     * This method is used in GdanskAdapter Class
     *
     * @return true If value of mAudioResourceID is NOT equal to constant variable NO_AUDIO = -1;
     */
    public boolean checkIfAudioRequired() {
        return mAudioResourceID != NO_AUDIO;
    }
}
