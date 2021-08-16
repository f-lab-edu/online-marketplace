package com.coupang.marketplace.auth;

import com.coupang.marketplace.global.constant.SessionKey;

public enum LoginType {

	USER{
		@Override
		public String getSessionKey(){
			return SessionKey.LOGIN_USER_ID;
		}
	};

	public abstract String getSessionKey();
}