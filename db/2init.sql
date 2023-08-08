use springboot_examples;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
                           `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
                           `age` int(0) NULL DEFAULT 0 COMMENT '年龄',
                           `remark` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                           `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志',
                           `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                           `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '人物表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES (1, 'Louis', 18, ' ', '0', now(), now());

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
                        `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                        `people_id` bigint(0) NOT NULL COMMENT '人物ID',
                        `pet_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宠物名称',
                        `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志',
                        `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                        `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '宠物表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, 1, '道尔格', '0', now(), now());
INSERT INTO `pet` VALUES (2, 1, '卡特', '0', now(), now());

SET FOREIGN_KEY_CHECKS = 1;
