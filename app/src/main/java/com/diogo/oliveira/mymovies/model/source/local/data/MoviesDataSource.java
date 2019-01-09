package com.diogo.oliveira.mymovies.model.source.local.data;

import android.content.ContentValues;

import com.diogo.oliveira.artifact.common.Strings;
import com.diogo.oliveira.artifact.common.data.BindValue;
import com.diogo.oliveira.artifact.common.data.query.Clause;
import com.diogo.oliveira.artifact.common.data.query.QueryCursor;
import com.diogo.oliveira.mymovies.model.Movie;

/**
 * Created in 21/05/18 11:18.
 *
 * @author Diogo Oliveira.
 */
public class MoviesDataSource extends DBHelper<Movie> implements IDBHelper.IMovie
{
    public MoviesDataSource()
    {
        super(TABLE_MOVIE);
    }

    @Override
    protected ContentValues contentValues(Movie movie, boolean insert)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MOV_ID, movie.getId());
        contentValues.put(COLUMN_MOV_TITLE, movie.getTitle().trim());
        contentValues.put(COLUMN_MOV_RELEASE_DATE, movie.getReleaseDate());
        contentValues.put(COLUMN_MOV_OVER_VIEW, movie.getOverView());
        contentValues.put(COLUMN_MOV_COVER, (Strings.isEmpty(movie.getCover()) ? null : movie.getCover().trim()));
        return contentValues;
    }

    @Override
    protected BindValue bindValue(BindValue bindValue, Movie movie)
    {
        bindValue.set(movie.getId());
        bindValue.set(movie.getTitle());
        bindValue.set(movie.getReleaseDate());
        bindValue.set(movie.getOverView());
        bindValue.set(movie.getCover());
        return bindValue;
    }

    @Override
    protected Movie model(QueryCursor cursor)
    {
        Movie movie = new Movie();
        movie.setId(cursor.getInt(COLUMN_MOV_ID));
        movie.setTitle(cursor.getString(COLUMN_MOV_TITLE));
        movie.setReleaseDate(cursor.getString(COLUMN_MOV_RELEASE_DATE));
        movie.setOverView(cursor.getString(COLUMN_MOV_OVER_VIEW));
        movie.setCover(cursor.getString(COLUMN_MOV_COVER));
        return movie;
    }

    @Override
    protected Clause constraints(Movie movie)
    {
        //@formatter:off
        return new Clause(movie.getId(), movie.getTitle().trim())
                .equal(COLUMN_MOV_ID)
                .equalsTrim(COLUMN_MOV_TITLE);
        //@formatter:on
    }
}
