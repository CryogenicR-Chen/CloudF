package com.cloud.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;

import com.cloud.listener.InsertCanalListener;
import com.cloud.service.ElasticSearchService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;

@Component
@RequiredArgsConstructor
public class MessageReceiver {

    @Autowired
    private ElasticSearchService elasticSearchService;

    private static final Logger logger = LoggerFactory.getLogger(InsertCanalListener.class);
    private Map<Long, Integer> counterMap =new ConcurrentHashMap<>();
    @RabbitListener(queues = "canal_mq_queue", ackMode = "MANUAL")
    public void getMsg(Message message, Channel channel, Entry entry) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        EventType eventType = entry.getHeader().getEventType();
        RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
        List<RowData> rowDatasList = rowChange.getRowDatasList();
        try {

            String primaryKey = "id";
            String index = "user_behavior";
            for (RowData rowData : rowDatasList) {
                List<Column> columns = rowData.getAfterColumnsList();
                Column idColumn = columns.stream().filter(column -> column.getIsKey() && primaryKey.equals(column.getName())).findFirst().orElse(null);
                if (idColumn == null || StringUtils.isBlank(idColumn.getValue())) {
                    return;
                }
                switch (eventType) {
                    case INSERT:
                    case UPDATE:
                        for (Column column : columns) {
                            elasticSearchService.insertDocument_mq(index, idColumn.getValue(), column.getValue());
                            logger.debug("Insert_es_info success！DeliveryTag: "+ deliveryTag + " Event Type: " + eventType + " SQL: " +  JSON.toJSONString(rowChange.getSql()));
                        }
                        break;
                    case DELETE:
                        elasticSearchService.deleteDocument(index, idColumn.getValue());
                        logger.debug("Delete_es_info success！DeliveryTag: "+ deliveryTag + " Event Type: " + eventType  + " SQL: " +  JSON.toJSONString(rowChange.getSql()));
                        break;
                    default:
                        break;
                }
            }
            channel.basicAck(deliveryTag, true);
        }catch (Exception e){
            if(counterMap.getOrDefault(deliveryTag,0) < 3){
                channel.basicNack(deliveryTag, false, true);
                logger.warn("Retry！DeliveryTag: "+ deliveryTag + " SQL:" + JSON.toJSONString(rowChange.getSql()));
                counterMap.put(deliveryTag,counterMap.getOrDefault(deliveryTag,0)+1);
            }else{
                channel.basicReject(deliveryTag,false);
                logger.error("Reject！DeliveryTag: "+ deliveryTag + " SQL:"+ JSON.toJSONString(rowChange.getSql()) );
                counterMap.remove(deliveryTag);
            }

        }
    }
}