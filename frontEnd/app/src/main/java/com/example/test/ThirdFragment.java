package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.InnerDataBase.DBCheckbox;

public class ThirdFragment extends Fragment {
    private CheckBox[] checkboxes;
    private DBCheckbox dbCheckbox;
    private String[] term = {
            "1m", "3m", "6m", "1y"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container, false);

        dbCheckbox = DBCheckbox.getInstance(requireContext());

        checkboxes = new CheckBox[4];
        for (int i = 0; i < 4; i++) {
            String tmp = "thr_checkbox_" + (i + 1);
            Log.d("aaa", tmp);
            int resID = getResources().getIdentifier(tmp, "id", getActivity().getPackageName());
            checkboxes[i] = (view.findViewById(resID));
            checkboxes[i].setTag(term[i]);
            checkboxes[i].setOnCheckedChangeListener(checkboxChangeListener);
        }

        ImageView select = view.findViewById(R.id.thr_seleted);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = dbCheckbox.getResult();
                if (!result.isEmpty()) {
                    // 데이터베이스에 데이터가 있을 때 처리
                    String[] res = new String[2];

                    Intent i = new Intent(requireContext(),SpotList.class);

                    res = dbCheckbox.getResult().split("/");
                    i.putExtra("area",res[0]);
                    i.putExtra("term",res[1]);
                    dbCheckbox.delete();
                    startActivity(i);

                    // 여기에 처리 내용을 작성하세요.
                } else {
                    // 데이터베이스에 데이터가 없을 때 처리
                    Toast.makeText(requireContext(), "체크박스를 모두 체크해주세요!", Toast.LENGTH_SHORT).show();

                    // 여기에 처리 내용을 작성하세요.
                }

            }
        });

        return view;
    }

    private final CompoundButton.OnCheckedChangeListener checkboxChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // 체크박스의 상태가 변경되었을 때 동작을 수행하는 부분
            if (isChecked) {
                // 체크박스가 선택되었을 때의 동작
                String selectedterm = buttonView.getTag().toString();
                Log.d("vvvvv", selectedterm);

                // 다른 체크박스들 비활성화
                disableOtherCheckboxes(buttonView);

                // 데이터베이스에 구 이름 저장
                dbCheckbox.insertterm(selectedterm);
                Log.d("log",dbCheckbox.getResult());
            } else {
                // 체크박스가 선택 해제되었을 때의 동작
                String selectedterm = buttonView.getTag().toString();
                Log.d("vvvvv", selectedterm);

                //다른 체크박스 활성화
                enableOtherCheckboxes();


                // 데이터베이스에서 구 이름 삭제
                dbCheckbox.delete();
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}