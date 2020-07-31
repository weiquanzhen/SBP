package com.sbp.app.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = -6450129977544676041L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 主键自增
	private Integer id;
	
	private String phone;
	private String username;
	private String email;
	private String password;
	
}
