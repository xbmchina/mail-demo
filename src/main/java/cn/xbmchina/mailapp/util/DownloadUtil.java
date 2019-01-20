package cn.xbmchina.mailapp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DownloadUtil {

    /**
     * 按路径进行下载
     * @param path
     * @param request
     * @param response
     * @throws Exception
     */
    public static void download(String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            Path file = Paths.get(path);
            if (Files.exists(file)) {
                String contentType = Files.probeContentType(Paths.get(path));
                response.setContentType(contentType);
                String filename = new String(file.getFileName().toString().getBytes("UTF-8"), "ISO-8859-1");
                response.addHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
                response.setCharacterEncoding("UTF-8");
                Files.copy(file, response.getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}