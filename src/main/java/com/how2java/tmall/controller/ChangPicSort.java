package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.ProductImage;
import com.how2java.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("admin_productImage_changesort")
    public String changesort(@RequestParam("id") int id, @RequestParam("sort") int sort) {
        ProductImage productImage = productImageService.get(id);
        List<ProductImage> pis = productImageService.getPis(productImage.getPid());
        ProductImage pnext = null;
        int max = 0;
        for (ProductImage pi : pis) {
            int pisort = pi.getSort();
            max = max > pisort ? max : pisort;
        }
        int index = productImage.getSort();
        if (sort == 0 && index != 1) {
            //向上移
            pnext = productImageService.getnext(productImage.getPid(), index - 1);
            pnext.setSort(index);
            productImage.setSort(index - 1);
        } else if(sort==1&&index!=max){
            //向下移
            pnext = productImageService.getnext(productImage.getPid(), index + 1);
            pnext.setSort(index);
            productImage.setSort(index + 1);
        }else
            return "redirect:admin_productImage_list?pid=" + productImage.getPid();
        productImageService.update(productImage);
        productImageService.update(pnext);
        return "redirect:admin_productImage_list?pid=" + productImage.getPid();

    }

}
