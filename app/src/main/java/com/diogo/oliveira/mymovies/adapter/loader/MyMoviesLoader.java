package com.diogo.oliveira.mymovies.adapter.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.source.local.MoviesLocalDataSource;
import com.diogo.oliveira.mymovies.model.source.local.data.MoviesDataSource;
import com.diogo.oliveira.mymovies.model.source.remote.MoviesRemoteDataSource;
import com.diogo.oliveira.mymovies.model.source.repository.MoviesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14/01/18 00:43.
 *
 * @author Diogo Oliveira.
 */
public class MyMoviesLoader extends AsyncTaskLoader<List<Movie>>
{
    private final MoviesRepository repository;
    private List<Movie> list;

    public MyMoviesLoader(Context context)
    {
        super(context);
        this.list = new ArrayList<>(0);
        this.repository = new MoviesRepository(new MoviesLocalDataSource(new MoviesDataSource()), new MoviesRemoteDataSource());
    }

    @Override
    public List<Movie> loadInBackground()
    {
        return repository.getMovieList();
    }

    @Override
    public void deliverResult(List<Movie> data)
    {
        if(this.isReset())
        {
            return;
        }

        if(this.isStarted())
        {
            this.list = data;
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading()
    {
        if(list.isEmpty() || this.takeContentChanged())
        {
            this.forceLoad();
        }
        else
        {
            this.deliverResult(list);
        }
    }

    @Override
    protected void onStopLoading()
    {
        this.cancelLoad();
    }

    @Override
    protected void onReset()
    {
        this.onStopLoading();
    }
}
