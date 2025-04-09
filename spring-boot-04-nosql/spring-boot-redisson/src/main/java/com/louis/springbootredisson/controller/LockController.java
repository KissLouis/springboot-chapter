package com.louis.springbootredisson.controller;

import com.louis.springbootredisson.service.DistributedLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class LockController {

    @Autowired
    private DistributedLockService lockService;


    @GetMapping("/lock")
    public String testLock() {
        String lockKey = "myLock";
        try {
            if (lockService.tryLock(lockKey, 10, 30, TimeUnit.SECONDS)) {
                // 模拟业务逻辑
                Thread.sleep(5000);
                return "Lock acquired and processed";
            }
            return "Failed to acquire lock";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Error occurred";
        } finally {
            lockService.unlock(lockKey);
        }
    }
}