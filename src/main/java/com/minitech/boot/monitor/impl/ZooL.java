package com.minitech.boot.monitor.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;

@Lazy(false)
public class ZooL implements InitializingBean {

	private ZooListener zooListener;

	private NofityObserver nofityObserver;

	public ZooListener getZooListener() {
		return zooListener;
	}

	public void setZooListener(ZooListener zooListener) {
		this.zooListener = zooListener;
	}

	public ZooL() {
		zooListener = new ZooListener();
	}

	public NofityObserver getNofityObserver() {
		return nofityObserver;
	}

	public void setNofityObserver(NofityObserver nofityObserver) {
		this.nofityObserver = nofityObserver;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		zooListener.setNofityObserver(nofityObserver);
		zooListener.monitor();

	}

}
