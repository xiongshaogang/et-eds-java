package com.edaisong.edsservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.edaisong.api.service.impl.QuartzService;
import com.edaisong.core.util.QuartzManager;



/**
 * @author haichao
 *
 */

public class Main {
	public static ApplicationContext contentApp = new ClassPathXmlApplicationContext("applicationContext.xml");
	public static QuartzService service = contentApp.getBean(QuartzService.class);
	public static void main(String[] args) throws Exception {

		
		/* 加载定时任务 */
		writePID();// 生成PID

		try {
			String job_name = "任务调度主线程";
			System.out.println("【系统启动】开始(每1秒输出一次)...");
			QuartzManager.addJob(job_name,
					com.edaisong.edsservice.service.QuartzJob.class,
					"0/10 * * * * ?");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writePID() throws IOException {
		File f = new File("device.pid");
		OutputStreamWriter writer = new OutputStreamWriter(
				new FileOutputStream(f));
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		String pid = processName.substring(0, processName.indexOf("@"));
		writer.write(String.valueOf(pid));
		writer.flush();
		writer.close();
	}
}
