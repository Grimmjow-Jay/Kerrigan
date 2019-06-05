package com.jay.kerrigan.common.entity.table;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Token implements Serializable {
	private static final long serialVersionUID = -786034845151336814L;

	private String tokenId;
	private String host;
	private String userName;
	private Date createDate;
	private Date updateDate;

	public static String getTableName() {
		return "t_token";
	}

	public Token() {
	}

	public Token(String tokenId, String host, String userName, Date createDate, Date updateDate) {
		super();
		this.tokenId = tokenId;
		this.host = host;
		this.userName = userName;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

}
