create schema if not exists bbs default character set utf8mb4;
use bbs;
set names utf8mb4;

drop table if exists bbsComment;

drop table if exists bbsPost;

drop table if exists bbsUser;

/*==============================================================*/
/* Table: bbsComment                                            */
/*==============================================================*/
create table bbsComment
(
   commentId            int not null auto_increment  comment '评论Id',
   postId               int not null  comment '帖子Id',
   userId               int  comment '用户Id',
   content              varchar(200)  comment '评论内容',
   updatedAt            int  comment '更新时间',
   primary key (commentId)
);

alter table bbsComment comment 'bbsComment 评论';

/*==============================================================*/
/* Table: bbsPost                                               */
/*==============================================================*/
create table bbsPost
(
   postId               int not null auto_increment  comment '帖子Id',
   userId               int  comment '用户Id',
   title                varchar(150)  comment '帖子标题',
   vote                 int  comment '点赞数',
   updatedAt            int  comment '更新时间',
   primary key (postId)
);

alter table bbsPost comment 'bbsPost 帖子';

/*==============================================================*/
/* Table: bbsUser                                               */
/*==============================================================*/
create table bbsUser
(
   userId               int not null auto_increment  comment '用户Id',
   username             varchar(20)  comment '',
   password             varchar(20)  comment '',
   primary key (userId)
);

alter table bbsUser comment 'bbsUser 用户';
