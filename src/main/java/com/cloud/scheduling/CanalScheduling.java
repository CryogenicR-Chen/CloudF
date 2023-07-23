package com.cloud.scheduling;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.Message;
import com.cloud.event.DeleteAbstractCanalEvent;
import com.cloud.event.InsertAbstractCanalEvent;
import com.cloud.event.UpdateAbstractCanalEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class CanalScheduling implements Runnable, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Resource
    private CanalConnector canalConnector;

    @Scheduled(fixedDelay = 1000)
    @Override
    public void run() {
        try {
            int batchSize = 100;
            Message message = canalConnector.getWithoutAck(batchSize);
            long batchId = message.getId();
            try {
                List<Entry> entries = message.getEntries();
                if (batchId != -1 && entries.size() > 0) {
                    entries.forEach(entry -> {
                        if (entry.getEntryType() == EntryType.ROWDATA) {
                            publishCanalEvent(entry);
                        }
                    });
                }
                canalConnector.ack(batchId);
            } catch (Exception e) {
                canalConnector.rollback(batchId);
            }
        } catch (Exception e) {
        }
    }

    private void publishCanalEvent(Entry entry) {
        EventType eventType = entry.getHeader().getEventType();
        switch (eventType) {
            case INSERT:
                applicationContext.publishEvent(new InsertAbstractCanalEvent(entry));
                break;
            case UPDATE:
                applicationContext.publishEvent(new UpdateAbstractCanalEvent(entry));
                break;
            case DELETE:
                applicationContext.publishEvent(new DeleteAbstractCanalEvent(entry));
                break;
            default:
                break;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
