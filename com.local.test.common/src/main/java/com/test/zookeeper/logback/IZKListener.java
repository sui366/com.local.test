package com.test.zookeeper.logback;

import org.apache.curator.framework.CuratorFramework;

public interface IZKListener {
    void executor(CuratorFramework client);
}