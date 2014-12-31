package com.example.ehayes.booksdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ehayes on 12/30/2014.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "MySQLiteHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "books";
    private static final String CREATE_BOOK_TABLE = "CREATE TABLE books (" +
            "_id INTEGER PRIMARY KEY, " +
            "Title TEXT, " +
            "author TEXT );";
    // Book table name
    private static final String TABLE_BOOKS = "books";

    // Book Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_AUTHOR = "author";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_AUTHOR};



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d(TAG, "onCreate() Called!!");
        db.execSQL(CREATE_BOOK_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG, "onUpgrade() Called!!");
        db.execSQL("DROP TABLE IF EXISTS books");

        this.onCreate(db);
    }

    /*
    Crud operations (create add read get update)
     */
    public void addBook(Book book){

        Log.d("addBook() called",book.toString());

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor()); // get author

        // 3. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }



}
