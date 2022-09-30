package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProductRepository implements Serializable {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static String webPage = "https://dummyjson.com/products";
    static Type type = new TypeToken<ProductRepository>(){}.getType();
    List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }
    public class Product implements Serializable{
        private int id;
        private String title;
        private String description;
        private int price;
        private double discountPercentage;
        private double rating;
        private int stock;
        private String brand;
        private String category;
        private String thumbnail;
        private String[] images;

        public Product(int id, String title, String description, int price, double discountPercentage, double rating, int stock, String brand, String category, String thumbnail, String[] images) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.price = price;
            this.discountPercentage = discountPercentage;
            this.rating = rating;
            this.stock = stock;
            this.brand = brand;
            this.category = category;
            this.thumbnail = thumbnail;
            this.images = images;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Double getDiscountPercentage() {
            return discountPercentage;
        }

        public void setDiscountPercentage(double discountPercentage) {
            this.discountPercentage = discountPercentage;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String[] getImages() {
            return images;
        }

        public void setImages(String[] images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", discountPercentage=" + discountPercentage +
                    ", rating=" + rating +
                    ", stock=" + stock +
                    ", brand='" + brand + '\'' +
                    ", category='" + category + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", images=" + Arrays.toString(images) +
                    '}';
        }
    }
    public int size(){
        return products.size();
    }
    public void print(){
        System.out.println(products.toString());
    }
    static void serialize(ProductRepository list, String filePath) throws IOException {
        Gson gson = new Gson();
        gson.toJson(list);
        try (OutputStream stream = new FileOutputStream(filePath);
             Writer writer = new OutputStreamWriter(stream)){
            gson.toJson(list,writer);
        }

    }
    public void generatePrice(int startPrice,int endPrice) throws IOException {
        String filePath = "SortByPrice.ser";
        List<Product> list= products.stream()
                .filter(x-> x.getPrice()>=startPrice&&x.getPrice()<endPrice)
                .toList();
        serialize(new ProductRepository(list),filePath);
    }
    public void generateCategory(Kategories category) throws IOException {
        String filePath = "SortByCategory.ser";
        List<Product> list= products.stream()
                .filter(x-> x.getCategory().equals(category.toString()))
                .toList();
        serialize(new ProductRepository(list),filePath);
    }
    public void generateDiscount(Double discount) throws IOException {
        String filePath = "SortByDiscount.ser";
        List<Product> list= products.stream()
                .filter(x-> x.getDiscountPercentage()>discount)
                .sorted((x,y)->x.getDiscountPercentage().compareTo(y.discountPercentage))
                .toList();
        serialize(new ProductRepository(list),filePath);
    }

}
