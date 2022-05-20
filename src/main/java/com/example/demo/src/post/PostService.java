package com.example.demo.src.post;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.GetPostRes;
import com.example.demo.src.post.model.GetPostResult;
import com.example.demo.src.post.model.PatchPostReq;
import com.example.demo.src.post.model.PostImgUrlRes;
import com.example.demo.src.post.model.PostLikeRes;
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

	public List<GetPostResult> getAllPostsById(int userIdx) throws BaseException {
		try {
			List<GetPostRes> postById = postDao.getPostById(userIdx);
			List<GetPostResult> postResults = new ArrayList<>();
			for (GetPostRes getPostRes : postById) {
				List<PostImgUrlRes> postImgUrlsByPostIdx = postDao.getPostImgUrlsByPostIdx(getPostRes.getPostIdx());
				postResults.add(getPostRes.toGetPostResult(postImgUrlsByPostIdx));
			}

			return postResults;
		} catch (Exception exception) {
			System.out.println(exception);
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public int getPostUserId(int postIdx) throws BaseException {
		try {
			int isSuccess = postDao.getPostUserId(postIdx);
			if (isSuccess == 1) {
				return postIdx;
			}
			throw new Exception();
		} catch (Exception exception) {
			System.out.println(exception);
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public void modifyPostContent(PatchPostReq req, int postIdx) throws BaseException {
		try {
			int result = postDao.updatePostContent(req, postIdx); // 해당 과정이 무사히 수행되면 True(1), 그렇지 않으면 False(0)입니다.
			if (result == 0) { // result값이 0이면 과정이 실패한 것이므로 에러 메서지를 보냅니다.
				throw new BaseException(MODIFY_FAIL_USERNAME);
			}
		} catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public String togglePost(int postIdx, int userIdx) throws BaseException {
		try {
			PostLikeRes postLike = postDao.findPostLike(postIdx, userIdx);
			if (postLike == null) {
				postDao.likePost(postIdx, userIdx);
				return "좋아요 반영";
			}

			postDao.unlikePost(postIdx, userIdx);
			return "좋아요 해제";
		} catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
			System.out.println(exception);
			throw new BaseException(DATABASE_ERROR);
		}
	}
}
