package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.ProductWithImageUploadDto;
import com.example.registrationlogindemo.entity.CartItem;
import com.example.registrationlogindemo.entity.Product;
import com.example.registrationlogindemo.service.impl.ProductServiceMySQL;
import com.example.registrationlogindemo.service.impl.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping
public class ProductController {
    private final ProductServiceMySQL productServiceMySQL;
    public ProductController(@Autowired ProductServiceMySQL productServiceMySQL) {
        this.productServiceMySQL = productServiceMySQL;
    }

    @GetMapping("/find/{productId}")
    public Product getProduct(@PathVariable("productId") Integer productId)
    {
        return this.productServiceMySQL.findById(productId);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId){
        this.productServiceMySQL.delete(productId);
    }

    @PostMapping(value = "/product/editProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView update(@RequestPart("product") Product product, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "static/images/upload-dir/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        Product productFound = this.productServiceMySQL.findById(product.getId());
        product.setImageURL("../images/upload-dir/" + fileName);
        this.productServiceMySQL.update(product, product.getId());
        model.addAttribute("productList", productServiceMySQL.all());
        return new RedirectView("/product/productList", true);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/product/addProduct")
    public RedirectView save(Product product, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImageURL("../images/upload-dir/" + fileName);
        Product savedProduct = this.productServiceMySQL.save(product);
        String uploadDir = "static/images/upload-dir/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/product/productList", true);
    }
}



