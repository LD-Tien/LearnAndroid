package com.ldt.movielistcrud.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.adapter.MovieRecyclerAdapter;
import com.ldt.movielistcrud.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieListFragment extends Fragment implements MovieRecyclerAdapter.OnMovieListener{
    private View view;
    private List<Movie> movieList;
    private RecyclerView rcvMovie;
    private MovieRecyclerAdapter movieRecyclerAdapter;
    private DatabaseReference myData = FirebaseDatabase.getInstance().getReference();
    private FloatingActionButton btnAdd;
    private EditText edtSearch;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieListFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieListFragment newInstance(String param1, String param2) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        getListMovie();
//        for (Movie m : movieList) {
//            myData.child("Movie").push().setValue(m);
//        }
//        movieList = movieDao.getMovies();

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        anhXa();

        movieList = new ArrayList<>();
        movieList = getMovies();
        movieRecyclerAdapter = new MovieRecyclerAdapter(getActivity(), this);
        movieRecyclerAdapter.setData(movieList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcvMovie.setLayoutManager(linearLayoutManager);
        rcvMovie.setAdapter(movieRecyclerAdapter);
        rcvMovie.setItemAnimator(new DefaultItemAnimator());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMovieActivity.class);
                startActivity(intent);
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                movieRecyclerAdapter.getFilter().filter(edtSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

//    private List<Movie> getListMovie() {
//        movieList = new ArrayList<>();
//        movieList.add(new Movie("https://tinhte.vn/store/2016/08/3847757_avatar2.jpg", "Avatar 2", "2h41m", "Action/Sci-fi", 2022, "abc",9.1, true));
//        movieList.add(new Movie("https://vicongly.com/wp-content/uploads/2021/07/Amazon-ket-thuc-Evangelion-bang-cach-dua-bo-phim-cuoi.jpeg", "Evangelion: 3.0 + 1.0", "2h45m", "Action/Sci-fi", 2022, "abc",9.0, true));
//        movieList.add(new Movie("https://attackofthefanboy.com/wp-content/uploads/2022/09/Cyberpunk-Edgerunners-Season-2.jpg", "Cyberpunk: Edgerunners", "10ep", "Action/Sci-fi", 2022, "abc",8.9, true));
//        movieList.add(new Movie("https://tuanlalarme.com/wp-content/uploads/2021/09/2001-space-odyssey2_1600.jpg", "2001: A Space Odyssey", "2h19m", "Sci-fi", 1968, "abc",8.7, false));
//        movieList.add(new Movie("https://image.vtc.vn/files/f2/2014/07/29/vi-sao-bo-gia-marlon-brando-che-giai-oscar-0.jpg", "The Godfather", "2h55m", "Thrillers", 1972, "abc",8.6, false));
//        return movieList;
//    }

    private List<Movie> getMovies () {
        myData.child("Movie").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Movie m =snapshot.getValue(Movie.class);
                movieList.add(m);
                movieRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return movieList;
    }


    private void anhXa() {
        rcvMovie = view.findViewById(R.id.rcvMovie);
        btnAdd = view.findViewById(R.id.btnAdd);
        edtSearch = view.findViewById(R.id.edtSearch);
    }

    @Override
    public void onMovieClick(int position) {
        Movie movie = movieList.get(position);
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra("movie", movie);
        intent.putExtra("position" ,position);
        startActivity(intent);
    }
}