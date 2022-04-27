package com.example.demo.src.post.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.utils.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class PostLike extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "postLikeIdx")
	private Long postLikeIdx;

	@Column(name = "userIdx")
	private Long userIdx;

	@Column(name = "postIdx")
	private Long postIdx;

	@Column(name = "status")
	private String status;

}
