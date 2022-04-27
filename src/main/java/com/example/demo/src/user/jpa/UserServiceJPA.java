package com.example.demo.src.user.jpa;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.List;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.jpa.model.GetUserRes;
import com.example.demo.src.user.jpa.model.PatchUserReq;
import com.example.demo.src.user.jpa.model.PostLoginReq;
import com.example.demo.src.user.jpa.model.PostLoginRes;
import com.example.demo.src.user.jpa.model.PostUserReq;
import com.example.demo.src.user.jpa.model.PostUserRes;
import com.example.demo.src.user.jpa.model.User;
import com.example.demo.utils.AES128;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

	private final UserRepository userRepository;

	@Override
	public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
		// 중복 확인: 해당 이메일을 가진 유저가 있는지 확인합니다. 중복될 경우, 에러 메시지를 보냅니다.
		if (checkEmail(postUserReq.getEmail()) == 1) {
			throw new BaseException(POST_USERS_EXISTS_EMAIL);
		}
		String pwd;
		try {
			// 암호화: postUserReq에서 제공받은 비밀번호를 보안을 위해 암호화시켜 DB에 저장합니다.
			// ex) password123 -> dfhsjfkjdsnj4@!$!@chdsnjfwkenjfnsjfnjsd.fdsfaifsadjfjaf
			pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(postUserReq.getPassword()); // 암호화코드
			postUserReq.saveSecuredPW(pwd);
		} catch (Exception ignored) { // 암호화가 실패하였을 경우 에러 발생
			throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
		}
		try {
			User savedUser = userRepository.save(postUserReq.toEntity());
			PostUserRes postUserRes = new PostUserRes(savedUser.getUserIdx());
			return postUserRes;

			//  *********** 해당 부분은 7주차 수업 후 주석해제하서 대체해서 사용해주세요! ***********
			//            //jwt 발급.
			//            String jwt = jwtService.createJwt(userIdx);
			//            return new PostUserRes(jwt,userIdx);
			//  *********************************************************************
		} catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
			throw new BaseException(DATABASE_ERROR);
		}
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
