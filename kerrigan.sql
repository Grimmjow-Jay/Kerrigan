/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.7.22 : Database - kerrigan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kerrigan` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `kerrigan`;

/*Table structure for table `t_flow` */

DROP TABLE IF EXISTS `t_flow`;

CREATE TABLE `t_flow` (
  `flow_id` int(11) NOT NULL AUTO_INCREMENT,
  `flow_name` varchar(32) NOT NULL,
  `flow_desc` varchar(128) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`flow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_flow` */

insert  into `t_flow`(`flow_id`,`flow_name`,`flow_desc`,`create_date`,`update_date`) values (1,'第一个flow','第一个flow描述','2018-12-28 00:00:00','2018-12-28 00:00:00'),(2,'第二个flow','第二个flow描述','2018-12-28 00:00:00','2018-12-28 00:00:00');

/*Table structure for table `t_job` */

DROP TABLE IF EXISTS `t_job`;

CREATE TABLE `t_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(32) NOT NULL,
  `job_desc` varchar(128) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `fk_project_id` int(11) NOT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_job` */

insert  into `t_job`(`job_id`,`job_name`,`job_desc`,`create_date`,`update_date`,`fk_project_id`) values (1,'第一个job','第一个job描述','2018-12-28 00:00:00','2018-12-28 00:00:00',1),(2,'第二个job','第二个job描述','2018-12-28 00:00:00','2018-12-28 00:00:00',1);

/*Table structure for table `t_job_flow` */

DROP TABLE IF EXISTS `t_job_flow`;

CREATE TABLE `t_job_flow` (
  `fk_job_id` int(11) NOT NULL,
  `fk_flow_id` int(11) NOT NULL,
  PRIMARY KEY (`fk_job_id`,`fk_flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_job_flow` */

insert  into `t_job_flow`(`fk_job_id`,`fk_flow_id`) values (1,1),(1,2);

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(32) NOT NULL,
  `project_desc` varchar(128) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_project` */

insert  into `t_project`(`project_id`,`project_name`,`project_desc`,`create_date`,`update_date`) values (1,'第一个project','第一个project描述','2018-12-28 00:00:00','2018-12-28 00:00:00');

/*Table structure for table `t_token` */

DROP TABLE IF EXISTS `t_token`;

CREATE TABLE `t_token` (
  `token_id` varchar(32) NOT NULL,
  `host` varchar(64) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`host`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_token` */

insert  into `t_token`(`token_id`,`host`,`user_name`,`create_date`,`update_date`) values ('179da399b73a429fb01eae62b9678d9d','0:0:0:0:0:0:0:1','kerrigan','2018-12-27 15:16:27','2018-12-27 16:01:53');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
