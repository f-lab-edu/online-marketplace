package com.coupang.marketplace.address.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Address {

	private long id;
	private boolean main;
	private String name;
	private String content;
	private long userId;

	@Builder
	public Address(long id, boolean main, String name, String content, long userId){
		this.id = id;
		this.main = main;
		this.name = name;
		this.content = content;
		this.userId = userId;
	}
}