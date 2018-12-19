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

-- init datas
truncate bbsUser;
INSERT INTO `bbs`.`bbsUser` (`username`, `password`) VALUES ('Tom', '123456');
INSERT INTO `bbs`.`bbsUser` (`username`, `password`) VALUES ('Jack', '123456');
INSERT INTO `bbs`.`bbsUser` (`username`, `password`) VALUES ('Steve', '123456');

truncate bbsPost;
INSERT INTO `bbs`.`bbsPost` (`userId`, `title`, `vote`, `updatedAt`) VALUES ('2', '本项目分享个人学习 react 技术栈（全家桶）的一些经验心得', '1', '1545189942');
INSERT INTO `bbs`.`bbsPost` (`userId`, `title`, `vote`, `updatedAt`) VALUES ('4', ' 学习过程会结合相关示例代码，在实践中学习更有成效。', '0', '1545189989');
INSERT INTO `bbs`.`bbsPost` (`userId`, `title`, `vote`, `updatedAt`) VALUES ('3', '可通过 fork + pull request 的方式，参与到该项目。', '0', '1545190023');

truncate bbsComment;
INSERT INTO `bbs`.`bbsComment` (`postId`, `userId`, `content`, `updatedAt`) VALUES ('1', '1', 'very good!', '1545185465');
INSERT INTO `bbs`.`bbsComment` (`postId`, `userId`, `content`, `updatedAt`) VALUES ('1', '2', 'very very good', '1545185485');
