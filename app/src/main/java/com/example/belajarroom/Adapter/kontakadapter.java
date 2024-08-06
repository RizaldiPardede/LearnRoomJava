package com.example.belajarroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belajarroom.R;
import com.example.belajarroom.database.entitas.kontak;

import java.util.List;

public class kontakadapter extends RecyclerView.Adapter<kontakadapter.viewAdapter>{
    private List<kontak> list;
    private Context context;
    private Dialog dialog;

    public interface  Dialog{
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public kontakadapter(List<kontak> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new viewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewAdapter holder, int position) {
        holder.nama.setText(list.get(position).fullname);
        holder.number.setText(list.get(position).nomor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewAdapter extends RecyclerView.ViewHolder{
        TextView nama, number;


        public viewAdapter(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            number = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());

                    }
                }
            });
        }
    }
}
