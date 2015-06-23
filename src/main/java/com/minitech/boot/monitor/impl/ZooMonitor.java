package com.minitech.boot.monitor.impl;

import org.springframework.context.annotation.Lazy;

@Lazy(false)
public class ZooMonitor {
	private ZooRegister zooRegister;

	public ZooRegister getZooRegister() {
		return zooRegister;
	}

	public void setZooRegister(ZooRegister zooRegister) {
		this.zooRegister = zooRegister;
	}

	public ZooMonitor() {
		zooRegister = new ZooRegister();
		zooRegister.register();
	}

}
