package com.vanistudio.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        pos = getIntent().getIntExtra("pos", 0);
        String inReplyTo = getIntent().getStringExtra("in_reply_to");
        EditText tvEditItem = (EditText)findViewById(R.id.etEditItem);
        String content = getIntent().getStringExtra("content");
        tvEditItem.setText(content);
        tvEditItem.setSelection(content.length());
        tvEditItem.requestFocus();
    }

    public void onSave(View v) {
        EditText etEditName = (EditText) findViewById(R.id.etEditItem);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("content", etEditName.getText().toString());
        data.putExtra("pos", pos); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
