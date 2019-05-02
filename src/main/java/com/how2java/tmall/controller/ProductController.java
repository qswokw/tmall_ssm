package com.how2java.tmall.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.Product;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.ProductService;
import com.how2java.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(Model model, Page page,int cid) {
        Category c = categoryService.get(cid);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        //分页限定类别参数，易忘
        page.setParam("&cid="+cid);

        model.addAttribute("c", c);
        model.addAttribute("ps", ps);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }

    @RequestMapping("admin_product_add")
    public String add(Product product) {
        productService.add(product);
        System.out.println(product.getCid()+"----------------");
        return "redirect:/admin_product_list?cid="+product.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        int cid=productService.get(id).getCid();
        productService.delete(id);

        return "redirect:/admin_product_list?cid="+cid;
    }

    @RequestMapping("admin_product_edit")
    public String edit(int id,Model model) {
        Product p = productService.get(id);
        //可不可以在Service中添加类属性，这里不写呢？不行因为他们创建的是同一id的对象，但是不是同一对象，此时创建的product中没有Category类
 //       或者在get方法中返回带Category势力的Product
//        int cid=p.getCid();
//        p.setCategory(categoryService.get(cid));
        model.addAttribute("p", p);
        return "admin/editProduct";
    }
    @RequestMapping("admin_product_update")
    public String update(Product product) {
        System.out.println(product.getCid()+"\\\\\\\\\\\\");
        productService.update(product);
        int cid=product.getCid();
        return "redirect:/admin_product_list?cid="+cid;
    }
}
