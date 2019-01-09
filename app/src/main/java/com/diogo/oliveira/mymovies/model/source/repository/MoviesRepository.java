package com.diogo.oliveira.mymovies.model.source.repository;

import com.diogo.oliveira.artifact.common.data.ResultDatabase;
import com.diogo.oliveira.artifact.common.mvp.Repository;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.source.MovieDataSourceListener;
import com.diogo.oliveira.mymovies.model.source.local.MoviesLocalDataSource;
import com.diogo.oliveira.mymovies.model.source.remote.MoviesRemoteDataSource;
import com.diogo.oliveira.mymovies.util.network.ResultError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 14/01/18 00:45.
 *
 * @author Diogo Oliveira.
 */
public class MoviesRepository extends Repository<MoviesLocalDataSource, MoviesRemoteDataSource> implements MovieDataSourceListener
{
    public MoviesRepository(MoviesLocalDataSource local, MoviesRemoteDataSource remote)
    {
        super(local, remote);
    }

    public void save(Movie movie, MovieListener listener)
    {
        ResultDatabase result = getLocal().save(movie);

        if(result.isSuccessful())
        {
            listener.onSuccess(movie);
        }
        else
        {
            listener.onError(new ResultError());
        }
    }

    public void delete(Movie movie, ActionMovieListener listener)
    {
        ResultDatabase result = getLocal().delete(movie);

        if(result.isSuccessful())
        {
            listener.onActionSuccess();
        }
        else
        {
            listener.onActionError(new ResultError());
        }
    }

    @Override
    public void search(String query, ListMovieListener listener)
    {
        getRemote().search(query, new ListMovieListener()
        {
            @Override
            public void onListSuccess(List<Movie> list)
            {
                listener.onListSuccess(list);
            }

            @Override
            public void onListError(ResultError error)
            {
                listener.onListError(error);
            }
        });
    }

    public List<Movie> getMovieList()
    {
        return new ArrayList<>(getLocal().search());
    }

    public boolean isFavorite(Movie movie)
    {
        return getLocal().isFavorite(movie);
    }
}
