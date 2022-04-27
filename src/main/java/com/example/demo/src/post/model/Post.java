package com.example.demo.src.post.model;

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
public class Post extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "postIdx")
	private Long postIdx;

	@Column(name = "userIdx")
	private Long userIdx;

	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private String status;
}
