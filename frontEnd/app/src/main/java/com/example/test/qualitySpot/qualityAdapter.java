package com.example.test.qualitySpot;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.test.OnitemClick;
import com.example.test.R;
import com.example.test.ResListAdapter;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.Spot.SpotListAdapter;

import java.util.ArrayList;
import java.util.List;

public class qualityAdapter extends RecyclerView.Adapter<qualityAdapter.MyViewHolder> {
    List<QualityRestaurantModel> qualitydata;

    public qualityAdapter(List<QualityRestaurantModel> qualitydata) {
        this.qualitydata = qualitydata;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.quality_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull qualityAdapter.MyViewHolder holder, int position) {
        holder.upsoName.setText(qualitydata.get(position).getUpsoName());
        holder.siteAddress.setText(qualitydata.get(position).getSiteAddrRd());
        holder.geEnYn.setText(qualitydata.get(position).getGeEnYn());
    }

    @Override
    public int getItemCount() {
        return qualitydata.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView upsoName;
        private TextView siteAddress;
        private TextView geEnYn;
        private ImageView dibs;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.upsoName = itemView.findViewById(R.id.upsoName);
            this.siteAddress = itemView.findViewById(R.id.siteAddress);
            this.geEnYn = itemView.findViewById(R.id.geEnYn);
            this.dibs = itemView.findViewById(R.id.dibs);
        }
    }
}
