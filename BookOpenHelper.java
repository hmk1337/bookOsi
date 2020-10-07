package com.example.sqliteapps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BookOpenHelper extends SQLiteOpenHelper {
  public int book_id;
  public String book_title;
  public String book_author;

  public BookOpenHelper(Context context) {
    super(context, "BookDB.db", null, 1);

  }
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE books" + "(id INTEGER PRIMARY KEY, title TEXT, author TEXT)");
    Log.d("BookDB", "BookDB:CREATE books SUCCESS");

  }
  @Override
  public void onUpgrade(SQLiteDatabase db, int argl, int arg2) {
    db.execSQL("DROP TABLE IF EXISTS books");
    Log.d("BookDB", "BookDB:DROP books SUCCESS");
    this.onCreate(db);

  }

  public void getBook(String id1) {
    SQLiteDatabase db = this.getWritableDatabase();
    String sql = "SELECT * FROM books where id=?";
    String[] args = new String[] {id1};
    Cursor cursor = db.rawQuery(sql, args);
    Log.d("BookDB", "BookDB-sql :" + sql + id1);
    if (cursor.moveToFirst()) {
      do {
        book_id = Integer.parseInt(cursor.getString(0));
        book_title = cursor.getString(1);
        book_author = cursor.getString(2);
      } while ( cursor.moveToNext());

    }
  }

  public void addBook() {
    SQLiteDatabase db = this.getWritableDatabase();
    String sql = "INSERT INTO books (id,title,author) VALUES (" +book_id+ " , '" +book_title+ "' , '" +book_author+ "') ";
    Log.d("BookDB", "BookDB-sql:" +sql);
    db.execSQL(sql);
  }
  public void updateBook(String id1) {
    SQLiteDatabase db = this.getWritableDatabase();
    String sql = "UPDATE books SET " + "title='" +book_title+ "', author= '" +book_author+ "' where id=?";
    String[] args = new String[] {id1};
    Log.d("BookDB", "BookDB-sql:" + sql + id1);
    db.execSQL(sql, args);

  }
  public void deleteBook(String id1) {
    SQLiteDatabase db = this.getWritableDatabase();
    String sql = "DELETE FROM books where id=?";
    String[] args = new String[] {id1};
    Log.d("BookDB", "BookDB-sql:" + sql + id1);
    db.execSQL(sql, args);
  }

}
