package com.atos.offer.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LogManager {

	public static void writeLogs(String toLog) throws IOException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		toLog = formatter.format(date) + " : " + toLog;

		List<String> lines = Arrays.asList(toLog);
		File file = new File("logs.txt");
		file.createNewFile();
		FileWriter myWriter = new FileWriter(file, true);
		myWriter.write(toLog + "\n");
		myWriter.close();
	}

}
