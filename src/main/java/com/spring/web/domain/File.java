package com.spring.web.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="com_file")
public class File {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_FILEID_GENERATOR")
	@SequenceGenerator(name="SEQ_FILEID_GENERATOR", sequenceName="SEQ_FILEID", initialValue=1, allocationSize=1)
	private Long fileid;
	
	private Long brdid;
	
	private String realfilenm;
	
	private String renmfilenm;
	
	private String savepath;
	
	private Long filesize;
	
	private String filegubun;
	
	private Date regdt;
	
}
