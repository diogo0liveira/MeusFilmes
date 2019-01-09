package com.diogo.oliveira.mymovies.util;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diogo.oliveira.artifact.common.ApplicationControlActivity;
import com.diogo.oliveira.mymovies.detail.MovieDetailActivity;
import com.diogo.oliveira.mymovies.search.SearchMoviesActivity;

/**
 * Created on 15/01/18 04:14.
 *
 * @author Diogo Oliveira.
 */

@SuppressLint("Registered")
public class AppControlActivity extends ApplicationControlActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setKeep(SearchMoviesActivity.class, MovieDetailActivity.class);
    }
}
