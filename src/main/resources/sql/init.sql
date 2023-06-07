create table t_user (
    id       int(10) auto_increment primary key,
    username varchar(32) not null,
    password varchar(32) not null
) comment '用户表'

