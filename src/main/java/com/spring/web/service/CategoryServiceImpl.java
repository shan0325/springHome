package com.spring.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.spring.web.domain.Category;
import com.spring.web.repository.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Resource(name="categoryRepository")
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getCategorys(Integer categorygrpcd) {
		
		return categoryRepository.findByCategorygrpcdAndUseyn(categorygrpcd, "Y");
	}

}
