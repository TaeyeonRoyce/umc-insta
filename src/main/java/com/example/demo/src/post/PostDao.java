package com.example.demo.src.post;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.src.post.model.GetPostRes;
import com.example.demo.src.post.model.PatchPostReq;
import com.example.demo.src.post.model.PostImgUrlRes;
import com.example.demo.src.post.model.PostLikeRes;
import com.example.demo.src.post.model.PostPostReq;

@Repository
public class PostDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired //readme 참고
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public int createPost(int userIdx, PostPostReq postPostreq) {
		String createPostQuery = "insert into post (content, user_idx) VALUES (?, ?)"; // 실행될 동적 쿼리문
		Object[] createPostParams = new Object[]{postPostreq.getContent(), userIdx}; // 동적 쿼리의 ?부분에 주입될 값
		this.jdbcTemplate.update(createPostQuery, createPostParams);

		String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값을 가져온다.
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
	}

	public void savePostImgUrl(int postIdx, String postImgUrl) {
		String createPostImgQuery = "insert into post_img_url (post_idx, img_url) VALUES (?, ?)"; // 실행될 동적 쿼리문
		Object[] createPostImgParams = new Object[]{postIdx, postImgUrl}; // 동적 쿼리의 ?부분에 주입될 값
		this.jdbcTemplate.update(createPostImgQuery, createPostImgParams);
	}

	public List<GetPostRes> getPostById(int userIdx) {
		String getPostsQuery = "select * from post where user_idx = ?";
		return this.jdbcTemplate.query(getPostsQuery,
			(rs, rowNum) -> new GetPostRes(
				rs.getInt("user_idx"),
				rs.getInt("post_idx"),
				rs.getString("content")// RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
		), userIdx); // 복수개의 회원정보들을 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보)의 결과 반환(동적쿼리가 아니므로 Parmas부분이 없음)
	}

	public List<PostImgUrlRes> getPostImgUrlsByPostIdx(int postIdx) {
		String getPostImgUrlQuery = "select * from post_img_url where post_idx = ?";
		return this.jdbcTemplate.query(getPostImgUrlQuery,
			(rs, rowNum) -> new PostImgUrlRes(
				rs.getString("img_url")// RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
			), postIdx);
	}

	public int getPostUserId(int userIdx) {
		String getPostUserIdQuery = "select * from post where post_idx = ?";
		Object[] findIdx = new Object[] {userIdx};
		return this.jdbcTemplate.update(getPostUserIdQuery, findIdx); //쿼리 요청(삭제했으면 1, 실패했으면 0)
	}

	public int updatePostContent(PatchPostReq req, int postIdx) {
		String modifyPostContentQuery = "update post set content = ? where post_idx = ? ";
		Object[] modifyPostContentParams = new Object[]{req.getContent(), postIdx};

		return this.jdbcTemplate.update(modifyPostContentQuery, modifyPostContentParams); // 대응시켜 매핑시켜 쿼리 요청(생성했으면 1, 실패했으면 0)
	}

	public PostLikeRes findPostLike(int postIdx, int userIdx) {
		try {
			String findPostLikeQuery = "select post_idx, user_idx from post_like where post_idx = ? and user_idx = ?";
			return this.jdbcTemplate.queryForObject(findPostLikeQuery,
				(rs, rowNum) -> new PostLikeRes(
					rs.getInt("post_idx"),
					rs.getInt("user_idx")// RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
				), postIdx, userIdx);
		} catch (IncorrectResultSizeDataAccessException e) {
			//Query 결과가 null인 경우
			return null;
		}
	}

	public void likePost(int postIdx, int userIdx) {
		String likePostQuery = "insert into post_like (post_idx, user_idx) VALUES (?, ?)";
		Object[] postLikeParams = new Object[]{postIdx, userIdx}; // 동적 쿼리의 ?부분에 주입될 값
		this.jdbcTemplate.update(likePostQuery, postLikeParams);
	}

	public void unlikePost(int postIdx, int userIdx) {
		String unlikePostQuery = "delete from post_like where post_idx = ? and user_idx = ?";
		Object[] postUnlikeParams = new Object[]{postIdx, userIdx}; // 동적 쿼리의 ?부분에 주입될 값
		this.jdbcTemplate.update(unlikePostQuery, postUnlikeParams);
	}


}
