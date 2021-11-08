package com.coupang.marketplace.address.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {

	private long id;
	private boolean isMain;
	private String name;
	private String content;
	private long userId;

	@Builder
	public Address(long id, boolean isMain, String name, String content, long userId){
		this.id = id;
		this.isMain = isMain;
		this.name = name;
		this.content = content;
		this.userId = userId;
	}
}