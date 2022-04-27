package com.example.demo.src.user;

import java.util.List;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.jpa.model.GetUserRes;
import com.example.demo.src.user.jpa.model.PatchUserReq;
import com.example.demo.src.user.jpa.model.PostLoginReq;
import com.example.demo.src.user.jpa.model.PostLoginRes;
import com.example.demo.src.user.jpa.model.PostUserReq;
import com.example.demo.src.user.jpa.model.PostUserRes;

public interface UserService {

	// 회원가입(POST)
	public PostUserRes createUser(PostUserReq postUserReq) throws BaseException;

	// 로그인(password 검사)
	public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException;

	// 해당 이메일이 이미 User Table에 존재하는지 확인
	public int checkEmail(String email) throws BaseException;

	// User들의 정보를 조회
	public List<GetUserRes> getUsers() throws BaseException;

	// 해당 nickname을 갖는 User들의 정보 조회
	public List<GetUserRes> getUsersByNickname(String nickname) throws BaseException;

	// 해당 userIdx를 갖는 User의 정보 조회
	public GetUserRes getUser(int userIdx) throws BaseException;


	// 회원정보 수정(Patch)
	public void modifyUserName(PatchUserReq patchUserReq) throws BaseException;
}
