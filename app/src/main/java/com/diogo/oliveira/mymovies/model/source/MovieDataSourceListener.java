package com.diogo.oliveira.mymovies.model.source;

import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.util.network.ResultError;

import java.util.List;

/**
 * Created on 14/01/18 18:22.
 *
 * @author Diogo Oliveira.
 */

public interface MovieDataSourceListener
{
    void search(String query, ListMovieListener listener);

    interface MovieListener
    {
        void onSuccess(Movie movie);

        void onError(ResultError error);
    }

    interface ListMovieListener
    {
        void onListSuccess(List<Movie> list);

        void onListError(ResultError error);
    }

    interface ActionMovieListener
    {
        void onActionSuccess();

        void onActionError(ResultError error);
    }
}
