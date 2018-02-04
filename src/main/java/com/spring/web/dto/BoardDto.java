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
		@NotBlank(message="이름을 입력해주세요.")
		private String regnm;
		
		@NotBlank(message="비밀번호를 입력해주세요.")
		@Size(min = 4, message="4자이상으로 입력해주세요.")
		private String pwd;
		
		@NotBlank(message="메시지를 입력해주세요.")
		private String contents;
	}
	
	@Data
	public static class Update {
		@NotBlank(message="이름을 입력해주세요.")
		private String regnm;
		
		@NotBlank(message="비밀번호를 입력해주세요.")
		@Size(min = 4, message="4자이상으로 입력해주세요.")
		private String pwd;
		
		@NotBlank(message="메시지를 입력해주세요.")
		private String contents;
	}
	
	@Data
	public static class CheckPassword {
		
		@NotBlank(message="비밀번호를 입력해주세요.")
		@Size(min = 4, message="4자이상으로 입력해주세요.")
		private String pwd;
	}
	
	
}
