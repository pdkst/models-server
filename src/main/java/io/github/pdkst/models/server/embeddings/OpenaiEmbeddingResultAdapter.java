package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.openai.api.common.Usage;
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
        final EmbeddingsResponse response = buildOpenaiResponse(result.getObjects());
        final Usage usage = buildOpenaiUsage(result.getUsage());
        response.setUsage(usage);
        return response;
    }

    private EmbeddingsResponse buildOpenaiResponse(List<EmbeddingData> vectorList) {
        final EmbeddingsResponse response = new EmbeddingsResponse();
        response.setObject("list");
        response.setModel("text-embedding-ada-002");
        if (isEmpty(vectorList)) {
            return response;
        }
        final List<EmbeddingObject> embeddingObjects = buildOpenaiEmbeddingObjects(vectorList);
        response.setData(embeddingObjects);
        return response;
    }

    private List<EmbeddingObject> buildOpenaiEmbeddingObjects(List<EmbeddingData> vectorList) {
        List<EmbeddingObject> embeddingObjects = new ArrayList<>();
        for (EmbeddingData data : vectorList) {
            final EmbeddingObject embeddingObject = buildOpenaiEmbeddingObject(data);
            embeddingObjects.add(embeddingObject);
        }
        return embeddingObjects;
    }

    private EmbeddingObject buildOpenaiEmbeddingObject(EmbeddingData data) {
        final EmbeddingObject embeddingObject = new EmbeddingObject();
        embeddingObject.setIndex(data.getIndex());
        embeddingObject.setEmbedding(data.getEmbedding());
        embeddingObject.setObject("embedding");
        return embeddingObject;
    }

    private Usage buildOpenaiUsage(EmbeddingUsage raw) {
        Usage usage = new Usage();
        usage.setPrompt_tokens(raw.getTotalTokens());
        usage.setTotal_tokens(raw.getTotalTokens());
        return usage;
    }
}
