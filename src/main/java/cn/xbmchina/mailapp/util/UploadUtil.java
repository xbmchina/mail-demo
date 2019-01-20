package cn.xbmchina.mailapp.util;

import cn.xbmchina.mailapp.entity.UploadVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadUtil {

    private static String projectPath = StringUtils.substringBefore(System.getProperty("user.dir").replaceAll("\\\\", "/"),"/");

    /**
     * 自定义上传路径和下载路径进行上传
     * @param files 文件
     * @param uploadPath 上传到路径
     * @return
     * @throws Exception
     */
    public static Map<String, Object> upload(MultipartFile[] files, String uploadPath) throws Exception {
        Map<String, Object> retMap = new HashMap<>();
        if (files != null && files.length > 0) {
            List<UploadVo> fileList = new ArrayList<>();
            for (MultipartFile file : files) {
                UploadVo entity = new UploadVo();
                String fileName = file.getOriginalFilename();
                String path = projectPath + uploadPath + fileName;
                File dest = new File(path);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                file.transferTo(dest);
                entity.setName(fileName);
                entity.setUrl(fileName);
                fileList.add(entity);
            }
            retMap.put("data", fileList);
            retMap.put("status", true);
        } else {
            retMap.put("data", null);
            retMap.put("status", false);
        }
        return retMap;
    }
}
