package org.bavand.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShopModel {

    @SerializedName("results")
    @Expose
    private List<ShopModel> shopResults = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("imageURL1")
    @Expose
    private String imageURL1;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("cityID")
    @Expose
    private String cityID;

    public List<ShopModel> getShopResults() {
        return shopResults;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageURL1() {
        return imageURL1;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCityID() {
        return cityID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
