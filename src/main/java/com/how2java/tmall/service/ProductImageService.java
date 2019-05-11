package com.how2java.tmall.service;

import com.how2java.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(ProductImage pi);
    void delete(int id);
    void update(ProductImage pi);
    ProductImage get(int id);
    List list(int pid, String type);

    List list();

    //通过pid获取
    List<ProductImage> getPis(int pid);

    //通过pid和sort获取
    ProductImage getnext(int pid, int sort);
}
