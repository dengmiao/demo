package com.corgi.limit.core;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @description: 令牌桶算法
 * @author: dengmiao
 * @create: 2019-04-22 11:32
 **/
public class RateLimiter {

    /**
     * qps：每秒并发数、令牌桶容量
     */
    private long qps;
    /**
     * 间隔：1000/QPS
     */
    private long period;
    /**
     * 初始延迟时间：毫秒
     */
    private long initialDelay;
    /**
     * 令牌桶初始容量：0
     */
    private AtomicLong bucket = new AtomicLong(0);

    private RateLimiter(long qps, long initialDelay) {
        this.qps = qps;
        this.initialDelay = initialDelay;
        this.period = qps > 0 ? 1000 / qps : Integer.MAX_VALUE;
        scheduled();
    }

    public static RateLimiter of(long qps) {
        return new RateLimiter(qps, 0);
    }

    public static RateLimiter of(long qps, long initialDelay) {
        return new RateLimiter(qps, initialDelay);
    }

    /**
     * CAS获取令牌,阻塞直到成功
     */
    public boolean tryAcquire() {
        long l = bucket.longValue();
        while (!(l > 0 && bucket.compareAndSet(l, l - 1))) {
            l = bucket.longValue();
        }
        return true;
    }

    /**
     * CAS获取令牌,获取不到立即失败
     */
    public boolean tryAcquireFailed() {
        long l = bucket.longValue();
        while (l > 0) {
            if (bucket.compareAndSet(l, l - 1)) {
                return true;
            }
            l = bucket.longValue();
        }
        return false;
    }

    /**
     * 周期性放令牌，控制访问速率
     */
    private void scheduled() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (qps > bucket.longValue()) {
                    bucket.incrementAndGet();
                }
            }
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
