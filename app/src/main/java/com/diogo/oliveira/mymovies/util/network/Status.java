package com.diogo.oliveira.mymovies.util.network;

/**
 * Created on 17/02/2016 09:18.
 *
 * @author Diogo Oliveira.
 */
public enum Status
{
    NOT_SPECIFIED(-1),
    SUCCESS(200),
    UNAUTHORIZED(401),
    INVALID_REFRESH_TOKEN(403),
    NOT_FOUND(404),
    INTERNAL_ERROR_CLIENT(400),
    INTERNAL_ERROR_SERVER(500),
    SERVICE_UNAVAILABLE(503);

    public final int value;

    Status(int value)
    {
        this.value = value;
    }

    public static Status get(int value)
    {
        for(Status status : Status.values())
        {
            if(status.value == value)
            {
                return status;
            }
        }

        return NOT_SPECIFIED;
    }
}
