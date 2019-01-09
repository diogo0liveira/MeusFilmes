package com.diogo.oliveira.mymovies.model.source.local.data;

/**
 * Created on 13/01/18 16:54.
 *
 * @author Diogo Oliveira.
 */
public interface IDBHelper
{
    interface IMovie
    {
        String TABLE_MOVIE = "MOVIE";
        String COLUMN_MOV_ID = "ID";
        String COLUMN_MOV_TITLE = "TITLE";
        String COLUMN_MOV_RELEASE_DATE = "RELEASE_DATE";
        String COLUMN_MOV_OVER_VIEW = "OVER_VIEW";
        String COLUMN_MOV_COVER = "COVER";
    }
}
