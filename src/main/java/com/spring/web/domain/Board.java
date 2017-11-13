package com.spring.web.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="com_bbs")
@SequenceGenerator(name="SEQ_BRDID_GENERATOR",
			sequenceName="SEQ_BRDID",
			initialValue=1,
			allocationSize=1)
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
				generator="")
	@Column(length=10)
	private Integer brdid;
	
	@Column(length=10, nullable=false)
	private Integer menuid;
	
	@Column(length=10)
	private Integer parbrdid;
	
	@Column(length=10)
	private Integer topbrdid;
	
	@Column(length=10)
	private Integer sitemenuid;
	
	@Column(length=5)
	private Integer depth;
	
	@Column(length=300)
	private String title;
	
	@Column(length=10)
	private Integer dataofferdept;
	
	@Column(length=50)
	private String ask;
	
	@Column
	private Date sdt;
	
	@Column
	private Date edt;
	
	@Column(length=10)
	private Integer categorycd;
	
	@Column(length=4000)
	private String contents;
	
	@Column(length=1)
	private String disyn;
	
	@Column
	private Integer viewcnt;
	
	@Column
	private Integer likecnt;
	
	@Column(length=18)
	private String ip;
	
	@Column(length=1)
	private String secretyn;
	
	@Column(length=50)
	private String pwd;
	
	@Column(length=50)
	private String email;
	
	@Column(length=1)
	private String emailrecvyn;
	
	@Column(length=20)
	private String tel;
	
	@Column(length=20)
	private String hp;
	
	@Column(length=200)
	private String url;
	
	@Column(length=20)
	private String urltarget;
	
	@Column(length=10)
	private String zipcd;
	
	@Column(length=100)
	private String addr1;
	
	@Column(length=100)
	private String addr2;
	
	@Column(length=10)
	private Integer ord;
	
	@Column(length=20)
	private String regid;
	
	@Column(length=20)
	private String regnm;
	
	@Column
	private Date regdt;
	
	@Column
	private Date moddt;
	
	@Column(length=1)
	private String topdisyn;
	

}
