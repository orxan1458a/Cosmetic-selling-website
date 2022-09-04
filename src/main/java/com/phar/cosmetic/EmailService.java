package com.phar.cosmetic;

import com.phar.cosmetic.model.ContactUsMessage;
import com.phar.cosmetic.model.OrderMesage;
import com.phar.cosmetic.service.ContactUsMessageService;
import com.phar.cosmetic.service.CosmeticService;
import com.phar.cosmetic.service.OrderMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    JavaMailSender emailSender;
    @Autowired
    CosmeticService cosmeticService;
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    //    public void sendMessage(String to,String subject,String text){
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("orxan1458a@mail.ru");
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        this.emailSender.send(message );
//    }
    public void contactUSMailSender( String name, String surName, String to, String text,String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orxan1458a@mail.ru");
        message.setTo(to);
        message.setSubject(name+" "+ surName + " adlı izləyicidən ismarıc var.");
        message.setText(text+"\n\n"+"İzləyicinin elektron poçtu : "+email);
        this.emailSender.send(message);

    }
    public void orderMailSender(Long id,String name,String surName,String phone,String address, int count,String addressDetail){
        double price=cosmeticService.findById(id).getPrice();
        double total=price*count;

        SimpleMailMessage orderMessage = new SimpleMailMessage();
        orderMessage.setFrom("orxan1458a@mail.ru");
        orderMessage.setTo("orxan1458a@mail.ru");
        orderMessage.setSubject(name+" "+ surName + " adlı müştəridən sifariş var.");
        orderMessage.setText( "Ad Soyad: "+name+" "+surName+"\n\nÇatdırılma növü : "+address+ "\n\nÜnvan : "+addressDetail+"\n\nPhone :"+phone+"\n\nMəhsulun id-si : "+id+"\n\nQiyməti : "+price+" AZN"+"\n\nSayı : "+count+" ədəd    \n\nYekun məbləğ : "+total+" AZN"+"\n\nMəhsulun linki : "+"http://localhost:8080/kosmetikalar/info?id="+id);
        this.emailSender.send(orderMessage);

    }
    public void adminMessageSender(Long id,String message,String email){


        SimpleMailMessage adminMessage = new SimpleMailMessage();
        adminMessage.setFrom("orxan1458a@mail.ru");
        adminMessage.setTo(email);
        adminMessage.setSubject("Salam dəyərli müştəri");
        adminMessage.setText( message);
        this.emailSender.send(adminMessage);

    }
}
