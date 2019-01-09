package com.diogo.oliveira.mymovies.model.source.local.data;

import android.database.sqlite.SQLiteDatabase;

import com.diogo.oliveira.artifact.common.data.helper.DBConnectionHelper;
import com.diogo.oliveira.mymovies.BuildConfig;

import static com.diogo.oliveira.mymovies.model.source.local.data.IDBHelper.IMovie;

/**
 * Created on 13/01/18 14:33.
 *
 * @author Diogo Oliveira.
 */
public abstract class DBHelper<T> extends DBConnectionHelper<T>
{
    private static final String DB_NAME = "RelatorioDespesasApp.db";
    private static final int DB_VERSION = 1;

    DBHelper(String table)
    {
        super(DB_NAME, DB_VERSION, table);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        /* TABLE MOVIE */
        sqLiteDatabase.execSQL(createTableMovie());
    }

    @Override
    public void onConfigure(SQLiteDatabase database)
    {
        /* Necessário para CASCADE */
        database.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        /* DROP TABELAS */
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IMovie.TABLE_MOVIE);

        /* EXECUTE ON CREATE */
        onCreate(sqLiteDatabase);
    }

    @Override
    protected boolean enableLogging()
    {
        /* Habilita o log de consultas */
        return BuildConfig.DEBUG;
    }

    private String createTableMovie()
    {
        return "CREATE TABLE " + IMovie.TABLE_MOVIE + " ("
                + IMovie.COLUMN_MOV_ID + " INTEGER NOT NULL, "
                + IMovie.COLUMN_MOV_TITLE + " TEXT NOT NULL, "
                + IMovie.COLUMN_MOV_RELEASE_DATE + " TEXT NOT NULL, "
                + IMovie.COLUMN_MOV_OVER_VIEW + " TEXT NOT NULL, "
                + IMovie.COLUMN_MOV_COVER + " TEXT, "
                + "PRIMARY KEY (" + IMovie.COLUMN_MOV_ID + ", " + IMovie.COLUMN_MOV_TITLE + "));";
    }
}
