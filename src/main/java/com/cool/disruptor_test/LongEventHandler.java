package com.cool.disruptor_test;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
		if (longEvent.getValue() % 10000000 == 0) {
			System.out.println(longEvent.getValue());
		}
	}
}
