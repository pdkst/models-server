package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.http.HttpResponse;
import io.github.pdkst.models.http.listener.StreamEventListener;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@RequiredArgsConstructor
public class EmitterStreamEventListener implements StreamEventListener {
    private final SseEmitter emitter;

    @Override
    public void onOpen(HttpResponse httpResponse) {

    }

    @Override
    public void onEvent(String id, String type, String data) {
        try {
            emitter.send(SseEmitter.event().data(data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClosed() {
        emitter.complete();
    }

    @Override
    public void onFailure(@Nullable Throwable t, @Nullable HttpResponse httpResponse) {
        emitter.completeWithError(t);
    }
}
