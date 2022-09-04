package com.phar.cosmetic.service.imp;

import com.phar.cosmetic.model.Cosmetic;
import com.phar.cosmetic.repository.CosmeticRepository;
import com.phar.cosmetic.service.CosmeticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CosmeticServiceImp implements CosmeticService {
    @Autowired
    CosmeticRepository cosmeticRepository;
    @Override
    public void add(String productName, String brendName, Long cosmeticCode,Double firstPrice, Double price, String productAbout, String category,String subCategory, String image) {
        Cosmetic cosmetic=new Cosmetic();
        cosmetic.setName(productName);
        cosmetic.setBrandName(brendName);
        cosmetic.setCosmeticCode(cosmeticCode);
        cosmetic.setFirstPrice(firstPrice);
        cosmetic.setPrice(price);
        cosmetic.setProductAbout(productAbout);
        System.out.println("category"+category);
        cosmetic.setCategory(category);
        cosmetic.setSubCategory(subCategory);
        cosmetic.setReview(0);

        LocalDateTime localDateTime = LocalDateTime.now();
        cosmetic.setCurrentDateTime(localDateTime);
            cosmetic.setImage(image);

        cosmeticRepository.save(cosmetic);

    }

    @Override
    public List<Cosmetic> findAll() {
        return cosmeticRepository.findAll();
    }

    @Override
    public Cosmetic findById(Long id) {

        return cosmeticRepository.findById(id).get();
    }

    @Override
    public List<Cosmetic> findByCategory(String category) {
        return cosmeticRepository.findByCategory(category);
    }

    @Override
    public List<Cosmetic> findBySubCategory(String subCategory) {
        return cosmeticRepository.findBySubCategory(subCategory);
    }

    @Override
    public void categoryController(Model model, String category) {
        List<Cosmetic> cosmetics = null;
        String title=null;
        if(category.equals("kirpik-tuslari")){category="Kirpik tuşları";
             cosmetics= findBySubCategory(category);
            title="Kirpik tuşları";
        }
        else if(category.equals("goz-kolgeleri")){category="Göz kölgələri";
             cosmetics= findBySubCategory(category);
            title="Göz kölgələri";}
        else if(category.equals("qelemler-linerler")){category="Qələmlər,linerlər";
             cosmetics= findBySubCategory(category);
            title="Qələmlər,linerlər";}
        else if(category.equals("goz-ucun-olan-butun-mehsullar")){category="Göz üçün";
             cosmetics= findByCategory(category);
            title="Göz üçün olan bütün məhsullar";}
        else if(category.equals("qash-ucun")){category="Qaş üçün";
             cosmetics= findByCategory(category);
            title="Qaş üçün";}
        else if(category.equals("tonal-kremler")){category="Tonal kremlər";
             cosmetics= findBySubCategory(category);
            title="Tonal kremlər";}
        else if(category.equals("uz-ucun-kirsan")){category="Kirşan";
             cosmetics= findBySubCategory(category);
            title="Üz üçün kirşanlar";}
        else if(category.equals("uz-ucun-korrektor")){category="Üz üçün korrektor";
             cosmetics= findBySubCategory(category);
            title="Üz üçün korrektorlar";}
        else if(category.equals("enlik")){category="Ənlik";
            cosmetics= findBySubCategory(category);
            title="Üz üçün ənliklər";}
        else if(category.equals("uz-ucun-olan-butun-mehsullar")){category="Üz üçün";
            cosmetics= findBySubCategory(category);
            title="Üz üçün olan bütün məhsullar";}
        else if(category.equals("dodaq-boyalari")){category="Dodaq boyaları";
            cosmetics= findBySubCategory(category);
            title="Dodaq boyaları";}
        else if(category.equals("qelemler")){category="Qələmlər";
            cosmetics= findBySubCategory(category);
            title="Qələmlər";}
        else if(category.equals("parladicilar-balzamlar")){category="Parladıcılar,Balzamlar";
            cosmetics= findBySubCategory(category);
            title="Parladıcılar,balzamlar";}
        else if(category.equals("dodaq-ucun-olan-butun-mehsullar")){category="Dodaq üçün";
            cosmetics= findBySubCategory(category);
            title="Dodaq üçün olan bütün məhsullar";}
        else if(category.equals("dirnaq-boyalari")){category="Dırnaq boyaları";
            cosmetics= findBySubCategory(category);
            title="Dırnaq boyaları";}
        else if(category.equals("baza-qurutma")){category="Baza,qurutma";
            cosmetics= findBySubCategory(category);
            title="Baza,qurutma";}
        else if(category.equals("dirnaq-ucun-korrektor")){category="Dırnaq üçün korrektor";
            cosmetics= findBySubCategory(category);
            title="Dırnaq üçün korrektorlar";}
        else if(category.equals("qulluq-vasiteleri")){category="Qulluq vasitələri";
            cosmetics= findBySubCategory(category);
            title="Qulluq vasitələri";}
        else if(category.equals("dirnaq-ucun-aksessuarlar")){category="Aksessuarlar";
            cosmetics= findBySubCategory(category);
            title="Aksessuarlar";}
        else if(category.equals("dirnaq-ucun-olan-butun-mehsullar")){category="Dırnaq üçün";
            cosmetics= findBySubCategory(category);
            title="Dırnaq üçün olan bütün məhsullar";}
        else if(category.equals("sac-boyalari")){category="Saç boyaları";
            cosmetics= findBySubCategory(category);
            title="Saç boyaları";}
        else if(category.equals("sac-tonlari")){category="Saç tonları";
            cosmetics= findBySubCategory(category);
            title="Saç tonları";}
        else if(category.equals("sac-sampunlari")){category="Saç şampunları";
            cosmetics= findBySubCategory(category);
            title="Saç şampunları";}
        else if(category.equals("rengli-sac-acicilar")){category="Rəngli saç açıcılar";
            cosmetics= findBySubCategory(category);
            title="Rəngli saç açıcılar";}
        else if(category.equals("sac-spreyleri")){category="Saç spreyləri";
            cosmetics= findBySubCategory(category);
            title="Saç spreyləri";}
        else if(category.equals("sac-ucun-diger")){category="Digər";
            cosmetics= findBySubCategory(category);
            title="Digər məhsullar";}
        else if(category.equals("sac-ucun-olan-butun-mehsullar")){category="Bütün məhsullar";
            cosmetics= findBySubCategory(category);
            title="Saç üçün olan bütün məhsullar";}
        else if(category.equals("ayaq-ucun")){category="Ayaq üçün";
            cosmetics= findByCategory(category);
            title="Ayaq üçün məhsullar";}
        else if(category.equals("teras-ucun")){category="Təraş üçün";
            cosmetics= findByCategory(category);
            title="Təraş üçün məhsullar";}
        else if(category.equals("gigiyenik-vasiteler")){category="Gigiyenik vasitələr";
            cosmetics= findByCategory(category);
            title="Gigiyenik vasitələr";}
        else if(category.equals("aksessuarlar")){category="Aksessuarlar";
            cosmetics= findByCategory(category);
            title="Aksessuarlar";}
        else if(category.equals("diger")){category="Digər";
            cosmetics= findByCategory(category);
            title="Digər məhsullar";}





        model.addAttribute("cosmetics",cosmetics);
        model.addAttribute("title",title);
    }

    @Override
    public void update(Long id, String name, String brandName, String category, Long cosmeticCode, Double price,String productAbout) {
        Cosmetic cosmetic=findById(id);
        cosmetic.setName(name);
        cosmetic.setBrandName(brandName);
        cosmetic.setCategory(category);
        cosmetic.setCosmeticCode(cosmeticCode);
        cosmetic.setPrice(price);
        cosmetic.setProductAbout(productAbout);
        cosmeticRepository.save(cosmetic);
    }

    @Override
    public void deleteById(Long id) {
        cosmeticRepository.deleteById(id);
    }


    @Override
    public List<Cosmetic> findByBrandName(String brandName) {
        return cosmeticRepository.findByBrandName(brandName);
    }

    @Override
    public Set<String> brandNames() {
        int size=findAll().size();
        List<Cosmetic> products=findAll();
        Set<String> brandNames= new HashSet<>();
        for (int i=0;i<size;i++)
        {

            brandNames.add(products.get(i).getBrandName());
        }


        return brandNames;
    }
}
