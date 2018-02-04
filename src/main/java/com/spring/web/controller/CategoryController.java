package com.spring.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.domain.Category;
import com.spring.web.service.CategoryService;

@RestController
public class CategoryController {
	
	@Resource(name="categoryService")
	private CategoryService categoryService;

	@GetMapping("/categorys/{categorygrpcd}")
	public ResponseEntity getCategorys(@PathVariable Integer categorygrpcd) {

		List<Category> categorys = categoryService.getCategorys(categorygrpcd);
		
		return new ResponseEntity<>(categorys, HttpStatus.OK);
	}
}
