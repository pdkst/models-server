package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;

/**
 * @author pdkst
 * @since 2024/05/13
 */
public interface Embeddings {

    /**
     * embedding the texts
     *
     * @param request texts
     * @return vectors
     */
    EmbeddingsResponse embeddings(EmbeddingsRequest request);
}
