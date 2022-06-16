package com.phar.cosmetic.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    private String title;
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String shortContent;
    private String url;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
}
