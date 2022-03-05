package com.param.bootbooks.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/3/5 6:07 PM
 */
@Slf4j
public class FileUtils {

    /**
     * static目录下的upload目录可自己建，也可不建。因为在上传的时候，会判断是否存在，若不存在便自动创建
     */
    private static final String prePath = System.getProperty("user.dir") + "/src/main/resources/static/upload/";

    /**
     * 上传文件
     *
     * @param file
     * @return 返回文件路径（以相对路径放回）
     */
    public static String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "";
        }
        // 获取原文件名
        String originFileName = file.getOriginalFilename();
        // 我们通过UUID 来重新重组文件名
        String uid = UUID.randomUUID().toString();
        assert originFileName != null;
        String suffix = originFileName.substring(originFileName.lastIndexOf('.') + 1);
        String path = prePath + uid + "." + suffix;
        String returnPath = "/upload/" + uid + "." + suffix;
        File newFile = new File(path);
        if (newFile.getParentFile() != null && !newFile.getParentFile().exists()) {
            log.info("创建目录ing");
            // 上面的 newFile.getParentFile() 已经保证了不为null.
            if (newFile.getParentFile().mkdirs()) {
                log.info("创建目录成功");
            } else {
                log.info("创建目录失败");
                return "";
            }
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return returnPath;
    }

}

