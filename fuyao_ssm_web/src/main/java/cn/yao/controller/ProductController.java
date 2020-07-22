package cn.yao.controller;

import cn.yao.domain.Product;
import cn.yao.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;
    @RequestMapping("/save.do")
    public String save(Product product){
        service.save(product);
        System.out.println("1111");
        return "forward:findAll.do" ;
    }
    @RequestMapping("del.do")
    public String del(String [] ids){
        System.out.println(Arrays.toString(ids));
        for(String id:ids){
            service.delete(id);
        }
        return "forward:/product/findAll.do";
    }
    @RequestMapping("/findAll.do")
//    @RolesAllowed("ADMIN")
    public ModelAndView findAll(){
        List<Product> products = service.findALl();
        System.out.println(products.size());
        ModelAndView mv=new ModelAndView();
        mv.setViewName("product-list");
        mv.addObject("productList",products);
        return mv;
    }
}
