if exists drop table t_concentrator ;

if exists drop table t_meterData ;

if exists drop table t_meter ;

/*==============================================================*/
/* Table: t_concentrator                                        */
/*==============================================================*/
create table t_concentrator
(
   id                   integer                        not null,
   comPort              varchar(50)                    not null,
   baudRate             integer                        not null,
   manufacturer         varchar(50)                    not null,
   model                varchar(50)                    not null,
   is_online            smallint                       null,
   code                 varchar(15)                    not null,
   smsc                 varchar(15)                    null,
   constraint PK_T_CONCENTRATOR primary key clustered (id)
);

comment on column t_concentrator.id is
'主键
';

comment on column t_concentrator.is_online is
' 0 是不在线 1 是在线';


/*==============================================================*/
/* Table: t_meter                                               */
/*==============================================================*/
create table t_meter
(
   id                   integer                        not null,
   code                   varchar(16)                    null,
   controller_id        integer                        null,
   constraint PK_T_METER primary key clustered (id)
);


/*==============================================================*/
/* Table: t_meterData                                           */
/*==============================================================*/
create table t_meterData
(
   id                   integer                        null,
   meter_id             integer                        null,
   code                 varchar(20)                    null,
   value                float                          null,
   create_time          datetime                       null
);

comment on table t_meterData is
'存储上传的数据';


