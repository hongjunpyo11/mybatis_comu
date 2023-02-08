package com.sbs.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sbs.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	public void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId, @Param("title") String title, @Param("body") String body);
	
	@Select("""
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId = M.id
			WHERE 1
			AND A.id = #{id}
			""")
	public Article getForPrintArticle(@Param("id") int id);

	public void deleteArticle(int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	@Select("""
			<script>
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId = M.id
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			ORDER BY A.id DESC
			</script>
			""")
	public List<Article> getArticles(@Param("boardId") int boardId);
	
	public int getLastInsertId();
	
	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM article AS A
			WHERE 1
			<if test="boardId != 0">
				AND A.boardId = #{boardId}
			</if>
			</script>
			""")
	public int getArticlesCount(@Param("boardId") int boardId);
}

/*
 * getForPrintArticle => 조인을해서 추가정보를 가져온다 현재 로그인한 회원 번호 가져와서 그 회원이 게시글을 수정이나 삭제를 할 수 있는지 정보까지 제공
 * getArticle => 단순히 select * from article
 */