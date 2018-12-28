package com.jay.kerrigan.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import org.springframework.util.FileSystemUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

	public static void closeStream(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				log.error("Stream close Exception: " + e.getMessage());
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
