package com.enation.framework.cache;

import org.apache.log4j.Logger;


/**
 * 缓存代理抽象类
 * @author kingapex
 * <p>2009-12-16 下午05:19:50</p>
 * @version 1.0
 * @param <T>
 */
public abstract class AbstractCacheProxy<T> {
	protected final Logger logger = Logger.getLogger(getClass());

	protected ICache<T> cache;

	public AbstractCacheProxy(String cacheName) {
		cache = CacheFactory.getCache(cacheName);
	}
	
	
}
