package io.github.pdkst.models.server.chat;

import io.github.pdkst.models.openai.api.chat.request.CompletionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pdkst
 * @since 2023/12/31
 */
@Slf4j
@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class CompletionController {
    private final ChatCompletionsAdapter chatCompletionsAdapter;

    @PostMapping(value = "/completions")
    public Object chatCompletion(@RequestBody CompletionRequest request) throws Exception {
        return chatCompletionsAdapter.completion(request);
    }

}
