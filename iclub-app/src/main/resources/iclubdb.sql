CREATE DATABASE  IF NOT EXISTS `iclubdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `iclubdb`;
-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: iclubdb
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `iclub_access_type`
--

DROP TABLE IF EXISTS `iclub_access_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_access_type` (
  `at_id` bigint(20) NOT NULL,
  `at_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `at_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `at_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_access_type`
--

LOCK TABLES `iclub_access_type` WRITE;
/*!40000 ALTER TABLE `iclub_access_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_access_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_account`
--

DROP TABLE IF EXISTS `iclub_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_account` (
  `a_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `a_acc_num` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `a_acc_type_id` bigint(20) DEFAULT NULL,
  `a_owner_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `a_owner_type` bigint(20) DEFAULT NULL,
  `a_bm_id` bigint(20) DEFAULT NULL,
  `a_status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `a_crtd_dt` Date NULL DEFAULT NULL,
  `a_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `fk_iclub_account_1_idx` (`a_bm_id`),
  KEY `fk_iclub_account_3` (`a_crtd_by`),
  KEY `fk_iclub_account_4_idx` (`a_acc_type_id`),
  KEY `fk_iclub_account_2_idx` (`a_owner_type`),
  CONSTRAINT `fk_iclub_account_1` FOREIGN KEY (`a_bm_id`) REFERENCES `iclub_bank_master` (`bm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_account_2` FOREIGN KEY (`a_owner_type`) REFERENCES `iclub_owner_type` (`ot_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_account_3` FOREIGN KEY (`a_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_account_4` FOREIGN KEY (`a_acc_type_id`) REFERENCES `iclub_account_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_account`
--

LOCK TABLES `iclub_account` WRITE;
/*!40000 ALTER TABLE `iclub_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_account_type`
--

DROP TABLE IF EXISTS `iclub_account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_account_type` (
  `at_id` bigint(20) NOT NULL,
  `at_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `at_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `at_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_account_type`
--

LOCK TABLES `iclub_account_type` WRITE;
/*!40000 ALTER TABLE `iclub_account_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_alarm_type`
--

DROP TABLE IF EXISTS `iclub_alarm_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_alarm_type` (
  `at_id` bigint(20) NOT NULL,
  `at_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `at_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `at_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_alarm_type`
--

LOCK TABLES `iclub_alarm_type` WRITE;
/*!40000 ALTER TABLE `iclub_alarm_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_alarm_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_bank_master`
--

DROP TABLE IF EXISTS `iclub_bank_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_bank_master` (
  `bm_id` bigint(20) NOT NULL,
  `bm_bank_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `bm_bank_code` int(6) DEFAULT NULL,
  `bm_branch_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `bm_branch_code` int(6) DEFAULT NULL,
  `bm_branch_address` varchar(399) COLLATE utf8_bin DEFAULT NULL,
  `bm_branch_lat` decimal(10,7) DEFAULT NULL,
  `bm_branch_long` decimal(10,7) DEFAULT NULL,
  `bm_branch_zip` int(5) DEFAULT NULL,
  `bm_crtd_dt` Date NULL DEFAULT NULL,
  `bm_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`bm_id`),
  KEY `fk_iclub_bank_master_1` (`bm_crtd_by`),
  CONSTRAINT `fk_iclub_bank_master_1` FOREIGN KEY (`bm_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_bank_master`
--

LOCK TABLES `iclub_bank_master` WRITE;
/*!40000 ALTER TABLE `iclub_bank_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_bank_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_bar_type`
--

DROP TABLE IF EXISTS `iclub_bar_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_bar_type` (
  `bt_id` bigint(20) NOT NULL,
  `bt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `bt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `bt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_bar_type`
--

LOCK TABLES `iclub_bar_type` WRITE;
/*!40000 ALTER TABLE `iclub_bar_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_bar_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_boundary_type`
--

DROP TABLE IF EXISTS `iclub_boundary_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_boundary_type` (
  `bt_id` bigint(20) NOT NULL,
  `bt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `bt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `bt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`bt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_boundary_type`
--

LOCK TABLES `iclub_boundary_type` WRITE;
/*!40000 ALTER TABLE `iclub_boundary_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_boundary_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_building_state`
--

DROP TABLE IF EXISTS `iclub_building_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_building_state` (
  `bs_id` bigint(20) NOT NULL,
  `bs_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `bs_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `bs_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`bs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_building_state`
--

LOCK TABLES `iclub_building_state` WRITE;
/*!40000 ALTER TABLE `iclub_building_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_building_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_claim`
--

DROP TABLE IF EXISTS `iclub_claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_claim` (
  `c_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `c_number` bigint(20) DEFAULT NULL,
  `c_policy_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `c_num_items` int(2) DEFAULT NULL,
  `c_value` decimal(15,5) DEFAULT NULL,
  `c_status_id` bigint(20) DEFAULT NULL,
  `c_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `c_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_iclub_claim_1_idx` (`c_status_id`),
  KEY `fk_iclub_claim_2_idx` (`c_policy_id`),
  KEY `fk_iclub_claim_3_idx` (`c_crtd_by`),
  CONSTRAINT `fk_iclub_claim_1` FOREIGN KEY (`c_status_id`) REFERENCES `iclub_claim_status` (`cs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_2` FOREIGN KEY (`c_policy_id`) REFERENCES `iclub_policy` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_3` FOREIGN KEY (`c_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_claim`
--

LOCK TABLES `iclub_claim` WRITE;
/*!40000 ALTER TABLE `iclub_claim` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_claim_item`
--

DROP TABLE IF EXISTS `iclub_claim_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_claim_item` (
  `ci_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `ci_claim_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ci_item_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ci_value` decimal(15,5) DEFAULT NULL,
  `ci_assesor_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ci_handler_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ci_status_id` bigint(20) DEFAULT NULL,
  `ci_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ci_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`ci_id`),
  KEY `fk_iclub_claim_item_1_idx` (`ci_claim_id`),
  KEY `fk_iclub_claim_item_2_idx` (`ci_item_id`),
  KEY `fk_iclub_claim_item_3_idx` (`ci_assesor_id`),
  KEY `fk_iclub_claim_item_5_idx` (`ci_status_id`),
  KEY `fk_iclub_claim_item_4_idx` (`ci_handler_id`),
  CONSTRAINT `fk_iclub_claim_item_1` FOREIGN KEY (`ci_claim_id`) REFERENCES `iclub_claim` (`c_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_item_2` FOREIGN KEY (`ci_item_id`) REFERENCES `iclub_insurance_item` (`ii_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_item_3` FOREIGN KEY (`ci_assesor_id`) REFERENCES `iclub_suppl_master` (`sm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_item_4` FOREIGN KEY (`ci_handler_id`) REFERENCES `iclub_suppl_master` (`sm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_claim_item_5` FOREIGN KEY (`ci_status_id`) REFERENCES `iclub_claim_status` (`cs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_claim_item`
--

LOCK TABLES `iclub_claim_item` WRITE;
/*!40000 ALTER TABLE `iclub_claim_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_claim_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_claim_status`
--

DROP TABLE IF EXISTS `iclub_claim_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_claim_status` (
  `cs_id` bigint(20) NOT NULL,
  `cs_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `cs_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `cs_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`cs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_claim_status`
--

LOCK TABLES `iclub_claim_status` WRITE;
/*!40000 ALTER TABLE `iclub_claim_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_claim_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_config`
--

DROP TABLE IF EXISTS `iclub_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_config` (
  `c_id` bigint(20) NOT NULL,
  `c_key` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `c_value` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `c_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `c_crtd_dt` Date NULL DEFAULT NULL,
  `c_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `c_key_UNIQUE` (`c_key`),
  UNIQUE KEY `UK_4mibl33yrx5ta5aujppxy7fhn` (`c_key`),
  KEY `fk_iclub_config_1` (`c_crtd_by`),
  CONSTRAINT `fk_iclub_config_1` FOREIGN KEY (`c_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_config`
--

LOCK TABLES `iclub_config` WRITE;
/*!40000 ALTER TABLE `iclub_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_country_code`
--

DROP TABLE IF EXISTS `iclub_country_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_country_code` (
  `cc_id` int(11) NOT NULL,
  `cc_short_id` char(2) COLLATE utf8_bin DEFAULT NULL,
  `cc_iso_id` char(3) COLLATE utf8_bin DEFAULT NULL,
  `cc_name` varchar(9999) COLLATE utf8_bin DEFAULT NULL,
  `cc_crtd_dt` Date NULL DEFAULT NULL,
  `cc_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`cc_id`),
  UNIQUE KEY `cc_short_id_UNIQUE` (`cc_short_id`),
  UNIQUE KEY `cc_iso_id_UNIQUE` (`cc_iso_id`),
  UNIQUE KEY `UK_sl24ao3jj4ho7t8yu08wbssjo` (`cc_short_id`),
  UNIQUE KEY `UK_hkpcuv50aua8l03j8m6j7uovw` (`cc_iso_id`),
  KEY `fk_iclub_country_code_1_idx` (`cc_crtd_by`),
  CONSTRAINT `fk_iclub_country_code_1` FOREIGN KEY (`cc_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_country_code`
--

LOCK TABLES `iclub_country_code` WRITE;
/*!40000 ALTER TABLE `iclub_country_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_country_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_cover_type`
--

DROP TABLE IF EXISTS `iclub_cover_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_cover_type` (
  `ct_id` bigint(20) NOT NULL,
  `ct_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `ct_long_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `ct_item_type_id` bigint(20) DEFAULT NULL,
  `ct_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `ct_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ct_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`ct_id`),
  KEY `fk_iclub_cover_type_1_idx` (`ct_crtd_by`),
  KEY `fk_iclub_cover_type_2_idx` (`ct_item_type_id`),
  CONSTRAINT `fk_iclub_cover_type_1` FOREIGN KEY (`ct_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_cover_type_2` FOREIGN KEY (`ct_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_cover_type`
--

LOCK TABLES `iclub_cover_type` WRITE;
/*!40000 ALTER TABLE `iclub_cover_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_cover_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_document`
--

DROP TABLE IF EXISTS `iclub_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_document` (
  `d_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `d_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `d_mime_type` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `d_size` bigint(20) DEFAULT NULL,
  `d_type_id` bigint(20) DEFAULT NULL,
  `d_entity_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `d_entity_type_id` bigint(20) DEFAULT NULL,
  `d_content` longblob,
  `d_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `d_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  KEY `fk_iclub_document_1_idx` (`d_type_id`),
  KEY `fk_iclub_document_2_idx` (`d_entity_type_id`),
  KEY `fk_iclub_document_3_idx` (`d_crtd_by`),
  CONSTRAINT `fk_iclub_document_1` FOREIGN KEY (`d_type_id`) REFERENCES `iclub_document_type` (`dt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_document_2` FOREIGN KEY (`d_entity_type_id`) REFERENCES `iclub_entity_type` (`et_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_document_3` FOREIGN KEY (`d_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_document`
--

LOCK TABLES `iclub_document` WRITE;
/*!40000 ALTER TABLE `iclub_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_document_type`
--

DROP TABLE IF EXISTS `iclub_document_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_document_type` (
  `dt_id` bigint(20) NOT NULL,
  `dt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `dt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `dt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_document_type`
--

LOCK TABLES `iclub_document_type` WRITE;
/*!40000 ALTER TABLE `iclub_document_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_document_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_driver`
--

DROP TABLE IF EXISTS `iclub_driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_driver` (
  `d_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `d_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `d_license_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `d_license_code` bigint(20) DEFAULT NULL,
  `d_issue_dt` Date NULL DEFAULT NULL,
  `d_person_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `d_dob` Date NULL DEFAULT NULL,
  `d_mar_status_id` bigint(20) DEFAULT NULL,
  `d_access_status_id` bigint(20) DEFAULT NULL,
  `d_crtd_by` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `d_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  KEY `fk_iclub_driver_1_idx` (`d_license_code`),
  KEY `fk_iclub_driver_2_idx` (`d_mar_status_id`),
  KEY `fk_iclub_driver_3_idx` (`d_access_status_id`),
  KEY `fk_iclub_driver_4_idx` (`d_crtd_by`),
  KEY `fk_iclub_driver_5_idx` (`d_person_id`),
  CONSTRAINT `fk_iclub_driver_1` FOREIGN KEY (`d_license_code`) REFERENCES `iclub_license_code` (`lc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_driver_2` FOREIGN KEY (`d_mar_status_id`) REFERENCES `iclub_maritial_status` (`ms_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_driver_3` FOREIGN KEY (`d_access_status_id`) REFERENCES `iclub_access_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_driver_4` FOREIGN KEY (`d_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_driver_5` FOREIGN KEY (`d_person_id`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_driver`
--

LOCK TABLES `iclub_driver` WRITE;
/*!40000 ALTER TABLE `iclub_driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_entity_type`
--

DROP TABLE IF EXISTS `iclub_entity_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_entity_type` (
  `et_id` bigint(20) NOT NULL,
  `et_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `et_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `et_tbl_nm` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `et_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`et_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_entity_type`
--

LOCK TABLES `iclub_entity_type` WRITE;
/*!40000 ALTER TABLE `iclub_entity_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_entity_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_event`
--

DROP TABLE IF EXISTS `iclub_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_event` (
  `e_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `e_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `e_type_id` bigint(20) DEFAULT NULL,
  `e_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `e_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`e_id`),
  KEY `fk_iclub_event_1_idx` (`e_type_id`),
  KEY `fk_iclub_event_2_idx` (`e_crtd_by`),
  CONSTRAINT `fk_iclub_event_1` FOREIGN KEY (`e_type_id`) REFERENCES `iclub_event_type` (`et_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_event_2` FOREIGN KEY (`e_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_event`
--

LOCK TABLES `iclub_event` WRITE;
/*!40000 ALTER TABLE `iclub_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_event_type`
--

DROP TABLE IF EXISTS `iclub_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_event_type` (
  `et_id` bigint(20) NOT NULL,
  `et_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `et_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `et_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`et_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_event_type`
--

LOCK TABLES `iclub_event_type` WRITE;
/*!40000 ALTER TABLE `iclub_event_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_event_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_extras`
--

DROP TABLE IF EXISTS `iclub_extras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_extras` (
  `e_id` bigint(20) NOT NULL,
  `e_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `e_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `e_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `e_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`e_id`),
  KEY `fk_iclub_extras_1_idx` (`e_crtd_by`),
  CONSTRAINT `fk_iclub_extras_1` FOREIGN KEY (`e_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_extras`
--

LOCK TABLES `iclub_extras` WRITE;
/*!40000 ALTER TABLE `iclub_extras` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_extras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_geo_loc`
--

DROP TABLE IF EXISTS `iclub_geo_loc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_geo_loc` (
  `gl_id` bigint(20) NOT NULL,
  `gl_address` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `gl_lat` decimal(10,7) DEFAULT NULL,
  `gl_long` decimal(10,7) DEFAULT NULL,
  `gl_rate` decimal(15,5) DEFAULT NULL,
  `gl_crtd_dt` Date NULL DEFAULT NULL,
  `gl_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`gl_id`),
  KEY `fk_iclub_geo_loc_1_idx` (`gl_crtd_by`),
  CONSTRAINT `fk_iclub_geo_loc_1` FOREIGN KEY (`gl_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_geo_loc`
--

LOCK TABLES `iclub_geo_loc` WRITE;
/*!40000 ALTER TABLE `iclub_geo_loc` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_geo_loc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_id_type`
--

DROP TABLE IF EXISTS `iclub_id_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_id_type` (
  `it_id` bigint(20) NOT NULL,
  `it_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `it_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `it_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`it_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_id_type`
--

LOCK TABLES `iclub_id_type` WRITE;
/*!40000 ALTER TABLE `iclub_id_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_id_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_insurance_item`
--

DROP TABLE IF EXISTS `iclub_insurance_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_insurance_item` (
  `ii_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `ii_quote_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ii_type_id` bigint(20) DEFAULT NULL,
  `ii_item_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ii_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `ii_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`ii_id`),
  KEY `fk_iclub_insurance_item_1_idx` (`ii_type_id`),
  KEY `fk_iclub_insurance_item_3_idx` (`ii_crtd_by`),
  CONSTRAINT `fk_iclub_insurance_item_1` FOREIGN KEY (`ii_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_insurance_item_2` FOREIGN KEY (`ii_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_insurance_item_3` FOREIGN KEY (`ii_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_insurance_item`
--

LOCK TABLES `iclub_insurance_item` WRITE;
/*!40000 ALTER TABLE `iclub_insurance_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_insurance_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_insurance_item_type`
--

DROP TABLE IF EXISTS `iclub_insurance_item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_insurance_item_type` (
  `iit_id` bigint(20) NOT NULL,
  `iit_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `iit_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `iit_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`iit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_insurance_item_type`
--

LOCK TABLES `iclub_insurance_item_type` WRITE;
/*!40000 ALTER TABLE `iclub_insurance_item_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_insurance_item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_insurer_master`
--

DROP TABLE IF EXISTS `iclub_insurer_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_insurer_master` (
  `im_id` bigint(20) NOT NULL,
  `im_name` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `im_trade_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `im_location` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `im_lat` decimal(10,7) DEFAULT NULL,
  `im_long` decimal(10,7) DEFAULT NULL,
  `im_reg_num` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `im_crtd_dt` Date NULL DEFAULT NULL,
  `im_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`im_id`),
  UNIQUE KEY `im_name_UNIQUE` (`im_name`),
  UNIQUE KEY `UK_dqqclkl4vg1j0fmmynwml6mdo` (`im_name`),
  KEY `fk_iclub_insurer_master_1_idx` (`im_crtd_by`),
  CONSTRAINT `fk_iclub_insurer_master_1` FOREIGN KEY (`im_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_insurer_master`
--

LOCK TABLES `iclub_insurer_master` WRITE;
/*!40000 ALTER TABLE `iclub_insurer_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_insurer_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_license_code`
--

DROP TABLE IF EXISTS `iclub_license_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_license_code` (
  `lc_id` bigint(20) NOT NULL,
  `lc_category` char(2) COLLATE utf8_bin DEFAULT NULL,
  `lc_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `lc_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `lc_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `lc_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`lc_id`),
  UNIQUE KEY `lc_category_UNIQUE` (`lc_category`),
  KEY `fk_iclub_license_code_1_idx` (`lc_crtd_by`),
  CONSTRAINT `fk_iclub_license_code_1` FOREIGN KEY (`lc_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_license_code`
--

LOCK TABLES `iclub_license_code` WRITE;
/*!40000 ALTER TABLE `iclub_license_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_license_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_login`
--

DROP TABLE IF EXISTS `iclub_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_login` (
  `l_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `l_name` varchar(70) COLLATE utf8_bin DEFAULT NULL,
  `l_person_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `l_passwd` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `l_role_id` bigint(20) DEFAULT NULL,
  `l_last_date` Date NULL DEFAULT NULL,
  `l_sec_ques_id` bigint(20) DEFAULT NULL,
  `l_sec_ans` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `l_crtd_dt` Date NULL DEFAULT NULL,
  `l_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`l_id`),
  UNIQUE KEY `l_name_UNIQUE` (`l_name`),
  UNIQUE KEY `UK_kfbaek00jrbymnyf6dicbopte` (`l_name`),
  KEY `fk_iclub_login_1` (`l_person_id`),
  KEY `fk_iclub_login_2` (`l_crtd_by`),
  KEY `fk_iclub_login_3_idx` (`l_sec_ques_id`),
  KEY `fk_iclub_login_4_idx` (`l_role_id`),
  CONSTRAINT `fk_iclub_login_1` FOREIGN KEY (`l_person_id`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_login_2` FOREIGN KEY (`l_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_login_3` FOREIGN KEY (`l_sec_ques_id`) REFERENCES `iclub_security_question` (`sq_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_login_4` FOREIGN KEY (`l_role_id`) REFERENCES `iclub_role_type` (`rt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_login`
--

LOCK TABLES `iclub_login` WRITE;
/*!40000 ALTER TABLE `iclub_login` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_maritial_status`
--

DROP TABLE IF EXISTS `iclub_maritial_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_maritial_status` (
  `ms_id` bigint(20) NOT NULL,
  `ms_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `ms_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ms_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ms_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_maritial_status`
--

LOCK TABLES `iclub_maritial_status` WRITE;
/*!40000 ALTER TABLE `iclub_maritial_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_maritial_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_mb_comment`
--

DROP TABLE IF EXISTS `iclub_mb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_mb_comment` (
  `mbc_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `mbc_mb_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `mbc_desc` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `mbc_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `mbc_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`mbc_id`),
  KEY `fk_iclub_mb_comment_1_idx` (`mbc_mb_id`),
  KEY `fk_iclub_mb_comment_2_idx` (`mbc_crtd_by`),
  CONSTRAINT `fk_iclub_mb_comment_1` FOREIGN KEY (`mbc_mb_id`) REFERENCES `iclub_message_board` (`mb_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_mb_comment_2` FOREIGN KEY (`mbc_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_mb_comment`
--

LOCK TABLES `iclub_mb_comment` WRITE;
/*!40000 ALTER TABLE `iclub_mb_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_mb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_message`
--

DROP TABLE IF EXISTS `iclub_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_message` (
  `m_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `m_sent_dt` Date NULL DEFAULT NULL,
  `m_tran_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `m_type_id` bigint(20) DEFAULT NULL,
  `m_content` longblob,
  `m_from_sys_id` bigint(20) DEFAULT NULL,
  `m_to_sys_id` bigint(20) DEFAULT NULL,
  `m_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `m_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `fk_iclub_message_1_idx` (`m_type_id`),
  KEY `fk_iclub_message_2_idx` (`m_from_sys_id`),
  KEY `fk_iclub_message_3_idx` (`m_to_sys_id`),
  KEY `fk_iclub_message_4_idx` (`m_crtd_by`),
  CONSTRAINT `fk_iclub_message_1` FOREIGN KEY (`m_type_id`) REFERENCES `iclub_message_type` (`mt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_message_2` FOREIGN KEY (`m_from_sys_id`) REFERENCES `iclub_system_type` (`st_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_message_3` FOREIGN KEY (`m_to_sys_id`) REFERENCES `iclub_system_type` (`st_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_message_4` FOREIGN KEY (`m_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_message`
--

LOCK TABLES `iclub_message` WRITE;
/*!40000 ALTER TABLE `iclub_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_message_board`
--

DROP TABLE IF EXISTS `iclub_message_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_message_board` (
  `mb_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `mb_title` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `mb_content` longtext COLLATE utf8_bin,
  `mb_tag` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `mb_crtd_by` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `mb_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`mb_id`),
  KEY `fk_iclub_message_board_1_idx` (`mb_crtd_by`),
  CONSTRAINT `fk_iclub_message_board_1` FOREIGN KEY (`mb_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_message_board`
--

LOCK TABLES `iclub_message_board` WRITE;
/*!40000 ALTER TABLE `iclub_message_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_message_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_message_type`
--

DROP TABLE IF EXISTS `iclub_message_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_message_type` (
  `mt_id` bigint(20) NOT NULL,
  `mt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `mt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `mt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`mt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_message_type`
--

LOCK TABLES `iclub_message_type` WRITE;
/*!40000 ALTER TABLE `iclub_message_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_message_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_notif`
--

DROP TABLE IF EXISTS `iclub_notif`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_notif` (
  `n_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `n_type_id` bigint(20) DEFAULT NULL,
  `n_title` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `n_body` longtext COLLATE utf8_bin,
  `n_from_addr` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `n_to_list` varchar(9999) COLLATE utf8_bin DEFAULT NULL,
  `n_status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `n_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `n_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`),
  KEY `fk_iclub_notif_1_idx` (`n_type_id`),
  KEY `fk_iclub_notif_2_idx` (`n_crtd_by`),
  CONSTRAINT `fk_iclub_notif_1` FOREIGN KEY (`n_type_id`) REFERENCES `iclub_notification_type` (`nt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_notif_2` FOREIGN KEY (`n_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_notif`
--

LOCK TABLES `iclub_notif` WRITE;
/*!40000 ALTER TABLE `iclub_notif` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_notif` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_notification_type`
--

DROP TABLE IF EXISTS `iclub_notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_notification_type` (
  `nt_id` bigint(20) NOT NULL,
  `nt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `nt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `nt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`nt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_notification_type`
--

LOCK TABLES `iclub_notification_type` WRITE;
/*!40000 ALTER TABLE `iclub_notification_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_occupation`
--

DROP TABLE IF EXISTS `iclub_occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_occupation` (
  `o_id` bigint(20) NOT NULL,
  `o_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `o_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `o_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `o_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `fk_iclub_occupation_1` (`o_crtd_by`),
  CONSTRAINT `fk_iclub_occupation_1` FOREIGN KEY (`o_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_occupation`
--

LOCK TABLES `iclub_occupation` WRITE;
/*!40000 ALTER TABLE `iclub_occupation` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_occupation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_occupied_status`
--

DROP TABLE IF EXISTS `iclub_occupied_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_occupied_status` (
  `os_id` bigint(20) NOT NULL,
  `os_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `os_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `os_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`os_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_occupied_status`
--

LOCK TABLES `iclub_occupied_status` WRITE;
/*!40000 ALTER TABLE `iclub_occupied_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_occupied_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_owner_type`
--

DROP TABLE IF EXISTS `iclub_owner_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_owner_type` (
  `ot_id` bigint(20) NOT NULL,
  `ot_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `ot_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ot_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_owner_type`
--

LOCK TABLES `iclub_owner_type` WRITE;
/*!40000 ALTER TABLE `iclub_owner_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_owner_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_payment`
--

DROP TABLE IF EXISTS `iclub_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_payment` (
  `p_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `p_policy_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_claim_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_value` decimal(15,5) DEFAULT NULL,
  `p_dr_cr_ind` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_account_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_gen_dt` Date NULL DEFAULT NULL,
  `p_status` bigint(20) DEFAULT NULL,
  `p_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_iclub_payment_1_idx` (`p_policy_id`),
  KEY `fk_iclub_payment_2_idx` (`p_claim_id`),
  KEY `fk_iclub_payment_3_idx` (`p_account_id`),
  KEY `fk_iclub_payment_4_idx` (`p_status`),
  KEY `fk_iclub_payment_5_idx` (`p_crtd_by`),
  CONSTRAINT `fk_iclub_payment_1` FOREIGN KEY (`p_policy_id`) REFERENCES `iclub_policy` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_payment_2` FOREIGN KEY (`p_claim_id`) REFERENCES `iclub_claim` (`c_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_payment_3` FOREIGN KEY (`p_account_id`) REFERENCES `iclub_account` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_payment_4` FOREIGN KEY (`p_status`) REFERENCES `iclub_payment_status` (`ps_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_payment_5` FOREIGN KEY (`p_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_payment`
--

LOCK TABLES `iclub_payment` WRITE;
/*!40000 ALTER TABLE `iclub_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_payment_status`
--

DROP TABLE IF EXISTS `iclub_payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_payment_status` (
  `ps_id` bigint(20) NOT NULL,
  `ps_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `ps_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ps_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_payment_status`
--

LOCK TABLES `iclub_payment_status` WRITE;
/*!40000 ALTER TABLE `iclub_payment_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_payment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_payment_type`
--

DROP TABLE IF EXISTS `iclub_payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_payment_type` (
  `pt_id` bigint(20) NOT NULL,
  `pt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `pt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `pt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_payment_type`
--

LOCK TABLES `iclub_payment_type` WRITE;
/*!40000 ALTER TABLE `iclub_payment_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_person`
--

DROP TABLE IF EXISTS `iclub_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_person` (
  `p_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `p_title` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `p_initials` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `p_f_name` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `p_l_name` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `p_mobile` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `p_email` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `p_contact_pref` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_gender` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_id_type` bigint(20) DEFAULT NULL,
  `p_id_num` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `p_id_issue_cntry` bigint(20) DEFAULT NULL,
  `p_id_issue_dt` date DEFAULT NULL,
  `p_id_expiry_dt` date DEFAULT NULL,
  `p_mar_status` bigint(20) DEFAULT NULL,
  `p_occupation` bigint(20) DEFAULT NULL,
  `p_dob` date DEFAULT NULL,
  `p_is_pensioner` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_address` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `p_lat` decimal(10,7) DEFAULT NULL,
  `p_long` decimal(10,7) DEFAULT NULL,
  `p_zip_cd` int(5) DEFAULT NULL,
  `p_crtd_dt` date DEFAULT NULL,
  `p_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_iclub_person_1_idx` (`p_id_type`),
  KEY `fk_iclub_person_2_idx` (`p_crtd_by`),
  KEY `fk_iclub_person_3_idx` (`p_mar_status`),
  CONSTRAINT `fk_iclub_person_1` FOREIGN KEY (`p_id_type`) REFERENCES `iclub_id_type` (`it_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_person_2` FOREIGN KEY (`p_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_person_3` FOREIGN KEY (`p_mar_status`) REFERENCES `iclub_maritial_status` (`ms_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_person`
--

LOCK TABLES `iclub_person` WRITE;
/*!40000 ALTER TABLE `iclub_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_policy`
--

DROP TABLE IF EXISTS `iclub_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_policy` (
  `p_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `p_number` bigint(20) NOT NULL,
  `p_quote_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_premium` decimal(15,5) DEFAULT NULL,
  `p_prorata_prm` decimal(15,5) DEFAULT NULL,
  `p_debit_dt` int(2) DEFAULT NULL,
  `p_account_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_status_id` bigint(20) DEFAULT NULL,
  `p_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_crtd_dt` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `p_number_UNIQUE` (`p_number`),
  KEY `fk_iclub_policy_1_idx` (`p_quote_id`),
  KEY `fk_iclub_policy_2_idx` (`p_account_id`),
  KEY `fk_iclub_policy_3_idx` (`p_status_id`),
  KEY `fk_iclub_policy_4_idx` (`p_crtd_by`),
  CONSTRAINT `fk_iclub_policy_1` FOREIGN KEY (`p_quote_id`) REFERENCES `iclub_quote` (`q_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_policy_2` FOREIGN KEY (`p_account_id`) REFERENCES `iclub_account` (`a_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_policy_3` FOREIGN KEY (`p_status_id`) REFERENCES `iclub_policy_status` (`ps_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_policy_4` FOREIGN KEY (`p_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_policy`
--

LOCK TABLES `iclub_policy` WRITE;
/*!40000 ALTER TABLE `iclub_policy` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_policy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_policy_status`
--

DROP TABLE IF EXISTS `iclub_policy_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_policy_status` (
  `ps_id` bigint(20) NOT NULL,
  `ps_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `ps_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `ps_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_policy_status`
--

LOCK TABLES `iclub_policy_status` WRITE;
/*!40000 ALTER TABLE `iclub_policy_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_policy_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_product_type`
--

DROP TABLE IF EXISTS `iclub_product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_product_type` (
  `pt_id` bigint(20) NOT NULL,
  `pt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `pt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `pt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_product_type`
--

LOCK TABLES `iclub_product_type` WRITE;
/*!40000 ALTER TABLE `iclub_product_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_property`
--

DROP TABLE IF EXISTS `iclub_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_property` (
  `p_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `p_reg_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `p_address` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `p_lat` decimal(10,7) DEFAULT NULL,
  `p_long` decimal(10,7) DEFAULT NULL,
  `p_postal_cd` int(6) DEFAULT NULL,
  `p_type_id` bigint(20) DEFAULT NULL,
  `p_cover_type_id` bigint(20) DEFAULT NULL,
  `p_purpose_type_id` bigint(20) DEFAULT NULL,
  `p_noclaim_yrs` int(2) DEFAULT NULL,
  `p_rent_fur_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_wall_type_id` bigint(20) DEFAULT NULL,
  `p_roof_type_id` bigint(20) DEFAULT NULL,
  `p_thatch_type_id` bigint(20) DEFAULT NULL,
  `p_comp_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_bar_type_id` bigint(20) DEFAULT NULL,
  `p_norobbery_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_access_type_id` bigint(20) DEFAULT NULL,
  `p_sec_gates_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `p_occupied_status_id` bigint(20) DEFAULT NULL,
  `p_est_value` decimal(15,5) DEFAULT NULL,
  `p_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `p_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `fk_iclub_property_1_idx` (`p_type_id`),
  KEY `fk_iclub_property_2_idx` (`p_cover_type_id`),
  KEY `fk_iclub_property_3_idx` (`p_purpose_type_id`),
  KEY `fk_iclub_property_4_idx` (`p_wall_type_id`),
  KEY `fk_iclub_property_5_idx` (`p_roof_type_id`),
  KEY `fk_iclub_property_6_idx` (`p_thatch_type_id`),
  KEY `fk_iclub_property_7_idx` (`p_access_type_id`),
  KEY `fk_iclub_property_8_idx` (`p_bar_type_id`),
  KEY `fk_iclub_property_9_idx` (`p_occupied_status_id`),
  KEY `fk_iclub_property_10_idx` (`p_crtd_by`),
  CONSTRAINT `fk_iclub_property_1` FOREIGN KEY (`p_type_id`) REFERENCES `iclub_property_type` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_2` FOREIGN KEY (`p_cover_type_id`) REFERENCES `iclub_cover_type` (`ct_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_3` FOREIGN KEY (`p_purpose_type_id`) REFERENCES `iclub_purpose_type` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_4` FOREIGN KEY (`p_wall_type_id`) REFERENCES `iclub_wall_type` (`wt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_5` FOREIGN KEY (`p_roof_type_id`) REFERENCES `iclub_roof_type` (`rt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_6` FOREIGN KEY (`p_thatch_type_id`) REFERENCES `iclub_thatch_type` (`tt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_7` FOREIGN KEY (`p_access_type_id`) REFERENCES `iclub_access_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_8` FOREIGN KEY (`p_bar_type_id`) REFERENCES `iclub_bar_type` (`bt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_9` FOREIGN KEY (`p_occupied_status_id`) REFERENCES `iclub_occupied_status` (`os_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_property_10` FOREIGN KEY (`p_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_property`
--

LOCK TABLES `iclub_property` WRITE;
/*!40000 ALTER TABLE `iclub_property` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_property_type`
--

DROP TABLE IF EXISTS `iclub_property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_property_type` (
  `pt_id` bigint(20) NOT NULL,
  `pt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `pt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `pt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`pt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_property_type`
--

LOCK TABLES `iclub_property_type` WRITE;
/*!40000 ALTER TABLE `iclub_property_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_property_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_purpose_type`
--

DROP TABLE IF EXISTS `iclub_purpose_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_purpose_type` (
  `pt_id` bigint(20) NOT NULL,
  `pt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `pt_long_desc` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `pt_item_type_id` bigint(20) DEFAULT NULL,
  `pt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `pt_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `pt_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`pt_id`),
  KEY `fk_iclub_purpose_type_1_idx` (`pt_crtd_by`),
  KEY `fk_iclub_purpose_type_2_idx` (`pt_item_type_id`),
  CONSTRAINT `fk_iclub_purpose_type_1` FOREIGN KEY (`pt_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_purpose_type_2` FOREIGN KEY (`pt_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_purpose_type`
--

LOCK TABLES `iclub_purpose_type` WRITE;
/*!40000 ALTER TABLE `iclub_purpose_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_purpose_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_quote`
--

DROP TABLE IF EXISTS `iclub_quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_quote` (
  `q_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `q_number` bigint(20) NOT NULL,
  `q_cover_type_id` bigint(20) DEFAULT NULL,
  `q_person_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `q_gen_dt` Date NULL DEFAULT NULL,
  `q_num_items` int(11) DEFAULT NULL,
  `q_gen_premium` decimal(15,5) DEFAULT NULL,
  `q_email` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `q_mobile` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `q_valid_until` Date NULL DEFAULT NULL,
  `q_status_id` bigint(20) DEFAULT NULL,
  `q_product_type` bigint(20) DEFAULT NULL,
  `q_prev_premium` decimal(15,5) DEFAULT NULL,
  `q_insurer_id` bigint(20) DEFAULT NULL,
  `q_is_matched` char(1) COLLATE utf8_bin DEFAULT NULL,
  `q_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `q_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`q_id`),
  UNIQUE KEY `q_number_UNIQUE` (`q_number`),
  KEY `fk_iclub_quote_1` (`q_cover_type_id`),
  KEY `fk_iclub_quote_2` (`q_person_id`),
  KEY `fk_iclub_quote_3` (`q_status_id`),
  KEY `fk_iclub_quote_4` (`q_product_type`),
  KEY `fk_iclub_quote_5` (`q_insurer_id`),
  KEY `fk_iclub_quote_6` (`q_crtd_by`),
  CONSTRAINT `fk_iclub_quote_1` FOREIGN KEY (`q_cover_type_id`) REFERENCES `iclub_cover_type` (`ct_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_quote_2` FOREIGN KEY (`q_person_id`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_quote_3` FOREIGN KEY (`q_status_id`) REFERENCES `iclub_quote_status` (`qs_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_quote_4` FOREIGN KEY (`q_product_type`) REFERENCES `iclub_product_type` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_quote_5` FOREIGN KEY (`q_insurer_id`) REFERENCES `iclub_insurer_master` (`im_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_quote_6` FOREIGN KEY (`q_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_quote`
--

LOCK TABLES `iclub_quote` WRITE;
/*!40000 ALTER TABLE `iclub_quote` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_quote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_quote_status`
--

DROP TABLE IF EXISTS `iclub_quote_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_quote_status` (
  `qs_id` bigint(20) NOT NULL,
  `qs_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `qs_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `qs_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`qs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_quote_status`
--

LOCK TABLES `iclub_quote_status` WRITE;
/*!40000 ALTER TABLE `iclub_quote_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_quote_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_rate_engine`
--

DROP TABLE IF EXISTS `iclub_rate_engine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_rate_engine` (
  `re_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `re_type_id` bigint(20) DEFAULT NULL,
  `re_insure_item_type_id` bigint(20) DEFAULT NULL,
  `re_field_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `re_base_value` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `re_max_value` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `re_rate` decimal(15,5) DEFAULT NULL,
  `re_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `re_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `re_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`re_id`),
  KEY `fk_iclub_rate_engine_1_idx` (`re_type_id`),
  KEY `fk_iclub_rate_engine_2_idx` (`re_insure_item_type_id`),
  KEY `fk_iclub_rate_engine_3_idx` (`re_crtd_by`),
  CONSTRAINT `fk_iclub_rate_engine_1` FOREIGN KEY (`re_type_id`) REFERENCES `iclub_rate_type` (`rt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_rate_engine_2` FOREIGN KEY (`re_insure_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_rate_engine_3` FOREIGN KEY (`re_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_rate_engine`
--

LOCK TABLES `iclub_rate_engine` WRITE;
/*!40000 ALTER TABLE `iclub_rate_engine` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_rate_engine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_rate_type`
--

DROP TABLE IF EXISTS `iclub_rate_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_rate_type` (
  `rt_id` bigint(20) NOT NULL,
  `rt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `rt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `rt_entity_type_id` bigint(20) DEFAULT NULL,
  `rt_item_type_id` bigint(20) DEFAULT NULL,
  `rt_field_nm` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `rt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `rt_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `rt_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`rt_id`),
  KEY `fk_iclub_rate_type_1_idx` (`rt_crtd_by`),
  KEY `fk_iclub_rate_type_2_idx` (`rt_item_type_id`),
  KEY `fk_iclub_rate_type_3_idx` (`rt_entity_type_id`),
  CONSTRAINT `fk_iclub_rate_type_3` FOREIGN KEY (`rt_entity_type_id`) REFERENCES `iclub_entity_type` (`et_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_rate_type_1` FOREIGN KEY (`rt_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_rate_type_2` FOREIGN KEY (`rt_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_rate_type`
--

LOCK TABLES `iclub_rate_type` WRITE;
/*!40000 ALTER TABLE `iclub_rate_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_rate_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_role_type`
--

DROP TABLE IF EXISTS `iclub_role_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_role_type` (
  `rt_id` bigint(20) NOT NULL,
  `rt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `rt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `rt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_role_type`
--

LOCK TABLES `iclub_role_type` WRITE;
/*!40000 ALTER TABLE `iclub_role_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_role_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_roof_type`
--

DROP TABLE IF EXISTS `iclub_roof_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_roof_type` (
  `rt_id` bigint(20) NOT NULL,
  `rt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `rt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `rt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`rt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_roof_type`
--

LOCK TABLES `iclub_roof_type` WRITE;
/*!40000 ALTER TABLE `iclub_roof_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_roof_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_security_device`
--

DROP TABLE IF EXISTS `iclub_security_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_security_device` (
  `sd_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `sd_tracker_id` bigint(20) DEFAULT NULL,
  `sd_item_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `sd_item_type_id` bigint(20) DEFAULT NULL,
  `sd_ser_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sd_contract_num` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sd_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `sd_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`sd_id`),
  KEY `fk_iclub_security_device_1_idx` (`sd_tracker_id`),
  KEY `fk_iclub_security_device_2_idx` (`sd_item_type_id`),
  KEY `fk_iclub_security_device_3_idx` (`sd_crtd_by`),
  CONSTRAINT `fk_iclub_security_device_1` FOREIGN KEY (`sd_tracker_id`) REFERENCES `iclub_tracker_master` (`tm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_security_device_2` FOREIGN KEY (`sd_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_security_device_3` FOREIGN KEY (`sd_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_security_device`
--

LOCK TABLES `iclub_security_device` WRITE;
/*!40000 ALTER TABLE `iclub_security_device` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_security_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_security_master`
--

DROP TABLE IF EXISTS `iclub_security_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_security_master` (
  `sm_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `sm_desc` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `sm_item_type_id` bigint(20) DEFAULT NULL,
  `sm_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  `sm_crtd_dt` Date NULL DEFAULT NULL,
  `sm_crtd_by` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sm_id`),
  KEY `fk_iclub_security_master_1_idx` (`sm_item_type_id`),
  KEY `fk_iclub_security_master_2_idx` (`sm_crtd_by`),
  CONSTRAINT `fk_iclub_security_master_1` FOREIGN KEY (`sm_item_type_id`) REFERENCES `iclub_insurance_item_type` (`iit_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_security_master_2` FOREIGN KEY (`sm_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_security_master`
--

LOCK TABLES `iclub_security_master` WRITE;
/*!40000 ALTER TABLE `iclub_security_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_security_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_security_question`
--

DROP TABLE IF EXISTS `iclub_security_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_security_question` (
  `sq_id` bigint(20) NOT NULL,
  `sq_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `sq_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `sq_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_security_question`
--

LOCK TABLES `iclub_security_question` WRITE;
/*!40000 ALTER TABLE `iclub_security_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_security_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_suppl_master`
--

DROP TABLE IF EXISTS `iclub_suppl_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_suppl_master` (
  `sm_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `sm_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `sm_trade_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `sm_type_id` bigint(20) DEFAULT NULL,
  `sm_reg_num` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `sm_address` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `sm_lat` decimal(10,7) DEFAULT NULL,
  `sm_long` decimal(10,7) DEFAULT NULL,
  `sm_cr_limit` decimal(15,5) DEFAULT NULL,
  `sr_action_dt` date DEFAULT NULL,
  `sm_rating` int(1) DEFAULT NULL,
  `sm_crtd_dt` Date NULL DEFAULT NULL,
  `sm_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sm_id`),
  KEY `fk_iclub_suppl_master_1_idx` (`sm_type_id`),
  KEY `fk_iclub_suppl_master_2` (`sm_crtd_by`),
  CONSTRAINT `fk_iclub_suppl_master_1` FOREIGN KEY (`sm_type_id`) REFERENCES `iclub_supplier_type` (`st_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_suppl_master_2` FOREIGN KEY (`sm_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_suppl_master`
--

LOCK TABLES `iclub_suppl_master` WRITE;
/*!40000 ALTER TABLE `iclub_suppl_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_suppl_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_supplier_type`
--

DROP TABLE IF EXISTS `iclub_supplier_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_supplier_type` (
  `st_id` bigint(20) NOT NULL,
  `st_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `st_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `st_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_supplier_type`
--

LOCK TABLES `iclub_supplier_type` WRITE;
/*!40000 ALTER TABLE `iclub_supplier_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_supplier_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_system_type`
--

DROP TABLE IF EXISTS `iclub_system_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_system_type` (
  `st_id` bigint(20) NOT NULL,
  `st_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `st_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `st_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_system_type`
--

LOCK TABLES `iclub_system_type` WRITE;
/*!40000 ALTER TABLE `iclub_system_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_system_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_thatch_type`
--

DROP TABLE IF EXISTS `iclub_thatch_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_thatch_type` (
  `tt_id` bigint(20) NOT NULL,
  `tt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `tt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `tt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_thatch_type`
--

LOCK TABLES `iclub_thatch_type` WRITE;
/*!40000 ALTER TABLE `iclub_thatch_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_thatch_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_tracker_master`
--

DROP TABLE IF EXISTS `iclub_tracker_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_tracker_master` (
  `tm_id` bigint(20) NOT NULL,
  `tm_name` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `tm_trade_name` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `tm_location` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `tm_lat` decimal(10,7) DEFAULT NULL,
  `tm_long` decimal(10,7) DEFAULT NULL,
  `tm_reg_num` varchar(25) COLLATE utf8_bin DEFAULT NULL,
  `tm_crtd_dt` Date NULL DEFAULT NULL,
  `tm_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`tm_id`),
  UNIQUE KEY `tm_name_UNIQUE` (`tm_name`),
  UNIQUE KEY `UK_g901t9ekec9rm40drt5isj7we` (`tm_name`),
  KEY `fk_iclub_tracker_master_1_idx` (`tm_crtd_by`),
  CONSTRAINT `fk_iclub_tracker_master_1` FOREIGN KEY (`tm_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_tracker_master`
--

LOCK TABLES `iclub_tracker_master` WRITE;
/*!40000 ALTER TABLE `iclub_tracker_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_tracker_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_vehicle`
--

DROP TABLE IF EXISTS `iclub_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_vehicle` (
  `v_id` varchar(36) COLLATE utf8_bin NOT NULL,
  `v_vm_id` bigint(20) DEFAULT NULL,
  `v_odometer` bigint(20) DEFAULT NULL,
  `v_on_area` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `v_on_lat` decimal(10,7) DEFAULT NULL,
  `v_on_long` decimal(10,7) DEFAULT NULL,
  `v_on_access_type_id` bigint(20) DEFAULT NULL,
  `v_dd_area` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `v_dd_lat` decimal(10,7) DEFAULT NULL,
  `v_dd_long` decimal(10,7) DEFAULT NULL,
  `v_dd_access_type_id` bigint(20) DEFAULT NULL,
  `v_year` int(4) DEFAULT NULL,
  `v_driver_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `v_insured_value` decimal(15,5) DEFAULT NULL,
  `v_concess_prct` decimal(15,5) DEFAULT NULL,
  `v_concess_reason` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `v_sec_dev_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `v_imm_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `v_gear_lock_yn` char(1) COLLATE utf8_bin DEFAULT NULL,
  `v_sec_mas_id` bigint(20) DEFAULT NULL,
  `v_owner` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `v_purpose_type_id` bigint(20) DEFAULT NULL,
  `v_noclaim_yrs` int(11) DEFAULT NULL,
  `v_vin` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `v_engine_nr` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `v_reg_num` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `v_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `v_crtd_dt` Date NULL DEFAULT NULL,
  PRIMARY KEY (`v_id`),
  KEY `fk_iclub_vehicle_1_idx` (`v_vm_id`),
  KEY `fk_iclub_vehicle_2_idx` (`v_on_access_type_id`),
  KEY `fk_iclub_vehicle_3_idx` (`v_dd_access_type_id`),
  KEY `fk_iclub_vehicle_4_idx` (`v_driver_id`),
  KEY `fk_iclub_vehicle_5_idx` (`v_sec_dev_id`),
  KEY `fk_iclub_vehicle_6_idx` (`v_sec_mas_id`),
  KEY `fk_iclub_vehicle_7_idx` (`v_purpose_type_id`),
  KEY `fk_iclub_vehicle_8_idx` (`v_crtd_by`),
  CONSTRAINT `fk_iclub_vehicle_1` FOREIGN KEY (`v_vm_id`) REFERENCES `iclub_vehicle_master` (`vm_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_2` FOREIGN KEY (`v_on_access_type_id`) REFERENCES `iclub_access_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_3` FOREIGN KEY (`v_dd_access_type_id`) REFERENCES `iclub_access_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_4` FOREIGN KEY (`v_driver_id`) REFERENCES `iclub_driver` (`d_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_5` FOREIGN KEY (`v_sec_dev_id`) REFERENCES `iclub_security_device` (`sd_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_6` FOREIGN KEY (`v_sec_mas_id`) REFERENCES `iclub_security_master` (`sm_item_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_7` FOREIGN KEY (`v_purpose_type_id`) REFERENCES `iclub_purpose_type` (`pt_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_iclub_vehicle_8` FOREIGN KEY (`v_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_vehicle`
--

LOCK TABLES `iclub_vehicle` WRITE;
/*!40000 ALTER TABLE `iclub_vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_vehicle_master`
--

DROP TABLE IF EXISTS `iclub_vehicle_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_vehicle_master` (
  `vm_id` bigint(20) NOT NULL,
  `vm_make` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `vm_model` varchar(999) COLLATE utf8_bin DEFAULT NULL,
  `vm_orig_rate` decimal(15,5) DEFAULT NULL,
  `vm_mrkt_rate` decimal(15,5) DEFAULT NULL,
  `vm_ret_rate` decimal(15,5) DEFAULT NULL,
  `vm_prod_dt` date DEFAULT NULL,
  `vm_crtd_dt` Date NULL DEFAULT NULL,
  `vm_crtd_by` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`vm_id`),
  UNIQUE KEY `vm_make_UNIQUE` (`vm_make`),
  UNIQUE KEY `UK_mq0cxx9p0kp95kpoj8baq0ekf` (`vm_make`),
  KEY `fk_iclub_vehicle_master_1` (`vm_crtd_by`),
  CONSTRAINT `fk_iclub_vehicle_master_1` FOREIGN KEY (`vm_crtd_by`) REFERENCES `iclub_person` (`p_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_vehicle_master`
--

LOCK TABLES `iclub_vehicle_master` WRITE;
/*!40000 ALTER TABLE `iclub_vehicle_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_vehicle_master` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iclub_wall_type`
--

DROP TABLE IF EXISTS `iclub_wall_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iclub_wall_type` (
  `wt_id` bigint(20) NOT NULL,
  `wt_short_desc` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `wt_long_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `wt_status` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`wt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iclub_wall_type`
--

LOCK TABLES `iclub_wall_type` WRITE;
/*!40000 ALTER TABLE `iclub_wall_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `iclub_wall_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-05 19:06:50
