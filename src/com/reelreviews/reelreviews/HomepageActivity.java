package com.reelreviews.reelreviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomepageActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_homepage);
	}
	
	public void create_recommendation(View view) {
		Intent intent = new Intent(this, CreateRecommendationActivity.class);
		startActivity(intent);
	}
	
	public void my_recommendations(View view) {
		Intent intent = new Intent(this, MyRecommendationsActivity.class);
		startActivity(intent);
	}
	
	public void profile(View view) {
		Intent intent = new Intent(this, ProfileActivity.class);
		startActivity(intent);
	}
	
	public void friends(View view) {
		Intent intent = new Intent(this, FriendsActivity.class);
		startActivity(intent);
	}

	public void news_feed(View view) {
		Intent intent = new Intent(this, NewsFeedActivity.class);
		startActivity(intent);
	}
	
	public void movie_directory(View view) {
		Intent intent = new Intent(this, MovieDirectoryActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.homepage, menu);
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
