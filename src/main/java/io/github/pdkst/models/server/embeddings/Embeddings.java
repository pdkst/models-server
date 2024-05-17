package io.github.pdkst.models.server.embeddings;

import java.util.List;

/**
 * @author pdkst
 * @since 2024/05/13
 */
public interface Embeddings {

    /**
     * embedding the texts
     *
     * @param texts texts
     * @return vectors
     */
    EmbeddingsResult embeddings(List<String> texts);
}
