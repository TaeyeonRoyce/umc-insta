package com.example.demo.src.comment.model;

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
public class Comment extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "commentIdx")
	private Long commentIdx;

	@Column(name = "postIdx")
	private Long postIdx;

	@Column(name = "userIdx")
	private Long userIdx;

	@Column(name = "content")
	private String content;

	@Column(name = "parentCommentIdx")
	private int parentCommentIdx;

	@Column(name = "status")
	private String status;
}
