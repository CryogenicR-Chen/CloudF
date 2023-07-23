package com.cloud.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.cloud.event.AbstractCanalEvent;
import com.google.protobuf.InvalidProtocolBufferException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractCanalListener<EVENT extends AbstractCanalEvent> implements ApplicationListener<EVENT> {


    @Override
    public void onApplicationEvent(EVENT event) {
        Entry entry = event.getEntry();
        String database = entry.getHeader().getSchemaName();
        String table = entry.getHeader().getTableName();
        String index = "user_behavior";
        String type = "type";
        RowChange change;
        try {
            change = RowChange.parseFrom(entry.getStoreValue());
        } catch (InvalidProtocolBufferException e) {
            return;
        }
        change.getRowDatasList().forEach(rowData -> doSync(database, table, index, type, rowData));
    }

    protected abstract void doSync(String database, String table, String index, String type, RowData rowData);
}
