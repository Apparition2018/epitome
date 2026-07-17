CREATE TABLE `tb_lamp`  (
  `id` bigint AUTO_INCREMENT PRIMARY KEY,
  `device_id` varchar(50),
  `status` int,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP
);
