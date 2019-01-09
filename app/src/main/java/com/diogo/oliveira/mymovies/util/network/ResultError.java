package com.diogo.oliveira.mymovies.util.network;

import android.support.annotation.StringRes;

import com.diogo.oliveira.artifact.common.Resources;
import com.diogo.oliveira.mymovies.R;
import com.diogo.oliveira.mymovies.util.json.GsonHelper;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created on 13/02/17 09:04.
 *
 * @author Diogo Oliveira.
 */
public class ResultError
{
    private int code;
    private final Status status;
    private final String message;
    private final int messageRes;

    public ResultError()
    {
        this.code = 0;
        this.status = Status.INTERNAL_ERROR_CLIENT;
        this.messageRes = R.string.app_internal_error_client;
        this.message = Resources.string(R.string.app_internal_error_client);
    }

    public ResultError(Status status, @StringRes int message)
    {
        this.status = status;
        this.messageRes = message;
        this.message = Resources.string(message);
    }

    public ResultError(Status status, @StringRes int messageRes, String message)
    {
        this.code = status.value;
        this.messageRes = messageRes;
        this.message = message;
        this.status = status;
    }

    public int getCode()
    {
        return code;
    }

    public Status getStatus()
    {
        return status;
    }

    public String getMessage()
    {
        return message;
    }

    public int getMessageResource()
    {
        return messageRes;
    }

    public static ResultError parse(Response response)
    {
        ResultError error = new ResultError(Status.INTERNAL_ERROR_SERVER, R.string.app_internal_server_unavailable);

        if(response != null)
        {
            try
            {
                ResponseBody body = response.errorBody();
                error = GsonHelper.build().fromJson(Objects.requireNonNull(body).string(), ResultError.class);
            }
            catch(JsonSyntaxException | IOException e)
            {
                try
                {
                    error = new ResultError(Status.INTERNAL_ERROR_SERVER, R.string.app_internal_error_client,
                            Objects.requireNonNull(response.errorBody()).string());
                }
                catch(IOException ex)
                {
                    ex.printStackTrace();
                }
            }
        }

        return error;
    }
}
