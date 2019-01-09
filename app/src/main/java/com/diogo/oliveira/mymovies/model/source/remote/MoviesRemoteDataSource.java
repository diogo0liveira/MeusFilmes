package com.diogo.oliveira.mymovies.model.source.remote;

import android.support.annotation.NonNull;

import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.model.source.MovieDataSourceListener;
import com.diogo.oliveira.mymovies.pojo.SearchResult;
import com.diogo.oliveira.mymovies.util.network.ResultError;
import com.diogo.oliveira.mymovies.util.network.Status;
import com.diogo.oliveira.mymovies.util.network.retrofit.ServiceGenerator;
import com.diogo.oliveira.mymovies.util.network.retrofit.Services;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 14/01/18 00:48.
 *
 * @author Diogo Oliveira.
 */
public class MoviesRemoteDataSource implements MovieDataSourceListener
{
    @Override
    public void search(String query, ListMovieListener listener)
    {
        Services.Movies service = ServiceGenerator.get(Services.Movies.class);
        Call<SearchResult> call = service.list(query);

        call.enqueue(new Callback<SearchResult>()
        {
            @Override
            public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response)
            {
                if(response.isSuccessful())
                {
                    //noinspection ConstantConditions
                    listener.onListSuccess(response.body().getResults());
                }
                else
                {
                    listener.onListError(ResultError.parse(response));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable throwable)
            {
                throwable.printStackTrace();
                listener.onListError(new ResultError(Status.SERVICE_UNAVAILABLE, R.string.app_internal_server_unavailable));
            }
        });
    }
}
