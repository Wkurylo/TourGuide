package com.example.wojtekkurylo.tourguide;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;

public class SingleItemWithPlay extends AppCompatActivity {

    public static int mONE_TIME_ONLY = 0;
    private String mDescription;
    private String mWebpage;
    private int mImageLargeID;
    private int mAudioID;
    private String mLocation;
    private Boolean mLargeImage;
    private Boolean mAudioRequired;
    //    Instance associated with MediaPlayer
    private MediaPlayer mMediaPlayerWojtek;
    private ImageView mPlay;
    private SeekBar mSeekBarWojtek;
    //    Counting the time of song
    private double mCurrentTime = 0;
    private double mTotalTime = 0;
    private Handler mWojtekHandler = new Handler();
    private TextView mLeft;
    private TextView mPassed;

    /**
     * Handles audio focus when playing a sound file
     */
    private AudioManager mAudioManagerWojtek;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangerWojtek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        destroyMediaPlayer();

        //Receiving Object details to set up each child of ListView
        Bundle extras = getIntent().getExtras();
        mDescription = extras.getString("description");
        mWebpage = extras.getString("webpage");
        mImageLargeID = extras.getInt("imageLargeID");
        mAudioID = extras.getInt("audioID");
        mLocation = extras.getString("location");
        mLargeImage = extras.getBoolean("largeImageRequired");
        mAudioRequired = extras.getBoolean("audioRequired");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_with_play);

        // Setting up the displayed image of selected place (if Large Image is Available)
        ImageView imageLarge = (ImageView) findViewById(R.id.imageToSee);
        if (mLargeImage == true) {
            imageLarge.setImageResource(mImageLargeID);
            imageLarge.setVisibility(View.VISIBLE);
        } else {
            imageLarge.setVisibility(View.GONE);
        }

        // Setting up the displayed description of selected place
        TextView descTextView = (TextView) findViewById(R.id.textToSee);
        descTextView.setText(mDescription);

        // Setting up the displayed webpage of selected place
        TextView webTextView = (TextView) findViewById(R.id.link_page);

        // Setting up onClickListener - If clicked web link
        webTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Go to website
                Uri webpageUri = Uri.parse(mWebpage);
                Intent goWebsite = new Intent(Intent.ACTION_VIEW, webpageUri);
                if (goWebsite.resolveActivity(getPackageManager()) != null) {
                    startActivity(goWebsite);
                }
            }
        });

        // Setting up the displayed map of selected place
        TextView map = (TextView) findViewById(R.id.link_map);

        // Setting up onClickListener - If clicked map link
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to map
                Uri mapUri = Uri.parse(mLocation);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(mapUri);

                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        RelativeLayout playNavBar = (RelativeLayout) findViewById(R.id.musicPlayer);

        if (mAudioRequired) {

            playNavBar.setVisibility(View.VISIBLE);

            //        Setting up Media Player and all connected to it

            mMediaPlayerWojtek = MediaPlayer.create(SingleItemWithPlay.this, mAudioID);
            mPlay = (ImageView) findViewById(R.id.button_play);
            mAudioManagerWojtek = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

            mAudioFocusChangerWojtek = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        mMediaPlayerWojtek.stop();
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayerWojtek.pause();
                        mMediaPlayerWojtek.seekTo(0); // starts from 0 millisecond

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayerWojtek.start();
                    }

                }

            };

            /** SeekBar & song time Variables*/

            mCurrentTime = mMediaPlayerWojtek.getCurrentPosition();
            mTotalTime = mMediaPlayerWojtek.getDuration();


            mSeekBarWojtek = (SeekBar) findViewById(R.id.seekbar);
            mSeekBarWojtek.setClickable(true);

            mLeft = (TextView) findViewById(R.id.left);
            mPassed = (TextView) findViewById(R.id.passed);

            /** Setting up play button */
            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int audioManagerWojtekResult = mAudioManagerWojtek.requestAudioFocus(mAudioFocusChangerWojtek, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    if (audioManagerWojtekResult == AUDIOFOCUS_REQUEST_GRANTED) {

                        /** Checking if the audio is already playing */
                        if (mMediaPlayerWojtek.isPlaying()) {
                            mMediaPlayerWojtek.pause();
                            mPlay.setImageResource(R.drawable.ic_play_arrow_black);
                            Toast.makeText(SingleItemWithPlay.this, "Audio Paused", Toast.LENGTH_SHORT).show();

                        } else {

                            mMediaPlayerWojtek.start();
                            mPlay.setImageResource(R.drawable.ic_pause_black);
                            Toast.makeText(SingleItemWithPlay.this, "Now Playing", Toast.LENGTH_SHORT).show();

                        }

                        mMediaPlayerWojtek.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                Toast.makeText(SingleItemWithPlay.this, "I'm done", Toast.LENGTH_LONG).show();
                                releaseMediaPlayer();
                            }
                        });

                        /** Setting up seekBar - adjusting seekBar to music length - only one time (while = 0)*/

                        if (mONE_TIME_ONLY == 0) {
                            mSeekBarWojtek.setMax((int) mTotalTime);
                            mONE_TIME_ONLY = 1;
                        }
                        /** Setting up seekBar */

                        mSeekBarWojtek.setProgress((int) mCurrentTime);
                        mWojtekHandler.postDelayed(UpdateSongTime, 100);
                    }
                }

            });
        } else {
            playNavBar.setVisibility(View.GONE);
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayerWojtek != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.

            //mMediaPlayerWojtek.release();
            mMediaPlayerWojtek.stop();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.

            //mMediaPlayerWojtek = null;
            mAudioManagerWojtek.abandonAudioFocus(mAudioFocusChangerWojtek);
            mONE_TIME_ONLY = 0;
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void destroyMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayerWojtek != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.

            mMediaPlayerWojtek.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.

            //mMediaPlayerWojtek = null;

            mMediaPlayerWojtek = null;
            mAudioManagerWojtek.abandonAudioFocus(mAudioFocusChangerWojtek);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Setting up seekBar  / Following the progress
     */
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {

            mCurrentTime = mMediaPlayerWojtek.getCurrentPosition();
            double remainTime = mTotalTime - mCurrentTime; // +

            /** Counting up */

            mPassed.setText(String.format(Locale.getDefault(), "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) mCurrentTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) mCurrentTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) mCurrentTime))));

            /** Counting down */

            mLeft.setText(String.format(Locale.getDefault(), "-" + "%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) remainTime),
                    TimeUnit.MILLISECONDS.toSeconds((long) remainTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) remainTime))));

            /** 100 (milliseconds) =  update every 1 sec */

            mSeekBarWojtek.setProgress((int) mCurrentTime);
            mWojtekHandler.postDelayed(this, 100);
        }
    };

}
