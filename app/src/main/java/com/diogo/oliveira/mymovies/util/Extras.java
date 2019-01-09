package com.diogo.oliveira.mymovies.util;

/**
 * Created on 14/01/18 02:29.
 *
 * @author Diogo Oliveira.
 */

public interface Extras
{
    String TAG = "MY-MOVIES";
    String MOVIE = "MOVIE";
    String ORDER = "ORDER";

    interface URL
    {
        String API = "https://api.themoviedb.org/";
        String IMAGE = "https://image.tmdb.org/";
        String KEY = "5d164463b005624aae85399374623ac0";

        abstract class MOVIES
        {
            public final static String SEARCH = (API + "3/search/movie");
            public final static String COVER = (IMAGE + "t/p/w500/");
        }
    }

    interface Loader
    {
        int LIST_MOVIES = 1000;
        int LIST_SEARCH = 1001;
    }

    interface RequestCode
    {
        int SEARCH_MOVIES = 2000;
        int DETAIL_MOVIE = 2001;
    }
}
