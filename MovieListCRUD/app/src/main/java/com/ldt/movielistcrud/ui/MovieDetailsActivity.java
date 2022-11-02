package com.ldt.movielistcrud.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView imgMovie;
    private TextView tvMovieName, tvMovieDuration, tvMovieProductionYear, tvMovieCategories, tvMovieRate, tvDescription;
    private CheckBox cbMovieFavorite;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        anhXa();
        setDataDetailsMovie();
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