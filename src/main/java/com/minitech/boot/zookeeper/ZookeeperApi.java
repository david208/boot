package com.minitech.boot.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

public class ZookeeperApi implements Watcher {

	private static CountDownLatch countDownLatch = new CountDownLatch(1);
	private static ZooKeeper keeper;
	private static Stat stat = new Stat();

	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		String path = "/zk-book";
		keeper = new ZooKeeper("172.16.200.90:2181,172.16.230.190:2181,172.16.230.190:2182", 2000, new ZookeeperApi());
		countDownLatch.await();
		if (null != (stat = keeper.exists(path, false)))
			keeper.delete(path, stat.getVersion());
		keeper.create(path, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		keeper.getChildren(path, true);
		keeper.create(path + "/test1", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		Thread.sleep(Integer.MAX_VALUE);
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			if (EventType.None == event.getType() && null == event.getPath()) {
				countDownLatch.countDown();
			} else if (event.getType() == EventType.NodeChildrenChanged) {
				try {
					System.out.println("Reget child:" + keeper.getChildren(event.getPath(), true));
				} catch (Exception e) {
				}
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		}

	}

}
