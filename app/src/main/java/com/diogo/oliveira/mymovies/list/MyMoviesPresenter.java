package com.diogo.oliveira.mymovies.list;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.diogo.oliveira.mymovies.adapter.loader.MyMoviesLoader;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.util.Extras;

import java.util.List;

/**
 * Created on 12/18/18 23:58.
 *
 * @author Diogo Oliveira.
 */
class MyMoviesPresenter implements MyMoviesInteractor.Presenter, LoaderManager.LoaderCallbacks<List<Movie>>
{
    private MyMoviesInteractor.View view;
    private final LoaderManager loaderManager;

    MyMoviesPresenter(LoaderManager loaderManager)
    {
        this.loaderManager = loaderManager;
    }

    @Override
    public void initialize(MyMoviesInteractor.View view)
    {
        this.view = view;
        this.view.initializeView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
     {
        /* Not Implemented */
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle, boolean savedState)
    {
        this.loaderManager.initLoader(Extras.Loader.LIST_MOVIES, Bundle.EMPTY, this);
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
        view.loadingMyMoviesList(list);
    }

    @Override
    public void loadMyMovieList()
    {
        loaderManager.restartLoader(Extras.Loader.LIST_MOVIES, Bundle.EMPTY, this);
    }
}
