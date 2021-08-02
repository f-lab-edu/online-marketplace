package com.coupang.marketplace.global.common;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

public class Timezone {

	@PostConstruct
	public void setTimezone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
