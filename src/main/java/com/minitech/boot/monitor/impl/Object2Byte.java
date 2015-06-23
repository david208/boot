package com.minitech.boot.monitor.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Object2Byte {

	public static byte[] objectToBytes(Object obj) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream sOut = new ObjectOutputStream(out);
		sOut.writeObject(obj);
		sOut.flush();
		byte[] bytes = out.toByteArray();
		out.close();
		sOut.close();
		return bytes;
	}

	/**
	 * 字节数组转对象
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static Object bytesToObject(byte[] bytes) throws Exception {

		// byte转object
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ObjectInputStream sIn = new ObjectInputStream(in);
		return sIn.readObject();

	}

	public static InetAddress getIP() throws UnknownHostException {

		return InetAddress.getLocalHost();
	}
}
