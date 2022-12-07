package com.github.bedrockecs.server;

/**
 * used to report exception and performs server shutdown
 */
public interface GlobalExceptionHook {
    void report(Throwable ex);
}
