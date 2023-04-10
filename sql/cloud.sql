-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: oj
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `competition`
--



--
-- Table structure for table `question`
--


DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                               `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                               `NAME` varchar(255) NOT NULL COMMENT '名字',
                               `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                               `brief_introduction` tinytext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '简介',
                               `creator` bigint NOT NULL COMMENT '创建者',
                               `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建者姓名',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE KEY `favorites_id_uindex` (`id`),
                               INDEX `userkey`(`creator`) USING BTREE,
                               CONSTRAINT `userkey` FOREIGN KEY (`creator`) REFERENCES `user_base` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='收藏夹';


DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                            `NAME` varchar(255) NOT NULL COMMENT '名字',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '题目内容',
                            `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建者',
                            `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建者姓名',
                            `tags` varchar(255) DEFAULT '',
                            `url` varchar(255) DEFAULT '',
                            `is_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏',
                            `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `modifier` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
                            `modifier_name` varchar(255) NOT NULL DEFAULT '' COMMENT '修改人姓名',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `blog_id_uindex` (`id`),
                             CONSTRAINT `creator_` FOREIGN KEY (`creator`) REFERENCES `user_base` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='题目';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `blog_favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_favorites` (
                                        `blog_name` varchar(255) NOT NULL DEFAULT '' COMMENT 'blog名字',
                                        `blog_id` bigint DEFAULT NULL COMMENT 'blogID',
                                        `favorites_id` bigint DEFAULT NULL COMMENT '收藏夹ID',
                                        `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `question_competition_id_uindex` (`id`),
                                        KEY `question_competition_index` (`favorites_id`) USING BTREE,
                                          CONSTRAINT `favor_id` FOREIGN KEY (`favorites_id`) REFERENCES `favorites` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                          CONSTRAINT `blo_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='blog-收藏夹映射';
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `question_competition`
--

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submit_records`
--



--
-- Table structure for table `testcase_question`
--



--
-- Table structure for table `user_base`
--

DROP TABLE IF EXISTS `user_base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_base` (
                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                             `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                             `NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名字',
                             `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                             `mail` varchar(255) DEFAULT NULL COMMENT '邮箱',
                             `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话号',
                             `photo_hash` text COMMENT '头像hash',
                             `authority` tinyint DEFAULT NULL COMMENT '权限',
                             `password` varchar(128) DEFAULT NULL COMMENT '密码',
                             `blog_number` int DEFAULT '0' COMMENT '博客数量',
                             `profile_photo` text COMMENT '头像',
                             `intro` varchar(400) DEFAULT 'This is an self introduction editing by yourself.' COMMENT '自我介绍',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE KEY `mail` (`mail`) USING BTREE,
                             KEY `user_id_index` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_competition`
--
DROP TABLE IF EXISTS `user_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_blog` (
                                    `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                    `blog_id` bigint DEFAULT NULL COMMENT 'blogID',
                                    `is_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
                                    `is_favorite` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否收藏',
                                    `is_create` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否创建',
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    PRIMARY KEY (`id`),
                                    KEY `user_blog_index` (`user_id`) USING BTREE,
                                     CONSTRAINT `user_id213` FOREIGN KEY (`user_id`) REFERENCES `user_base` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                     CONSTRAINT `blos_id123` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户-blog映射';

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                            `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
                            `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                            `blog_id` bigint DEFAULT NULL COMMENT 'blogID',
                            `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `delete_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '删除时间',
                            `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '题目内容',
                            `creator` bigint NOT NULL DEFAULT '0' COMMENT '创建者',
                            `creator_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建者姓名',
                            `is_hide` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否隐藏',
                            `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `modifier` bigint NOT NULL DEFAULT '0' COMMENT '修改人id',
                            `modifier_name` varchar(255) NOT NULL DEFAULT '' COMMENT '修改人姓名',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `comment_id_uindex` (`id`),
                            CONSTRAINT `user_id23` FOREIGN KEY (`user_id`) REFERENCES `user_base` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                            CONSTRAINT `blos_id` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='题目';

/*!40101 SET character_set_client = @saved_cs_client */;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-21 22:16:12
