package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingObject;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * openai embedding格式返回适配器
 *
 * @author pdkst
 * @since 2024/05/13
 */
@Component
public class OpenaiEmbeddingResultAdapter implements EmbeddingResultAdapter {
    @Override
    public Object adapt(EmbeddingsResult result) {
        final EmbeddingsResponse response = toOpenaiResponse(result.getVector());
        response.setUsage(response.getUsage());
        return response;
    }

    private EmbeddingsResponse toOpenaiResponse(List<List<Float>> vectorList) {
        final EmbeddingsResponse response = new EmbeddingsResponse();
        response.setObject("list");
        response.setModel("text-embedding-ada-002");
        if (isEmpty(vectorList)) {
            return response;
        }
        List<EmbeddingObject> embeddingObjects = new ArrayList<>();
        int i = 0;
        for (List<Float> vector : vectorList) {
            final EmbeddingObject embeddingObject = buildEmbeddingObject(i++, vector);
            embeddingObjects.add(embeddingObject);
        }
        response.setData(embeddingObjects);
        return response;
    }

    private EmbeddingObject buildEmbeddingObject(int i, List<Float> vector) {
        final EmbeddingObject embeddingObject = new EmbeddingObject();
        embeddingObject.setIndex(i);
        embeddingObject.setEmbedding(vector);
        embeddingObject.setObject("embedding");
        return embeddingObject;
    }
}
