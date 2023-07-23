package com.cloud.event;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;


public class InsertAbstractCanalEvent extends AbstractCanalEvent {

    public InsertAbstractCanalEvent(Entry source) {
        super(source);
    }
}
