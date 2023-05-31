package com.example.test.RoomDatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.test.R;

import java.util.ArrayList;

public class DibsAdapter extends RecyclerView.Adapter<DibsAdapter.MyViewHolder> {
    private ArrayList<String> items = new ArrayList<>();

    public void addItem(String dibsItem){
        items.add(dibsItem);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.dibs_item,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView upsoName;
        private TextView siteAddress;
        private TextView geEnYn;
        private ImageView dibs;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        items.set(pos,"hello"+pos);

                        notifyDataSetChanged();
                    }
                }
            });
            upsoName = itemView.findViewById(R.id.upsoName);
            siteAddress = itemView.findViewById(R.id.siteAddress);
            geEnYn = itemView.findViewById(R.id.geEnYn);
            dibs = itemView.findViewById(R.id.dibs);
        }


        public void onBind(String dibsItem){
            String[] dbElement = dibsItem.split(",");
//            Double latitude = Double.parseDouble(dbElement[0]);
//            Double longitude = Double.parseDouble(dbElement[1]);
//            String code = dbElement[3];
            String name = dbElement[4];
            String address = dbElement[5];
            String quality = dbElement[6];

            upsoName.setText(name);
            siteAddress.setText(address);
            geEnYn.setText(quality);
        }
    }
}
