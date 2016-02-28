package com.vanistudio.todolist;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by thuynh6 on 2/28/2016.
 */
public class NewItemFragment extends DialogFragment {

    private EditText et_fNewTask;
    private EditText et_fNewDue;
    private Button bt_fNewItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = (View) inflater.inflate(R.layout.fragment_new_item, container, false);
        et_fNewTask = (EditText) v.findViewById(R.id.et_fNewTask);
        et_fNewDue = (EditText) v.findViewById(R.id.et_fNewDue);
        bt_fNewItem = (Button) v.findViewById(R.id.bt_fNewItem);
        bt_fNewItem.setOnClickListener(AddOnClickListener);
        et_fNewTask.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().trim().length()== 0 || et_fNewDue.getText().toString().trim().length() == 0) {
                            bt_fNewItem.setEnabled(false);
                        }
                        else {
                            bt_fNewItem.setEnabled(true);
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // TODO Auto-generated method stub
                    }
                }

        );
        et_fNewDue.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().trim().length()== 0 || et_fNewTask.getText().toString().trim().length() == 0) {
                            bt_fNewItem.setEnabled(false);
                        }
                        else {
                            bt_fNewItem.setEnabled(true);
                        }
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        // TODO Auto-generated method stub
                    }
                }

        );
        getDialog().setTitle("Add New Item");
        return v;
    }

    private Button.OnClickListener AddOnClickListener
            = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            NewItemFragmentListener activity = (NewItemFragmentListener) getActivity();
            activity.updateResult(et_fNewTask.getText().toString() + ";" + et_fNewDue.getText().toString());
            dismiss();
        }
    };

    public interface NewItemFragmentListener {
        void updateResult(String inputText);
    }

}
