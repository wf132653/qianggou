package com.wf.qianggou.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池定义
 *
 * @author wf
 * @date 2020/05/09
 */
@Slf4j
public class ThreadPoolUtil {
    /**
     * 核心线程数量
     */
    private static final int CORE_POOL_SIZE = 8;
    /**
     * 线程最大数量
     */
    private static final int MAXI_MUN_POOL_SIZE = 8;

    /**
     * 创建出来的 MAXI_MUN_POOL_SIZE - CORE_POOL_SIZE 个线程的等待时间，达到该时间后就会被销毁
     */
    private static final int KEEP_ALIVE_TIME = 300;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    /**
     * 没有空闲线程时，任务会放到该队列中等待，等待的队列长度为500
     */
    private static final BlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(500);
    private static final ThreadFactory THREAD_FACTORY = new NameTreadFactory();
    private static final RejectedExecutionHandler HANDLER = new MyIgnorePolicy();
    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXI_MUN_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, WORK_QUEUE, THREAD_FACTORY, HANDLER);


    static class NameTreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            log.info("线程为 {} 的线程已成功被创建", t.getName());
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            log.info("{} {} 拒绝执行新的任务", r.toString(), e.toString());
        }
    }
}
