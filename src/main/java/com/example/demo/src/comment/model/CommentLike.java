package com.example.demo.src.comment.model;

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
public class CommentLike extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "commentLikeIdx")
	private Long commentLikeIdx;

	@Column(name = "commentIdx")
	private Long commentIdx;

	@Column(name = "userIdx")
	private Long userIdx;

	@Column(name = "status")
	private String status;
}
