package com.reelreviews.reelreviews;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "reelreviews";

	//Single instance of Database
	private static DatabaseHelper sInstance;

	// Table Names
	private static final String TABLE_USERS = "users";
	private static final String TABLE_MOVIES = "movies";
	private static final String TABLE_SOURCES = "sources";
	private static final String TABLE_RECOMMENDATIONS = "recommendations";
	private static final String TABLE_PERSONS = "persons";
	private static final String TABLE_FAVORITES = "favorites";
	private static final String TABLE_CREDITS = "credits";
	private static final String TABLE_AVAILABLES = "availables";
	private static final String TABLE_GENRES = "genres";
	private static final String TABLE_MOVIEGENRES = "moviegenres";

	// Universal Column Name
	private static final String KEY_ID = "id";

	// TABLE_USERS - column names
	private static final String KEY_USERS_EMAIL = "email";
	private static final String KEY_USERS_USERNAME = "username";
	private static final String KEY_USERS_PASSWORD = "password";
	private static final String KEY_USERS_SCORE = "score";

	// TABLE_MOVIES - column names
	private static final String KEY_MOVIES_TITLE = "title";
	private static final String KEY_MOVIES_YEAR = "year";
	private static final String KEY_MOVIES_IMAGE = "image";

	// TABLE_SOURCES - column names
	private static final String KEY_SOURCES_ORIGIN = "origin";
	private static final String KEY_SOURCES_LINK = "link";

	// TABLE_RECOMMENDATIONS - column names
	private static final String KEY_RECOMMENDATIONS_SENDER = "sender";
	private static final String KEY_RECOMMENDATIONS_RECEIVER = "receiver";
	private static final String KEY_RECOMMENDATIONS_MOVIE = "movie";
	private static final String KEY_RECOMMENDATIONS_REVIEW = "review";
	private static final String KEY_RECOMMENDATIONS_DATE = "date";
	private static final String KEY_RECOMMENDATIONS_STARS = "stars";
	private static final String KEY_RECOMMENDATIONS_SOURCE = "source";

	// TABLE_PERSONS - column names
	private static final String KEY_PERSONS_NAME = "name";

	// TABLE_FAVORITES - column names
	private static final String KEY_FAVORITES_USER = "user";
	private static final String KEY_FAVORITES_MOVIE = "movie";
	private static final String KEY_FAVORITES_NUM = "num";

	// TABLE_CREDITS - column names
	private static final String KEY_CREDITS_MOVIE = "movie";
	private static final String KEY_CREDITS_PERSON = "person";
	private static final String KEY_CREDITS_ROLE = "role";

	// TABLE_AVAILABLES - column names
	private static final String KEY_AVAILABLES_MOVIE = "movie";
	private static final String KEY_AVAILABLES_SOURCE = "source";

	// TABLE_GENRES - column names
	private static final String KEY_GENRES_NAME = "name";

	// TABLE_MOVIEGENRES - column names
	private static final String KEY_MOVIEGENRES_MOVIE = "movie";
	private static final String KEY_MOVIEGENRES_GENRE = "genre";

	//CREATE TABLE statements
	private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_USERS_EMAIL + " TEXT NOT NULL UNIQUE, " +
			KEY_USERS_USERNAME + " TEXT, " +
			KEY_USERS_PASSWORD + " TEXT, " +
			KEY_USERS_SCORE + " INTEGER);";
	private static final String CREATE_TABLE_MOVIES = "CREATE TABLE " + TABLE_MOVIES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_MOVIES_TITLE + " TEXT NOT NULL, " +
			KEY_MOVIES_YEAR + " INTEGER NOT NULL, " +
			KEY_MOVIES_IMAGE + " TEXT);";
	private static final String CREATE_TABLE_SOURCES = "CREATE TABLE " + TABLE_SOURCES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_SOURCES_ORIGIN + " TEXT, " +
			KEY_SOURCES_LINK + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_RECOMMENDATIONS = "CREATE TABLE " + TABLE_RECOMMENDATIONS + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_RECOMMENDATIONS_SENDER + " INTEGER NOT NULL, " +
			KEY_RECOMMENDATIONS_RECEIVER + " INTEGER NOT NULL, " +
			KEY_RECOMMENDATIONS_MOVIE + " INTEGER NOT NULL, " +
			KEY_RECOMMENDATIONS_REVIEW + " TEXT, " +
			KEY_RECOMMENDATIONS_DATE + " INTEGER, " +
			KEY_RECOMMENDATIONS_STARS + " INTEGER, " +
			KEY_RECOMMENDATIONS_SOURCE + " INTEGER, " +
			"FOREIGN KEY(" + KEY_RECOMMENDATIONS_SENDER + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_RECOMMENDATIONS_RECEIVER + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_RECOMMENDATIONS_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_RECOMMENDATIONS_SOURCE + ") REFERENCES " + TABLE_SOURCES + "(" + KEY_ID + "));";
	private static final String CREATE_TABLE_PERSONS = "CREATE TABLE " + TABLE_PERSONS + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_PERSONS_NAME + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE " + TABLE_FAVORITES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_FAVORITES_USER + " INTEGER NOT NULL, " +
			KEY_FAVORITES_MOVIE + " INTEGER NOT NULL, " +
			KEY_FAVORITES_NUM + " INTEGER NOT NULL, " +
			"FOREIGN KEY(" + KEY_FAVORITES_USER + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_FAVORITES_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + KEY_ID + "));";
	private static final String CREATE_TABLE_CREDITS = "CREATE TABLE " + TABLE_CREDITS + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_CREDITS_MOVIE + " INTEGER NOT NULL, " +
			KEY_CREDITS_PERSON + " INTEGER NOT NULL, " +
			KEY_CREDITS_ROLE + " TEXT NOT NULL, " +
			"FOREIGN KEY(" + KEY_CREDITS_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_CREDITS_PERSON + ") REFERENCES " + TABLE_PERSONS + "(" + KEY_ID + "));";
	private static final String CREATE_TABLE_AVAILABLES = "CREATE TABLE " + TABLE_AVAILABLES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_AVAILABLES_MOVIE + " INTEGER NOT NULL, " +
			KEY_AVAILABLES_SOURCE + " INTEGER NOT NULL, " +
			"FOREIGN KEY(" + KEY_AVAILABLES_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_AVAILABLES_SOURCE + ") REFERENCES " + TABLE_SOURCES + "(" + KEY_ID + "));";
	private static final String CREATE_TABLE_GENRES = "CREATE TABLE " + TABLE_GENRES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_GENRES_NAME + " TEXT NOT NULL);";
	private static final String CREATE_TABLE_MOVIEGENRES = "CREATE TABLE " + TABLE_MOVIEGENRES + "(" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_MOVIEGENRES_MOVIE + " INTEGER NOT NULL, " +
			KEY_MOVIEGENRES_GENRE + " INTEGER NOT NULL, " +
			"FOREIGN KEY(" + KEY_MOVIEGENRES_MOVIE + ") REFERENCES " + TABLE_MOVIES + "(" + KEY_ID + "), " +
			"FOREIGN KEY(" + KEY_MOVIEGENRES_GENRE + ") REFERENCES " + TABLE_GENRES + "(" + KEY_ID + "));";

	//Private constructor - Only for getInstance(Context) method
	private DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//Public method for get or create
	public static DatabaseHelper getInstance(Context context) {
		if (sInstance == null)
			sInstance = new DatabaseHelper(context.getApplicationContext());
		return sInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("DB onCreate:", CREATE_TABLE_USERS);
		db.execSQL(CREATE_TABLE_USERS);
		Log.d("DB onCreate:", CREATE_TABLE_MOVIES);
		db.execSQL(CREATE_TABLE_MOVIES);
		Log.d("DB onCreate:", CREATE_TABLE_SOURCES);
		db.execSQL(CREATE_TABLE_SOURCES);
		Log.d("DB onCreate:", CREATE_TABLE_RECOMMENDATIONS);
		db.execSQL(CREATE_TABLE_RECOMMENDATIONS);
		Log.d("DB onCreate:", CREATE_TABLE_PERSONS);
		db.execSQL(CREATE_TABLE_PERSONS);
		Log.d("DB onCreate:", CREATE_TABLE_FAVORITES);
		db.execSQL(CREATE_TABLE_FAVORITES);
		Log.d("DB onCreate:", CREATE_TABLE_CREDITS);
		db.execSQL(CREATE_TABLE_CREDITS);
		Log.d("DB onCreate:", CREATE_TABLE_AVAILABLES);
		db.execSQL(CREATE_TABLE_AVAILABLES);
		Log.d("DB onCreate:", CREATE_TABLE_GENRES);
		db.execSQL(CREATE_TABLE_GENRES);
		Log.d("DB onCreate:", CREATE_TABLE_MOVIEGENRES);
		db.execSQL(CREATE_TABLE_MOVIEGENRES);
		Log.d("DB onCreate:", "Tables created successfully");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_MOVIES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_SOURCES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCES);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_RECOMMENDATIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECOMMENDATIONS);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_PERSONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_FAVORITES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_CREDITS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDITS);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_AVAILABLES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVAILABLES);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_GENRES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENRES);
		Log.d("DB onUpgrade:", "Dropping table : " + TABLE_MOVIEGENRES);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIEGENRES);
		Log.d("DB onUpgrade:", "All tables successfully dropped");
		onCreate(db);
	}


	// ------------------ USERS methods --------------------- //
	public long addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, user.getId());
		values.put(KEY_USERS_EMAIL, user.getEmail());
		values.put(KEY_USERS_USERNAME, user.getUsername());
		values.put(KEY_USERS_PASSWORD, user.getPassword());
		values.put(KEY_USERS_SCORE, user.getScore());
		long result = db.insertWithOnConflict(TABLE_USERS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public User getUser(int user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE " + KEY_ID + " = " + user_id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(user_id)};
		Cursor c = db.query(TABLE_USERS, null, selection, args, null, null, null, null);
		if (c == null)
			return null;
		c.moveToFirst();
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		String email = c.getString(c.getColumnIndex(KEY_USERS_EMAIL));
		String username = c.getString(c.getColumnIndex(KEY_USERS_USERNAME));
		String password = c.getString(c.getColumnIndex(KEY_USERS_PASSWORD));
		int score = c.getInt(c.getColumnIndex(KEY_USERS_SCORE));
		db.close();
		return new User(id, email, username, password, score);
	}

	public User getUser(String email) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE " + KEY_USERS_EMAIL + " = " + email;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_USERS_EMAIL + " = ?";
		String[] args = {email};
		Cursor c = db.query(TABLE_USERS, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		String username = c.getString(c.getColumnIndex(KEY_USERS_USERNAME));
		String password = c.getString(c.getColumnIndex(KEY_USERS_PASSWORD));
		int score = c.getInt(c.getColumnIndex(KEY_USERS_SCORE));
		db.close();
		return new User(id, email, username, password, score);
	}

	public ArrayList<User> getUsers() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<User> users = new ArrayList<User>();
//		String selectQuery = "SELECT  * FROM " + TABLE_USERS;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_USERS, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				String email = c.getString(c.getColumnIndex(KEY_USERS_EMAIL));
				String username = c.getString(c.getColumnIndex(KEY_USERS_USERNAME));
				String password = c.getString(c.getColumnIndex(KEY_USERS_PASSWORD));
				int score = c.getInt(c.getColumnIndex(KEY_USERS_SCORE));
				User user = new User(id, email, username, password, score);
				users.add(user);
			} while (c.moveToNext());
		}
		db.close();
		return users;
	}

	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, user.getId());
		values.put(KEY_USERS_EMAIL, user.getEmail());
		values.put(KEY_USERS_USERNAME, user.getUsername());
		values.put(KEY_USERS_PASSWORD, user.getPassword());
		values.put(KEY_USERS_SCORE, user.getScore());
		int result = db.update(TABLE_USERS, values, KEY_ID + " = ?", new String[] {String.valueOf(user.getId())});
		db.close();
		return result;
	}

	public void deleteUser(int user_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_ID + " = ?", new String[] {String.valueOf(user_id)});
		db.close();
	}

	public void deleteUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_USERS, KEY_ID + " = ?", new String[] {String.valueOf(user.getId())});
		db.close();
	}

	// ------------------ MOVIES methods --------------------- //
	public long addMovie(Movie movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, movie.getId());
		values.put(KEY_MOVIES_TITLE, movie.getTitle());
		values.put(KEY_MOVIES_YEAR, movie.getYear());
		values.put(KEY_MOVIES_IMAGE, movie.getImage());
		long result = db.insertWithOnConflict(TABLE_MOVIES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Movie getMovie(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIES + " WHERE " + KEY_ID + " = " + movie_id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_MOVIES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		String title = c.getString(c.getColumnIndex(KEY_MOVIES_TITLE));
		int year = c.getInt(c.getColumnIndex(KEY_MOVIES_YEAR));
		String image = c.getString(c.getColumnIndex(KEY_MOVIES_IMAGE));
		db.close();
		return new Movie(id, title, year, image);
	}

	public Movie getMovie(String title) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIES + " WHERE " + KEY_MOVIES_TITLE + " = " + title;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_MOVIES_TITLE + " = ?";
		String[] args = {title};
		Cursor c = db.query(TABLE_MOVIES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		int year = c.getInt(c.getColumnIndex(KEY_MOVIES_YEAR));
		String image = c.getString(c.getColumnIndex(KEY_MOVIES_IMAGE));
		db.close();
		return new Movie(id, title, year, image);
	}

	public Movie getMovie(String title, int year) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIES + " WHERE " + KEY_MOVIES_TITLE + " = " + title + " AND " + KEY_MOVIES_YEAR + " = " + year;
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_MOVIES_TITLE + " = ? AND " + KEY_MOVIES_YEAR + " = ?";
		String[] args = {title, String.valueOf(year)};
		Cursor c = db.query(TABLE_MOVIES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		String image = c.getString(c.getColumnIndex(KEY_MOVIES_IMAGE));
		db.close();
		return new Movie(id, title, year, image);
	}

	public ArrayList<Movie> getMovies() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Movie> movies = new ArrayList<Movie>();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_MOVIES, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				String title = c.getString(c.getColumnIndex(KEY_MOVIES_TITLE));
				int year = c.getInt(c.getColumnIndex(KEY_MOVIES_YEAR));
				String image = c.getString(c.getColumnIndex(KEY_MOVIES_IMAGE));
				Movie movie = new Movie(id, title, year, image);
				movies.add(movie);
			} while (c.moveToNext());
		}
		db.close();
		return movies;
	}

	public int updateMovie(Movie movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, movie.getId());
		values.put(KEY_MOVIES_TITLE, movie.getTitle());
		values.put(KEY_MOVIES_YEAR, movie.getYear());
		values.put(KEY_MOVIES_IMAGE, movie.getImage());
		int result = db.update(TABLE_MOVIES, values, KEY_ID + " = ?", new String[] {String.valueOf(movie.getId())});
		db.close();
		return result;
	}

	public void deleteMovie(int movie_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MOVIES, KEY_ID + " = ?", new String[] {String.valueOf(movie_id)});
		db.close();
	}

	public void deleteMovie(Movie movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MOVIES, KEY_ID + " = ?", new String[] {String.valueOf(movie.getId())});
		db.close();
	}

	// ------------------ SOURCES methods --------------------- //
	public long addSource(Source source) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, source.getId());
		values.put(KEY_SOURCES_ORIGIN, source.getOrigin());
		values.put(KEY_SOURCES_LINK, source.getLink());
		long result = db.insertWithOnConflict(TABLE_SOURCES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Source getSource(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_SOURCES + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_SOURCES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		String origin = c.getString(c.getColumnIndex(KEY_SOURCES_ORIGIN));
		String link = c.getString(c.getColumnIndex(KEY_SOURCES_LINK));
		db.close();
		return new Source(id, origin, link);
	}

	public Source getSource(String origin)
	{
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_SOURCES + " WHERE " + KEY_SOURCES_ORIGIN + " = " + origin;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_SOURCES_ORIGIN + " = ?";
		String[] args = {origin};
		Cursor c = db.query(TABLE_SOURCES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		String link = c.getString(c.getColumnIndex(KEY_SOURCES_LINK));
		db.close();
		return new Source(id, origin, link);
	}

	public ArrayList<Source> getSources() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Source> sources = new ArrayList<Source>();
//		String selectQuery = "SELECT  * FROM " + TABLE_SOURCES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_SOURCES, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				String origin = c.getString(c.getColumnIndex(KEY_SOURCES_ORIGIN));
				String link = c.getString(c.getColumnIndex(KEY_SOURCES_LINK));
				Source source = new Source(id, origin, link);
				sources.add(source);
			} while (c.moveToNext());
		}
		db.close();
		return sources;
	}

	public int updateSource(Source source) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, source.getId());
		values.put(KEY_SOURCES_ORIGIN, source.getOrigin());
		values.put(KEY_SOURCES_LINK, source.getLink());
		int result = db.update(TABLE_SOURCES, values, KEY_ID + " = ?", new String[] {String.valueOf(source.getId())});
		db.close();
		return result;
	}

	public void deleteSource(int source_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SOURCES, KEY_ID + " = ?", new String[] {String.valueOf(source_id)});
		db.close();
	}

	public void deleteSource(Source source) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SOURCES, KEY_ID + " = ?", new String[] {String.valueOf(source.getId())});
		db.close();
	}

	// ------------------ RECOMMENDATIONS methods --------------------- //
	public long addRecommendation(Recommendation recommendation) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, recommendation.getId());
		values.put(KEY_RECOMMENDATIONS_SENDER, recommendation.getSender().getId());
		values.put(KEY_RECOMMENDATIONS_RECEIVER, recommendation.getReceiver().getId());
		values.put(KEY_RECOMMENDATIONS_MOVIE, recommendation.getMovie().getId());
		values.put(KEY_RECOMMENDATIONS_REVIEW, recommendation.getReview());
		values.put(KEY_RECOMMENDATIONS_DATE, recommendation.getDate());
		values.put(KEY_RECOMMENDATIONS_STARS, recommendation.getStars());
		values.put(KEY_RECOMMENDATIONS_SOURCE, recommendation.getSource().getId());
		long result = db.insertWithOnConflict(TABLE_RECOMMENDATIONS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Recommendation getRecommendation(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_RECOMMENDATIONS + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_RECOMMENDATIONS, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int sender_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_SENDER));
		int receiver_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_RECEIVER));
		int movie_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_MOVIE));
		String review = c.getString(c.getColumnIndex(KEY_RECOMMENDATIONS_REVIEW));
		long date = c.getLong(c.getColumnIndex(KEY_RECOMMENDATIONS_DATE));
		int stars = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_STARS));
		int source_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_SOURCE));
		db.close();
		User sender = getUser(sender_id);
		User receiver = getUser(receiver_id);
		Movie movie = getMovie(movie_id);
		Source source = getSource(source_id);
		return new Recommendation(id, sender, receiver, movie, review, date, stars, source);
	}

	public ArrayList<Recommendation> getRecommendations() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Recommendation> recommendations = new ArrayList<Recommendation>();
