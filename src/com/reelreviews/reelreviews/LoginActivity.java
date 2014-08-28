package com.reelreviews.reelreviews;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {
	private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = DatabaseHelper.getInstance(this);
        User user = new User(1, "mcdelsi@gmail.com", "Del", "2eagles23", 1000);
        db.addUser(user);
    }

    public void login(View view){
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passwordText)).getText().toString();
		User user = db.getUser(email);
		if(user.getPassword().equals(password)) {
			SharedPreferences mySharedPreferences = getSharedPreferences("login", Activity.MODE_PRIVATE);
			SharedPreferences.Editor editor = mySharedPreferences.edit();
			editor.putString("email", email);
			editor.putBoolean("authenticated", true);
			editor.commit();
			Intent intent = new Intent(this, HomepageActivity.class);
			startActivity(intent);
		}
		else {
			Toast.makeText(this, "Email/password invalid", Toast.LENGTH_LONG).show();
		}
	}
    
    public void create_new_account(View view) {
    	Intent intent = new Intent(this, CreateNewAccountActivity.class);
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
