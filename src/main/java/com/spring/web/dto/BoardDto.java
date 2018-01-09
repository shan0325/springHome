package com.spring.web.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.web.domain.File;

import lombok.Data;

public class BoardDto {
	
	//board 리스트
	@Data
	public static class Board {
		private Long brdid;
		private Integer menuid;
		private Integer parbrdid;
		private Integer topbrdid;
		private Integer depth;
		private String title;
		private String contents;
		private String regnm;
		private Date regdt;
		private String topdisyn;
		private List<File> files;
	}
	
	//boatd 상세보기
	@Data
	public static class BoardDetail {
		private Long brdid;
		private Integer menuid;
		private Integer parbrdid;
		private Integer topbrdid;
		private Integer depth;
		private String title;
		private String contents;
		private Integer viewcnt;
		private Integer likecnt;
		private String regnm;
		private Date regdt;
		private List<File> files;
	}
	
	@Data
	public static class Create {
		@NotBlank
		private String regnm;
		
		@NotBlank
		@Size(min = 4)
		private String pwd;
		
		@NotBlank
		private String contents;
	}
	
}
