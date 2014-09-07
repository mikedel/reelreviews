package com.reelreviews.reelreviews;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LoginTask extends AsyncTask<String, Void, String> {
	private Activity activity;
	private static final int BUFFER_SIZE = 1000;
	private String url_string;
	private String method;



	public LoginTask(Activity activity) {
	    this.activity = activity;
	}

	@Override
	protected String doInBackground(String... urls) {
		url_string = activity.getString(R.string.server_url) + urls[0];
		method = urls[1];
		Log.d("doInBackground", "method: " + method);
		if(method.equals("login")){
			Log.d("doInBackground", "method: " + method);
			try {
				return login(url_string);
			} catch (Exception e) {
				Log.d("doInBackground", "Exception was caught" + e.getMessage());
				return "FAIL";
			}
		}
		else if(method.equals("get_recommendations")){
			try {
				return get_recommendations(url_string);
			} catch (Exception e) {
				Log.d("doInBackground", "Exception was caught" + e.getMessage());
				return "FAIL";
			}
		}
		else
			return "FAIL";
	}

	private String login(String url_string) throws IOException {
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(url_string);
			conn = (HttpURLConnection) url.openConnection();
	        conn.connect();
	        is = new BufferedInputStream(conn.getInputStream());
	        Reader reader = new InputStreamReader(is, "UTF-8");
	        char[] buffer = new char[BUFFER_SIZE];
	        reader.read(buffer);
	        String result = new String(buffer);
	        return result;
		} catch (Exception e) {
			Log.d("login", "Exception was caught" + e.getMessage());
			return "FAIL";
		} finally {
			if(is != null)
				is.close();
			if(conn != null)
				conn.disconnect();
		}
	}

	private String get_recommendations(String url_string) throws IOException {
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(url_string);
			conn = (HttpURLConnection) url.openConnection();
	        conn.connect();
	        is = new BufferedInputStream(conn.getInputStream());
	        Reader reader = new InputStreamReader(is, "UTF-8");
	        char[] buffer = new char[BUFFER_SIZE];
	        reader.read(buffer);
	        return new String(buffer);
		} catch (Exception e) {
			Log.d("get_recommendations", "Exception was caught" + e.getMessage());
			return "FAIL";
		} finally {
			if(is != null)
				is.close();
			if(conn != null)
				conn.disconnect();
		}
	}

	@Override
	protected void onPostExecute(String result) {
		if(result.equals("FAIL")) {
			Toast.makeText(activity, "method failed: " + method, Toast.LENGTH_LONG).show();
			Log.d("onPostExecute", "result FAIL: " + result);
		}
		else if(method.equals("login")) {
			Intent intent = new Intent(activity, HomepageActivity.class);
			activity.startActivity(intent);
		}
		else if(method.equals("get_recommendations")) {
			ListView listview = (ListView) activity.findViewById(R.id.myRecommendationsListView);
			String[] array = null;
			try {
				JSONArray json_array = new JSONArray(result);
				JSONObject object = null;
				JSONObject fields = null;
				int id, sender_id, receiver_id, movie_id, source_id, stars;
				String review, rec;
				long date;
				ArrayList<String> recs = new ArrayList<String>();
				for(int i = 0; i < json_array.length(); i++) {
					object = json_array.getJSONObject(i);
					id = object.getInt("pk");
					fields = object.getJSONObject("fields");
					sender_id = fields.getInt("sender_id");
					receiver_id = fields.getInt("receiver_id");
					movie_id = fields.getInt("movie_id");
					source_id = fields.getInt("source_id");
					stars = fields.getInt("stars");
					review = fields.getString("review");
					date = fields.getLong("date");
					rec = "Recommendation(" + id + ") from " + sender_id + " for " + movie_id + "\nReview: " +
							review + "\nStars: " + stars + " Source: " + source_id + " Date: " + date;
					recs.add(rec);
					Log.d("onPostExecute", rec);
				}
				array = (String[]) recs.toArray();
			} catch (JSONException e) { e.printStackTrace(); }
			if(array != null) {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, array);
				listview.setAdapter(adapter);
			}
		}
	}
}
