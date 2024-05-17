package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.http.listener.StreamEventListener;
import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import io.github.pdkst.models.openai.api.chat.response.CompletionResponse;
import io.github.pdkst.models.server.common.JsonFileResolver;
import io.github.pdkst.models.server.common.JsonLineFileResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileOpenaiChatCompletions implements ChatCompletions {
    private final JacksonMapper mapper = JacksonMapper.getInstance();

    @Override
    public CompletionResponse completionObject(CompletionRequest request) throws IOException {
        final JsonFileResolver resolver = new JsonFileResolver("/out/chat/chat_completion_example.json", mapper);
        return resolver.resolve(CompletionResponse.class);
    }

    @Override
    public void completionStream(CompletionRequest request,
                                 StreamEventListener listener) throws IOException {
        final JsonLineFileResolver resolver = new JsonLineFileResolver("/out/chat/stream_chat_completion.jsonl");
        resolver.resolve(listener);
    }
}
