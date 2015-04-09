package com.wyyang.modernartsui;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.SeekBar;

public class ModernArtUIActivity extends Activity {
    private TextView[] mTextViews = new TextView[5];
    private int[] mAlpha = new int[5];
    private int[] mRed = new int[5];
    private int[] mGreen = new int[5];
    private int[] mBlack = new int[5];
    private int mGray;
	protected SeekBar mSeekBar;
	private DialogFragment mDialog;

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_morden_art_ui);
		mTextViews[0] = (TextView) findViewById(R.id.textView1);
	    mTextViews[1] = (TextView) findViewById(R.id.textView2);
	    mTextViews[2] = (TextView) findViewById(R.id.textView3);
	    mTextViews[3] = (TextView) findViewById(R.id.textView4);
	    mTextViews[4] = (TextView) findViewById(R.id.textView5);
	    mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
	    Random rnd = new Random(); 
	    mGray = rnd.nextInt(5);
	    int color;

		for (int i=0; i < 5 ; i++) {
			mRed[i] = rnd.nextInt(256);
			mGreen[i] = rnd.nextInt(256);
			mBlack[i] = rnd.nextInt(256);
			mAlpha[i] = 128;
			if (i == mGray) {
				mRed[i] = mGreen[i];
				mBlack[i] = mGreen[i];
			}
		    color = Color.argb(mAlpha[i], mRed[i], mGreen[i], mBlack[i]);   
		    mTextViews[i].setBackgroundColor(color);
		}
		
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
        	// Called when the user swipes the RatingBar
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        		int color;
        		for (int i=0; i < 5 ; i++) {
        			if ( i != mGray) {
        			mAlpha[i] = 128 + progress;
        		    color = Color.argb(mAlpha[i], mRed[i], mGreen[i], mBlack[i]);   
        		    mTextViews[i].setBackgroundColor(color);
        			}
        		}
        		
			}
        	
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }


       	

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.morden_art_ui, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			mDialog = AlertDialogFragment.newInstance();

			// Show AlertDialogFragment
			mDialog.show(getFragmentManager(), "Alert");

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// Class that creates the AlertDialog
	public static class AlertDialogFragment extends DialogFragment {

		public static AlertDialogFragment newInstance() {
			return new AlertDialogFragment();
		}

		// Build AlertDialog using AlertDialog.Builder
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(getActivity())
					.setMessage("Inspired by the works of artists such as Pief Mondrian and Ben Nicholson. \n\nClick below to learn more!")
					
					// User cannot dismiss dialog by hitting back button
					.setCancelable(true)
					
					// Set up No Button
					.setNegativeButton("Visit MOMA",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
							        Uri url = Uri.parse("http://www.moma.org");
							        Intent httpIntent = new Intent(Intent.ACTION_VIEW, url);
									
									Intent chooserIntent = null;
									chooserIntent = Intent.createChooser(httpIntent, "Load " + url + " with:");        
							        
									// TODO - Start the chooser Activity, using the chooser intent
									startActivity(chooserIntent);

								}
							})
							
					// Set up Yes Button
					.setPositiveButton("Not Now",
							new DialogInterface.OnClickListener() {
								public void onClick(
										final DialogInterface dialog, int id) {
							        
								}
							}).create();
		}
	}

	
}
