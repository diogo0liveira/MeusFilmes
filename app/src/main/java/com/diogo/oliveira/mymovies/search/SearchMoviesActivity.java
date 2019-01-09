package com.diogo.oliveira.mymovies.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.diogo.oliveira.artifact.common.Strings;
import com.diogo.oliveira.artifact.common.network.NetworkManager;
import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.adapter.MyMoviesAdapter;
import com.diogo.oliveira.mymovies.databinding.ActivitySearchMoviesBinding;
import com.diogo.oliveira.mymovies.databinding.ViewEmptySearchMoviesBinding;
import com.diogo.oliveira.mymovies.detail.MovieDetailActivity;
import com.diogo.oliveira.mymovies.model.Movie;
import com.diogo.oliveira.mymovies.util.AppControlActivity;
import com.diogo.oliveira.mymovies.util.Extras;

import java.util.Collections;
import java.util.List;

/**
 * Created on 14/01/18 19:44.
 *
 * @author Diogo Oliveira.
 */
public class SearchMoviesActivity extends AppControlActivity implements SearchMoviesInteractor.View, MyMoviesAdapter.OnCollectionChangedListener, MyMoviesAdapter.MovieViewOnClickListener
{
    private SearchMoviesInteractor.Presenter presenter;
    private ViewEmptySearchMoviesBinding helperEmpty;
    private ActivitySearchMoviesBinding helper;
    private MyMoviesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        helper = DataBindingUtil.setContentView(this, R.layout.activity_search_movies);
        presenter = new SearchMoviesPresenter(this.getLoaderManager());
        presenter.initialize(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_search_movies, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)item.getActionView();

        searchView.setIconifiedByDefault(true);
        searchView.setIconified(false);

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                if(NetworkManager.isConnected() && NetworkManager.available())
                {
                    helperEmpty.setVisibility(View.VISIBLE);
                    helperEmpty.setProgressBar(true);
                    presenter.searchMovies(query);
                    searchView.clearFocus();
                }
                else
                {
                    showToast(R.string.app_internal_no_connection, Toast.LENGTH_LONG);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                if(Strings.isEmpty(newText))
                {
                    adapter.clean();
                }

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        switch(requestCode)
        {
            case Extras.RequestCode.DETAIL_MOVIE:
            {
                if(resultCode == RESULT_OK)
                {
                    setResult(RESULT_OK);
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
    public Context context()
    {
        return this;
    }

    @Override
    public void initializeView()
    {
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
        helper.searchList.addItemDecoration(dividerItemDecoration);
        helper.searchList.setHasFixedSize(true);

        adapter = new MyMoviesAdapter(this, Collections.emptyList(), this);
        adapter.setOnCollectionChangedListener(this);
        helper.searchList.setAdapter(adapter);
    }

    @Override
    public void onCollectionChanged()
    {
        helperEmpty.setVisibility((adapter.getItemCount() > 0) ? View.GONE : View.VISIBLE);
        helperEmpty.setProgressBar(false);
    }

    @Override
    public void onMovieViewOnClick(Movie movie)
    {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        startActivityForResult(intent.putExtra(Extras.MOVIE, movie), Extras.RequestCode.DETAIL_MOVIE);
    }

    @Override
    public void showToast(int text, int duration)
    {
        Toast.makeText(this, text, duration).show();
    }

    @Override
    public void loadingSearchResult(List<Movie> list)
    {
        adapter.setDataList(list);
    }
}
