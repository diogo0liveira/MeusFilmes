package com.diogo.oliveira.mymovies.model.source.local;

import com.diogo.oliveira.artifact.common.data.ResultDatabase;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.source.local.data.MoviesDataSource;

import java.util.HashSet;

/**
 * Created on 15/01/18 21:27.
 *
 * @author Diogo Oliveira.
 */
public class MoviesLocalDataSource
{
    private MoviesDataSource dataSource;

    public MoviesLocalDataSource(MoviesDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public ResultDatabase save(Movie movie)
    {
        return dataSource.insert(movie);
    }

    public ResultDatabase delete(Movie movie)
    {
        return dataSource.delete(movie);
    }

    public boolean isFavorite(Movie movie)
    {
        return dataSource.contains(movie);
    }

    public HashSet<Movie> search()
    {
        return dataSource.findAll();
    }
}
