package com.yezhihun.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.yezhihun.demo.configuration.JedisProxy;
import com.yezhihun.demo.vo.response.BaseJsonVo;

/**
 * Created by tianye on 2018/5/3.
 */
@RestController
@RequestMapping("/upload/")
public class UploadImageController {
	private static Logger logger = LoggerFactory.getLogger(UploadImageController.class);

	@Autowired
	private JedisProxy jedisProxy;

//	@Value("${image_local_dir}")
	private String localPath;
//	@Value("${image_server_url}")
	private String serverPath;
//	@Value("${image_domain}")
	private String imageDomain;

	private static final List<String> ALLOW_TYPE = Arrays.asList(".bmp", ".png", ".gif", ".jpg", ".jpeg", ".pjpeg");

	private static final long MAX_IMG_SIZE = 5 * 1024 * 1024;

	@RequestMapping("image")
	public BaseJsonVo uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> data = new HashMap<>();
		if (!(request instanceof MultipartRequest)) {
			return BaseJsonVo.error("no MultipartRequest");
		}

		try {
			MultipartRequest multipartRequest = (MultipartRequest) request;
			MultiValueMap<String, MultipartFile> fileMap = multipartRequest.getMultiFileMap();
			for (Map.Entry<String, List<MultipartFile>> entry : fileMap.entrySet()) {
				for (MultipartFile file : entry.getValue()) {
					if (file.getSize() <= 0) {
						continue;
					}
					// 判断图片是否超过指定的大小
					if (file.getSize() > MAX_IMG_SIZE) {
						return BaseJsonVo.error("图片大小超过上限");
					}
					Pair<Boolean, String> uploadResult = uploadImage(file);
					if (!uploadResult.getLeft()) {
						return BaseJsonVo.error(uploadResult.getRight());
					}
					data.put("url", uploadResult.getRight());
					return BaseJsonVo.success(data);
				}
			}
		} catch (Exception e) {
			logger.error("上传图片异常", e);
		}
		return BaseJsonVo.error("上传失败");
	}

	private Pair<Boolean, String> uploadImage(MultipartFile file) throws IllegalStateException, IOException {

		return Pair.of(true, "上传成功");
	}

}
