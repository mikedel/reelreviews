package com.reelreviews.reelreviews;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RatingBar;

public class CreateRecommendationActivity extends ActionBarActivity {
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_recommendation);
		db = DatabaseHelper.getInstance(this);
	}

	public void send_recommendation(View view) {
		String movie_title = ((AutoCompleteTextView)findViewById(R.id.movieRecommendationTextView)).getText().toString();
		String friend_email = ((AutoCompleteTextView)findViewById(R.id.friendRecommendationTextView)).getText().toString();
		String review = ((EditText)findViewById(R.id.reviewRecommendationTextView)).getText().toString();
		int num_stars = ((RatingBar)findViewById(R.id.recommendationRatingBar)).getNumStars();
		Movie movie = db.getMovie(movie_title);
		User friend = db.getUser(friend_email);
		SharedPreferences mySharedPreferences = getSharedPreferences("login", Activity.MODE_PRIVATE);
		String user_email = mySharedPreferences.getString("email", "");
		boolean isAuthenticated = mySharedPreferences.getBoolean("authenticated", false);
		if (user_email == null || user_email.equals("") || !isAuthenticated)
			return; //TODO: Notify user
		User user = db.getUser(user_email);
		Recommendation recommendation = new Recommendation(-1, user, friend, movie, review, System.currentTimeMillis(), num_stars, null);
		db.addRecommendation(recommendation);
		Intent intent = new Intent(this, HomepageActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_recommendation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
