package com.spring.web.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="com_bbs")
public class Board {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BRDID_GENERATOR")
	@SequenceGenerator(name="SEQ_BRDID_GENERATOR", sequenceName="SEQ_BRDID", initialValue=1, allocationSize=1)
	@Column(length=10)
	private Long brdid;
	
	@Column(length=10, nullable=false)
	private Integer menuid;
	
	@Column(length=10)
	private Integer parbrdid;
	
	@Column(length=10)
	private Integer topbrdid;
	
	@Column(length=10)
	private Integer sitemenuid;
	
	@Column(length=5)
	private Integer depth = 1;
	
	@Column(length=300)
	private String title;
	
	@Column(length=10)
	private Integer dataofferdept = 0;
	
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
	private String disyn = "Y";
	
	@Column
	private Integer viewcnt = 0;
	
	@Column
	private Integer likecnt = 0;
	
	@Column(length=18)
	private String ip;
	
	@Column(length=1)
	private String secretyn = "N";
	
	@Column(length=50)
	private String pwd;
	
	@Column(length=50)
	private String email;
	
	@Column(length=1)
	private String emailrecvyn = "N";
	
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
	private Integer ord = 0;
	
	@Column(length=20)
	private String regid;
	
	@Column(length=20)
	private String regnm;
	
	@Column
	private Date regdt;
	
	@Column
	private Date moddt;
	
	@Column(length=1)
	private String topdisyn = "N";
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="brdid")
	private List<File> files;

}
