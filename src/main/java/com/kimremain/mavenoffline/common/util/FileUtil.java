package com.kimremain.mavenoffline.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileUtil {

	public static void zipFiles(List<String> sourceFiles, String targetZipFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(targetZipFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : sourceFiles) {
            File fileToZip = new File(srcFile);
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }
	
	public static void unzipFile(String sourceFile, String targetPath) throws IOException {
		File destDir = new File(targetPath);
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(new FileInputStream(sourceFile));
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
			File newFile = newFile(destDir, zipEntry);
			if (zipEntry.isDirectory()) {
				if (!newFile.isDirectory() && !newFile.mkdirs()) {
					zis.close();
					throw new IOException("Failed to create directory " + newFile);
				}
			} else {
				// fix for Windows-created archives
				File parent = newFile.getParentFile();
				if (!parent.isDirectory() && !parent.mkdirs()) {
					zis.close();
					throw new IOException("Failed to create directory " + parent);
				}

				// write file content
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
			}
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());
	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
	    }
	    
	    return destFile;
	}	
	
	public static void copyFile(String sourcefile, String targetFile) throws IOException {
	    Path copied = Paths.get(targetFile);
	    Path originalPath = Paths.get(sourcefile);
	    Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
	}
	
	public static void moveFile(String sourcefile, String targetFile) throws IOException {
	    Path fileToMovePath = Paths.get(sourcefile);
	    Path targetPath = Paths.get(targetFile);
	    Files.move(fileToMovePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
	}
	
	public static void createFile(String source, String targetFile) throws IOException {
	    Path path = Paths.get(targetFile);
	    byte[] strToBytes = source.getBytes(StandardCharsets.UTF_8);
	    Files.write(path, strToBytes);
	}
	
	public static void createJsonFile(Object source, String targetFile) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File(targetFile), source);
	}
	
	public static <T> Object readJsonFile(String sourceFile, Class<T> cls) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new File(sourceFile), cls);
	}
	
	public static String readFile(String sourceFile) throws IOException {
		String result = "";
		List<String> lines = Files.readAllLines(Paths.get(sourceFile), StandardCharsets.UTF_8);
        for(String line : lines) {
        	result = result + line + System.lineSeparator();
        }
        //return String.join(System.lineSeparator(), lines);
        return result;
	}
	

	
}
