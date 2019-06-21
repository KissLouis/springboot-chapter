package com.springboot.controller;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.util.result.Result;
import com.springboot.util.result.ResultCode;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
public class UploadController {

	@Value("${web.upload-path}")
	@Getter
	@Setter
	private String uploadPath;

	@PostMapping("/upload")
	public Result Upload(@RequestParam("file") MultipartFile file, Model model) {
		if (file == null || file.isEmpty()) {
			return new Result(500, "请选择上传文件", false);
		}
		// 文件名
		String orinialName = file.getOriginalFilename();
		// 后缀
		String suffixName = orinialName.substring(orinialName.lastIndexOf("."));
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileNewName = fileName + suffixName;

		try {
			file.transferTo(new File(uploadPath + fileNewName));
			return new Result(ResultCode.SUCCESS, "filePath：" + uploadPath + fileNewName);
		} catch (Exception e) {
			// TODO: handle exception
			return new Result(500, e.getMessage(), false);
		}

	}

}
