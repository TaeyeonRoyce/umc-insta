package com.example.demo.src.user.jpa.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "User", schema = "umcdb", catalog = "")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "userIdx")
	private int userIdx;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Basic(optional = false)
	@Column(name = "nickName")
	private String nickName;

	@Basic(optional = false)
	@Column(name = "email")
	private String email;

	@Basic(optional = false)
	@Column(name = "pwd")
	private String pwd;

	@Basic
	@Column(name = "profileImgUrl")
	private String profileImgUrl;

	@Basic
	@Column(name = "gender")
	private String gender;

	@Basic
	@Column(name = "birth")
	private Date birth;

	@Basic
	@Column(name = "website")
	private String website;

	@Basic
	@Column(name = "introduction")
	private String introduction;

	@Basic
	@Column(name = "status")
	private String status;

	@Basic
	@Column(name = "createdAt")
	private Timestamp createdAt;

	@Basic
	@Column(name = "updatedAt")
	private Timestamp updatedAt;

	@Builder
	public User(int userIdx, String name, String nickName, String email, String pwd, String profileImgUrl,
		String gender, Date birth, String website, String introduction, String status, Timestamp createdAt,
		Timestamp updatedAt) {
		this.userIdx = userIdx;
		this.name = name;
		this.nickName = nickName;
		this.email = email;
		this.pwd = pwd;
		this.profileImgUrl = profileImgUrl;
		this.gender = gender;
		this.birth = birth;
		this.website = website;
		this.introduction = introduction;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}


