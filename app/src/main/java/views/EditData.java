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

public class EditData extends AppCompatActivity {

    private EditText title , description;
    private Button editButton;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        title = findViewById(R.id.titleIdFromEditData);
        description = findViewById(R.id.descriptionIdFromEditData);
        editButton = findViewById(R.id.editButtonId);

        dataBaseHelper = new DataBaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        String strbundle = bundle.getString("position");
        Data data = dataBaseHelper.getData(Integer.parseInt(strbundle));

        if( data != null ) {
            title.setText(data.getTitle());
            description.setText(data.getDescription());
        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data.setTitle(title.getText().toString());
                data.setDescription(description.getText().toString());

                dataBaseHelper.updateData( data );
                MainActivity.notifyAdapter();


                Intent intent = new Intent(EditData.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}