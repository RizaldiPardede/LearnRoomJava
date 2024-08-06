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
import com.example.belajarroom.database.entitas.recycle;

import java.util.List;

public class recycleadapter  extends RecyclerView.Adapter<recycleadapter.viewAdapter>{
    private List<recycle> list;
    private Context context;
    private kontakadapter.Dialog dialog;

    public recycleadapter(List<recycle> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public interface  Dialog{
        void onClick(int position);
    }

    public void setDialog(kontakadapter.Dialog dialog){
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public viewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new recycleadapter.viewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewAdapter holder, int position) {
        holder.nama.setText(list.get(position).rfullname);
        holder.number.setText(list.get(position).rnumber);
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
