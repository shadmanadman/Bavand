package org.bavand.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {
    @SerializedName("results")
    @Expose
    private List<CategoryModel> results = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("categoryImage")
    @Expose
    private String categoryImage;
    @SerializedName("categoryName")
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public List<CategoryModel> getResults() {
        return results;
    }
}
