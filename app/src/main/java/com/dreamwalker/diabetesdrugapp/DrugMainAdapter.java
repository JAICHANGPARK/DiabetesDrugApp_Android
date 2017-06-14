package com.dreamwalker.diabetesdrugapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 2E313JCP on 2017-06-14.
 */

public class DrugMainAdapter extends RecyclerView.Adapter<DrugMainAdapter.MyViewHolder> {

    private Context context;
    private List<DrugMainData> drugList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,detail;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.txtName);
            detail = (TextView)itemView.findViewById(R.id.txtDetail);
            thumbnail =(ImageView)itemView.findViewById(R.id.cardImage);
            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        DrugMainData clickedDataItem = drugList.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("position", getAdapterPosition());
                        intent.putExtra("original_name", drugList.get(pos).getDrugName());
                        intent.putExtra("detail", drugList.get(pos).getDrugDetail());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"You Clicked"+clickedDataItem.getDrugName(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        DrugMainData clickedDataItem = drugList.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("position", getAdapterPosition());
                        intent.putExtra("original_name", drugList.get(pos).getDrugName());
                        intent.putExtra("detail", drugList.get(pos).getDrugDetail());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(),"You Clicked"+clickedDataItem.getDrugName(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
    public DrugMainAdapter(Context context, List<DrugMainData> drugList) {
        this.context = context;
        this.drugList = drugList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_layout,parent,false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        DrugMainData drugMainData = drugList.get(position);
        holder.title.setText(drugMainData.getDrugName());
        holder.detail.setText(drugMainData.getDrugDetail());
        Glide.with(context).load(drugMainData.getThumbnail()).into(holder.thumbnail);
    }
    @Override
    public int getItemCount() {
        return drugList.size();
    }
}
