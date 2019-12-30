package com.example.jetpack.workmanager;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.jetpack.utlis.Logger;

/**
 * Date: 2019-12-24
 * Author: 锅得铁
 * # 统计使用时间
 */
public class TimerWark extends Worker {
    private  Context context;
    public TimerWark(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context =context;
    }

    @NonNull
    @Override
    public Result doWork() {
        uploadTime();
        return Result.success();
    }

    private void uploadTime() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.d("模拟上传 使用时间");
    }
}
