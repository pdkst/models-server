package io.github.pdkst.models.server.common;

import io.github.pdkst.models.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@RequiredArgsConstructor
public class JsonFileResolver {
    private final String file;
    private final JsonMapper jsonMapper;

    public <T> T resolve(Class<T> tClass) throws IOException {
        final String content = load(file);
        return jsonMapper.parse(content, tClass);
    }

    private static String load(String file) throws IOException {
        final InputStream stream = JsonFileResolver.class.getResourceAsStream(file);
        if (stream == null) {
            throw new RuntimeException("not found");
        }
        try (BufferedInputStream bis = new BufferedInputStream(stream);
             final InputStreamReader reader = new InputStreamReader(bis);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }
}
