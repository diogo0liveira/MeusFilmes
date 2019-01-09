package com.diogo.oliveira.mymovies.util.json;

import com.diogo.oliveira.mymovies.util.network.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created on 02/12/2015 16:13.
 *
 * @author Diogo Oliveira.
 */
public class GsonHelper
{
    //    Gson gson = new GsonBuilder()
    //            .registerTypeAdapter(Id.class, new IdTypeAdapter())
    //            .enableComplexMapKeySerialization()
    //            .serializeNulls()
    //            .setDateFormat(DateFormat.LONG)
    //            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
    //            .setPrettyPrinting()
    //            .setVersion(1.0)
    //            .create();

    public static Gson build()
    {
        //@formatter:off
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(Status.class, new EnumResultStatusAdapter()).create();
        //@formatter:on
    }

    private static class EnumResultStatusAdapter implements JsonSerializer<Status>, JsonDeserializer<Status>
    {
        @Override
        public JsonElement serialize(Status status, Type type, JsonSerializationContext context)
        {
            return context.serialize(status.value);
        }

        @Override
        public Status deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException
        {
            return Status.get(json.getAsInt());
        }
    }
}