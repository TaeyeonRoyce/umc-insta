package com.example.demo.src.user.jpa;

import java.util.List;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.jdbc.model.GetUserRes;
import com.example.demo.src.user.jdbc.model.PatchUserReq;
import com.example.demo.src.user.jdbc.model.PostLoginReq;
import com.example.demo.src.user.jdbc.model.PostLoginRes;
import com.example.demo.src.user.jdbc.model.PostUserReq;
import com.example.demo.src.user.jdbc.model.PostUserRes;

public class UserServiceJPA implements UserService {

	@Override
	public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
		return null;
	}

	@Override
	public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException {
		return null;
	}

	@Override
	public int checkEmail(String email) throws BaseException {
		return 0;
	}

	@Override
	public List<GetUserRes> getUsers() throws BaseException {
		return null;
	}

	@Override
	public List<GetUserRes> getUsersByNickname(String nickname) throws BaseException {
		return null;
	}

	@Override
	public GetUserRes getUser(int userIdx) throws BaseException {
		return null;
	}

	@Override
	public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {

	}
}
