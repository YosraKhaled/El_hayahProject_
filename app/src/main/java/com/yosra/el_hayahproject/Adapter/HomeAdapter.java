package com.yosra.el_hayahproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yosra.el_hayahproject.pojo.HomeModel;
import com.yosra.el_hayahproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<HomeModel> list;
    private OnClickCar onClickCar;

    public HomeAdapter(Context context, List<HomeModel> list, OnClickCar onClickCar) {
        this.context = context;
        this.list = list;
        this.onClickCar = onClickCar;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.specialty.setText(list.get(position).getSpecialty());
        Glide.with(context).load(list.get(position).getImg()).into(holder.image);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String ID = list.get(position).getId();
               onClickCar.onItemClick(ID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, specialty;
        ImageView image;
        Button order;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            specialty = itemView.findViewById(R.id.Model);
            image = itemView.findViewById(R.id.Image);
            order = itemView.findViewById(R.id.order);


        }


    }

    public interface OnClickCar {
        void onItemClick(String id);
    }

}
