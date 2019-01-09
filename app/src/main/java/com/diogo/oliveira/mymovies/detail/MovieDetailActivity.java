package com.diogo.oliveira.mymovies.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.databinding.ActivityMovieDetailBinding;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.util.AppControlActivity;

/**
 * Created on 15/01/18 21:30.
 *
 * @author Diogo Oliveira.
 */
public class MovieDetailActivity extends AppControlActivity implements MovieDetailInteractor.View, View.OnClickListener
{
    private MovieDetailInteractor.Presenter presenter;
    private ActivityMovieDetailBinding helper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        helper = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        presenter = new MovieDetailPresenter();
        presenter.initialize(this);

        if(savedInstanceState != null)
        {
            presenter.onRestoreInstanceState(savedInstanceState, true);
        }
        else
        {
            presenter.onRestoreInstanceState(this.getIntent().getExtras(), false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case android.R.id.home:
            {
                finish();
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void initializeView()
    {
        setSupportActionBar(helper.toolbar);
        ActionBar actionBar = this.getSupportActionBar();

        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        helper.buttonSave.setOnClickListener(this);
    }

    @Override
    public void putOnForm(Movie movie)
    {
        helper.setMovie(movie);
    }

    @Override
    public void movieSaveSuccess()
    {
        helper.buttonSave.setImageResource(R.drawable.vd_favorite_24dp);
        setResult(RESULT_OK);
    }

    @Override
    public void movieDeleteSuccess()
    {
        helper.buttonSave.setImageResource(R.drawable.vd_not_favorite_24dp);
        setResult(RESULT_OK);
    }

    @Override
    public void showToast(int text, int duration)
    {
        Toast.makeText(this, text, duration).show();
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_save:
            {
                presenter.movieAction();
                break;
            }
        }
    }
}
