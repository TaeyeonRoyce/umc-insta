package com.example.demo.src.user.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserRes {
	private int userIdx;
	private String nickname;
	private String email;
	private String password;


}