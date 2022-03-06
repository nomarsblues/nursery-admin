package com.aperture.nursery.admin.common.context;

public class SessionContext {
    private static final ThreadLocal<ContextParam> THREAD_LOCAL = new ThreadLocal<>();

    public static ContextParam get() {
        ContextParam contextParam = THREAD_LOCAL.get();
        if (contextParam == null) {
            contextParam = new ContextParam();
            THREAD_LOCAL.set(contextParam);
        }
        return contextParam;
    }

    public static void exit() {
        THREAD_LOCAL.remove();
    }
}
