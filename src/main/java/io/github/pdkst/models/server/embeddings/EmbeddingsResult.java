package io.github.pdkst.models.server.embeddings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author pdkst
 * @since 2024/05/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddingsResult {
    /**
     * 嵌入向量
     */
    private List<EmbeddingData> objects;
    /**
     * 使用情况
     */
    private EmbeddingUsage usage;
}
