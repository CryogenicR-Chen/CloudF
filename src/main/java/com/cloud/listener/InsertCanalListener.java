package com.cloud.listener;

import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.cloud.event.InsertAbstractCanalEvent;
import com.cloud.service.ElasticSearchService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class InsertCanalListener extends AbstractCanalListener<InsertAbstractCanalEvent> {
    @Autowired
    private ElasticSearchService elasticsearchService;

    @Override
    protected void doSync(String database, String table, String index, String type, RowData rowData) {
        List<Column> columns = rowData.getAfterColumnsList();
        String primaryKey ="id";
        Column idColumn = columns.stream().filter(column -> column.getIsKey() && primaryKey.equals(column.getName())).findFirst().orElse(null);
        if (idColumn == null || StringUtils.isBlank(idColumn.getValue())) {
            return;
        }
        for (Column column : columns) {
            elasticsearchService.insertDocument(index, idColumn.getValue(), column.getValue());
        }
    }
}
