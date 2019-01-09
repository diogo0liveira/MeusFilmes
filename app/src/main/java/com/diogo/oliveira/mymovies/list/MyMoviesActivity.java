package com.diogo.oliveira.mymovies.list;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.adapter.MyMoviesAdapter;
import com.diogo.oliveira.mymovies.databinding.ActivityMyMoviesBinding;
import com.diogo.oliveira.mymovies.databinding.ViewEmptyMyMoviesBinding;
import com.diogo.oliveira.mymovies.detail.MovieDetailActivity;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.model.Order;
import com.diogo.oliveira.mymovies.search.SearchMoviesActivity;
import com.diogo.oliveira.mymovies.util.Extras;

import java.util.Collections;
import java.util.List;

/**
 * Created on 12/18/18 08:57.
 *
 * @author Diogo Oliveira.
 */
public class MyMoviesActivity extends AppCompatActivity implements MyMoviesInteractor.View, View.OnClickListener, MyMoviesAdapter.OnCollectionChangedListener, MyMoviesAdapter.MovieViewOnClickListener
{
    private static String KEY_ORDER = "ORDER";

    private MyMoviesInteractor.Presenter presenter;
    private ViewEmptyMyMoviesBinding helperEmpty;
    private ActivityMyMoviesBinding helper;
    private Order order = Order.TITLE;
    private MyMoviesAdapter adapter;

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putParcelable(KEY_ORDER, order);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        helper = DataBindingUtil.setContentView(this, R.layout.activity_my_movies);
        presenter = new MyMoviesPresenter(this.getLoaderManager());
        presenter.initialize(this);

        if(savedInstanceState != null)
        {
            order = savedInstanceState.getParcelable(KEY_ORDER);
            presenter.onRestoreInstanceState(savedInstanceState, true);
        }
        else
        {
            presenter.onRestoreInstanceState(this.getIntent().getExtras(), false);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem feedback = menu.findItem(R.id.menu_send_feedback);
        MenuItem order = menu.findItem(R.id.menu_order);

        if(feedback != null)
        {
            feedback.setVisible(false);
        }

        if(order != null)
        {
            switch(this.order)
            {
                case TITLE:
                {
                    order.getSubMenu().findItem(R.id.menu_order_title).setChecked(true);
                    break;
                }
                case DATE:
                {
                    order.getSubMenu().findItem(R.id.menu_order_date).setChecked(true);
                    break;
                }
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_my_movies, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_order_title:
            {
                item.setChecked(true);
                sortList(Order.TITLE);
                return true;
            }
            case R.id.menu_order_date:
            {
                item.setChecked(true);
                sortList(Order.DATE);
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        switch(requestCode)
        {
            case Extras.RequestCode.DETAIL_MOVIE:
            case Extras.RequestCode.SEARCH_MOVIES:
            {
                if(resultCode == RESULT_OK)
                {
                    presenter.loadMyMovieList();
                }
                break;
            }
            default:
            {
                super.onActivityResult(requestCode, resultCode, intent);
                break;
            }
        }
    }

    @Override
    public void initializeView()
    {
        helper.buttonAdd.setOnClickListener(this);

        if(helper.messageEmpty.isInflated())
        {
            helperEmpty.setVisibility(View.VISIBLE);
        }
        else
        {
            //noinspection ConstantConditions
            helper.messageEmpty.getViewStub().setVisibility(View.VISIBLE);
            helperEmpty = DataBindingUtil.findBinding(helper.messageEmpty.getRoot());
        }

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        helper.moviesList.addItemDecoration(dividerItemDecoration);
        helper.moviesList.setHasFixedSize(true);

        adapter = new MyMoviesAdapter(this, Collections.emptyList(), this);
        adapter.setOnCollectionChangedListener(this);
        helper.moviesList.setAdapter(adapter);
    }

    @Override
    public Context context()
    {
        return this.getApplicationContext();
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_add:
            {
                startSearchMoviesActivity();
                break;
            }
        }
    }

    @Override
    public void loadingMyMoviesList(List<Movie> list)
    {
        adapter.setDataList(list);
        sortList(order);
    }

    @Override
    public void onMovieViewOnClick(Movie movie)
    {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        startActivityForResult(intent.putExtra(Extras.MOVIE, movie), Extras.RequestCode.DETAIL_MOVIE);
    }

    @Override
    public void onCollectionChanged()
    {
        helperEmpty.setVisibility((adapter.getItemCount() > 0) ? View.GONE : View.VISIBLE);
    }

    @Override
    public void startSearchMoviesActivity()
    {
        Intent intent = new Intent(this, SearchMoviesActivity.class);
        startActivityForResult(intent, Extras.RequestCode.SEARCH_MOVIES);
    }

    private void sortList(Order order)
    {
        this.order = order;
        saveOrder(order);

        switch(order)
        {
            case TITLE:
            {
                adapter.sort(new Movie().SortTitle);
                break;
            }
            case DATE:
            {
                adapter.sort(Collections.reverseOrder(new Movie().SortDate));
                break;
            }
        }
    }

    public void saveOrder(Order order)
    {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        preferences.edit().putInt(Extras.ORDER, order.value()).apply();
    }
}
