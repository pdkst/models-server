package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/05/17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatCompletionsAdapter {
    private final ChatCompletions chatCompletions;

    public Object completion(CompletionRequest request) throws IOException {
        final Boolean stream = request.getStream();
        if (BooleanUtils.isFalse(stream)) {
            return chatCompletions.completionObject(request);
        } else {
            final SseEmitter emitter = new SseEmitter();
            chatCompletions.completionStream(request, new EmitterStreamEventListener(emitter));
            return emitter;
        }
    }
}
