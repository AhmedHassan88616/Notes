package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.zip.Inflater;

import javax.xml.transform.Result;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_TITLE = "EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "EXTRA_PRIORITY";


    EditText editTextTitle;
    EditText editTextDescription;
    NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextDescription = (EditText) findViewById(R.id.edit_text_description);
        numberPickerPriority = (NumberPicker) findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            String id = intent.getStringExtra(AddEditNoteActivity.EXTRA_ID);
            String title = intent.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String desription = intent.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = intent.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            editTextTitle.setText(title);
            editTextDescription.setText(title);
            numberPickerPriority.setValue(priority);
        } else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    void saveNote() {

        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int priority = numberPickerPriority.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "please enter the title and description", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id=getIntent().getIntExtra("EXTRA_ID",-1);
        if(id!=-1)
        {
            data.putExtra(EXTRA_ID, id);

        }

        setResult(RESULT_OK, data);
        finish();
    }
}
