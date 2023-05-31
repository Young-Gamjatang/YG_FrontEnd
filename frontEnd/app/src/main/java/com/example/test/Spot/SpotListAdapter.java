package com.example.test.Spot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;

public class SpotListAdapter extends RecyclerView.Adapter<SpotListAdapter.MyViewHolder> {
    private ArrayList<String> items = new ArrayList<>();

    public void addItem(String body) {
        items.add(body);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_list_item,parent,false);
        return new SpotListAdapter.MyViewHolder(view);
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
        private TextView admDispoYmd;
        private ImageView dibs;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            upsoName = itemView.findViewById(R.id.upsoName);
            siteAddress = itemView.findViewById(R.id.siteAddress);
            admDispoYmd = itemView.findViewById(R.id.geEnYn);
            dibs = itemView.findViewById(R.id.dibs);
        }


        public void onBind(String spotItem) {
            String[] dbElement = spotItem.split(",");
            String name = dbElement[0];
            String address = dbElement[1];
            String quality = dbElement[2];

            upsoName.setText(name);
            siteAddress.setText(address);
            admDispoYmd.setText(quality);
        }
    }
}
