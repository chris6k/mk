package com.jk.makemoney.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author kun
 */
public class ThreadPool {
    private ExecutorService executorService;

    public ThreadPool() {
        executorService = Executors.newCachedThreadPool();
    }

    public static ThreadPool getInstance() {
        return ThreadPoolGen.instance;
    }

    public void exec(Runnable runnable) {
        executorService.execute(runnable);
    }

    public <V> Future<V> submit(Callable<V> callable) {
        return executorService.submit(callable);
    }


    private static final class ThreadPoolGen {
        private static ThreadPool instance;

        static {
            instance = new ThreadPool();
        }
    }
}
