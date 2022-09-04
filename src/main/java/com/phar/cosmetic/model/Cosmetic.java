package com.phar.cosmetic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cosmetic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String brandName;
    private Long cosmeticCode;
    private Double firstPrice;
    private Double price;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String productAbout;
    private String category;
    private String subCategory;
    private String image;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime currentDateTime;
    private int review;
}



