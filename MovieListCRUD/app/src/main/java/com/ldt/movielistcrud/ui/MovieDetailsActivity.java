package com.ldt.movielistcrud.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView tvMovieName, tvMovieDuration, tvMovieProductionYear, tvMovieCategories, tvMovieRate, tvDescription;
    private CheckBox cbMovieFavorite;
    private Movie movie;
    private ImageButton btnDelete, btnEdit, btnBack;
    private DatabaseReference myData = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        anhXa();
        setDataDetailsMovie();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MovieDetailsActivity.this);
                builder.setTitle("Are you sure");
                builder.setMessage("Deleted data can't be Undo.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myData.child("Movie").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                Movie m = snapshot.getValue(Movie.class);
                                if(movie.getName().equals(m.getName()) && movie.getDuration().equals(m.getDuration()) && movie.getProductionYear() == m.getProductionYear() && movie.getCategories().equals(m.getCategories()) && movie.getRate().equals(m.getRate())) {
                                    myData.child("Movie").child(snapshot.getKey()).removeValue();
                                    Toast.makeText(MovieDetailsActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                    finish();
                                    return;
                                }
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
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MovieDetailsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieDetailsActivity.this, UpdateMovieActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
                finish();
            }
        });
    }

    private void anhXa() {
        imgMovie = findViewById(R.id.imgMovie);
        tvMovieName = findViewById(R.id.tvMovieName);
        tvMovieDuration = findViewById(R.id.tvMovieDuration);
        tvMovieProductionYear = findViewById(R.id.tvMovieProductionYear);
        tvMovieCategories = findViewById(R.id.tvMovieCategories);
        tvMovieRate = findViewById(R.id.tvMovieRate);
        tvDescription = findViewById(R.id.tvDescription);
        cbMovieFavorite = findViewById(R.id.cbMovieFavorite);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
    }

    private void setDataDetailsMovie() {
        movie = (Movie) getIntent().getSerializableExtra("movie");
        Glide.with(this)
                .load(movie.getImgUrl())
                .placeholder(R.mipmap.ic_launcher) // ảnh mặt định
                .error(R.drawable.ic_baseline_error_72) // ảnh khi lỗi
                .into(imgMovie);
        tvMovieName.setText(movie.getName());
        tvMovieDuration.setText(movie.getDuration());
        tvMovieProductionYear.setText(movie.getProductionYear() + "");
        tvMovieCategories.setText(movie.getCategories());
        tvMovieRate.setText(movie.getRate().toString());
        cbMovieFavorite.setChecked(movie.isFavorite());
        tvDescription.setText(movie.getDescription());
    }
}