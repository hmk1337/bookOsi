package com.example.sqliteapps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
  BookOpenHelper db;
  EditText et_id;
  EditText et_title;
  EditText et_author;@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    db = new BookOpenHelper(this);
    et_id = (EditText) findViewById(R.id.EditText1);
    et_title = (EditText) findViewById(R.id.EditText2);
    et_author = (EditText) findViewById(R.id.EditText3);

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  public void searchRec(View view) {
    String id1 = et_id.getText().toString();
    db.getBook(id1);
    et_title.setText(db.book_title, TextView.BufferType.EDITABLE);
    et_author.setText(db.book_author, TextView.BufferType.EDITABLE);

  }

  public void addRec(View view) {
    db.book_id = Integer.parseInt(et_id.getText().toString());
    db.book_title = et_title.getText().toString();
    db.book_author = et_author.getText().toString();
    db.addBook();

  }

  public void updateRec(View view) {
    db.book_title = et_title.getText().toString();
    db.book_author = et_author.getText().toString();
    db.updateBook(et_id.getText().toString());

  }
  public void deleteRec(View view) {
    db.deleteBook(et_id.getText().toString());
  }
}
