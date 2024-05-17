package io.github.pdkst.models.server.embeddings;

/**
 * 结果适配器，将嵌入结果转换为另一种响应格式
 *
 * @author pdkst
 * @since 2024/05/13
 */
public interface EmbeddingResultAdapter {
    /**
     * 嵌入结果适配器
     *
     * @param result 嵌入结果
     * @return 另一种响应格式
     */
    Object adapt(EmbeddingsResult result);
}
