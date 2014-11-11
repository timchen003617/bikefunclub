package com.bikefunclub.log;

import java.io.IOException;
import java.util.*;
import org.apache.log4j.*;

public class log4j {
	static Logger logger;
	public static void main(String args[]){
		//取得properties物件
		Properties logp = new Properties();
		try {
			logp.load(log4j.class.getClassLoader().getResourceAsStream("log4j.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//動態配置一個參數，這裡是一個properties文件所在地
		PropertyConfigurator.configure(logp);
		logger = Logger.getRootLogger();
		//等級：debug < info < warn < error <fatal
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		logger.fatal("fatal");
	}
}
