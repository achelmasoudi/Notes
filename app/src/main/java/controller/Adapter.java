package controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.myprojectofnotes2022.notesapplication.MainActivity;
import org.myprojectofnotes2022.notesapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Data;
import views.EditData;
import views.ViewDataActivity;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<Data> dataList;
    private DataBaseHelper dataBaseHelper;
    private AlertDialog.Builder alertDialog;

    public Adapter(Context pContext , List<Data> pDataList) {
        this.context = pContext;
        this.dataList = pDataList;
    }

    public Adapter(Context pContext , List<Data> pDataList , DataBaseHelper pDataBaseHelper) {
        this.context = pContext;
        this.dataList = pDataList;
        this.dataBaseHelper = pDataBaseHelper;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data data = dataList.get(position);
        holder.title.setText(data.getTitle());
        holder.timeStamp.setText(formatDate( data.getTimeStamp() ));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , EditData.class);
                intent.putExtra("position" , String.valueOf(data.getId()) );
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new AlertDialog.Builder(context);
                alertDialog.setCancelable(false);
                alertDialog.setTitle("DELETE");
                alertDialog.setMessage("Are You Sure You Want To Delete !");
                alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteOfData(position);
                    }
                });
                alertDialog.setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title , timeStamp;
        private ImageView edit , delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleId);
            timeStamp = itemView.findViewById(R.id.timeStampId);
            edit = itemView.findViewById(R.id.editId);
            delete = itemView.findViewById(R.id.deleteId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Data data = dataList.get(position);
            Intent intent = new Intent(context , ViewDataActivity.class);
            intent.putExtra("title" , data.getTitle());
            intent.putExtra("desc" , data.getDescription());
            context.startActivity(intent);
        }
    }

    public String formatDate(String timeStamp) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(timeStamp);
            SimpleDateFormat newSdf = new SimpleDateFormat("MMM d");
            return newSdf.format(date);
        } catch (ParseException e) {
            Log.e("ERROR" , e.getMessage());
        }
        return "";

    }

    public void deleteOfData(int position) {
        dataBaseHelper.deleteData(dataList.get(position));
        dataList.remove(position);
        MainActivity.notifyAdapter();
    }
}