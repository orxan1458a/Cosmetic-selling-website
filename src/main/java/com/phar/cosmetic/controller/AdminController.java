package com.phar.cosmetic.controller;

import com.phar.cosmetic.EmailService;
import com.phar.cosmetic.model.ContactUsMessage;
import com.phar.cosmetic.model.Cosmetic;
import com.phar.cosmetic.model.Information;
import com.phar.cosmetic.model.OrderMesage;
import com.phar.cosmetic.repository.ContactUsMessageRepository;
import com.phar.cosmetic.repository.CosmeticRepository;
import com.phar.cosmetic.repository.InformationRepository;
import com.phar.cosmetic.repository.OrderMessageRepository;
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

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CosmeticService cosmeticService;
    @Autowired
    OrderMessageService orderMessageService;
    @Autowired
    ContactUsMessageService contactUsMessageService;
    @Autowired
    ContactUsMessageRepository contactUsMessageRepository;
    @Autowired
    CosmeticRepository cosmeticRepositroy;
    @Autowired
    InformationService informationService;
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    OrderMessageRepository orderMessageRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    UserService userService;

    @GetMapping({"/admin/kosmetikalar","/admin"})
    public String home(Model model){
        Pageable paging = PageRequest.of(0,18);
        Page<Cosmetic> cosmetics=  cosmeticRepositroy.findAll(paging);;
        int totalPages = cosmetics.getTotalPages();
        int currentPage = 1;


        model.addAttribute("cosmetics",cosmetics);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        return "adminHome";
    }
    @GetMapping("admin/page/{pageNumber}")
    public String listByPageAdmin(Model model, @PathVariable("pageNumber")int currentPage)
    {



        Pageable paging = PageRequest.of(currentPage-1,18);
        Page<Cosmetic> cosmetics=  cosmeticRepositroy.findAll(paging);


        int totalPages = cosmetics.getTotalPages();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("cosmetics",cosmetics);
        return "home";

    }
    @GetMapping("/admin/kosmetikalar/info")
    public String productInfo(Model  model,@RequestParam("id")Long id) {

        Cosmetic cosmetic = cosmeticService.findById(id);
        List<Cosmetic> category = cosmeticService.findByCategory(cosmetic.getCategory());
        model.addAttribute("cosmetic", cosmetic);
        model.addAttribute("category", category);
        return "adminProductInfo";

    }
    @PostMapping("/admin/update")
    public String update(@RequestParam("id")Long id,
                         @RequestParam("name")String  name,
                         @RequestParam("brandName")String brandName,
                         @RequestParam("category")String category,
//                         @RequestParam("subCategory")String subCategory,
                         @RequestParam("cosmeticCode")Long productCode,
                         @RequestParam("price")Double price,
                         @RequestParam("productAbout")String productAbout)

    {
        cosmeticService.findById(id);
        cosmeticService.update(id,name,brandName,category,productCode,price,productAbout);
        System.out.println("bazada yenilendi");
        return "redirect:/admin/kosmetikalar";
    }
    @GetMapping("/admin/delete")
    public String delete(@RequestParam Long id){
        cosmeticService.deleteById(id);
        return "redirect:/admin/kosmetikalar";
    }
    @GetMapping("/admin/mehsul-elave-et")
    public String addProduct(){
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("name")String name,
                             @RequestParam("image") String image,
                             @RequestParam("brandName")String brandName,
                             @RequestParam("firstPrice")Double firstPrice,
                             @RequestParam("price")Double price,
                             @RequestParam("cosmeticCode") Long cosmeticCode,
                             @RequestParam("productAbout")String productAbout,
                             @RequestParam("category")String category,
                             @RequestParam("subCategory")String subCategory)
    {
        cosmeticService.add(name,brandName,cosmeticCode,firstPrice,price,productAbout,category,subCategory,image);
        return "redirect:/kosmetikalar";
    }

    @GetMapping("/admin/brendler")
    public String brands(Model model){

        model.addAttribute("brandNames",cosmeticService.brandNames());
        return "brandsAdmin";
    }

    @GetMapping("/admin/melumat-elave-et")
    public String melumatElaveEt(){
        return "addInformation";
    }

    @PostMapping("/admin/melumat-elave-et")
    public String melumatElaveEt(@RequestParam("title")String title,
                                 @RequestParam("content")String content,
                                 @RequestParam("url")String url,
                                 @RequestParam("image")MultipartFile image){
        informationService.addInformation(title,content,url,image);
        return "redirect:/admin/melumatlar";
    }
    @GetMapping("/admin/melumatlar")
    public String informationAdmin(Model model){
        Pageable paging = PageRequest.of(0,15);
        Page<Information> information=  informationRepository.findAll(paging);
        int totalPages = information.getTotalPages();
        int currentPage = 1;
        model.addAttribute("informationn",information);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        return "informationAdmin";
    }
    @GetMapping("/admin/ismariclar")
    public String messages(Model model){
        Pageable paging = PageRequest.of(0,15);
        Page<ContactUsMessage> messages=  contactUsMessageRepository.findAll(paging);
        int totalPages = messages.getTotalPages();
        int currentPage = 1;
        model.addAttribute("messages",messages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        return "messages";
    }
    @PostMapping("/sendMessage")
    public String sendMesage(@RequestParam("id")Long id,
                             @RequestParam("message")String message){
        contactUsMessageService.sendMessage(id,message);

        return "redirect:/admin/ismariclar";
    }
    @GetMapping("/admin/messages/page/{pageNumber}")
    public String messagePage(Model model, @PathVariable("pageNumber")int currentPage)
    {



        Pageable paging = PageRequest.of(currentPage-1,15);
        Page<ContactUsMessage> messages=  contactUsMessageRepository.findAll(paging);


        int totalPages = messages.getTotalPages();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("messages",messages);
        return "messages";

    }
    @GetMapping("/admin/sifarisler")
    public String messagePage(Model model)
    {
        Pageable paging = PageRequest.of(0,15);
        Page<OrderMesage> orders=  orderMessageRepository.findAll(paging);
        int totalPages = orders.getTotalPages();
        int currentPage = 1;
        model.addAttribute("orders",orders);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        return "orders";

    }
    @GetMapping("/admin/sifarisler/page/{pageNumber}")
    public String orderPage(Model model, @PathVariable("pageNumber")int currentPage)
    {



        Pageable paging = PageRequest.of(currentPage-1,15);
        Page<OrderMesage> orders=  orderMessageRepository.findAll(paging);


        int totalPages = orders.getTotalPages();

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("orders",orders);
        return "orders";

    }
    @GetMapping("/admin/sifarisler/catdi")
    public String messagePage(Model model,@RequestParam("id")Long id)
    {
        orderMessageService.findById(id).setStatus("Məhsul müstəriyə çatıb");
        orderMessageService.save(orderMessageService.findById(id));
        return "redirect:/admin/sifarisler";

    }
    @GetMapping("/login")
    public String login()
    {

        return "login";
    }


}
