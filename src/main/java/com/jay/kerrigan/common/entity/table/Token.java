package com.jay.kerrigan.common.entity.table;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "t_token")
public class Token {

	@Id
	private String token;
	private String host;
	private String userName;
	private Date createDate;
	private Date updateDate;

	public Token() {
	}

	public Token(String token, String host, String userName, Date createDate, Date updateDate) {
		super();
		this.token = token;
		this.host = host;
		this.userName = userName;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + ", host=" + host + ", userName=" + userName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
}