//		String selectQuery = "SELECT  * FROM " + TABLE_RECOMMENDATIONS;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_RECOMMENDATIONS, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int sender_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_SENDER));
				int receiver_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_RECEIVER));
				int movie_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_MOVIE));
				String review = c.getString(c.getColumnIndex(KEY_RECOMMENDATIONS_REVIEW));
				long date = c.getLong(c.getColumnIndex(KEY_RECOMMENDATIONS_DATE));
				int stars = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_STARS));
				int source_id = c.getInt(c.getColumnIndex(KEY_RECOMMENDATIONS_SOURCE));
				User sender = getUser(sender_id);
				User receiver = getUser(receiver_id);
				Movie movie = getMovie(movie_id);
				Source source = getSource(source_id);
				Recommendation recommendation = new Recommendation(id, sender, receiver, movie, review, date, stars, source);
				recommendations.add(recommendation);
			} while (c.moveToNext());
		}
		db.close();
		return recommendations;
	}

	public int updateRecommendation(Recommendation recommendation) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, recommendation.getId());
		values.put(KEY_RECOMMENDATIONS_SENDER, recommendation.getSender().getId());
		values.put(KEY_RECOMMENDATIONS_RECEIVER, recommendation.getReceiver().getId());
		values.put(KEY_RECOMMENDATIONS_MOVIE, recommendation.getMovie().getId());
		values.put(KEY_RECOMMENDATIONS_REVIEW, recommendation.getReview());
		values.put(KEY_RECOMMENDATIONS_DATE, recommendation.getDate());
		values.put(KEY_RECOMMENDATIONS_STARS, recommendation.getStars());
		values.put(KEY_RECOMMENDATIONS_SOURCE, recommendation.getSource().getId());
		int result = db.update(TABLE_RECOMMENDATIONS, values, KEY_ID + " = ?", new String[] {String.valueOf(recommendation.getId())});
		db.close();
		return result;
	}

	public void deleteRecommendation(int recommendation_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RECOMMENDATIONS, KEY_ID + " = ?", new String[] {String.valueOf(recommendation_id)});
		db.close();
	}

	public void deleteRecommendation(Recommendation recommendation) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_RECOMMENDATIONS, KEY_ID + " = ?", new String[] {String.valueOf(recommendation.getId())});
		db.close();
	}

	// ------------------ PERSONS methods --------------------- //
	public long addPerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, person.getId());
		values.put(KEY_PERSONS_NAME, person.getName());
		long result = db.insertWithOnConflict(TABLE_PERSONS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Person getPerson(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_PERSONS + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_PERSONS, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		String name = c.getString(c.getColumnIndex(KEY_PERSONS_NAME));
		db.close();
		return new Person(id, name);
	}

	public Person getPerson(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_PERSONS + " WHERE " + KEY_PERSONS_NAME + " = " + name;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_PERSONS_NAME + " = ?";
		String[] args = {name};
		Cursor c = db.query(TABLE_PERSONS, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int id = c.getInt(c.getColumnIndex(KEY_ID));
		db.close();
		return new Person(id, name);
	}

	public ArrayList<Person> getPersons() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Person> persons = new ArrayList<Person>();
//		String selectQuery = "SELECT  * FROM " + TABLE_PERSONS;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_PERSONS, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				String name = c.getString(c.getColumnIndex(KEY_PERSONS_NAME));
				Person person = new Person(id, name);
				persons.add(person);
			} while (c.moveToNext());
		}
		db.close();
		return persons;
	}

	public int updatePerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, person.getId());
		values.put(KEY_PERSONS_NAME, person.getName());
		int result = db.update(TABLE_PERSONS, values, KEY_ID + " = ?", new String[] {String.valueOf(person.getId())});
		db.close();
		return result;
	}

	public void deletePerson(int person_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PERSONS, KEY_ID + " = ?", new String[] {String.valueOf(person_id)});
		db.close();
	}

	public void deletePerson(Person person) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PERSONS, KEY_ID + " = ?", new String[] {String.valueOf(person.getId())});
		db.close();
	}

	// ------------------ FAVORITES methods --------------------- //
	public long addFavorite(Favorite favorite) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, favorite.getId());
		values.put(KEY_FAVORITES_USER, favorite.getUser().getId());
		values.put(KEY_FAVORITES_MOVIE, favorite.getMovie().getId());
		values.put(KEY_FAVORITES_NUM, favorite.getNum());
		long result = db.insertWithOnConflict(TABLE_FAVORITES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Favorite getFavorite(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_FAVORITES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int user_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_USER));
		int movie_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_MOVIE));
		int num = c.getInt(c.getColumnIndex(KEY_FAVORITES_NUM));
		db.close();
		User user = getUser(user_id);
		Movie movie = getMovie(movie_id);
		return new Favorite(id, user, movie, num);
	}

	public ArrayList<Favorite> getFavorites() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Favorite> favorites = new ArrayList<Favorite>();
