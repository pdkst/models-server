package io.github.pdkst.models.server.common;

import io.github.pdkst.models.http.listener.StreamEventListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@RequiredArgsConstructor
public class JsonLineFileResolver implements Closeable {
    private final BufferedReader reader;

    public JsonLineFileResolver(String file) {
        this(load(file));
    }

    public JsonLineFileResolver(InputStream stream) {
        final BufferedInputStream bis = new BufferedInputStream(stream);
        final InputStreamReader in = new InputStreamReader(bis);
        this.reader = new BufferedReader(in);
    }

    private static InputStream load(String file) {
        final InputStream stream = JsonLineFileResolver.class.getResourceAsStream(file);
        if (stream == null) {
            throw new IllegalArgumentException("file not found: " + file);
        }
        return stream;
    }


    /**
     * read stream and send line to listener
     */
    public void resolve(StreamEventListener listener) throws IOException {
        try {
            listener.onOpen(null);
            String line;
            while ((line = reader.readLine()) != null) {
                listener.onEvent(null, null, line);
                log.info(line);
            }
        } catch (Exception e) {
            listener.onFailure(e, null);
        } finally {
            listener.onClosed();
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
