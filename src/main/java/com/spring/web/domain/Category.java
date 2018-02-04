package com.spring.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="categorycd")
public class Category {
	
	private Integer categorygrpcd;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CATEGORYCD_GENERATOR")
	@SequenceGenerator(name="SEQ_CATEGORYCD_GENERATOR", sequenceName="SEQ_CATEGORYCD", initialValue=1, allocationSize=1)
	private Integer categorycd;
	private String categorynm;
	private String categorydesc;
	private String useyn;
	private Integer odr;
	private Date makedate;
	private Date modifydate;
}
