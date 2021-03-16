CREATE TABLE mark_label_config (
  tid int(11) NOT NULL AUTO_INCREMENT,
  project_id varchar(20) NOT NULL DEFAULT '' COMMENT '项目ID',
  label_id varchar(20) NOT NULL DEFAULT '' COMMENT '标签ID',
  color varchar(10) NOT NULL DEFAULT '#000000' COMMENT '标签颜色',
  shortcut_key varchar(10) NOT NULL DEFAULT '' COMMENT '标签快捷键',
  element varchar(50) NOT NULL DEFAULT '' COMMENT '标签要素',
  type varchar(10) NOT NULL DEFAULT '' COMMENT '标签类型',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (tid),
  UNIQUE KEY uniq_project_label_id (project_id,label_id) USING BTREE
);

CREATE TABLE mark_project (
  tid int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  project_id varchar(20) NOT NULL DEFAULT '' COMMENT '项目ID',
  name varchar(20) NOT NULL DEFAULT '' COMMENT '项目名称',
  description varchar(500) NOT NULL DEFAULT '' COMMENT '项目描述',
  create_username varchar(10) NOT NULL DEFAULT '' COMMENT '项目所有者',
  import_time timestamp NOT NULL DEFAULT '1970-01-02 00:00:00' COMMENT '导入时间',
  project_type varchar(20) NOT NULL DEFAULT '' COMMENT '项目类型',
  data_type varchar(1) NOT NULL DEFAULT '' COMMENT '数据类型，如单轮/多轮',
  data_finish_count int(11) NOT NULL DEFAULT '0' COMMENT '标注数据完成数',
  data_total_count int(11) NOT NULL DEFAULT '0' COMMENT '标注数据总数',
  remark varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (tid),
  UNIQUE KEY uniq_project_id (project_id)
);

CREATE TABLE mark_project_data (
  tid int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  project_id varchar(20) NOT NULL DEFAULT '' COMMENT '项目ID',
  text_sequence int(11) NOT NULL DEFAULT '0' COMMENT '文本序号',
  original_text text COMMENT '原始文本',
  mark_entity text COMMENT '实体',
  mark_intention varchar(20) NOT NULL DEFAULT '' COMMENT '意图',
  mark_type varchar(10) NOT NULL DEFAULT '' COMMENT '文本类别',
  structured_data text COMMENT '结构化数据',
  standardised_data text COMMENT '标准化数据',
  status varchar(2) NOT NULL DEFAULT '' COMMENT '标注状态',
  mark_username varchar(10) NOT NULL DEFAULT '' COMMENT '标注人',
  time_consuming int(11) NOT NULL DEFAULT '0' COMMENT '耗时，单位毫秒',
  remark varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (tid),
  UNIQUE KEY uniq_project_text_sequence (project_id,text_sequence) USING BTREE
);

CREATE TABLE `ums_user` (
  `tid` int NOT NULL COMMENT '主键' PRIMARY KEY,
  `username` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `department` varchar(20) NOT NULL DEFAULT '' COMMENT '用户所属部门',
  `role` varchar(10) NOT NULL DEFAULT '0' COMMENT '用户角色',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
);


