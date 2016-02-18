package com.vanistudio.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by thuynh6 on 2/15/2016.
 */
public class CustomListViewAdapter extends ArrayAdapter<ListviewRow> {
    private static class ViewHolder {
        TextView task;
        TextView due;
        CheckBox done;
    }

    public CustomListViewAdapter(Context context, ArrayList<ListviewRow> tasks) {
        super(context, R.layout.listview_row, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListviewRow task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_row, parent, false);
            viewHolder.task = (TextView) convertView.findViewById(R.id.tvTask);
            viewHolder.due = (TextView) convertView.findViewById(R.id.tvDue);
            viewHolder.done = (CheckBox) convertView.findViewById(R.id.chbDone);
            convertView.setTag(viewHolder);

            viewHolder.done.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    ListviewRow row = (ListviewRow) cb.getTag();
                    row.setDone(cb.isChecked());
                }
            });

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.task.setText(task.task);
        viewHolder.due.setText(task.due);
        viewHolder.done.setChecked(task.done);
        viewHolder.done.setTag(task);
        // Return the completed view to render on screen
        return convertView;
    }
}
