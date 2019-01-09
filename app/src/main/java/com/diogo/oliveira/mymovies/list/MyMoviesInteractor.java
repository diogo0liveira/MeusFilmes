package com.diogo.oliveira.mymovies.list;

import android.content.Context;

import com.diogo.oliveira.artifact.common.mvp.IPresenter;
import com.diogo.oliveira.artifact.common.mvp.IView;
import com.diogo.oliveira.mymovies.model.Movie;

import java.util.List;

/**
 * Created on 12/18/18 23:57.
 *
 * @author Diogo Oliveira.
 */
interface MyMoviesInteractor
{
    interface View extends IView
    {
        Context context();

        void loadingMyMoviesList(List<Movie> list);

        void startSearchMoviesActivity();
    }

    interface Presenter extends IPresenter<View>
    {
        void loadMyMovieList();
    }
}
