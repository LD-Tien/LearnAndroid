package com.ldt.btgk.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ldt.btgk.LoginActivity;
import com.ldt.btgk.User;
import com.ldt.btgk.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

private FragmentProfileBinding binding;

    private Button btnLogout;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
//        ProfileViewModel galleryViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
    //        final TextView textView = binding.textGallery;
    //        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        anhXa();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    private void anhXa() {
        btnLogout = binding.btnLogout;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}