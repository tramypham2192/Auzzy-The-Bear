package com.example.registrationlogindemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithImageUploadDto {
    private int id;
    private String name;
    private int price;
    private String description;
    private MultipartFile image;
}
