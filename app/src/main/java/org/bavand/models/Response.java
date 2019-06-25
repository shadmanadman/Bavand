package org.bavand.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("results")
    @Expose
    private List<AdsModel> adsResults = null;
    private List<CategoryModel> categoryResults = null;

    /**
     * No args constructor for use in serialization
     */
    public Response() {
    }

    /**
     * @param adsResults
     */
    public Response(List<AdsModel> adsResults, List<CategoryModel> categoryResults) {
        super();
        this.adsResults = adsResults;
        this.categoryResults = categoryResults;
    }


    public List<AdsModel> getAdsResults() {
        return adsResults;
    }


    public List<CategoryModel> getCategoryResults() {
        return categoryResults;
    }
}
