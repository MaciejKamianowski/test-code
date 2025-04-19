package com.dsv.datafactory.file.extraction.processor;
// add empty new line between package name and imports
import com.dsv.datafactory.model.MetaData;
import com.dsv.datafactory.serde.MetaDataSerde;
import com.google.inject.Inject;
import com.dsv.datafactory.file.extraction.processor.domain.*;
import org.apache.kafka.common.serialization.Serdes;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.KeyValue;

public class ExtractionStream {

	// those two fields could be final
	// if we are not going to reassign them
	private Config config;

	private ExtractContent extractDocument;

	@Inject
	public ExtractionStream(Config config, ExtractContent extractDocument) {
		this.config = config;
		this.extractDocument = extractDocument;
	}

	public void createFrom(StreamsBuilder builder) {


		KStream<String, MetaData> stream = builder.stream(config.imageExtractionMetadataTopic,
				Consumed.with(Serdes.String(), new MetaDataSerde()));
      
      	// no error logging or handling
		// what if extractDocument.execute() will throw an exception ?

		KStream<String, MetaData> documentExtractions = stream.mapValues(extractDocument::execute);
		// use method references constistently
		// .filter((k, v) -> Objects.nonNull(v))
		KStream<String, MetaData> documentExtractionsFiltered = documentExtractions.filter((k, v) -> v != null);
		documentExtractionsFiltered.to(config.extractedDocumentTopic, Produced.with(Serdes.String(), new MetaDataSerde()));

		// my proposal for code
/*		KStream<String, MetaData> stream = builder.stream(
				config.imageExtractionMetadataTopic,
				Consumed.with(Serdes.String(), new MetaDataSerde())
		);

		KStream<String, MetaData> documentExtractions = stream.mapValues(value -> {
			try {
				return extractDocument.execute(value);
			} catch (Exception e) {
				// TODO: Replace with real logger
				System.err.println("Failed to extract: " + e.getMessage());
				return null;
			}
		});

		documentExtractions
				.filter((k, v) -> v != null)
				.to(config.extractedDocumentTopic, Produced.with(Serdes.String(), new MetaDataSerde()));*/


	}

}
