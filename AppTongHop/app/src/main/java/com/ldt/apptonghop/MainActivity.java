package com.ldt.apptonghop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ldt.apptonghop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
//    private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        DrawerLayout layout = binding.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.Movies, R.id.Profile).
//        NavController navController = Navigation.findNavController(this, R.id.bottomNavigationView);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        MoviesFragment moviesFragment = new MoviesFragment();
        ProfileFragment profileFragment = new ProfileFragment();

        replaceFragment(moviesFragment); // trang bắt đầu

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navMovies:
                    replaceFragment(moviesFragment);
                    break;
                case R.id.navProfile:
                    replaceFragment(profileFragment);
                    break;
            }
            return true;
        });

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame_layout, fragment);
//        fragmentTransaction.commit();
    }

    public void onBackPressed() {
        BottomNavigationView mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        if (mBottomNavigationView.getSelectedItemId() == R.id.navMovies)
        {
            super.onBackPressed();
            finish();
        }
        else
        {
            mBottomNavigationView.setSelectedItemId(R.id.navMovies);
        }
    }
}