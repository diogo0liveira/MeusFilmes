package com.diogo.oliveira.mymovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created in 21/05/18 11:02.
 *
 * @author Diogo Oliveira.
 */
public enum Order implements Parcelable
{
    TITLE
            {
                @Override
                public int value()
                {
                    return 0;
                }
            },

    DATE
            {
                @Override
                public int value()
                {
                    return 3;
                }
            };

    public abstract int value();

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(ordinal());
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>()
    {
        @Override
        public Order createFromParcel(Parcel in)
        {
            return Order.values()[in.readInt()];
        }

        @Override
        public Order[] newArray(int size)
        {
            return new Order[size];
        }
    };
}