//		String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_RECOMMENDATIONS, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int user_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_USER));
				int movie_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_MOVIE));
				int num = c.getInt(c.getColumnIndex(KEY_FAVORITES_NUM));
				User user = getUser(user_id);
				Movie movie = getMovie(movie_id);
				Favorite favorite = new Favorite(id, user, movie, num);
				favorites.add(favorite);
			} while (c.moveToNext());
		}
		db.close();
		return favorites;
	}

	public ArrayList<Favorite> getFavorites(User user) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Favorite> favorites = new ArrayList<Favorite>();
//		String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES +
//				" WHERE " + KEY_FAVORITES_USER + " = " + user.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_FAVORITES_USER + " = ?";
		String[] args = {String.valueOf(user.getId())};
		Cursor c = db.query(TABLE_FAVORITES, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_MOVIE));
				int num = c.getInt(c.getColumnIndex(KEY_FAVORITES_NUM));
				Movie movie = getMovie(movie_id);
				Favorite favorite = new Favorite(id, user, movie, num);
				favorites.add(favorite);
			} while (c.moveToNext());
		}
		db.close();
		return favorites;
	}

	public ArrayList<Favorite> getFavorites(int user_id) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Favorite> favorites = new ArrayList<Favorite>();
//		String selectQuery = "SELECT  * FROM " + TABLE_FAVORITES +
//				" WHERE " + KEY_FAVORITES_USER + " = " + user_id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_FAVORITES_USER + " = ?";
		String[] args = {String.valueOf(user_id)};
		Cursor c = db.query(TABLE_FAVORITES, null, selection, args, null, null, null, null);
		User user = getUser(user_id);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_FAVORITES_MOVIE));
				int num = c.getInt(c.getColumnIndex(KEY_FAVORITES_NUM));
				Movie movie = getMovie(movie_id);
				Favorite favorite = new Favorite(id, user, movie, num);
				favorites.add(favorite);
			} while (c.moveToNext());
		}
		db.close();
		return favorites;
	}

	public int updateFavorite(Favorite favorite) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, favorite.getId());
		values.put(KEY_FAVORITES_USER, favorite.getUser().getId());
		values.put(KEY_FAVORITES_MOVIE, favorite.getMovie().getId());
		values.put(KEY_FAVORITES_NUM, favorite.getNum());
		int result = db.update(TABLE_FAVORITES, values, KEY_ID + " = ?", new String[] {String.valueOf(favorite.getId())});
		db.close();
		return result;
	}

	public void deleteFavorite(int favorite_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FAVORITES, KEY_ID + " = ?", new String[] {String.valueOf(favorite_id)});
		db.close();
	}

	public void deleteFavorite(Favorite favorite) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FAVORITES, KEY_ID + " = ?", new String[] {String.valueOf(favorite.getId())});
		db.close();
	}

	// ------------------ CREDITS methods --------------------- //
	public long addCredit(Credit credit) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, credit.getId());
		values.put(KEY_CREDITS_MOVIE, credit.getMovie().getId());
		values.put(KEY_CREDITS_PERSON, credit.getPerson().getId());
		values.put(KEY_CREDITS_ROLE, credit.getRole());
		long result = db.insertWithOnConflict(TABLE_CREDITS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Credit getCredit(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_CREDITS +
//				" WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_CREDITS, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int movie_id = c.getInt(c.getColumnIndex(KEY_CREDITS_MOVIE));
		int person_id = c.getInt(c.getColumnIndex(KEY_CREDITS_PERSON));
		String role = c.getString(c.getColumnIndex(KEY_CREDITS_ROLE));
		db.close();
		Movie movie = getMovie(movie_id);
		Person person = getPerson(person_id);
		return new Credit(id, movie, person, role);
	}

	public ArrayList<Credit> getCredits() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Credit> credits = new ArrayList<Credit>();
//		String selectQuery = "SELECT  * FROM " + TABLE_CREDITS;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_CREDITS, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_CREDITS_MOVIE));
				int person_id = c.getInt(c.getColumnIndex(KEY_CREDITS_PERSON));
				String role = c.getString(c.getColumnIndex(KEY_CREDITS_ROLE));
				Movie movie = getMovie(movie_id);
				Person person = getPerson(person_id);
				Credit credit = new Credit(id, movie, person, role);
				credits.add(credit);
			} while (c.moveToNext());
		}
		db.close();
		return credits;
	}

	public ArrayList<Credit> getCredits(Movie movie) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Credit> credits = new ArrayList<Credit>();
