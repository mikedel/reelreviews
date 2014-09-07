package com.reelreviews.reelreviews;

import java.net.CookieHandler;
import java.net.CookieManager;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends Activity {
	private DatabaseHelper db;
	BasicHttpContext localContext = new BasicHttpContext();
	BasicCookieStore cookieStore = new BasicCookieStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = DatabaseHelper.getInstance(this);
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
    }

    public void login(View view){
		String email = ((EditText)findViewById(R.id.emailText)).getText().toString();
		String password = ((EditText)findViewById(R.id.passwordText)).getText().toString();
		SharedPreferences.Editor editor = getSharedPreferences("login", Activity.MODE_PRIVATE).edit();
		editor.putString("email", email);
		editor.putBoolean("authenticated", false);
		editor.commit();
//		using local database:
//		TODO: use shared preferences
//		User user = db.getUser(email);
//		if(user.getPassword().equals(password)) {
//			SharedPreferences mySharedPreferences = getSharedPreferences("login", Activity.MODE_PRIVATE);
//			SharedPreferences.Editor editor = mySharedPreferences.edit();
//			editor.putString("email", email);
//			editor.putBoolean("authenticated", true);
//			editor.commit();
//			Intent intent = new Intent(this, HomepageActivity.class);
//			startActivity(intent);
//		}
//		else {
//			Toast.makeText(this, "Email/password invalid", Toast.LENGTH_LONG).show();
//		}
		
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	String url = "login/?username=" + email + "&password=" + password;
	    	String method = "login";
	        new LoginTask(this).execute(url, method);
	    } else 
	        Toast.makeText(this, "No network connection", Toast.LENGTH_LONG).show();
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
