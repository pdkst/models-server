package io.github.pdkst.models.server.embeddings;

import io.github.pdkst.models.json.JacksonMapper;
import io.github.pdkst.models.openai.api.embeddings.request.EmbeddingsRequest;
import io.github.pdkst.models.openai.api.embeddings.response.EmbeddingsResponse;
import io.github.pdkst.models.server.common.JsonFileResolver;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public EmbeddingsResponse embeddings(EmbeddingsRequest request) {
        final JsonFileResolver resolver = new JsonFileResolver("/out/embeddings/example.json", mapper);
        return resolver.resolve(EmbeddingsResponse.class);
    }
}
