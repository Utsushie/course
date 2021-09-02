DROP table IF EXISTS `cs_chapter`;
CREATE table `cs_chapter` (
    `id` INT UNSIGNED AUTO_INCREMENT comment 'ID',
    `course_id` char(16) comment '课程ID',
    `name` varchar(64) comment '名称',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='课程大章';

ALTER table cs_chapter CHANGE id id  CHAR(64) NOT NULL;
ALTER table cs_chapter CHANGE course_id course_id  CHAR(64);