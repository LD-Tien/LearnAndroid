package com.ldt.btgk.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldt.btgk.Movie;
import com.ldt.btgk.MovieRecyclerAdapter;
import com.ldt.btgk.MovieDetailsActivity;
import com.ldt.btgk.R;
import com.ldt.btgk.databinding.FragmentMoviesBinding;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment implements MovieRecyclerAdapter.OnMovieListener {
    private FragmentMoviesBinding binding;

    private RecyclerView rcvMovie;
    private MovieRecyclerAdapter movieRecyclerAdapter;
    List<Movie> movieList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MoviesViewModel moviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
////        final TextView textView = binding.tvMovies;
////        moviesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        anhXa();
        movieRecyclerAdapter = new MovieRecyclerAdapter(getActivity(), this);
        movieRecyclerAdapter.setData(getListMovie());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvMovie.setLayoutManager(linearLayoutManager);
        rcvMovie.setAdapter(movieRecyclerAdapter);
        rcvMovie.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    private List<Movie> getListMovie() {
        movieList = new ArrayList<>();
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.vivy, "Vivy", "12ep", "Action", (short) 2021, "abc",9.1, true));
        return movieList;
    }

    private void anhXa() {
        rcvMovie = binding.rcvMovie;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMovieClick(int position) {
        Log.d("Clicked", "onMovieClick: ");

        Movie movie = movieList.get(position);
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
//        intent.putExtra("imgResource", movieList.get(position).getImgResource());
//        intent.putExtra("name", movieList.get(position).getName());
//        intent.putExtra("duration", movieList.get(position).getDuration());
//        intent.putExtra("productionYear", movieList.get(position).getProductionYear());
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}