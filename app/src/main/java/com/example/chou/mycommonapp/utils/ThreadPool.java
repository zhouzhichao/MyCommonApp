package com.example.chou.mycommonapp.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by txj on 16/7/23.
 * 线程池
 */
public class ThreadPool {
    /**
     * POOL_SIZE的值为new Thread(Runnable r)的使用次数
     */
    public static final int POOL_SIZE = 8;
    private static ExecutorService pool;

    public static void init() {
        pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
    }

    public synchronized static void execute(Runnable runnable) {
        if (pool == null) {
            init();
        }
        pool.execute(runnable);
    }

    public static ExecutorService getPool() {
        return pool;
    }

    public static int getPoolSize() {
        return Runtime.getRuntime().availableProcessors() * POOL_SIZE;
    }
}
