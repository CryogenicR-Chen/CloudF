package com.cloud.event;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;


public class DeleteAbstractCanalEvent extends AbstractCanalEvent {

    public DeleteAbstractCanalEvent(Entry source) {
        super(source);
    }
}
