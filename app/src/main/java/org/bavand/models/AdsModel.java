package org.bavand.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsModel {
    @SerializedName("results")
    @Expose
    private List<AdsModel> adsResults = null;
    @SerializedName("shopID")
    @Expose
    private String shopID;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("shopTitle")
    @Expose
    private String shopTitle;
    @SerializedName("shopComment")
    @Expose
    private String shopComment;


    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopComment() {
        return shopComment;
    }

    public void setShopComment(String shopComment) {
        this.shopComment = shopComment;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<AdsModel> getAdsResults() {
        return adsResults;
    }
}
