package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.example.ProductRepository.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ProductRepository list;
        try (InputStream stream = new URL(webPage).openStream();
             Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)){
            list = gson.fromJson(reader,ProductRepository.type);
        }
        System.out.println(list.size());
        list.print();
        String filePath ="File.ser";
        try {
            ProductRepository.serialize(list,filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.generatePrice(300,600);
        list.generateCategory(Kategories.smartphones);
        list.generateDiscount(17.4);
        class Product implements Externalizable{
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

            public double getDiscountPercentage() {
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
            public void writeExternal(ObjectOutput out) throws IOException {
                out.writeInt(id);
                out.writeUTF(title);
                out.writeUTF(description);
                out.writeInt(price);
                out.writeDouble(discountPercentage);
                out.writeDouble(rating);
                out.writeInt(stock);
                out.writeUTF(brand);
                out.writeUTF(category);
                out.writeUTF(thumbnail);
                out.writeObject(images);
            }

            @Override
            public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
                this.id=in.readInt();
                this.title=in.readUTF();
                this.description=in.readUTF();
                this.price=in.readInt();
                this.discountPercentage=in.readDouble();
                this.rating=in.readDouble();
                this.stock=in.readInt();
                this.brand=in.readUTF();
                this.category=in.readUTF();
                this.thumbnail=in.readUTF();
                this.images = (String[]) in.readObject();
            }
        }
        List<Product> products = List.of(
                new Product(12,"ffdsfs","not bad",321,16.5,43.0,10,"brand",Kategories.automotive.toString(),"thumbnail",new String[]{"image1","image732"}),
                new Product(13,"hfghgfh","not bad",431,15.6,27.0,10,"brand",Kategories.fragrances.toString(),"thumbnail",new String[]{"image14","image53"}),
                new Product(14,"yrtyrhj","not bad",325,17.0,54.7,10,"brand",Kategories.groceries.toString(),"thumbnail",new String[]{"image11","image243"}),
                new Product(15,"fewter","not bad",234,14.5,46.0,10,"brand",Kategories.automotive.toString(),"thumbnail",new String[]{"image43","image21"}),
                new Product(16,"xccnvn","not bad",541,15.5,49.0,10,"brand",Kategories.automotive.toString(),"thumbnail",new String[]{"image112","image23"})
        );
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("Products.ser"))){
            for (Product item:
                 products) {
                item.writeExternal(writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}