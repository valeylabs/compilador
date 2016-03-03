package io.valey.compilador.log;

public class Logger {
	public enum Level {
		DEBUG
	}

	private static void log(Level level, Object message) {
		message = message == null ? "" : message;
		System.out.println("[" + level.toString() + "] " + message.toString());
	}

	public static void d(Object message) {
		log(Level.DEBUG, message);
	}
}
