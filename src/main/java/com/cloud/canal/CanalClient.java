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

}
