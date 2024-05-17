package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.openai.api.common.Usage;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingObject;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import io.github.pdkst.models.server.common.JsonFileResolver;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pdkst
 * @since 2024/04/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileOpenaiEmbeddings implements Embeddings {
    private final JacksonMapper mapper = JacksonMapper.getInstance();

    @Override
    @SneakyThrows
    public EmbeddingsResult embeddings(List<String> texts) {
        final JsonFileResolver resolver = new JsonFileResolver("/out/embeddings/example.json", mapper);
        final EmbeddingsResponse response = resolver.resolve(EmbeddingsResponse.class);
        return mapToResult(response);
    }

    private EmbeddingsResult mapToResult(EmbeddingsResponse response) {
        final List<EmbeddingData> objects = this.buildDataList(response.getData());
        final EmbeddingUsage usage = this.buildUsage(response.getUsage());
        return new EmbeddingsResult(objects, usage);
    }

    @NotNull
    private List<EmbeddingData> buildDataList(List<EmbeddingObject> objects) {
        if (objects == null) {
            return Collections.emptyList();
        }
        List<EmbeddingData> list = new ArrayList<>();
        for (EmbeddingObject datum : objects) {
            List<Float> embedding = datum.getEmbedding();
            list.add(new EmbeddingData(datum.getIndex(), embedding));
        }
        return list;
    }

    private EmbeddingUsage buildUsage(Usage usage) {
        return new EmbeddingUsage(usage.getTotal_tokens());
    }
}
