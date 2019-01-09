package com.diogo.oliveira.mymovies.util.binding;

import android.databinding.BindingAdapter;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diogo.oliveira.artifact.common.DateTime;
import com.diogo.oliveira.artifact.common.Strings;
import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.util.Extras;

import java.sql.Date;

/**
 * Created on 15/01/18 01:28.
 *
 * @author Diogo Oliveira.
 */
public class BindingAdapters
{
    @BindingAdapter(value = {"text", "date"}, requireAll = false)
    public static void date(TextView view, String text, String date)
    {
        String message = DateTime.dateFormatMedium(Date.valueOf(date).getTime());

        if(!Strings.isEmpty(text))
        {
            message = String.format(text, message);
        }

        view.setText(message);

    }

    @BindingAdapter("cover")
    public static void cover(ImageView view, String uri)
    {
        String url = (Extras.URL.MOVIES.COVER + uri);
        Glide.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("favorite")
    public static void favorite(FloatingActionButton view, boolean favorite)
    {
        view.setImageResource((favorite ? R.drawable.vd_favorite_24dp : R.drawable.vd_not_favorite_24dp));
    }
}
