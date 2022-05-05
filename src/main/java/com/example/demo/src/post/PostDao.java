package com.example.demo.src.post;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.src.post.model.PostPostReq;
import com.example.demo.src.user.model.PostUserReq;

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

}
