package com.reelreviews.reelreviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewAccountActivity extends ActionBarActivity {
	private DatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_account);
		db = DatabaseHelper.getInstance(this);
	}
	
	public void create_account(View view) {
		String email = ((EditText)findViewById(R.id.newEmailText)).getText().toString();
		String username = ((EditText)findViewById(R.id.newUsernameText)).getText().toString();
		String password = ((EditText)findViewById(R.id.newPasswordText)).getText().toString();
		String confirmPassword = ((EditText)findViewById(R.id.confirmPasswordText)).getText().toString();
		
		if(!password.equals(confirmPassword))
			Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
		else if (db.getUser(email) != null)
			Toast.makeText(this, "There is already an account associated with this email address", Toast.LENGTH_LONG).show();
		else {
			db.addUser(new User(-1, email, username, password, 0));
			Intent intent = new Intent(this, HomepageActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_new_account, menu);
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
