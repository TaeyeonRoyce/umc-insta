package com.example.demo.src.post;

import static com.example.demo.config.BaseResponseStatus.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.post.model.GetPostRes;
import com.example.demo.src.post.model.GetPostResult;
import com.example.demo.src.post.model.PatchPostReq;
import com.example.demo.src.post.model.PostPostReq;
import com.example.demo.utils.JwtService;

@RestController
@RequestMapping("/app/posts")
public class PostController {

	final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log를 남기기: 일단은 모르고 넘어가셔도 무방합니다.

	// 객체 생성을 스프링에서 자동으로 생성해주는 역할. 주입하려 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
	// IoC(Inversion of Control, 제어의 역전) / DI(Dependency Injection, 의존관계 주입)에 대한 공부하시면, 더 깊이 있게 Spring에 대한 공부를 하실 수 있을 겁니다!(일단은 모르고 넘어가셔도 무방합니다.)
	// IoC 간단설명,  메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
	// DI 간단설명, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식

	@Autowired
	private final PostService postService;
	@Autowired
	private final JwtService jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!

	public PostController(PostService postService, JwtService jwtService) {
		this.postService = postService;
		this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
	}

	@ResponseBody
	@PostMapping("/post")
	public BaseResponse<String> addPost(@RequestBody PostPostReq req) {
		try {
			//jwt에서 idx 추출.
			int userIdxByJwt = jwtService.getUserIdx();
			
			//userIdx와 접근한 유저가 같은지 확인
			postService.addNewPost(userIdxByJwt, req);

			String result = "게시글이 등록되었습니다.";
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>((exception.getStatus()));
		}
	}

	@ResponseBody
	@GetMapping("/{userIdx}")
	public BaseResponse<List<GetPostResult>> getPostsByUser(@PathVariable int userIdx) {
		try {
			List<GetPostResult> allPostsById = postService.getAllPostsById(userIdx);
			return new BaseResponse<>(allPostsById);
		} catch (BaseException exception) {
			return new BaseResponse<>((exception.getStatus()));
		}
	}

	@ResponseBody
	@PatchMapping("/{postIdx}")
	public BaseResponse<String> modifyPostContent(@PathVariable("postIdx") int postIdx, @RequestBody PatchPostReq req) {
		try {
			//jwt에서 idx 추출.
			int userIdxByJwt = jwtService.getUserIdx();
			int postUserId = postService.getPostUserId(postIdx);

			//수정하려는 post의 userIdx(주인)이 Jwt와 다르면 예외
			if (postUserId != userIdxByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			postService.modifyPostContent(req, postIdx);

			String result = "게시글이 수정되었습니다.";
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>((exception.getStatus()));
		}
	}

}
