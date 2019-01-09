package com.diogo.oliveira.mymovies.search;

import android.content.Context;
import android.support.annotation.StringRes;

import com.diogo.oliveira.artifact.common.annotation.Duration;
import com.diogo.oliveira.artifact.common.mvp.IPresenter;
import com.diogo.oliveira.artifact.common.mvp.IView;
import com.diogo.oliveira.mymovies.model.Movie;

import java.util.List;

/**
 * Created on 14/01/18 19:44.
 *
 * @author Diogo Oliveira.
 */
interface SearchMoviesInteractor
{
    interface View extends IView
    {
        Context context();

        void loadingSearchResult(List<Movie> list);

        void showToast(@StringRes int text, @Duration int duration);
    }

    interface Presenter extends IPresenter<View>
    {
        void searchMovies(String query);
    }
}
