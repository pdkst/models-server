package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/05/17
 */
public interface ChatCompletions {
    CompletionResponse completionObject(CompletionRequest request) throws IOException;

    void completionStream(CompletionRequest request, StreamEventListener listener) throws IOException;
}
