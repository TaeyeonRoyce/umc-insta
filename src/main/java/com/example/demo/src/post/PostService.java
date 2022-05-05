package com.example.demo.src.post;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.demo.config.BaseException;
import com.example.demo.src.post.model.PostPostReq;
import com.example.demo.utils.JwtService;

@Service
public class PostService {

	final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

	// *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
	private final PostDao postDao;
	private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

	@Autowired //readme 참고
	public PostService(PostDao postDao, JwtService jwtService) {
		this.postDao = postDao;
		this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

	}


	public void addNewPost(int userIdx, PostPostReq req) throws BaseException {
		try {
			int postIdx = postDao.createPost(userIdx, req);
			List<String> postImgUrls = req.getPostImgUrls();

			//Post Img 저장
			for (String postImgUrl : postImgUrls) {
				postDao.savePostImgUrl(postIdx, postImgUrl);
			}
		} catch (Exception exception) {
			System.out.println(exception);
			throw new BaseException(DATABASE_ERROR);
		}

	}
}
