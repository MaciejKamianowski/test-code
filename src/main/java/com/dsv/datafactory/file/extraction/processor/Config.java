package com.dsv.datafactory.file.extraction.processor;

import com.google.cloud.vision.v1.Feature;

// class name too generic
// should more descriptive for example KafkaConfig
public class Config {

	// the fields should be private, final and read only
	// to make it thread-safe and reliable
	public String imageExtractionMetadataTopic;
	public Feature feature;

	public String extractedDocumentTopic;		// we should have validation for the fields like @NotNull

	public String kafkaClientId;

	public String kafkaGroupId;

	public String kafkaAutoOffsetReset;

	public int kafkaCommitIntervalMs;

	public String enableKafkaSSL;

	public String kafkaBootstrapServers;

	public String kafkaMaxRequestSize;

	public String kafkaTruststorePath;

	public String kafkaTruststoreFile;

	public String kafkaTruststorePassword;

	public String kafkaSLLProtocol;		// typo kafkaSSLProtocol

	public String kafkaSSLCipher;

	public int kakfaPollIntervalMs;	// typo kafkaPollIntervalMs

	public int kafkaRequestTimeoutMs;

    public String lineServiceUrl;		// should be validated @Pattern

	public String startNumberOfClasses;

	public String enableRBAC;

	public String goodnessOfFit;		// what exactly goodnessOfFit means in this context

    public String googleCredPath;

    public String runGVInPararell;	// TYPO runGVInParallel
	// unclear what GV stands for
	// we should have a Confluence documentation provided what this variable represents
}
