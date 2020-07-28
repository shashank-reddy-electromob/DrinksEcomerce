package org.electromob.drinksecomerce;

public class CartAddition {

    public String item,price,itemcount;

    public CartAddition() {
    }

    public CartAddition(String item, String price, String itemcount) {
        this.item = item;
        this.price = price;
        this.itemcount = itemcount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemcount() {
        return itemcount;
    }

    public void setItemcount(String itemcount) {
        this.itemcount = itemcount;
    }
}
