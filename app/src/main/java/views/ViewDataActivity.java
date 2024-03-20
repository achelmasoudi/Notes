package views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.myprojectofnotes2022.notesapplication.R;

import controller.DataBaseHelper;
import model.Data;

public class ViewDataActivity extends AppCompatActivity {

    private TextView title , description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        title = findViewById(R.id.titleIdFromViewData);
        description = findViewById(R.id.descriptionIdFromViewData);

        Bundle bundle = getIntent().getExtras();
        String myTitle = bundle.getString("title");
        String myDesc = bundle.getString("desc");

        if( bundle != null ) {
            title.setText(myTitle);
            description.setText(myDesc);
        }

    }
}