//		String selectQuery = "SELECT  * FROM " + TABLE_CREDITS +
//				" WHERE " + KEY_CREDITS_MOVIE + " = " + movie.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_CREDITS_MOVIE + " = ?";
		String[] args = {String.valueOf(movie.getId())};
		Cursor c = db.query(TABLE_CREDITS, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int person_id = c.getInt(c.getColumnIndex(KEY_CREDITS_PERSON));
				String role = c.getString(c.getColumnIndex(KEY_CREDITS_ROLE));
				Person person = getPerson(person_id);
				Credit credit = new Credit(id, movie, person, role);
				credits.add(credit);
			} while (c.moveToNext());
		}
		db.close();
		return credits;
	}

	public ArrayList<Credit> getCredits(Person person) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Credit> credits = new ArrayList<Credit>();
//		String selectQuery = "SELECT  * FROM " + TABLE_CREDITS +
//				" WHERE " + KEY_CREDITS_PERSON + " = " + person.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_CREDITS_PERSON + " = ?";
		String[] args = {String.valueOf(person.getId())};
		Cursor c = db.query(TABLE_CREDITS, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_CREDITS_MOVIE));
				String role = c.getString(c.getColumnIndex(KEY_CREDITS_ROLE));
				Movie movie = getMovie(movie_id);
				Credit credit = new Credit(id, movie, person, role);
				credits.add(credit);
			} while (c.moveToNext());
		}
		db.close();
		return credits;
	}

	public int updateCredit(Credit credit) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, credit.getId());
		values.put(KEY_CREDITS_MOVIE, credit.getMovie().getId());
		values.put(KEY_CREDITS_PERSON, credit.getPerson().getId());
		values.put(KEY_CREDITS_ROLE, credit.getRole());
		int result = db.update(TABLE_CREDITS, values, KEY_ID + " = ?", new String[] {String.valueOf(credit.getId())});
		db.close();
		return result;
	}

	public void deleteCredit(int credit_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CREDITS, KEY_ID + " = ?", new String[] {String.valueOf(credit_id)});
		db.close();
	}

	public void deleteCredit(Credit credit) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CREDITS, KEY_ID + " = ?", new String[] {String.valueOf(credit.getId())});
		db.close();
	}

	// ------------------ AVAILABLES methods --------------------- //
	public long addAvailable(Available available) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, available.getId());
		values.put(KEY_AVAILABLES_MOVIE, available.getMovie().getId());
		values.put(KEY_AVAILABLES_SOURCE, available.getSource().getId());
		long result = db.insertWithOnConflict(TABLE_AVAILABLES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Available getAvailable(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_AVAILABLES + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_AVAILABLES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int movie_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_MOVIE));
		int source_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_SOURCE));
		Movie movie = getMovie(movie_id);
		Source source = getSource(source_id);
		db.close();
		return new Available(id, movie, source);
	}

	public ArrayList<Available> getAvailables() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Available> availables = new ArrayList<Available>();
