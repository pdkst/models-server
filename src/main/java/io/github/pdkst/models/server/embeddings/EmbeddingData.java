package io.github.pdkst.models.server.embeddings;

import lombok.Data;

import java.util.List;

/**
 * @author pdkst
 * @since 2024/05/17
 */
@Data
public class EmbeddingData {
    private Integer index;
    private List<Float> embedding;

    public EmbeddingData(Integer index, List<Float> embedding) {
        this.index = index;
        this.embedding = embedding;
    }
}
