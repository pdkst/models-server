package io.github.pdkst.models.server.embeddings;

/**
 * @author pdkst
 * @since 2024/05/13
 */
public interface EmbeddingResultAdapter {
    Object adapt(EmbeddingsResult result);
}
