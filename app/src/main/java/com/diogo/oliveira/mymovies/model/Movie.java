package com.diogo.oliveira.mymovies.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.Comparator;

/**
 * Created on 15/01/18 22:27.
 *
 * @author Diogo Oliveira.
 */
public class Movie implements Parcelable, Comparator<Movie>, Comparable<Movie>
{
    @Expose
    @SerializedName("id")
    public int id;
    @Expose
    @SerializedName("title")
    public String title;
    @Expose
    @SerializedName("release_date")
    public String releaseDate;
    @Expose
    @SerializedName("overview")
    public String overView;
    @Expose
    @SerializedName("poster_path")
    public String cover;

    @Expose(serialize = false, deserialize = false)
    private boolean isFavorite;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public String getOverView()
    {
        return overView;
    }

    public void setOverView(String overView)
    {
        this.overView = overView;
    }

    public String getCover()
    {
        return cover;
    }

    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public boolean isFavorite()
    {
        return isFavorite;
    }

    public void setFavorite(boolean favorite)
    {
        isFavorite = favorite;
    }

    @Override
    public String toString()
    {
        return title;
    }

    @Override
    public int compareTo(@NonNull Movie despesa)
    {
        return compare(this, despesa);
    }

    @Override
    public int compare(Movie movie1, Movie movie2)
    {
        return movie1.title.compareTo(movie2.title);
    }

    public final Comparator<Movie> SortTitle = (movie1, movie2) -> movie1.title.compareTo(movie2.title);

    public final Comparator<Movie> SortDate = (Movie movie1, Movie movie2) ->
    {
        Date date1 = java.sql.Date.valueOf(movie1.releaseDate);
        Date date2 = java.sql.Date.valueOf(movie2.releaseDate);
        return date1.compareTo(date2);
    };

    public Movie()
    {
        /* Not Implemented */
    }

    protected Movie(Parcel in)
    {
        this.id = in.readInt();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.overView = in.readString();
        this.cover = in.readString();
        this.isFavorite = (in.readByte() != 0);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overView);
        dest.writeString(this.cover);
        dest.writeByte((byte)(this.isFavorite ? 1 : 0));
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>()
    {
        @Override
        public Movie createFromParcel(Parcel source)
        {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size)
        {
            return new Movie[size];
        }
    };
}
