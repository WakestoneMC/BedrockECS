package com.github.bedrockecs;

/**
 * used to report exception and performs server shutdown
 */
public interface GlobalExceptionHook {
    void report(Throwable ex);
}
