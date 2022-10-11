package com.ldt.apptonghop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ldt.apptonghop.databinding.FragmentMoviesBinding;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment implements MovieRecyclerAdapter.OnMovieListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private FragmentMoviesBinding binding;
    private RecyclerView rcvMovie;
    private MovieRecyclerAdapter movieRecyclerAdapter;
    List<Movie> movieList;


    public MoviesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
//    public static MoviesFragment newInstance(String param1, String param2) {
//        MoviesFragment fragment = new MoviesFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentMoviesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
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
        movieList.add(new Movie(R.drawable.avatar2, "Avatar 2", "2h41m", "Action/Sci-fi", (short) 2022, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.filnal_evangilion, "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", (short) 2022, "abc",9.0, true));
        movieList.add(new Movie(R.drawable.cyperpuck_edgerunners, "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", (short) 2022, "abc",8.9, true));
        movieList.add(new Movie(R.drawable.a_space_odyssey, "2001: A Space Odyssey", "2h19m", "Sci-fi", (short) 1968, "abc",8.7, false));
        movieList.add(new Movie(R.drawable.the_godfather, "The Godfather", "2h55m", "Thrillers", (short) 1972, "abc",8.6, false));
        movieList.add(new Movie(R.drawable.avatar2, "Avatar 2", "2h41m", "Action/Sci-fi", (short) 2022, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.filnal_evangilion, "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", (short) 2022, "abc",9.0, true));
        movieList.add(new Movie(R.drawable.cyperpuck_edgerunners, "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", (short) 2022, "abc",8.9, true));
        movieList.add(new Movie(R.drawable.a_space_odyssey, "2001: A Space Odyssey", "2h19m", "Sci-fi", (short) 1968, "abc",8.7, false));
        movieList.add(new Movie(R.drawable.the_godfather, "The Godfather", "2h55m", "Thrillers", (short) 1972, "abc",8.6, false));
        movieList.add(new Movie(R.drawable.avatar2, "Avatar 2", "2h41m", "Action/Sci-fi", (short) 2022, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.filnal_evangilion, "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", (short) 2022, "abc",9.0, true));
        movieList.add(new Movie(R.drawable.cyperpuck_edgerunners, "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", (short) 2022, "abc",8.9, true));
        movieList.add(new Movie(R.drawable.a_space_odyssey, "2001: A Space Odyssey", "2h19m", "Sci-fi", (short) 1968, "abc",8.7, false));
        movieList.add(new Movie(R.drawable.the_godfather, "The Godfather", "2h55m", "Thrillers", (short) 1972, "abc",8.6, false));
        movieList.add(new Movie(R.drawable.avatar2, "Avatar 2", "2h41m", "Action/Sci-fi", (short) 2022, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.filnal_evangilion, "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", (short) 2022, "abc",9.0, true));
        movieList.add(new Movie(R.drawable.cyperpuck_edgerunners, "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", (short) 2022, "abc",8.9, true));
        movieList.add(new Movie(R.drawable.a_space_odyssey, "2001: A Space Odyssey", "2h19m", "Sci-fi", (short) 1968, "abc",8.7, false));
        movieList.add(new Movie(R.drawable.the_godfather, "The Godfather", "2h55m", "Thrillers", (short) 1972, "abc",8.6, false));
        movieList.add(new Movie(R.drawable.avatar2, "Avatar 2", "2h41m", "Action/Sci-fi", (short) 2022, "abc",9.1, true));
        movieList.add(new Movie(R.drawable.filnal_evangilion, "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", (short) 2022, "abc",9.0, true));
        movieList.add(new Movie(R.drawable.cyperpuck_edgerunners, "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", (short) 2022, "abc",8.9, true));
        movieList.add(new Movie(R.drawable.a_space_odyssey, "2001: A Space Odyssey", "2h19m", "Sci-fi", (short) 1968, "abc",8.7, false));
        movieList.add(new Movie(R.drawable.the_godfather, "The Godfather", "2h55m", "Thrillers", (short) 1972, "abc",8.6, false));
        return movieList;
    }

    private void anhXa() {
        rcvMovie = binding.rcvMovie;
    }

    @Override
    public void onMovieClick(int position) {
        replaceFragment(new MovieDetailsFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}