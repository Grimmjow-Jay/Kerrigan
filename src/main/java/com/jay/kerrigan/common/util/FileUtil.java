package com.jay.kerrigan.common.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileSystemUtils;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static StringBuilder readContent(File file) throws IOException {
		BufferedReader reader = null;
		String line = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
			}
		} catch (IOException e) {
			logger.error("Read content file [" + file.getAbsolutePath() + "] Exception: " + e.getMessage());
			throw e;
		} finally {
			closeStream(reader);
		}
		return builder;
	}

	public static List<String> readLines(File file) throws IOException {
		BufferedReader reader = null;
		String line = null;
		List<String> lines = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			logger.error("Read content file [" + file.getAbsolutePath() + "] Exception: " + e.getMessage());
			throw e;
		} finally {
			closeStream(reader);
		}
		return lines;
	}

	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				logger.error("Stream close Exception: " + e.getMessage());
			} finally {
				stream = null;
			}
		}
	}

	public static boolean deleteRecursively(File file) {
		return FileSystemUtils.deleteRecursively(file);
	}

	public static boolean deleteRecursively(Iterable<File> files) {
		if (files == null) {
			return false;
		}

		boolean result = true;
		for (File file : files) {
			result = result && deleteRecursively(file);
		}
		return result;
	}
}
