package com.diogo.oliveira.mymovies.search;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.diogo.oliveira.mymovies.adapter.loader.MyMoviesLoader;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.source.MovieDataSourceListener;
import com.diogo.oliveira.mymovies.model.source.local.MoviesLocalDataSource;
import com.diogo.oliveira.mymovies.model.source.local.data.MoviesDataSource;
import com.diogo.oliveira.mymovies.model.source.remote.MoviesRemoteDataSource;
import com.diogo.oliveira.mymovies.model.source.repository.MoviesRepository;
import com.diogo.oliveira.mymovies.util.Extras;
import com.diogo.oliveira.mymovies.util.network.ResultError;

import java.util.List;

/**
 * Created on 14/01/18 19:44.
 *
 * @author Diogo Oliveira.
 */
class SearchMoviesPresenter implements SearchMoviesInteractor.Presenter, LoaderManager.LoaderCallbacks<List<Movie>>
{
    private SearchMoviesInteractor.View view;
    private MoviesRepository repository;
    private final LoaderManager loaderManager;

    SearchMoviesPresenter(LoaderManager loaderManager)
    {
        this.loaderManager = loaderManager;
    }

    @Override
    public void initialize(SearchMoviesInteractor.View view)
    {
        this.view = view;
        this.view.initializeView();
        this.repository = new MoviesRepository(new MoviesLocalDataSource(new MoviesDataSource()), new MoviesRemoteDataSource());
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        /* Not Implemented */
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle, boolean savedState)
    {
        this.loaderManager.initLoader(Extras.Loader.LIST_SEARCH, Bundle.EMPTY, this);
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader)
    {
        /* Not Implemented */
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args)
    {
        return new MyMoviesLoader(view.context());
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> list)
    {
        view.loadingSearchResult(list);
    }

    @Override
    public void searchMovies(String query)
    {
        repository.search(query, new MovieDataSourceListener.ListMovieListener()
        {
            @Override
            public void onListSuccess(List<Movie> list)
            {
                view.loadingSearchResult(list);
            }

            @Override
            public void onListError(ResultError error)
            {
                view.showToast(error.getMessageResource(), Snackbar.LENGTH_LONG);
            }
        });
    }
}
