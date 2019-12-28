drop schema if exists bbs;
create schema bbs default character set utf8mb4;
use bbs;
set names utf8mb4;

drop table if exists bbs_comment;
drop table if exists bbs_post;
drop table if exists bbs_user;
drop table if exists bbs_user_star_and_praise_map;

/*==============================================================*/
/* Table: bbs_comment                                           */
/*==============================================================*/
create table bbs_comment
(
   comment_id           int not null auto_increment  comment '评论Id',
   post_id              int not null  comment '帖子Id',
   user_id              int  comment '用户Id',
   content              MEDIUMTEXT  comment '评论内容',
   updated_at           int  comment '更新时间',
   primary key (comment_id)
);

alter table bbs_comment comment 'bbs_comment 评论';

/*==============================================================*/
/* Index: ik_post_id                                            */
/*==============================================================*/
create index ik_post_id on bbs_comment
(
   post_id
);

/*==============================================================*/
/* Table: bbs_post                                              */
/*==============================================================*/
create table bbs_post
(
   post_id              int not null auto_increment  comment '帖子Id',
   user_id              int  comment '用户Id',
   title                varchar(150)  comment '帖子标题',
   content              MEDIUMTEXT  comment '帖子内容',
   vote                 int  comment '点赞数',
   updated_at           int  comment '更新时间',
   primary key (post_id)
);

alter table bbs_post comment 'bbs_post 帖子';

/*==============================================================*/
/* Table: bbs_user                                              */
/*==============================================================*/
create table bbs_user
(
   user_id              int not null auto_increment  comment '用户Id',
   username             varchar(20)  comment '',
   password             varchar(20)  comment '',
   primary key (user_id)
);

alter table bbs_user comment 'bbs_user 用户';

/*==============================================================*/
/* Table: bbs_user_star_and_praise_map                          */
/*==============================================================*/
create table bbs_user_star_and_praise_map
(
   user_SAPM_id         int not null auto_increment  comment '',
   user_id              int  comment '用户Id',
   post_id              int  comment '帖子Id',
   map_type             int  comment '关联类型：1. star 2. praise',
   created_at           int  comment '创建时间',
   primary key (user_SAPM_id)
);

alter table bbs_user_star_and_praise_map comment 'bbs_user_star_and_praise_map 用户标星（收藏）、点赞关联表';

/*==============================================================*/
/* Index: ik_post_id                                            */
/*==============================================================*/
create index ik_post_id on bbs_user_star_and_praise_map
(
   post_id
);

/*==============================================================*/
/* Index: ik_user_id                                            */
/*==============================================================*/
create index ik_user_id on bbs_user_star_and_praise_map
(
   user_id
);

-- init data
truncate bbs_post;
INSERT INTO `bbs_user` (`username`, `password`) VALUES
 ('Tom', '123456')
,('Jack', '123456')
,('Steve', '123456')
,('AndyChen', '123456');

truncate bbs_post;
INSERT INTO `bbs_post` (`user_id`, `title`, `content`, `vote`, `updated_at`) VALUES
 ('2', '分享学习', '本项目分享个人学习 react 技术栈（全家桶）的一些经验心得', '1', '1545189942')
,('4', '结合相关示例代码', ' 学习过程会结合相关示例代码，在实践中学习更有成效。', '0', '1545189989')
,('3', '参与到该项目', '可通过 fork + pull request 的方式，参与到该项目。', '0', '1545190023');

truncate bbs_comment;
INSERT INTO `bbs_comment` (`post_id`, `user_id`, `content`, `updated_at`) VALUES
 ('1', '1', 'very good!', '1545185465')
,('1', '2', 'very very good', '1545185485');
