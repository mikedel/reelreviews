package com.reelreviews.reelreviews;

import java.net.CookieHandler;

import android.app.Application;
import android.webkit.CookieManager;

public class ReelReviewApp extends Application {
//	private BasicHttpContext myHttpContext;
//	private BasicCookieStore myCookieStore;
//	
//	public BasicHttpContext getHttpContext() {
//		return myHttpContext;
//	}
//	public void setHttpContext(BasicHttpContext myHttpContext) {
//		this.myHttpContext = myHttpContext;
//	}
//	
//	public BasicCookieStore getCookieStore() {
//		return myCookieStore;
//	}
//	public void setCookieStore(BasicCookieStore myCookieStore) {
//		this.myCookieStore = myCookieStore;
//		myHttpContext.setAttribute(ClientContext.COOKIE_STORE, myCookieStore);
//	}
	private CookieManager cookieManager;

	public CookieManager getCookieManager() {
		return cookieManager;
	}

	public void setCookieManager(CookieManager cookieManager) {
		this.cookieManager = cookieManager;
		CookieHandler.setDefault(cookieManager);
	}
	
	
}
