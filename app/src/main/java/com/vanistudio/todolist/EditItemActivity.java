package com.vanistudio.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        pos = getIntent().getIntExtra("pos", 0);
        EditText etEditTask = (EditText)findViewById(R.id.etEditTask);
        EditText etEditDue = (EditText)findViewById(R.id.etEditDue);
        CheckBox chbEditDone = (CheckBox)findViewById(R.id.chbEditDone);
        String task = getIntent().getStringExtra("task");
        String due = getIntent().getStringExtra("due");
        boolean done = getIntent().getBooleanExtra("done", false);
        etEditTask.setText(task);
        etEditTask.setSelection(task.length());
        etEditTask.requestFocus();
        etEditDue.setText(due);
        chbEditDone.setChecked(done);
    }

    public void onSave(View v) {
        EditText etEditTask = (EditText)findViewById(R.id.etEditTask);
        EditText etEditDue = (EditText)findViewById(R.id.etEditDue);
        CheckBox chbEditDone = (CheckBox)findViewById(R.id.chbEditDone);
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("task", etEditTask.getText().toString());
        data.putExtra("due", etEditDue.getText().toString());
        data.putExtra("done", chbEditDone.isChecked());
        data.putExtra("pos", pos); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
}
