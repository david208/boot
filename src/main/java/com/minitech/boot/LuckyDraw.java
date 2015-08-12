package com.minitech.boot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LuckyDraw {
	List<Long> list = new ArrayList<Long>();

	 Set<Long> set = new HashSet<Long>();

	java.util.Random ran = new java.util.Random();

	public static void main(String[] args) {
		LuckyDraw choujiang = new LuckyDraw();
		List<Long> ll = new ArrayList<Long>();
		for (long i = 0l; i < 1000000l; i++) {
			ll.add(i);
		}
		choujiang.start(ll);
		System.out.println(choujiang.set);
		System.out.println(choujiang.set.size());
	}

	public long shake(int size) {
		int base = ran.nextInt(size);
		Long winner = list.get(base);
		if (set.contains(winner))
			return shake(size);
		else
			return winner;
	}

	public void start(List<Long> ll) {
		list = ll;
		int size = list.size();
		if (!validate())
			throw new RuntimeException();
		for (int i = 0; i < 100; i++) {
			set.add(shake(size));
		}
	}

	//检查是否可以开奖，人数不足以及是否摇奖过了
	public boolean validate() {
		return true;
	}

}
