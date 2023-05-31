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
    int currentDate;
    String term;
    String[] admDipoYmd = splitYmd(String.valueOf(currentDate));

    public SpotListAdapter(List<BadHygieneRestaurantModel> baddata, int currentDate,String term) {
        this.baddata = baddata;
        this.currentDate = currentDate;
        this.term = term;
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

        switch(term){
            case "1m":
                if(admDipoYmd[1] == "01"){
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 8900)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }else{
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 100)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }
                break;
            case "3m":
                if(admDipoYmd[1] == "03"){
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 9100)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }else{
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 300)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }
                break;
            case "6m":
                if(admDipoYmd[1] == "06"){
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 9400)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }else{
                    if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 600)){
                        holder.upsoName.setText(baddata.get(position).getUpsoNm());
                        holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                        holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                    }
                }
                break;
            case "1y":
                if(Integer.parseInt(baddata.get(position).getAdmDispoYmd()) >= (currentDate -= 1000)){
                    holder.upsoName.setText(baddata.get(position).getUpsoNm());
                    holder.siteAddress.setText(baddata.get(position).getSiteAddrRd());
                    holder.admDispoYmd.setText(baddata.get(position).getAdmDispoYmd());
                }
                break;
        }
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

    public String[] splitYmd(String ymd){
        String year = ymd.substring(0,4);
        String month = ymd.substring(4,6);
        String day = ymd.substring(6,8);

        String[] admDispoYmd = {year, month, day};

        return admDispoYmd;
    }
}
