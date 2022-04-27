package com.example.demo.src.user.jpa.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 해당 클래스에 대한 접근자 생성
@AllArgsConstructor // 해당 클래스의 모든 멤버 변수(email, password, nickname, profileImage)를 받는 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 해당 클래스의 파라미터가 없는 생성자를 생성, 접근제한자를 PROTECTED로 설정.
public class PostUserReq {
	private String email;
	private String password;
	private String nickname;
	private String name;

	public void saveSecuredPW(String pwd) {
		this.password = pwd;
	}

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.pwd(this.password)
			.nickName(this.nickname)
			.name(this.name)
			.build();
	}
}
