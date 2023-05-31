package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Region extends AppCompatActivity {

    public static String selecteddistrict;

    private CheckBox[] checkboxes;
    private String[] seoulDistricts = {
            "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구",
            "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구",
            "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        checkboxes = new CheckBox[25];
        selecteddistrict = null;

        for (int i = 0; i < 25; i++) {
            String tmp = "region_checkbox_" + (i + 1);
            Log.d("aaa", tmp);
            int resID = getResources().getIdentifier(tmp, "id", getPackageName());
            checkboxes[i] = findViewById(resID);
            checkboxes[i].setTag(seoulDistricts[i]);
            checkboxes[i].setOnCheckedChangeListener(checkboxChangeListener);
        }


        ImageView selbtn = findViewById(R.id.selbtn);
        selbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selecteddistrict==null) Toast.makeText(Region.this,"체크박스를 체크해주세요!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Region.this,SpotList.class);
                i.putExtra("area",selecteddistrict);
                startActivity(i);
            }
        });
    }
    private final CompoundButton.OnCheckedChangeListener checkboxChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // 체크박스의 상태가 변경되었을 때 동작을 수행하는 부분
            if (isChecked) {
                // 체크박스가 선택되었을 때의 동작
                String selectedDistrict = buttonView.getTag().toString();
                Log.d("vvvvv", selectedDistrict);

                // 다른 체크박스들 비활성화
                disableOtherCheckboxes(buttonView);
                selecteddistrict = selectedDistrict;

            } else {
                // 체크박스가 선택 해제되었을 때의 동작
                String selectedDistrict = buttonView.getTag().toString();
                Log.d("vvvvv", selectedDistrict);

                //다른 체크박스 활성화
                enableOtherCheckboxes();
                selecteddistrict = null;
            }
        }
    };

    private void disableOtherCheckboxes(CompoundButton checkedCheckbox) {
        for (CheckBox checkbox : checkboxes) {
            if (checkbox != checkedCheckbox) {
                checkbox.setEnabled(false);
                checkbox.setChecked(false);
            }
        }
    }
    private void enableOtherCheckboxes() {
        for (CheckBox checkbox : checkboxes) {
            checkbox.setEnabled(true);
        }
    }

}
