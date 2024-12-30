package com.example.demo.Entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("t_book")
public class Book {
    @TableId
    private String bid;//主键
    private String bname;//图名
    private String author;//作者
    private double price;//定价
    private double discount;//折扣
    private String press;//出版社
    private int edition;//版次
    @TableField("pagenum")
    private int pageNum;//页数
    @TableField("wordnum")
    private int wordNum;//字数
    @TableField(exist = false)
    private Category category;//所属分类
    private String img;//封面路径
    private String thumimg;//缩略图路径

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getWordNum() {
        return wordNum;
    }

    public void setWordNum(int wordNum) {
        this.wordNum = wordNum;
    }

    public Category getCategory() {
        System.out.println("========================>获取了分类" + category);
        return category;
    }

    public void setCategory(Category _category) {
        System.out.println("========================>设置了分类" + category + "  [new]  " + _category);
        this.category = _category;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getThumimg() {
        return thumimg;
    }

    public void setThumimg(String thumimg) {
        this.thumimg = thumimg;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid='" + bid + '\'' +
                ", bname='" + bname + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", press='" + press + '\'' +
                ", edition=" + edition +
                ", pageNum=" + pageNum +
                ", wordNum=" + wordNum +
                ", category=" + category +
                ", img='" + img + '\'' +
                ", thumimg='" + thumimg + '\'' +
                '}';
    }
}