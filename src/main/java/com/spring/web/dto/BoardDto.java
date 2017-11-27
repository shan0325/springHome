package com.spring.web.dto;

import java.util.Date;
import java.util.List;

import com.spring.web.domain.File;

import lombok.Data;

public class BoardDto {
	
	@Data
	public static class Board {
		private Long brdid;
		private Integer menuid;
		private Integer parbrdid;
		private Integer topbrdid;
		private Integer depth;
		private String title;
		private String contents;
		private Date regdt;
		private List<File> files;
	}
}
