package com.example.demo.src.user.jpa.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.utils.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTimeEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "userIdx")
	private Long userIdx;

	@Column(name = "name", length = 10, nullable = false)
	private String name;

	@Column(name = "nickName", length = 30, nullable = false)
	private String nickName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "pwd", nullable = false)
	private String pwd;

	@Column(name = "profileImgUrl")
	private String profileImgUrl;

	@Column(name = "gender")
	private String gender;

	@Column(name = "birth")
	private Date birth;

	@Column(name = "website")
	private String website;

	@Column(name = "introduction")
	private String introduction;

	@Column(name = "status")
	private String status;

	@Builder
	public User(Long userIdx, String name, String nickName, String email, String pwd, String profileImgUrl,
		String gender, Date birth, String website, String introduction, String status) {
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
	}
}


