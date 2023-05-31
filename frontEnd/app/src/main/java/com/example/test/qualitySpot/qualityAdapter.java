package com.example.test.qualitySpot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.Spot.SpotListAdapter;

import java.util.ArrayList;

public class qualityAdapter extends RecyclerView.Adapter<qualityAdapter.MyViewHolder> {
    private ArrayList<String> items = new ArrayList<>();

    public void addItem(String qualityItem){
        items.add(qualityItem);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.quality_item,parent,false);
        return new qualityAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView upsoName;
        private TextView siteAddress;
        private TextView geEnYn;
        private ImageView dibs;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            upsoName = itemView.findViewById(R.id.upsoName);
            siteAddress = itemView.findViewById(R.id.siteAddress);
            geEnYn = itemView.findViewById(R.id.geEnYn);
            dibs = itemView.findViewById(R.id.dibs);
        }


        public void onBind(String dibsItem){
            String[] dbElement = dibsItem.split(",");
            String name = dbElement[0];
            String address = dbElement[1];
            String quality = dbElement[2];

            upsoName.setText(name);
            siteAddress.setText(address);
            geEnYn.setText(quality);
        }
    }
}
