package com.vanistudio.todolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<ListviewRow> items = new ArrayList<ListviewRow>();
    CustomListViewAdapter itemsAdapter;
    ListView lvItems;
    private final int REQUEST_CODE = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        itemsAdapter = new CustomListViewAdapter(this, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
        EditText etNewTask = (EditText)findViewById(R.id.etNewTask);
        etNewTask.requestFocus();
    }

    public void onAddItem(View v) {
        EditText etNewTask = (EditText)findViewById(R.id.etNewTask);
        String task = etNewTask.getText().toString();
        EditText etNewDue = (EditText)findViewById(R.id.etNewDue);
        String due = etNewDue.getText().toString();
        ListviewRow row = new ListviewRow(task, due, false);
        itemsAdapter.add(row);
        writeItems();
        etNewTask.setText("");
        etNewDue.setText("");
        etNewTask.requestFocus();
    }

    private void setupListViewListener(){
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                itemsAdapter.remove(items.get(pos));
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View item, int pos, long id) {
                Object editItem = lvItems.getItemAtPosition(pos);
                launchEditView(pos, (ListviewRow)editItem);
            }
        });
    }

    private void readItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            ArrayList<String> lines = new ArrayList<String>(FileUtils.readLines(todoFile));
            ListviewRow row;
            for(String line: lines) {
                String[] content = line.split(";");
                if (content[2].equalsIgnoreCase("false")) {
                    row = new ListviewRow(content[0], content[1], false);
                }
                else {
                    row = new ListviewRow(content[0], content[1], true);
                }

                items.add(row);
            }
        }
        catch (IOException e){
            items = new ArrayList<ListviewRow>();
        }
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            ArrayList<String> content = new ArrayList<String>();
            for (ListviewRow row : items) {
                content.add(row.getTask() + ";" + row.getDue() + ";" + row.getDone());
            }
            FileUtils.writeLines(todoFile, content);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void launchEditView(int pos, ListviewRow content) {
        // first parameter is the context, second is the class of the activity to launch
        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
        i.putExtra("pos", pos);
        i.putExtra("task", content.getTask());
        i.putExtra("due", content.getDue());
        i.putExtra("done", content.getDone());
        startActivityForResult(i, REQUEST_CODE); // brings up the second activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            // Extract name value from result extras
            ListviewRow item;
            item = new ListviewRow(data.getStringExtra("task"), data.getStringExtra("due"), data.getBooleanExtra("done", false));
            items.set(data.getIntExtra("pos", 0), item);
            itemsAdapter.notifyDataSetChanged();
            writeItems();
        }
    }
}
