DROP table IF EXISTS `cs_chapter`;
CREATE table `cs_chapter` (
    `id` char(8) NOT NULL comment 'ID',
    `course_id` char(16) comment '课程ID',
    `name` varchar(64) comment '名称',
    primary key (`id`)
)engine=innodb default charset=utf8mb4 comment='课程大章'

alter table cs_chapter change id id int AUTO_INCREMENT;