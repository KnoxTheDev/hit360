package dev.knoxy.hit360;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.appender.ConsoleAppender.Target;

public class Hit360 implements ClientModInitializer {
	private static final Logger LOGGER = LogManager.getLogger("dev.knoxy.hit360");

	static {
		// Get the logger context and configuration
		LoggerContext context = (LoggerContext) LogManager.getContext(false);
		Configuration config = context.getConfiguration();

		// Define a custom layout
		PatternLayout layout = PatternLayout.newBuilder()
				.withPattern("[%d{HH:mm:ss}] [KNOXY/%level]: %msg%n")
				.build();

		// Create a new console appender with our layout
		ConsoleAppender appender = ConsoleAppender.newBuilder()
				.setName("KnoxyConsole")
				.setTarget(Target.SYSTEM_OUT)
				.setLayout(layout)
				.build();
		appender.start();

		// Register the new appender
		config.addAppender(appender);

		// Get the logger config for your mod
		LoggerConfig loggerConfig = config.getLoggerConfig("dev.knoxy.hit360");
		loggerConfig.addAppender(appender, Level.INFO, null);
		loggerConfig.setAdditive(false); // Prevent duplicate log messages

		// Apply changes
		context.updateLoggers();
	}

	public static void logStartupMessage() {
		String version = FabricLoader.getInstance()
				.getModContainer("hit360") // Ensure this matches your mod ID from fabric.mod.json
				.map(ModContainer::getMetadata)
				.map(metadata -> metadata.getVersion().getFriendlyString())
				.orElse("Unknown");

        LOGGER.info("\n\n═════════════════════════════════════════════\n\uD83C\uDFAF [Hit360 Fabric] – Target Locked & Spinning! \uD83C\uDFAF\n═════════════════════════════════════════════\n✅ Mod Loaded Successfully! \n⚙️ Version: {}\n\uD83D\uDE08 Activating the Susiest Hits Known to Man...\n\uD83E\uDD2B They Won’t Even Know What Hit ‘Em!\n═════════════════════════════════════════════\n", version);
	}

	@Override
	public void onInitializeClient() {
		logStartupMessage();
	}
}
