package com.example.demo.src.follow.model;

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
public class FollowInfo extends BaseTimeEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "followIdx")
	private Long followIdx;

	@Column(name = "followerIdx")
	private Long followerIdx;

	@Column(name = "followeeIdx")
	private Long followeeIdx;

	@Column(name = "status")
	private String status;
}
