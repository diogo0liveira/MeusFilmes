package com.diogo.oliveira.mymovies.pojo;

import com.diogo.oliveira.mymovies.model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created on 15/01/18 01:03.
 *
 * @author Diogo Oliveira.
 */
public class SearchResult
{
    @Expose
    @SerializedName("page")
    private Integer page;
    @Expose
    @SerializedName("total_results")
    private Integer totalResults;
    @Expose
    @SerializedName("total_pages")
    private Integer totalPages;
    @Expose
    @SerializedName("results")
    private List<Movie> results;

    public SearchResult(Integer page, Integer totalResults, Integer totalPages, List<Movie> results)
    {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults)
    {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }

    public List<Movie> getResults()
    {
        return results;
    }

    public void setResults(List<Movie> results)
    {
        this.results = results;
    }
}
