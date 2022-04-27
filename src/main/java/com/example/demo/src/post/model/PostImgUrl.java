package com.example.demo.src.post.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.utils.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class PostImgUrl extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "postImgUrlIdx")
	private Long postImgUrlIdx;

	@Column(name = "postIdx")
	private Long postIdx;

	@Column(name = "imgUrl")
	private String imgUrl;

	@Column(name = "status")
	private String status;
}
