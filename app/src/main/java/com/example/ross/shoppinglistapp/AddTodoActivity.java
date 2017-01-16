package com.example.ross.shoppinglistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ross.shoppinglistapp.data.Todo;

public class AddTodoActivity extends AppCompatActivity {

    public static final String KEY_TODO = "KEY_TODO";

    private Todo todoToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);
        final EditText etTodo = (EditText) findViewById(R.id.etTodo);
        final EditText etDescription = (EditText) findViewById(R.id.etDescription);
        final EditText etPrice = (EditText) findViewById(R.id.etPrice);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final CheckBox cbDone = (CheckBox) findViewById(R.id.cbDone);

        if (getIntent() != null
                && getIntent().hasExtra(MainActivity.KEY_TODO_TO_EDIT)) {
            todoToEdit = (Todo) getIntent().getSerializableExtra(MainActivity.KEY_TODO_TO_EDIT);
            etTodo.setText(todoToEdit.getTodoTitle());
            etDescription.setText(todoToEdit.getTodoDescription());
            etPrice.setText(todoToEdit.getTodoPrice());
            if (todoToEdit.getTodoType().equals("Food")) {
                spinner.setSelection(0);
            }
            else if (todoToEdit.getTodoType().equals("Electronic")) {
                spinner.setSelection(1);
            }
            else if (todoToEdit.getTodoType().equals("Book")) {
                spinner.setSelection(2);
            }
            else if (todoToEdit.getTodoType().equals("Cleaning")) {
                spinner.setSelection(3);
            }
            cbDone.setChecked(todoToEdit.isDone());
        }

        Button btnSave = (Button) findViewById(R.id.btnAddTodoItem);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();

                Todo newTodo = todoToEdit;
                if (newTodo == null) {
                    newTodo = new Todo(etTodo.getText().toString(), etDescription.getText().toString(),
                            etPrice.getText().toString(), spinner.getSelectedItem().toString(), cbDone.isChecked());
                } else {
                    newTodo.setTodoTitle(etTodo.getText().toString());
                    newTodo.setTodoDescription(etDescription.getText().toString());
                    newTodo.setTodoPrice(etPrice.getText().toString());
                    newTodo.setTodoType(spinner.getSelectedItem().toString());
                    newTodo.setDone(cbDone.isChecked());
                }


                result.putExtra(KEY_TODO, newTodo);

                setResult(RESULT_OK, result);
                finish();
            }
        });

    }
}