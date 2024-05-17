package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.common.Usage;
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
    private List<List<Float>> vector;
    private Usage usage;
}
