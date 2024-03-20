package views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.myprojectofnotes2022.notesapplication.MainActivity;
import org.myprojectofnotes2022.notesapplication.R;

import controller.DataBaseHelper;
import model.Data;

public class AddData extends AppCompatActivity {

    private EditText title , description;
    private Button addButton;

    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        title = findViewById(R.id.titleIdFromAddData);
        description = findViewById(R.id.descriptionIdFromAddData);
        addButton = findViewById(R.id.addButtonId);
        dataBaseHelper = new DataBaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String theTitle = title.getText().toString();
                String theDescription = description.getText().toString();

                dataBaseHelper.addData( new Data( theTitle , theDescription ) );

                Intent intent = new Intent(AddData.this , MainActivity.class);
                startActivity(intent);

                finish();

            }
        });



    }

}