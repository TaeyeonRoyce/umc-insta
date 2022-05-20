package com.example.demo.src.post.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // 해당 클래스에 대한 접근자 생성
@Setter // 해당 클래스에 대한 설정자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(email, password)를 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeRes {
	private int postIdx;
	private int userIdx;
}
