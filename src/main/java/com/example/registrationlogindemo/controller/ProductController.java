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
//    @GetMapping("/update/{productId}")
//    public String updateProductForm(@PathVariable("productId") int productId, Model model){
//        Product product = productServiceMySQL.findById(productId);
//        model.addAttribute("product", product);
//        return "/product/editProduct";
//    }
//    @PreAuthorize("hasPermission(#product, #productId, #multipartFile, 'edit')")
//    @PostMapping("product/update/{productId}")
//    @CrossOrigin
//    @PostMapping(value = "/update/{productId}", consumes = {"multipart/form-data"})
//    public Product update(@RequestBody Product product, @PathVariable("productId") Integer productId){
//        return this.productServiceMySQL.update(product, productId);
//    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId){
        this.productServiceMySQL.delete(productId);
    }

//    @PostMapping(value = "product/editProduct/{productId}", consumes = {"multipart/form-data"})
    @PostMapping(value = "/product/editProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView update(@RequestPart("product") Product product, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
//    public void update() throws IOException {
            System.out.println("update product called");
            System.out.println(product.getId());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "static/images/upload-dir/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        System.out.println("filename: " + fileName);

        Product productFound = this.productServiceMySQL.findById(product.getId());
//        productFound.setName(product.getName());
//        productFound.setDescription(product.getDescription());
//        productFound.setPrice(product.getPrice());
        product.setImageURL("../images/upload-dir/" + fileName);
        System.out.println(product.getImageURL());
//        productFound.setImageURL(product.getImageURL());
        this.productServiceMySQL.update(product, product.getId());
        model.addAttribute("productList", productServiceMySQL.all());
//        return productFound;
//        return "redirect:/product/productList";
        return new RedirectView("/product/productList", true);
    }
//    @CrossOrigin
//    @PostMapping("/product")
//    public Product save(@RequestBody ProductDto productDto){
//        Product newProduct = new Product();
//        newProduct.setName(productDto.getName());
//        newProduct.setPrice(productDto.getPrice());
//        newProduct.setDescription(productDto.getDescription());
//        newProduct.setImageURL(productDto.getImageUrl());
//        return this.productServiceMySQL.save(newProduct);
//    }

    @CrossOrigin(origins = "*")
    @PostMapping("/product/addProduct")
    public RedirectView save(Product product, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        System.out.println("filename: " + fileName);
        product.setImageURL("../images/upload-dir/" + fileName);
//        System.out.println("product: " + product.toString());
        Product savedProduct = this.productServiceMySQL.save(product);
//        String uploadDir = "upload-dir";
        String uploadDir = "static/images/upload-dir/";
//        FileUploadUtil.cleanDir(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/product/productList", true);
    }
}



