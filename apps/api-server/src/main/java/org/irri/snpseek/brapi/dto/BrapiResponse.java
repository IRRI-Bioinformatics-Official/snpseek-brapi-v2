package org.irri.snpseek.brapi.dto;

/**
 * Generic BrAPI single-object response envelope:
 * <pre>
 * { "metadata": { ... }, "result": { ... } }
 * </pre>
 *
 * @param <T> result type
 */
public record BrapiResponse<T>(BrapiMetadata metadata, T result) {

    public static <T> BrapiResponse<T> of(T result) {
        return new BrapiResponse<>(BrapiMetadata.empty(), result);
    }
}