//		String selectQuery = "SELECT  * FROM " + TABLE_AVAILABLES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_AVAILABLES, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_MOVIE));
				int source_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_SOURCE));
				Movie movie = getMovie(movie_id);
				Source source = getSource(source_id);
				Available available = new Available(id, movie, source);
				availables.add(available);
			} while (c.moveToNext());
		}
		db.close();
		return availables;
	}

	public ArrayList<Available> getAvailables(Movie movie) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Available> availables = new ArrayList<Available>();
//		String selectQuery = "SELECT  * FROM " + TABLE_AVAILABLES +
//				" WHERE " + KEY_AVAILABLES_MOVIE + " = " + movie.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_AVAILABLES_MOVIE + " = ?";
		String[] args = {String.valueOf(movie.getId())};
		Cursor c = db.query(TABLE_AVAILABLES, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int source_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_SOURCE));
				Source source = getSource(source_id);
				Available available = new Available(id, movie, source);
				availables.add(available);
			} while (c.moveToNext());
		}
		db.close();
		return availables;
	}

	public ArrayList<Available> getAvailables(Source source) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Available> availables = new ArrayList<Available>();
//		String selectQuery = "SELECT  * FROM " + TABLE_AVAILABLES +
//				" WHERE " + KEY_AVAILABLES_SOURCE + " = " + source.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_AVAILABLES_SOURCE + " = ?";
		String[] args = {String.valueOf(source.getId())};
		Cursor c = db.query(TABLE_AVAILABLES, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_AVAILABLES_MOVIE));
				Movie movie = getMovie(movie_id);
				Available available = new Available(id, movie, source);
				availables.add(available);
			} while (c.moveToNext());
		}
		db.close();
		return availables;
	}

	public int updateAvailable(Available available) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, available.getId());
		values.put(KEY_AVAILABLES_MOVIE, available.getMovie().getId());
		values.put(KEY_AVAILABLES_SOURCE, available.getSource().getId());
		int result = db.update(TABLE_AVAILABLES, values, KEY_ID + " = ?", new String[] {String.valueOf(available.getId())});
		db.close();
		return result;
	}

	public void deleteAvailable(int available_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_AVAILABLES, KEY_ID + " = ?", new String[] {String.valueOf(available_id)});
		db.close();
	}

	public void deleteAvailable(Available available) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_AVAILABLES, KEY_ID + " = ?", new String[] {String.valueOf(available.getId())});
		db.close();
	}

	// ------------------ GENRES methods --------------------- //
	public long addGenre(Genre genre) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, genre.getId());
		values.put(KEY_GENRES_NAME, genre.getName());
		long result = db.insertWithOnConflict(TABLE_GENRES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public Genre getGenre(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_GENRES + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_GENRES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		String name = c.getString(c.getColumnIndex(KEY_GENRES_NAME));
		db.close();
		return new Genre(id, name);
	}
	
	//TODO: getGenre(String name)

	public ArrayList<Genre> getGenres() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<Genre> genres = new ArrayList<Genre>();
//		String selectQuery = "SELECT  * FROM " + TABLE_GENRES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_GENRES, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				String name = c.getString(c.getColumnIndex(KEY_GENRES_NAME));
				Genre genre = new Genre(id, name);
				genres.add(genre);
			} while (c.moveToNext());
		}
		db.close();
		return genres;
	}
	
	//TODO:getGenres(Movie movie)
	
	public int updateGenre(Genre genre) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, genre.getId());
		values.put(KEY_GENRES_NAME, genre.getName());
		int result = db.update(TABLE_GENRES, values, KEY_ID + " = ?", new String[] {String.valueOf(genre.getId())});
		db.close();
		return result;
	}

	public void deleteGenre(int genre_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GENRES, KEY_ID + " = ?", new String[] {String.valueOf(genre_id)});
		db.close();
	}

	public void deleteGenre(Genre genre) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_GENRES, KEY_ID + " = ?", new String[] {String.valueOf(genre.getId())});
		db.close();
	}

	// ------------------ MOVIEGENRES methods --------------------- //
	public long addMovieGenre(MovieGenre moviegenre) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put(KEY_ID, moviegenre.getId());
		values.put(KEY_MOVIEGENRES_MOVIE, moviegenre.getMovie().getId());
		values.put(KEY_MOVIEGENRES_GENRE, moviegenre.getGenre().getId());
		long result = db.insertWithOnConflict(TABLE_MOVIEGENRES, null, values, SQLiteDatabase.CONFLICT_REPLACE);
		db.close();
		return result;
	}

	public MovieGenre getMovieGenre(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIEGENRES + " WHERE " + KEY_ID + " = " + id;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_ID + " = ?";
		String[] args = {String.valueOf(id)};
		Cursor c = db.query(TABLE_MOVIEGENRES, null, selection, args, null, null, null, null);
		if (c == null || !c.moveToFirst())
			return null;
		int movie_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_MOVIE));
		int genre_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_GENRE));
		Movie movie = getMovie(movie_id);
		Genre genre = getGenre(genre_id);
		db.close();
		return new MovieGenre(id, movie, genre);
	}

	public ArrayList<MovieGenre> getMovieGenres() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<MovieGenre> moviegenres = new ArrayList<MovieGenre>();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIEGENRES;
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		Cursor c = db.query(TABLE_MOVIEGENRES, null, null, null, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_MOVIE));
				int genre_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_GENRE));
				Movie movie = getMovie(movie_id);
				Genre genre = getGenre(genre_id);
				MovieGenre moviegenre = new MovieGenre(id, movie, genre);
				moviegenres.add(moviegenre);
			} while (c.moveToNext());
		}
		db.close();
		return moviegenres;
	}

	public ArrayList<MovieGenre> getMovieGenres(Movie movie) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<MovieGenre> moviegenres = new ArrayList<MovieGenre>();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIEGENRES +
