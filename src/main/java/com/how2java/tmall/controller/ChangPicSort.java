package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class ChangPicSort {
    @Autowired
    ProductImageService productImageService;

    @RequestMapping("addsort")
    @ResponseBody
    public String addsort() {

        List<ProductImage> productImages = productImageService.list();
        int pid=-1;
        int i=1;
        for (ProductImage p : productImages) {
            if (pid != p.getPid().intValue()) {
                pid = p.getPid();
                i=1;
            }
            p.setSort(i);
            productImageService.update(p);
            i++;
        }
        return "su";
    }


}
