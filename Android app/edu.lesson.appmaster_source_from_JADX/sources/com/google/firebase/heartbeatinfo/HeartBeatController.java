package com.google.firebase.heartbeatinfo;

import com.google.android.gms.tasks.Task;

public interface HeartBeatController {
    Task<String> getHeartBeatsHeader();
}
