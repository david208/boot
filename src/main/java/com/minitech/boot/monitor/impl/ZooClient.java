package com.minitech.boot.monitor.impl;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZooClient {

	private CuratorFramework newClinet;

	private String connectString = "localhost:2181";

	public ZooClient() {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		this.setNewClinet(CuratorFrameworkFactory.newClient(connectString+"/ZooMonitor", retryPolicy));
		newClinet.newNamespaceAwareEnsurePath("/ZooMonitor");
		newClinet.start();
	}

	public CuratorFramework getNewClinet() {
		return newClinet;
	}

	public void setNewClinet(CuratorFramework newClinet) {
		this.newClinet = newClinet;
	}

	public String getConnectString() {
		return connectString;
	}

	public void setConnectString(String connectString) {
		this.connectString = connectString;
	}

}
