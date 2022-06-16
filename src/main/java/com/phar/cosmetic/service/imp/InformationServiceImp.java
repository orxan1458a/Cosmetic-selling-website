package com.phar.cosmetic.service.imp;

import com.phar.cosmetic.model.Information;
import com.phar.cosmetic.repository.InformationRepository;
import com.phar.cosmetic.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class InformationServiceImp implements InformationService {
    @Autowired
    InformationRepository informationRepository;
    @Override
    public void addInformation(String title, String content, String url, MultipartFile image) {
        Information information=new Information();
        information.setTitle(title);
        information.setContent(content);
        information.setUrl(url);
        try {
            information.setImage(Base64.getEncoder().encodeToString(image.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String shortContent =stringSplit(content);

        information.setShortContent(shortContent);
        informationRepository.save(information);
    }

    @Override
    public String stringSplit(String content) {
        List<Character> list=new ArrayList<>();
        if(content.length()>150)
        {
            for(int i=0;i<140;i++)
            {

                list.add(content.charAt(i));
                //System.out.print( content.charAt(i));
            }
        }
        else for(int i=0;i<content.length();i++)
        {

            list.add(content.charAt(i));
            //System.out.print( content.charAt(i));
        }

        StringBuilder string=new StringBuilder();
        for(Character chr : list)
            string.append(chr);
        String result=string.toString();

        result=result+"[.]";
        return result;
    }

    @Override
    public List<Information> findAll() {
        return informationRepository.findAll();
    }

    @Override
    public Information findById(Long id) {
        return informationRepository.findById(id).get();
    }
}
