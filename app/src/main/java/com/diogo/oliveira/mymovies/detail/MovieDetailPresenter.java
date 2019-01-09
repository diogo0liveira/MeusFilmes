package com.diogo.oliveira.mymovies.detail;

import android.os.Bundle;
import android.widget.Toast;

import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.source.MovieDataSourceListener;
import com.diogo.oliveira.mymovies.model.source.local.MoviesLocalDataSource;
import com.diogo.oliveira.mymovies.model.source.local.data.MoviesDataSource;
import com.diogo.oliveira.mymovies.model.source.remote.MoviesRemoteDataSource;
import com.diogo.oliveira.mymovies.model.source.repository.MoviesRepository;
import com.diogo.oliveira.mymovies.util.Extras;
import com.diogo.oliveira.mymovies.util.network.ResultError;

/**
 * Created on 15/01/18 03:35.
 *
 * @author Diogo Oliveira.
 */
class MovieDetailPresenter implements MovieDetailInteractor.Presenter
{
    private MovieDetailInteractor.View view;
    private MoviesRepository repository;
    private Movie movie;

    @Override
    public void initialize(MovieDetailInteractor.View view)
    {
        this.view = view;
        this.view.initializeView();
        this.repository = new MoviesRepository(new MoviesLocalDataSource(new MoviesDataSource()), new MoviesRemoteDataSource());
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelable(Extras.MOVIE, movie);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle, boolean savedState)
    {
        movie = bundle.getParcelable(Extras.MOVIE);

        if(!savedState)
        {
            movie.setFavorite(repository.isFavorite(movie));
        }

        view.putOnForm(movie);
    }

    @Override
    public void movieAction()
    {
        if(repository.isFavorite(movie))
        {
            movieNotFavorite();
        }
        else
        {
            movieFavorite();
        }
    }

    private void movieFavorite()
    {
        repository.save(movie, new MovieDataSourceListener.MovieListener()
        {
            @Override
            public void onSuccess(Movie movie)
            {
                view.movieSaveSuccess();
            }

            @Override
            public void onError(ResultError error)
            {
                view.showToast(error.getMessageResource(), Toast.LENGTH_LONG);
            }
        });
    }

    private void movieNotFavorite()
    {
        repository.delete(movie, new MovieDataSourceListener.ActionMovieListener()
        {
            @Override
            public void onActionSuccess()
            {
                view.movieDeleteSuccess();
            }

            @Override
            public void onActionError(ResultError error)
            {
                view.showToast(error.getMessageResource(), Toast.LENGTH_LONG);
            }
        });
    }
}
