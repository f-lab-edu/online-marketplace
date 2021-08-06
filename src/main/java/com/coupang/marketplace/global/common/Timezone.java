package com.coupang.marketplace.global.common;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

public class Timezone {

	public static final String LocalTimezone = "UTC";

	@PostConstruct
	public void setTimezone() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
