package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.domain.Product;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jinhuaquan on 2017/7/7.
 */
@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private MemberService memberService;

    /**
     * 添加一个新产品
     * @param product
     * @return
     */
    @PostMapping("/add")
    public void createProduct(@RequestBody Member product){

        memberService.create(product.getId(),product.getUsername(),product.getPassword());
        System.out.println(product.getUsername()+" created success!");
        //return product.getUsername()+" created success!";
    }

    /**
     * 删除指定ID的产品
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id){
    	memberService.deleteById(id);

        return "id is "+id+" deleted success!";
    }

}
