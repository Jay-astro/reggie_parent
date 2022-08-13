package com.reggie.controller.admin;

import com.reggie.constant.MessageConstant;
import com.reggie.result.R;
import com.reggie.utils.AliOSSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private AliOSSUtil aliOSSUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public R<String> upload(@RequestParam MultipartFile file) {
        log.info("文件上传:{}", file);
        String filePath = "";

        try {
            ImageIO.read(file.getInputStream());
            //获得原始文件名
            String originalFilename = file.getOriginalFilename();
            //文件后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //生成随机文件名
            String objectName = UUID.randomUUID().toString() + extension;
            filePath = aliOSSUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return  R.error(MessageConstant.UPLOAD_FAILED);
        }
        return R.success(filePath);
    }
}
