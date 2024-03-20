package org.myprojectofnotes2022.notesapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuInflater;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import controller.Adapter;
import controller.DataBaseHelper;
import views.AddData;

public class MainActivity extends AppCompatActivity {

    private static Adapter adapter;
    private RecyclerView recyclerView;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.myRecyclerViewId);
        dataBaseHelper = new DataBaseHelper(this);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddData.class);
                startActivity(intent);
                finish();
            }
        });
        
        adapter = new Adapter( this , dataBaseHelper.getAllData() , dataBaseHelper );

        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()) );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public static void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.help_icon_id:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc882");
                intent.putExtra(Intent.EXTRA_EMAIL , new String[] {"elmassoudiachraf9@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT , "HELP !");
                intent.putExtra(Intent.EXTRA_TEXT , "My Message : ");
                startActivity(intent);
                Toast.makeText(getApplicationContext() , "Help !" , Toast.LENGTH_SHORT).show();
                break;

            case R.id.share_icon_id:
                String appTitle = "Notes Application";
                String appLink = "http://play.google.com/store/apps/details?id=org.myprojectofnotes2022.notesapplication";
                Intent intent1 = new Intent(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT , appTitle + "\n" + appLink);
                startActivity(intent1);
                Toast.makeText(getApplicationContext() , "Share !" , Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}