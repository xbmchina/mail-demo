package cn.xbmchina.mailapp.api;

import cn.xbmchina.mailapp.entity.MailBean;
import cn.xbmchina.mailapp.service.MailService;
import cn.xbmchina.mailapp.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;


    @RequestMapping("/send")
    public Map<String, Object> sendMail(@RequestBody MailBean mailBean) {
        Map<String, Object> restMap = new HashMap<>();
        try {
            mailService.sendMultiAttachmentsMail(mailBean);
//            mailService.sendSimpleMail(mailBean);
//            mailService.sendMailInline(mailBean);
//            mailService.sendMailTemplate(mailBean);

            restMap.put("status", 200);
            restMap.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
            restMap.put("status", 500);
            restMap.put("message", "error");
        }


        return restMap;
    }


    @RequestMapping("/upload")
    public Map<String, Object> uploadAttach(MultipartFile[] file, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> retMap = new HashMap<>();

        try {
            retMap = UploadUtil.upload(file, "/temp/");
        } catch (Exception e) {
            e.printStackTrace();
            retMap.put("data", null);
            retMap.put("status", false);
        }

        return retMap;
    }
}
