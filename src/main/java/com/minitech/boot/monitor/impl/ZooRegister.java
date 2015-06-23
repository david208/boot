package com.minitech.boot.monitor.impl;

import java.net.InetAddress;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.util.Assert;

import com.minitech.boot.monitor.PropertyReader;
import com.minitech.boot.monitor.Register;

public class ZooRegister implements Register {

	private ZooClient client = new ZooClient();

	private String systemCode = "";

	@Override
	public void register() {
		PropertyReader propertyReader = new PropertyReader();
		String systemCode = (propertyReader.getProperty("system.code"));
		Assert.notNull(systemCode);
		CuratorFramework newClinet = client.getNewClinet();
		MonitorInfo data = new MonitorInfo();
		data.setSystemCode(systemCode);
		String path = "/" + systemCode.substring(0, 4) + "/" + systemCode+"A";// 0000000000
		try {
			InetAddress addr = Object2Byte.getIP();
			String ip = addr.getHostAddress();// 获得本机IP
			String address = addr.getHostName();// 获得本机名称
			data.setIp(ip);
			data.setAddress(address);
			byte[] d = Object2Byte.objectToBytes(data);
			newClinet.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, d);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ZooClient getClient() {
		return client;
	}

	public void setClient(ZooClient client) {
		this.client = client;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

}
