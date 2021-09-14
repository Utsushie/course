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
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程小节'

INSERT INTO `cs_section`(id,course_id,chapter_id,title,video,`time`,charge,sort)
    VALUES('00000001','00000001','00000000','测试小结01','',500,'0',1)