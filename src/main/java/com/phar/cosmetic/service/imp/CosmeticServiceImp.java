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
        if(category.equals("kirpik-tuslari")){category="Kirpik tu??lar??";
             cosmetics= findBySubCategory(category);
            title="Kirpik tu??lar??";
        }
        else if(category.equals("goz-kolgeleri")){category="G??z k??lg??l??ri";
             cosmetics= findBySubCategory(category);
            title="G??z k??lg??l??ri";}
        else if(category.equals("qelemler-linerler")){category="Q??l??ml??r,linerl??r";
             cosmetics= findBySubCategory(category);
            title="Q??l??ml??r,linerl??r";}
        else if(category.equals("goz-ucun-olan-butun-mehsullar")){category="G??z ??????n";
             cosmetics= findByCategory(category);
            title="G??z ??????n olan b??t??n m??hsullar";}
        else if(category.equals("qash-ucun")){category="Qa?? ??????n";
             cosmetics= findByCategory(category);
            title="Qa?? ??????n";}
        else if(category.equals("tonal-kremler")){category="Tonal kreml??r";
             cosmetics= findBySubCategory(category);
            title="Tonal kreml??r";}
        else if(category.equals("uz-ucun-kirsan")){category="Kir??an";
             cosmetics= findBySubCategory(category);
            title="??z ??????n kir??anlar";}
        else if(category.equals("uz-ucun-korrektor")){category="??z ??????n korrektor";
             cosmetics= findBySubCategory(category);
            title="??z ??????n korrektorlar";}
        else if(category.equals("enlik")){category="??nlik";
            cosmetics= findBySubCategory(category);
            title="??z ??????n ??nlikl??r";}
        else if(category.equals("uz-ucun-olan-butun-mehsullar")){category="??z ??????n";
            cosmetics= findBySubCategory(category);
            title="??z ??????n olan b??t??n m??hsullar";}
        else if(category.equals("dodaq-boyalari")){category="Dodaq boyalar??";
            cosmetics= findBySubCategory(category);
            title="Dodaq boyalar??";}
        else if(category.equals("qelemler")){category="Q??l??ml??r";
            cosmetics= findBySubCategory(category);
            title="Q??l??ml??r";}
        else if(category.equals("parladicilar-balzamlar")){category="Parlad??c??lar,Balzamlar";
            cosmetics= findBySubCategory(category);
            title="Parlad??c??lar,balzamlar";}
        else if(category.equals("dodaq-ucun-olan-butun-mehsullar")){category="Dodaq ??????n";
            cosmetics= findBySubCategory(category);
            title="Dodaq ??????n olan b??t??n m??hsullar";}
        else if(category.equals("dirnaq-boyalari")){category="D??rnaq boyalar??";
            cosmetics= findBySubCategory(category);
            title="D??rnaq boyalar??";}
        else if(category.equals("baza-qurutma")){category="Baza,qurutma";
            cosmetics= findBySubCategory(category);
            title="Baza,qurutma";}
        else if(category.equals("dirnaq-ucun-korrektor")){category="D??rnaq ??????n korrektor";
            cosmetics= findBySubCategory(category);
            title="D??rnaq ??????n korrektorlar";}
        else if(category.equals("qulluq-vasiteleri")){category="Qulluq vasit??l??ri";
            cosmetics= findBySubCategory(category);
            title="Qulluq vasit??l??ri";}
        else if(category.equals("dirnaq-ucun-aksessuarlar")){category="Aksessuarlar";
            cosmetics= findBySubCategory(category);
            title="Aksessuarlar";}
        else if(category.equals("dirnaq-ucun-olan-butun-mehsullar")){category="D??rnaq ??????n";
            cosmetics= findBySubCategory(category);
            title="D??rnaq ??????n olan b??t??n m??hsullar";}
        else if(category.equals("sac-boyalari")){category="Sa?? boyalar??";
            cosmetics= findBySubCategory(category);
            title="Sa?? boyalar??";}
        else if(category.equals("sac-tonlari")){category="Sa?? tonlar??";
            cosmetics= findBySubCategory(category);
            title="Sa?? tonlar??";}
        else if(category.equals("sac-sampunlari")){category="Sa?? ??ampunlar??";
            cosmetics= findBySubCategory(category);
            title="Sa?? ??ampunlar??";}
        else if(category.equals("rengli-sac-acicilar")){category="R??ngli sa?? a????c??lar";
            cosmetics= findBySubCategory(category);
            title="R??ngli sa?? a????c??lar";}
        else if(category.equals("sac-spreyleri")){category="Sa?? spreyl??ri";
            cosmetics= findBySubCategory(category);
            title="Sa?? spreyl??ri";}
        else if(category.equals("sac-ucun-diger")){category="Dig??r";
            cosmetics= findBySubCategory(category);
            title="Dig??r m??hsullar";}
        else if(category.equals("sac-ucun-olan-butun-mehsullar")){category="B??t??n m??hsullar";
            cosmetics= findBySubCategory(category);
            title="Sa?? ??????n olan b??t??n m??hsullar";}
        else if(category.equals("ayaq-ucun")){category="Ayaq ??????n";
            cosmetics= findByCategory(category);
            title="Ayaq ??????n m??hsullar";}
        else if(category.equals("teras-ucun")){category="T??ra?? ??????n";
            cosmetics= findByCategory(category);
            title="T??ra?? ??????n m??hsullar";}
        else if(category.equals("gigiyenik-vasiteler")){category="Gigiyenik vasit??l??r";
            cosmetics= findByCategory(category);
            title="Gigiyenik vasit??l??r";}
        else if(category.equals("aksessuarlar")){category="Aksessuarlar";
            cosmetics= findByCategory(category);
            title="Aksessuarlar";}
        else if(category.equals("diger")){category="Dig??r";
            cosmetics= findByCategory(category);
            title="Dig??r m??hsullar";}





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
