package com.ldt.movielistcrud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>{
    private Context context;
    private List<Movie> movieList;
    private List<Movie> movieList2;
    private OnMovieListener onMovieListener;

    public MovieRecyclerAdapter(FragmentActivity context, OnMovieListener onMovieListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
    }

    public void setData(List<Movie> movieList) {
        this.movieList = movieList;
        this.movieList2 = movieList;
        notifyDataSetChanged(); // bin/ load dữ liệu vào MovieAdapter
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);
        Movie movie = movieList.get(position);
        if(movie == null) {
            return;
        }

        holder.cbMovieFavorite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                movieList.get(holder.getAdapterPosition()).setFavorite(b);
            }
        });

        Glide.with(holder.imgMovie.getContext())
                .load(movieList.get(position).getImgUrl())
                .placeholder(R.mipmap.ic_launcher) // ảnh mặt định
                .error(R.drawable.ic_baseline_error_72) // ảnh khi lỗi
                .into(holder.imgMovie);
        holder.tvMovieName.setText(movie.getName());
        holder.tvMovieDuration.setText(movie.getDuration());
        holder.tvMovieProductionYear.setText((String.valueOf(movie.getProductionYear())));
        holder.tvMovieCategories.setText(movie.getCategories());
        holder.tvMovieRate.setText(movie.getRate().toString());
        holder.cbMovieFavorite.setChecked(movie.isFavorite());
    }

    @Override
    public int getItemCount() {
        if(movieList != null)
            return movieList.size();
        return 0;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgMovie;
        private TextView tvMovieName, tvMovieDuration, tvMovieProductionYear, tvMovieCategories, tvMovieRate;
        private CheckBox cbMovieFavorite;
        OnMovieListener onMovieListener;
        public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
            super(itemView);
            this.onMovieListener = onMovieListener;
            imgMovie = itemView.findViewById(R.id.imgMovie);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            tvMovieDuration = itemView.findViewById(R.id.tvMovieDuration);
            tvMovieProductionYear = itemView.findViewById(R.id.tvMovieProductionYear);
            tvMovieCategories = itemView.findViewById(R.id.tvMovieCategories);
            tvMovieRate = itemView.findViewById(R.id.tvMovieRate);
            cbMovieFavorite = itemView.findViewById(R.id.cbMovieFavorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMovieListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface OnMovieListener {
        void onMovieClick(int position);
    }

    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0) {
                    filterResults.count = movieList2.size();
                    filterResults.values = movieList2;
                } else {
                    String searchChr = charSequence.toString().toLowerCase();
                    List<Movie> resultData = new ArrayList<>();
                    for(Movie userModel: movieList2) {
                        if(userModel.getName().toLowerCase().contains(searchChr)){
                            resultData.add(userModel);
                        }
                        filterResults.count = resultData.size();
                        filterResults.values = resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieList = (List<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
