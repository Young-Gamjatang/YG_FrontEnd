package com.example.test;


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
import com.example.test.Retrofitmanager.QualityRestaurantModel;

import java.util.List;

public class ResListAdapter extends RecyclerView.Adapter<ResListAdapter.MyViewHolder> {
    List<QualityRestaurantModel> resdata;
    private OnitemClick mCallback;

    public ResListAdapter( List<QualityRestaurantModel> resdata) {
        this.resdata = resdata;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView address;
        public LinearLayout frame;
        public ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title =itemView.findViewById(R.id.text_main_title);
            this.address =itemView.findViewById(R.id.text_main_address);
            this.frame = itemView.findViewById(R.id.frame_item);
            this.image = itemView.findViewById(R.id.mainimg);

        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.main_listview_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResListAdapter.MyViewHolder holder, int position) {

        holder.title.setText(resdata.get(position).getUpsoName());
        holder.address.setText(resdata.get(position).getSiteAddrRd());
        Log.d("url", String.valueOf(Uri.parse(resdata.get(position).getUrl())));
        Glide.with(holder.itemView.getContext()).load(resdata.get(position).getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return resdata.size();
    }
}
