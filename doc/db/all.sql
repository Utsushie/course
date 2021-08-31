DROP table IF EXISTS `cs_chapter`;
CREATE table `cs_chapter` (
    `id` INT UNSIGNED AUTO_INCREMENT comment 'ID',
    `course_id` char(16) comment '课程ID',
    `name` varchar(64) comment '名称',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='课程大章';