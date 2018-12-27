package com.jay.kerrigan.master.mapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import com.jay.kerrigan.common.entity.table.Token;

@Mapper
public interface TokenMapper {

	@SelectProvider(type = TokenProvider.class, method = "getByTokenAndUserName")
	Token getByUserNameAndHost(@Param("userName") String userName, @Param("host") String host);

	@SelectProvider(type = TokenProvider.class, method = "getByHostAndTokenId")
	Token getByHostAndTokenId(@Param("host") String host, @Param("tokenId") String tokenId);

	@InsertProvider(type = TokenProvider.class, method = "insertToken")
	int insertToken(Token token);

	@UpdateProvider(type = TokenProvider.class, method = "updateToken")
	int updateToken(Token token);

	@UpdateProvider(type = TokenProvider.class, method = "extendExpireTime")
	int extendExpireTime(Token token);

	@DeleteProvider(type = TokenProvider.class, method = "deleteToken")
	int deleteToken(Token token);

	class TokenProvider {

		public String getByTokenAndUserName(String userName, String host) {
			return new SQL().SELECT("*").FROM(Token.getTableName()).WHERE("user_name=#{userName}", "host=#{host}")
					.toString();
		}

		public String getByHostAndTokenId(String host, String tokenId) {
			return new SQL().SELECT("*").FROM(Token.getTableName()).WHERE("host = #{host}", "token_id = #{tokenId}")
					.toString();
		}

		public String insertToken(Token token) {
			return new SQL() {
				{
					INSERT_INTO(Token.getTableName());
					VALUES("token_id", "#{tokenId}");
					VALUES("host", "#{host}");
					VALUES("user_name", "#{userName}");
					VALUES("create_date", "#{createDate}");
					VALUES("update_date", "#{updateDate}");
				}
			}.toString();
		}

		public String updateToken(Token token) {
			return new SQL() {
				{
					UPDATE(Token.getTableName());
					SET("token_id=#{tokenId}");
					SET("update_date=#{updateDate}");
					WHERE("host=#{host}", "user_name=#{userName}");
				}
			}.toString();
		}

		public String extendExpireTime(Token token) {
			return new SQL() {
				{
					UPDATE(Token.getTableName());
					SET("update_date=#{updateDate}");
					WHERE("token_id=#{tokenId}");
				}
			}.toString();
		}

		public String deleteToken(Token token) {
			SQL sql = new SQL().DELETE_FROM(Token.getTableName());
			if (!StringUtils.isBlank(token.getTokenId())) {
				return sql.WHERE("token_id=#{tokenId}").toString();
			}
			sql.WHERE("host=#{host}");
			if (!StringUtils.isBlank(token.getUserName())) {
				sql.WHERE("user_name=#{userName}");
			}
			return sql.toString();
		}

	}

}
