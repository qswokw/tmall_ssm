package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ProductImageMapper;
import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.pojo.ProductImageExample;
import com.how2java.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage pi) {
        productImageMapper.insert(pi);
    }

    @Override
    public void delete(int id) {
        productImageMapper.deleteByPrimaryKey(id);

    }

    @Override
    public void update(ProductImage pi) {
productImageMapper.updateByPrimaryKey(pi);
    }

    @Override
    public ProductImage get(int id) {
        return productImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid, String type) {
        ProductImageExample productImageExample = new ProductImageExample();
        productImageExample.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);
        productImageExample.setOrderByClause("sort asc");
        return productImageMapper.selectByExample(productImageExample);
    }

//添加detail数据列及更改排序
    @Override
    public List list() {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andTypeEqualTo(ProductImageService.type_detail);
        example.setOrderByClause("id desc");
        return productImageMapper.selectByExample(example);
    }

    @Override
    public List<ProductImage> getPis(int pid) {
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(ProductImageService.type_detail);
        return productImageMapper.selectByExample(example);
    }

    @Override
    public ProductImage getnext(int pid, int sort) {
        System.out.println("QQQQQQQQQQ"+sort+"=="+pid);
        ProductImageExample example = new ProductImageExample();
        example.createCriteria().andPidEqualTo(pid).andSortEqualTo(sort);
        System.out.println(productImageMapper.selectByExample(example));
        return productImageMapper.selectByExample(example).get(0);
    }
}
