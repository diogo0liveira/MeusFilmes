package com.diogo.oliveira.mymovies.util.network.retrofit;

import com.diogo.oliveira.artifact.common.network.ContentType;
import com.diogo.oliveira.mymovies.pojo.SearchResult;
import com.diogo.oliveira.mymovies.util.Extras;
import com.diogo.oliveira.mymovies.util.json.JsonKey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created on 15/04/2016 17:07.
 *
 * @author Diogo Oliveira.
 */
public class Services
{
    public interface Movies
    {
        @GET(Extras.URL.MOVIES.SEARCH)
        @Headers(ContentType.APPLICATION_JSON)
        Call<SearchResult> list(@Query(JsonKey.QUERY) String query);
    }
}
