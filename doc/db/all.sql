DROP table IF EXISTS `cs_chapter`;
CREATE table `cs_chapter` (
    `id` INT UNSIGNED AUTO_INCREMENT comment 'ID',
    `course_id` char(16) comment '课程ID',
    `name` varchar(64) comment '名称',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='课程大章';

ALTER table cs_chapter CHANGE id id  CHAR(64) NOT NULL;
ALTER table cs_chapter CHANGE course_id course_id  CHAR(64);
ALTER table cs_chapter ADD COLUMN is_del int(2) default 0 COMMENT '是否删除:0-否;1-是';

ALTER TABLE `cs_chapter` ADD UNIQUE KEY index_course_id ( `course_id` );

DROP table IF EXISTS `cs_section`;
CREATE TABLE `cs_section` (
    `id` CHAR(64) NOT NULL DEFAULT '' COMMENT 'ID',
    `course_id` VARCHAR(64) COMMENT '课程ID',
    `chapter_id` VARCHAR(64) COMMENT '章节ID',
    `title` VARCHAR(64)NOT NULL COMMENT '标题',
    `video` VARCHAR(200) COMMENT '视频',
    `time` INT COMMENT '时长|单位秒',
    `charge` CHAR(2) DEFAULT NULL COMMENT '收费:0-否;1-是',
    `sort` INT COMMENT '顺序',
    `is_del` INT DEFAULT '0' COMMENT '是否删除:0-否;1-是',
    `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '创建人',
    `updated_by` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_course_id` (`course_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程小节';

INSERT INTO `cs_section`(id,course_id,chapter_id,title,video,`time`,charge,sort)
    VALUES('00000001','00000001','00000000','测试小结01','',500,'0',1)


#data_bak
DROP TABLE IF EXISTS `cs_chapter`;

CREATE TABLE `cs_chapter` (
                              `id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                              `course_id` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '课程ID',
                              `name` varchar(64) DEFAULT NULL COMMENT '名称',
                              `is_del` int DEFAULT '0' COMMENT '是否删除:0-否;1-是',
                              `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                              `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '创建人',
                              `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '修改人',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `index_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程大章';

/*Data for the table `cs_chapter` */

insert  into `cs_chapter`(`id`,`course_id`,`name`,`is_del`,`created_time`,`updated_time`,`created_by`,`updated_by`) values
('1','00000001','spring-boot',0,'2021-09-06 03:56:11','2021-09-08 14:43:25','admin','Yx'),
('10','00000010','多线程',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('11','00000011','分布式开发',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('12','00000012','应用集群',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('13','00000013','python',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('14','00000014','linux',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('15','11111111','小熊喵',1,'2021-09-06 03:56:11','2021-09-07 14:54:15','Yx','Yx'),
('16','00000015','小熊喵',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('2','00000002','mysql8.0',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('3','00000003','redis',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('4','00000004','oracle',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('5','00000005','vue',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('6','00000006','mybatis',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('7','00000007','spring-cloud',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('8','00000008','css',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('9','00000009','pgsql',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin'),
('mSMrec9H','00000022','vue3',1,'2021-09-07 16:15:33','2021-09-08 14:57:30','Yx','Yx'),
('oN109uJk','00000023','vue3',1,'2021-09-07 16:17:44','2021-09-08 14:43:54','Yx','Yx'),
('s9Rx9Swp','00000024','数据结构',0,'2021-09-08 17:53:53','2021-09-08 17:53:53','Yx','Yx'),
('uXCKhUqO','00000020','course001',0,'2021-09-06 03:56:11','2021-09-06 03:56:12','admin','admin');

/*Table structure for table `cs_section` */

DROP TABLE IF EXISTS `cs_section`;

CREATE TABLE `cs_section` (
                              `id` char(64) NOT NULL DEFAULT '' COMMENT 'ID',
                              `course_id` varchar(64) DEFAULT NULL COMMENT '课程ID',
                              `chapter_id` varchar(64) DEFAULT NULL COMMENT '章节ID',
                              `title` varchar(64) NOT NULL COMMENT '标题',
                              `video` varchar(200) DEFAULT NULL COMMENT '视频',
                              `time` int DEFAULT NULL COMMENT '时长',
                              `charge` char(2) DEFAULT NULL COMMENT '收费:0-否;1-是',
                              `sort` int DEFAULT NULL COMMENT '顺序',
                              `is_del` int DEFAULT '0' COMMENT '是否删除:0-否;1-是',
                              `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                              `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '创建人',
                              `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '修改人',
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `index_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程小节';

/*Data for the table `cs_section` */

insert  into `cs_section`(`id`,`course_id`,`chapter_id`,`title`,`video`,`time`,`charge`,`sort`,`is_del`,`created_time`,`updated_time`,`created_by`,`updated_by`) values
('00000001','00000001','00000000','测试小结01','',500,'0',1,0,'2021-09-13 18:19:23','2021-11-10 17:46:50','Yx','Yx');

/* create table cs_course */
DROP TABLE IF EXISTS cs_course;
CREATE TABLE cs_course(
                          `id` CHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ID',
                          `name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
                          `summary` VARCHAR(200) DEFAULT NULL COMMENT '概述',
                          `time` INT DEFAULT 0 COMMENT '时长|单位秒',
                          `price` DECIMAL(8,2) DEFAULT 0 NOT NULL COMMENT '价格(元)',
                          `image` VARCHAR(120) DEFAULT NULL COMMENT '封面',
                          `level` CHAR(1) DEFAULT NULL COMMENT '级别|ONE("1","初级"),TWO("2","中级"),THREE("3","高级")',
                          `charge` CHAR(1) DEFAULT NULL COMMENT '收费|CHARGE("1",收费),FREE("0","免费")',
                          `status` CHAR(1) DEFAULT NULL COMMENT '状态|PUBLISH("1","发布"),DRAFT("0","草稿")',
                          `enroll` INT DEFAULT 0 COMMENT '报名数',
                          `sort` INT DEFAULT NULL COMMENT '顺序',
                          `is_del` INT DEFAULT 0 COMMENT '是否删除:0-否;1-是',
                          `created_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `updated_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          `created_by` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '创建人',
                          `updated_by` VARCHAR(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'admin' COMMENT '修改人',
                          PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表'