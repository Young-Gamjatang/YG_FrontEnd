package com.example.test;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FoodAdapter extends FragmentStateAdapter {
    public FoodAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SecondFragment();
            case 1:
                return new ThirdFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;       // 페이지 수
    }

    public void setAdapter(FoodAdapter foodAdapter) {

    }
}
