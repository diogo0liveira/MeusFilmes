package com.diogo.oliveira.mymovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.diogo.oliveira.artifact.designer.list.simple.Recycler;
import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.databinding.ViewRowMovieBinding;
import com.diogo.oliveira.mymovies.model.Movie;

import java.util.List;

/**
 * Created on 14/01/18 00:42.
 *
 * @author Diogo Oliveira.
 */
public class MyMoviesAdapter extends Recycler.Adapter<Movie, MyMoviesAdapter.ViewHolder>
{
    private final MovieViewOnClickListener listener;

    public MyMoviesAdapter(Context context, List<Movie> list, MovieViewOnClickListener listener)
    {
        super(context, list);
        setHasStableIds(true);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        ViewRowMovieBinding binding = this.inflate(parent, R.layout.view_row_movie);
        return new ViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Movie movie)
    {
        holder.binding.setMovie(movie);
    }
    
    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final MovieViewOnClickListener listener;
        private final ViewRowMovieBinding binding;

        ViewHolder(ViewRowMovieBinding binding, MovieViewOnClickListener listener)
        {
            super(binding.getRoot());
            this.binding = binding;

            this.listener = listener;
            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            listener.onMovieViewOnClick(this.binding.getMovie());
        }
    }

    public interface MovieViewOnClickListener
    {
        void onMovieViewOnClick(Movie movie);
    }
}
