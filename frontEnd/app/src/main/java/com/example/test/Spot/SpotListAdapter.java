package com.example.test.Spot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.Retrofitmanager.BadHygieneRestaurantModel;
import com.example.test.Retrofitmanager.QualityRestaurantModel;
import com.example.test.qualitySpot.qualityAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;

public class SpotListAdapter extends RecyclerView.Adapter<SpotListAdapter.MyViewHolder> {
    List<BadHygieneRestaurantModel> baddata;

    public SpotListAdapter(List<BadHygieneRestaurantModel> baddata) {
        this.baddata = baddata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.spot_list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.upsoName.setText(baddata.get(position).getUpsoNm());
        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
    }

    @Override
    public int getItemCount() {
        return baddata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView upsoName;
        private TextView siteAddress;
        private TextView admDispoYmd;
        private ImageView dibs;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.upsoName = itemView.findViewById(R.id.upsoName);
            this.siteAddress = itemView.findViewById(R.id.siteAddress);
            this.admDispoYmd = itemView.findViewById(R.id.geEnYn);
            this.dibs = itemView.findViewById(R.id.dibs);
        }
    }
}
