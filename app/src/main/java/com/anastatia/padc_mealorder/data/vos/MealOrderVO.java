package com.anastatia.padc_mealorder.data.vos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Nyein Nyein on 7/18/2016.
 */
public class MealOrderVO implements Serializable{

    @SerializedName("name")
    private String name;

    @SerializedName("img_url")
    private String img_url;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private String price;

    @SerializedName("ingredients")
    private String[] ingredients;

    public void setName(String name) {
        this.name = name;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {

        return name;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public MealOrderVO(String name, String img_url, String description, String price, String[] ingredients) {

        this.name = name;
        this.img_url = img_url;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public MealOrderVO() {

    }

}
