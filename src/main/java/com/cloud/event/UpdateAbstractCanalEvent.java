package com.cloud.event;

import com.alibaba.otter.canal.protocol.CanalEntry.Entry;


public class UpdateAbstractCanalEvent extends AbstractCanalEvent {

    public UpdateAbstractCanalEvent(Entry source) {
        super(source);
    }
}
