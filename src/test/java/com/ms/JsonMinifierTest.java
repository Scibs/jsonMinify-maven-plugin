package com.ms;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class JsonMinifierTest {

	private static final String SRC_DIR = "src/test/resources/";
	private static final String OUTPUT_DIR = "src/target/test/";

	@Test
	public void shouldReadJsonFileThenMinifyThenWrite() throws IOException {
		JsonMinifier.minifyJsonFilesInDir(SRC_DIR, OUTPUT_DIR);
		
		String result = new String(Files.readAllBytes(Paths.get(OUTPUT_DIR.concat("testFile.json"))));
		
		assertThat(result).isNotNull();
		assertThat(result).hasLineCount(1);
		
		//Assert no whitespace outiside of double quotes
		assertThat(result).doesNotContainPattern("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
	}

}
