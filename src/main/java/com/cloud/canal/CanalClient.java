package com.cloud.canal;


import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;
import com.google.common.collect.Lists;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


@Component
public class CanalClient {

        private CanalConnector canalConnector;

        @Value("${canal.client.instances.example.host}")
        private String host;
        @Value("${canal.client.instances.example.port}")
        private String port;


        private Queue<String> SQL_QUEUE = new ConcurrentLinkedQueue<>();
        @Resource
        private DataSource dataSource;
        @Bean
        public CanalConnector getCanalConnector() {
            canalConnector = CanalConnectors.newClusterConnector(Lists.newArrayList(new InetSocketAddress(host, Integer.valueOf(port))), "example", "canal", "password");
            canalConnector.connect();
            canalConnector.subscribe(".*\\..*");
            // 回滚寻找上次中断的位置
            canalConnector.rollback();
            System.out.println("canal 初始化完成");
            return canalConnector;
        }
//        public void run() {
//            CanalConnector connector =
//                    CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
//            int batchSize = 1000;
//            try {
//                connector.connect();
//                connector.subscribe("cloud\\\\..*");
//                connector.rollback();
//                try {
//                    while (true) {
//                        Message message = connector.getWithoutAck(batchSize);
//                        long batchId = message.getId();
//                        int size = message.getEntries().size();
//                        printEntry(message.getEntries());
//                        if (batchId == -1 || size == 0) {
//                            Thread.sleep(1000);
//                        } else {
//                            dataHandle(message.getEntries());
//
//                        }
//                        connector.ack(batchId);
//                        if (SQL_QUEUE.size() >= 1) {
//                            executeQueueSql();
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (InvalidProtocolBufferException e) {
//                    e.printStackTrace();
//                }
//            } finally {
//                connector.disconnect();
//            }
//        }
//
//        private void dataHandle(List<Entry> entrys) throws InvalidProtocolBufferException {
////            for (Entry entry : entrys) {
////                if (EntryType.ROWDATA == entry.getEntryType()) {
////                    RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
////                    EventType eventType = rowChange.getEventType();
////                    if (eventType == EventType.DELETE) {
////                        saveDeleteSql(entry);
////                    } else if (eventType == EventType.UPDATE) {
////                        saveUpdateSql(entry);
////                    } else if (eventType == EventType.INSERT) {
////                        saveInsertSql(entry);
////                    }
////                }
////            }
//        }
//
//        private void saveDeleteSql(Entry entry) {
//            try {
//                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
//                List<RowData> rowDatasList = rowChange.getRowDatasList();
//                for (RowData rowData : rowDatasList) {
//                    List<Column> columnList = rowData.getBeforeColumnsList();
//                    StringBuffer sql = new StringBuffer("delete from " + entry.getHeader().getTableName() + " where ");
//                    for (Column column : columnList) {
//                        if (column.getIsKey()) {
//                            // 暂时只支持单一主键
//                            sql.append(column.getName() + "=" + column.getValue());
//                            break;
//                        }
//                    }
//                    SQL_QUEUE.add(sql.toString());
//                }
//            } catch (InvalidProtocolBufferException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private void saveUpdateSql(Entry entry) {
//            try {
//                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
//                List<RowData> rowDatasList = rowChange.getRowDatasList();
//                for (RowData rowData : rowDatasList) {
//                    List<Column> newColumnList = rowData.getAfterColumnsList();
//                    StringBuffer sql = new StringBuffer("update " + entry.getHeader().getTableName() + " set ");
//                    for (int i = 0; i < newColumnList.size(); i++) {
//                        sql.append(" " + newColumnList.get(i).getName() + " = '" + newColumnList.get(i).getValue() + "'");
//                        if (i != newColumnList.size() - 1) {
//                            sql.append(",");
//                        }
//                    }
//                    sql.append(" where ");
//                    List<Column> oldColumnList = rowData.getBeforeColumnsList();
//                    for (Column column : oldColumnList) {
//                        if (column.getIsKey()) {
//                            // 暂时只支持单一主键
//                            sql.append(column.getName() + "=" + column.getValue());
//                            break;
//                        }
//                    }
//                    SQL_QUEUE.add(sql.toString());
//                }
//            } catch (InvalidProtocolBufferException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private void saveInsertSql(Entry entry) {
//            try {
//                RowChange rowChange = RowChange.parseFrom(entry.getStoreValue());
//                List<RowData> rowDatasList = rowChange.getRowDatasList();
//                for (RowData rowData : rowDatasList) {
//                    List<Column> columnList = rowData.getAfterColumnsList();
//                    StringBuffer sql = new StringBuffer("insert into " + entry.getHeader().getTableName() + " (");
//                    for (int i = 0; i < columnList.size(); i++) {
//                        sql.append(columnList.get(i).getName());
//                        if (i != columnList.size() - 1) {
//                            sql.append(",");
//                        }
//                    }
//                    sql.append(") VALUES (");
//                    for (int i = 0; i < columnList.size(); i++) {
//                        sql.append("'" + columnList.get(i).getValue() + "'");
//                        if (i != columnList.size() - 1) {
//                            sql.append(",");
//                        }
//                    }
//                    sql.append(")");
//                    SQL_QUEUE.add(sql.toString());
//                }
//            } catch (InvalidProtocolBufferException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void executeQueueSql() {
//            int size = SQL_QUEUE.size();
//            for (int i = 0; i < size; i++) {
//                String sql = SQL_QUEUE.poll();
//                System.out.println("[sql]----> " + sql);
//                this.execute(sql.toString());
//            }
//        }
//
//        public void execute(String sql) {
//            Connection con = null;
//            try {
//                if (null == sql)
//                    return;
//                con = dataSource.getConnection();
//                QueryRunner qr = new QueryRunner();
//                int row = qr.execute(con, sql);
//                System.out.println("update: " + row);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                DbUtils.closeQuietly(con);
//            }
//        }
//
//
//    private static void printEntry(List<Entry> entrys) {
//        for (Entry entry : entrys) {
//            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
//                continue;
//            }
//
//            RowChange rowChage = null;
//            try {
//                rowChage = RowChange.parseFrom(entry.getStoreValue());
//            } catch (Exception e) {
//                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
//                        e);
//            }
//
//            EventType eventType = rowChage.getEventType();
//            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
//                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
//                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
//                    eventType));
//
//            for (RowData rowData : rowChage.getRowDatasList()) {
//                if (eventType == EventType.DELETE) {
//                    printColumn(rowData.getBeforeColumnsList());
//                } else if (eventType == EventType.INSERT) {
//                    printColumn(rowData.getAfterColumnsList());
//                } else {
//                    System.out.println("-------&gt; before");
//                    printColumn(rowData.getBeforeColumnsList());
//                    System.out.println("-------&gt; after");
//                    printColumn(rowData.getAfterColumnsList());
//                }
//            }
//        }
//    }
//
//    private static void printColumn(List<Column> columns) {
//        for (Column column : columns) {
//            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
//        }
//    }
}
