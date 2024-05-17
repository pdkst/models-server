package io.github.pdkst.models.server.embeddings;

import lombok.Data;

/**
 * @author pdkst
 * @since 2024/05/17
 */
@Data
public class EmbeddingUsage {
    /**
     * The number of tokens in the embedding.
     */
    private Integer totalTokens;

    public EmbeddingUsage(Integer totalTokens) {
        this.totalTokens = totalTokens;
    }
}
