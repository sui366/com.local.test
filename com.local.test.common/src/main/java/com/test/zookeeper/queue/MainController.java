package com.test.zookeeper.queue;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final CuratorFramework zkClient;
    //通过Spring注入CuratorFramework实例
    public MainController(final CuratorFramework zkClient) {
        Assert.notNull(zkClient, "zkClient cannot be null");
        this.zkClient = zkClient;
    }

    //简单的使用传递值来做数据处理的实体
    @RequestMapping("/put/{val}")
    @ResponseBody
    public String put(@PathVariable String val) throws Exception {
        //需要使用特定的格式来添加数据到队列，使用ItemSerializer来做格式化生成byte。
        byte[] bytes = ItemSerializer.serialize(val, new QueueItemSerializer());
        String path = "" ;    

        //创建znode并添加数据
        path = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/zk_queue_test/queue-");
        zkClient.setData().forPath(path, bytes);
        return path;
    }

}