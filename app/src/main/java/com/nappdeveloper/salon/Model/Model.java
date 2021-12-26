package com.nappdeveloper.salon.Model;

public class Model {

    String filterName,shopImage,shopName;

    public Model() {}

    public Model(String filterName, String shopImage, String shopName) {
        this.filterName = filterName;
        this.shopImage = shopImage;
        this.shopName = shopName;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
