package com.dsv.datafactory.file.extraction.processor;

import com.dsv.datafactory.file.extraction.processor.logging.PackageLoggersConfig;
import com.dsv.datafactory.file.extraction.processor.logging.ECSLoggerProvider;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.dsv.datafactory.file.extraction.processor.modules.ConfigModule;
import com.dsv.datafactory.file.extraction.processor.modules.StreamModule;
import org.apache.kafka.streams.KafkaStreams;
import com.dsv.logger.ECSLogger;
import org.bytedeco.tesseract.TessBaseAPI;		// unused import

import java.util.concurrent.CountDownLatch;

public class App {
	// CountDownLatch is declared as volatile,
	// so it will be visible across threads GOOD
	public static volatile CountDownLatch latch = new CountDownLatch(1);
	// Logging with ECSLogger  is consistent and initialized properly. GOOD
	private static final ECSLogger logger = ECSLoggerProvider.getLogger(App.class.getName());
	// if you copy-paste this class and forget to update the class name,
	// it could lead to wrong logger names. BAD
	// better to use
	// private static final ECSLogger logger = ECSLoggerProvider.getLogger(App.class);
	public static void main(String[] args) {
		// redundant new line BAD
		App app = new App();
		app.start();
	}


	private void start()
	{	// the opening curly bracket should be on the same level, not below, for Java language	BAD
		// if that was C or C++ or C# code then it would be fine
		Module modules = Modules.combine(new ConfigModule(), new StreamModule());
		Injector injector = Guice.createInjector(modules);

		KafkaStreams streams = injector.getInstance(KafkaStreams.class);
		addShutdownHook(streams);

		PackageLoggersConfig.configure();


		try {

			streams.start();

			// PROPOSAL
			// more refine handling of runtime exceptions
			// streams.setUncaughtExceptionHandler((thread, throwable) -> {
			//    logger.error("Uncaught exception in Kafka Streams", throwable);
			//    latch.countDown(); // Optionally shut down app
			//});
			App.latch.await();
			shutdown(injector);


		}
		catch (InterruptedException e) {
			// redundant error logging
			e.printStackTrace();	// printing entire stack trace and logging error in the same time is redundant
			logger.error("Unexpected error", e);	// change to be more descriptive
			// e.g.
			// logger.error("Unexpected error in Kafka Streams execution", e);

			// PROPOSAL
			// restore interruption status
			// Thread.currentThread().interrupt();
		}

	}

	private void addShutdownHook(KafkaStreams stream) {
		Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
			@Override
			public void run() {
				stream.close();
				latch.countDown();
			}
		});
	}

	private void shutdown(Injector injector) {
		logger.info("Shutting down remaining resources.");
	}
	// shutdown logic is empty
	// When having Guice-managed lifecycle resources  e.g. DB clients, threads, services etc.
	// please consider calling their close()/shutdown() methods here.
}
