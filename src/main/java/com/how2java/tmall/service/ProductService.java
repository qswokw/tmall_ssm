package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);

    void delete(int id);

    void update(Product product);

    Product get(int id);

    List list(int cid);

    //为home页类别导航填充
    public void fill(List<Category> categorys);

    public void fill(Category category);

    public void fillByRow(List<Category> categorys);

    //    增加为产品设置销量和评价数量的方法：
    void setSaleAndReviewNumber(Product p);

    void setSaleAndReviewNumber(List<Product> ps);

    //搜索产品
    List<Product> search(String keyword);
}