//				" WHERE " + KEY_MOVIEGENRES_MOVIE + " = " + movie.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_MOVIEGENRES_MOVIE + " = ?";
		String[] args = {String.valueOf(movie.getId())};
		Cursor c = db.query(TABLE_MOVIEGENRES, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int genre_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_GENRE));
				Genre genre = getGenre(genre_id);
				MovieGenre moviegenre = new MovieGenre(id, movie, genre);
				moviegenres.add(moviegenre);
			} while (c.moveToNext());
		}
		db.close();
		return moviegenres;
	}

	public ArrayList<MovieGenre> getMovieGenres(Genre genre) {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<MovieGenre> moviegenres = new ArrayList<MovieGenre>();
//		String selectQuery = "SELECT  * FROM " + TABLE_MOVIEGENRES +
//				" WHERE " + KEY_MOVIEGENRES_SOURCE + " = " + source.getId();
//		Log.d("Database Query", selectQuery);
//		Cursor c = db.rawQuery(selectQuery, null);
		String selection = KEY_MOVIEGENRES_GENRE + " = ?";
		String[] args = {String.valueOf(genre.getId())};
		Cursor c = db.query(TABLE_MOVIEGENRES, null, selection, args, null, null, null, null);
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex(KEY_ID));
				int movie_id = c.getInt(c.getColumnIndex(KEY_MOVIEGENRES_MOVIE));
				Movie movie = getMovie(movie_id);
				MovieGenre moviegenre = new MovieGenre(id, movie, genre);
				moviegenres.add(moviegenre);
			} while (c.moveToNext());
		}
		db.close();
		return moviegenres;
	}

	public int updateMovieGenre(MovieGenre moviegenre) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ID, moviegenre.getId());
		values.put(KEY_MOVIEGENRES_MOVIE, moviegenre.getMovie().getId());
		values.put(KEY_MOVIEGENRES_GENRE, moviegenre.getGenre().getId());
		int result = db.update(TABLE_MOVIEGENRES, values, KEY_ID + " = ?", new String[] {String.valueOf(moviegenre.getId())});
		db.close();
		return result;
	}

	public void deleteMoviegenre(int moviegenre_id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MOVIEGENRES, KEY_ID + " = ?", new String[] {String.valueOf(moviegenre_id)});
		db.close();
	}

	public void deleteMoviegenre(MovieGenre moviegenre) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MOVIEGENRES, KEY_ID + " = ?", new String[] {String.valueOf(moviegenre.getId())});
		db.close();
	}

	//---------------- close -------------------//
	public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
