package com.jack.md.table.test.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorUtils {
    // 使用Runtime获取CPU核心数来设置核心线程数等参数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, CPU_COUNT);
    private static final int MAXIMUM_POOL_SIZE = Math.min(CPU_COUNT * 2, 10);
    private static final int KEEP_ALIVE = 60;
    private static final BlockingQueue<Runnable> eventPoolWaitQueue = new LinkedBlockingQueue<>(256);

    private static final ThreadFactory eventThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "eventAsyncAndBackground #" + mCount.getAndIncrement());
        }
    };

    private static final ThreadPoolExecutor.CallerRunsPolicy eventHandler =
            new ThreadPoolExecutor.CallerRunsPolicy();

    private static ExecutorService eventExecutor;// = Executors.newFixedThreadPool(5);// = Executors.newCachedThreadPool(eventThreadFactory);
    static {
        eventExecutor =
                new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                        eventPoolWaitQueue, eventThreadFactory, eventHandler);
    }

    public static ExecutorService getExecutor() {
//        if (BuildConfig.DEBUG) {
//            Log.e("jack----", "-------> executor info: " + ((ThreadPoolExecutor) eventExecutor).toString());
//        }
//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        for (StackTraceElement element : stackTrace) {
//            Log.d("jack----", element.toString());
//        }
        return eventExecutor;
    }
}