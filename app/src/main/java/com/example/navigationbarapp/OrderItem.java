package com.example.navigationbarapp;
import android.os.Parcel;
import android.os.Parcelable;

public class OrderItem implements Parcelable {
    private String burger;
    private String drink;
    private String friesSize;
    private String comment;

    public OrderItem(String burger, String drink, String friesSize, String comment) {
        this.burger = burger;
        this.drink = drink;
        this.friesSize = friesSize;
        this.comment = comment;
    }

    protected OrderItem(Parcel in) {
        burger = in.readString();
        drink = in.readString();
        friesSize = in.readString();
        comment = in.readString();
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    public String getBurger() {
        return burger;
    }

    public String getDrink() {
        return drink;
    }

    public String getFriesSize() {
        return friesSize;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(burger);
        dest.writeString(drink);
        dest.writeString(friesSize);
        dest.writeString(comment);
    }

    @Override
    public String toString() {
        // Customize the string representation
        return "Burger: " + burger + "\nDrink: " + drink +"\n"+ friesSize + (comment != null && !comment.isEmpty() ? "\nComment: " + comment : "");
    }
}
