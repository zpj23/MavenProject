package com.zpj.concurrency;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zpj
 * @ClassName: UserRejectHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/7/3
 */

public class UserRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//        System.out.println("task rejected........"+executor.toString());
    }
}
