package com.minitech.boot.monitor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
	private static Logger logger = LoggerFactory.getLogger(PropertyReader.class);

	private Properties prop;

	public PropertyReader() {
		this.prop = new Properties();

		try {

			if (null != System.getProperty("system.code"))
				prop = System.getProperties();
			else {
				InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream("application.properties");
				this.prop.load(in);
				logger.info(prop.getProperty("system.code").toString());
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

	public void addProperty(String key, String value) {
		prop.put(key, value);
	}
}