package com.minitech.boot.monitor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

import com.minitech.boot.monitor.Listener;

public class ZooListener implements Listener {

	private ZooClient client = new ZooClient();
	
	private NofityObserver nofityObserver;

	private Map<String, Map<String, MonitorInfo>> nodeMap = new HashMap<String, Map<String, MonitorInfo>>();

	private PathChildrenCache rootCache;

	private List<PathChildrenCache> childCaches = new ArrayList<PathChildrenCache>();

	@Override
	public void monitor() {
		final CuratorFramework newClinet = client.getNewClinet();

		try {
			init(newClinet);
			rootCache = new PathChildrenCache(newClinet, "/", false);
			rootCache.start();
			rootCache.getListenable().addListener(new PathChildrenCacheListener() {

				@Override
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					init(newClinet);
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void init(final CuratorFramework newClinet) throws Exception {
		synchronized (nodeMap) {

			nodeMap.clear();
			for (PathChildrenCache cache : childCaches) {
				cache.close();
			}
			childCaches.clear();
			List<String> nodes = newClinet.getChildren().forPath("/");
			for (final String node : nodes) {
				List<String> childNodes = newClinet.getChildren().forPath("/" + node);
				PathChildrenCache childCache = new PathChildrenCache(newClinet, "/" + node, true);
				childCache.start();
				childCache.getListenable().addListener(new PathChildrenCacheListener() {

					@Override
					public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
						String node = event.getData().getPath().substring(1, 5);
						if (event.getType() == Type.CHILD_REMOVED || event.getType() == Type.CHILD_UPDATED) {
							if (nodeMap.get(node) == null) {
								Map<String, MonitorInfo> childMap = new HashMap<String, MonitorInfo>();
								nodeMap.put(node, childMap);
							}
							nodeMap.get(node).remove(StringUtils.substringBefore(StringUtils.substringAfterLast(event.getData().getPath(), "/"), "A"));
						}
						if (event.getType() == Type.CHILD_ADDED || event.getType() == Type.CHILD_UPDATED) {
							byte[] bytes = event.getData().getData();
							MonitorInfo value = (MonitorInfo) Object2Byte.bytesToObject(bytes);
							if (nodeMap.get(node) == null) {
								Map<String, MonitorInfo> childMap = new HashMap<String, MonitorInfo>();
								nodeMap.put(node, childMap);
							}
							nodeMap.get(node).put(StringUtils.substringBefore(StringUtils.substringAfterLast(event.getData().getPath(), "/"), "A"), value);
						}
						nofityObserver.notifyDo();
					}

				});
				childCaches.add(childCache);

				Map<String, MonitorInfo> childMap = new HashMap<String, MonitorInfo>();
				for (String childNode : childNodes) {
					byte[] bytes = newClinet.getData().forPath("/" + node + "/" + childNode);
					MonitorInfo value = (MonitorInfo) Object2Byte.bytesToObject(bytes);
					childMap.put(StringUtils.substringBefore(StringUtils.substringAfterLast("/" + node + "/" + childNode, "/"), "A"), value);
				}
				nodeMap.put(node, childMap);

			}
		}
	}

	public ZooClient getClient() {
		return client;
	}

	public void setClient(ZooClient client) {
		this.client = client;
	}

	public Map<String, Map<String, MonitorInfo>> getNodeMap() {
		return nodeMap;
	}

	public NofityObserver getNofityObserver() {
		return nofityObserver;
	}

	public void setNofityObserver(NofityObserver nofityObserver) {
		this.nofityObserver = nofityObserver;
	}

}
