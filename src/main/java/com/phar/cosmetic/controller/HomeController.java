package com.phar.cosmetic.controller;

import com.phar.cosmetic.EmailService;
import com.phar.cosmetic.model.Cosmetic;
import com.phar.cosmetic.model.Information;
import com.phar.cosmetic.repository.CosmeticRepository;
import com.phar.cosmetic.repository.InformationRepository;
import com.phar.cosmetic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CosmeticService cosmeticService;
    @Autowired
    OrderMessageService orderMessageService;
    @Autowired
    ContactUsMessageService contactUsMessageService;
    @Autowired
    CosmeticRepository cosmeticRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    InformationService informationService;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    UserService userService;


    @GetMapping({"/kosmetikalar","/"})
    public String home(Model model){

        Pageable paging = PageRequest.of(0,12);
        Page<Cosmetic> cosmetics=  cosmeticRepository.findAll(paging);;
        int totalPages = cosmetics.getTotalPages();
        int currentPage = 1;


        model.addAttribute("cosmetics",cosmetics);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        return "home";
    }
    @GetMapping("/page/{pageNumber}")
    public String listByPageAdmin(Model model, @PathVariable("pageNumber")int currentPage)
    {
        Pageable paging = PageRequest.of(currentPage-1,12);
        Page<Cosmetic> cosmetics=  cosmeticRepository.findAll(paging);


        int totalPages = cosmetics.getTotalPages();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("cosmetics",cosmetics);
        return "home";

    }
    @GetMapping("/kosmetikalar/info")
    public String cosmeticDetails(Model  model,@RequestParam("id")Long id){
        List<Cosmetic> category=null;

        Cosmetic cosmetic= cosmeticService.findById(id);
        cosmetic.setReview(cosmetic.getReview()+1);
        cosmeticRepository.save(cosmetic);

        if (cosmeticService.findByCategory(cosmetic.getCategory()).size()>12) {
            category=new ArrayList<>();
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(1));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(2));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(3));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(4));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(5));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(6));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(7));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(8));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(9));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(10));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(11));
            category.add(cosmeticService.findByCategory(cosmetic.getCategory()).get(12));

        }
        else{
            category=cosmeticService.findByCategory(cosmetic.getCategory());

        }
//        userService.saveDB();
        model.addAttribute("cosmetic",cosmetic);
        model.addAttribute("category",category);
        return "productInfo";
    }



    @GetMapping("/bizimle-elaqe")
    public String contactUs(){

        return "contactUs";
    }
    @PostMapping("/bizimle-elaqe")
    public String contactUS(@RequestParam("name")String name,
                            @RequestParam("surName")String surName,
                            @RequestParam("email")String email,
                            @RequestParam("message")String message)
    {
        contactUsMessageService.saveDB(name,surName,email,message);
        emailService.contactUSMailSender(name,surName,"orxan1458ab@mail.ru",message,email);

        return "redirect:/kosmetikalar";
    }
    @GetMapping("/melumatlar")
    public String information(Model model){
        Pageable paging = PageRequest.of(0,15);
        Page<Information> information=  informationRepository.findAll(paging);
        int totalPages = information.getTotalPages();
        int currentPage = 1;
        model.addAttribute("informationn",information);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        return "information";
    }
    @GetMapping("/melumatlar/page/{pageNumber}")
    public String informationPagination(Model model, @PathVariable("pageNumber")int currentPage){
        Pageable paging = PageRequest.of(currentPage-1,18);
        Page<Information> information=  informationRepository.findAll(paging);
        int totalPages = information.getTotalPages();
        model.addAttribute("informationn",information);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        return "information";


    }


    @PostMapping("/kosmetikalar/sifaris-et")
    public String order(@RequestParam("id")Long cosmeticId,
                        @RequestParam("name")String name,
                        @RequestParam("surName") String surName,
                        @RequestParam("phone")String phone,
                        @RequestParam("address")String address,
                        @RequestParam("count") int count){
        orderMessageService.saveDB(name,surName,phone,address,count,cosmeticId);
        emailService.orderMailSender(cosmeticId,name,surName,phone,address,count);
        return "redirect:/kosmetikalar";
    }

    @GetMapping("/kosmetikalar/kateqoriyalar")
    public String category(Model  model,@RequestParam("kateqoriya")String category){
        cosmeticService.categoryController(model,category);

        return "categoryResult";
    }
    @GetMapping("/brendler")
    public String brands(Model model){

        model.addAttribute("brandNames",cosmeticService.brandNames());
        return "brands";
    }
    @GetMapping("/kosmetikalar/brendler")
    public String productsOfBrand(Model model,@RequestParam("brandName")String brandName){
        List<Cosmetic> cosmetics=cosmeticService.findByBrandName(brandName);

        model.addAttribute("productsOfBrand", cosmetics);
        model.addAttribute("brandName", brandName);
        return "productsOfBrand";
    }
    @GetMapping("/kosmetikalar/etrafli-melumat")
    public String informationDetails(Model model,@RequestParam("id")Long id){
    model.addAttribute("information",informationService.findById(id));
    return "informationDetails";
    }

//    @GetMapping("/kosmetikalar/brendler/{pageNumber}")
//    public String pageBrend(Model model,@RequestParam("brandName")String brandName,@RequestParam("id")Long id)
//    {
//
//        long length=16*id;
//        List<Cosmetic> cosmetics=cosmeticService.findByBrandName(brandName);
//        List<Cosmetic> cosmeticFirstPage = new ArrayList<>();
//        if (cosmetics.size()<(16*id)){
//            length=cosmetics.size();
//        }
//        for(long i=16*(id-1);i<length;i++){
//
//            cosmeticFirstPage.add(cosmetics.get((int) i));
//
//        }
//        model.addAttribute("productsOfBrand", cosmeticFirstPage);
//        return "productsOfBrand";
//
//
//    }

}
