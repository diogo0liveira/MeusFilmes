package com.diogo.oliveira.mymovies.detail;

import android.support.annotation.StringRes;

import com.diogo.oliveira.artifact.common.annotation.Duration;
import com.diogo.oliveira.artifact.common.mvp.IPresenter;
import com.diogo.oliveira.artifact.common.mvp.IView;
import com.diogo.oliveira.mymovies.model.Movie;

/**
 * Created on 15/01/18 03:35.
 *
 * @author Diogo Oliveira.
 */
interface MovieDetailInteractor
{
    interface View extends IView
    {
        void putOnForm(Movie movie);

        void movieSaveSuccess();

        void movieDeleteSuccess();

        void showToast(@StringRes int text, @Duration int duration);
    }

    interface Presenter extends IPresenter<MovieDetailInteractor.View>
    {
        void movieAction();
    }
}
