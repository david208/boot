package com.minitech.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.minitech.boot.monitor.impl.NofityObserver;
import com.minitech.boot.ws.BroadcastService;

@Component
public class LogNofityObserver implements NofityObserver {

	@Autowired
	private BroadcastService broadcastService;

	@Override
	public void notifyDo() {
		broadcastService.broadcast("changed");

	}

}
