package com.cool.disruptor_test;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory {

	@Override
	public Object newInstance() {
		// TODO Auto-generated method stub
		return new LongEvent();
	}
	
}
