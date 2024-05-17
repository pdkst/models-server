package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * openai embedding格式返回适配器
 *
 * @author pdkst
 * @since 2024/05/13
 */
@Component
@RequiredArgsConstructor
public class EmbeddingsAdapter {
    private final Embeddings embeddings;

    public EmbeddingsResponse embeddings(EmbeddingsRequest request) throws Exception {
        return embeddings.embeddings(request);
    }
}
