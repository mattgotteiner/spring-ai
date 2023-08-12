package org.springframework.ai.openai.embedding;

import org.junit.jupiter.api.Test;
import org.springframework.ai.core.embedding.EmbeddingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmbeddingIntegrationTest {

	@Autowired
	private OpenAiEmbeddingClient embeddingClient;

	@Test
	void simpleEmbedding() {
		assertThat(embeddingClient).isNotNull();

		EmbeddingResult embeddingResult = embeddingClient.embed(List.of("Hello World"));
		System.out.println(embeddingResult);
		assertThat(embeddingResult.getData()).hasSize(1);
		assertThat(embeddingResult.getData().get(0).getEmbedding()).isNotEmpty();
		assertThat(embeddingResult.getMetadata()).containsEntry("model", "text-embedding-ada-002-v2");
		assertThat(embeddingResult.getMetadata()).containsEntry("completion-tokens", 0L);
		assertThat(embeddingResult.getMetadata()).containsEntry("total-tokens", 2L);
		assertThat(embeddingResult.getMetadata()).containsEntry("prompt-tokens", 2L);

	}

}
