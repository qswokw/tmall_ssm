package com.how2java.tmall.controller;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.util.ImageUtil;
import com.how2java.tmall.util.Page;
import com.how2java.tmall.util.UploadedImageFile;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model,Page page) {
        List<Category> cs = categoryService.list(page);
        int total=categoryService.total();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("cs", cs);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.add(c);
        //获取资源路径
        File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
        System.out.println(session.getServletContext().getRealPath("img/category"));
        //通过id保存图片
        File file=new File(imageFolder,c.getId()+".jpg");
        //category文件夹不存在则创建
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
//        获取文件
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";//注意此处加/和不加区别，不加是追加连接
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) {
        categoryService.delete(id);
        File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
        File file=new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id,Model model) {
        Category c = categoryService.get(id);
        model.addAttribute("c", c);
        return "admin.editCategory";
    }
}
