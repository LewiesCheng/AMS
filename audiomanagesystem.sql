#
# Table structure for table administrator
#

CREATE TABLE audiomanagesystem.administrator (
  aId INT NOT NULL AUTO_INCREMENT,
  aName VARCHAR(20) NOT NULL,
  aPassword VARCHAR(20) NOT NULL,
  PRIMARY KEY (aId),
  UNIQUE INDEX `aId_UNIQUE` (aId ASC),
  UNIQUE INDEX `aName_UNIQUE` (aName ASC)
 )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
INSERT INTO audiomanagesystem.administrator VALUES ('1','liucheng','liucheng');
INSERT INTO audiomanagesystem.administrator VALUES ('2','huangzhongyu','huangzhongyu');
INSERT INTO audiomanagesystem.administrator VALUES ('3','kanjie','kanjie');
INSERT INTO audiomanagesystem.administrator VALUES ('4','shixinhua','shixinhua');


#
# Table structure for table customers
#

CREATE TABLE audiomanagesystem.customers (
  cId INT NOT NULL AUTO_INCREMENT,
  cName VARCHAR(20) NOT NULL,
  cPassword VARCHAR(20) NULL DEFAULT NULL,
  cContactWay VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (cId),
  UNIQUE INDEX `cId_UNIQUE` (cId ASC),
  UNIQUE INDEX `cName_UNIQUE` (cName ASC)
 )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
INSERT INTO audiomanagesystem.customers VALUES ('1','liucheng','liu','10001');
INSERT INTO audiomanagesystem.customers VALUES ('2','huangzhongyu','huang','10002');
INSERT INTO audiomanagesystem.customers VALUES ('3','kanjie','kan','10003');
INSERT INTO audiomanagesystem.customers VALUES ('4','shixinhua','shi','10004');
INSERT INTO audiomanagesystem.customers VALUES ('5','chenxin','chen','10005');
INSERT INTO audiomanagesystem.customers VALUES ('6','shenya','shen','10006');
INSERT INTO audiomanagesystem.customers VALUES ('7','sunyahui','sun','10007');
INSERT INTO audiomanagesystem.customers VALUES ('8','yuanxu','yuan','10008');

#
# Table structure for table products
#

CREATE TABLE audiomanagesystem.products (
  pId INT NOT NULL AUTO_INCREMENT,
  pType VARCHAR(45) NULL DEFAULT NULL,
  pName VARCHAR(45) NOT NULL,
  pInventory INT NOT NULL,
  pPriceOneDay INT NOT NULL,
  PRIMARY KEY (pId),
  UNIQUE INDEX `cId_UNIQUE` (pId ASC)
 )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
INSERT INTO audiomanagesystem.products VALUES ('1','DVD','c”Ô—‘','10','2');
INSERT INTO audiomanagesystem.products VALUES ('2','DVD','cpp','8','3');
INSERT INTO audiomanagesystem.products VALUES ('3','DVD','java','16','4');
INSERT INTO audiomanagesystem.products VALUES ('4','DVD','html','1','5');
INSERT INTO audiomanagesystem.products VALUES ('5','VCD','servlet','0','2');
INSERT INTO audiomanagesystem.products VALUES ('6','VCD','jsp','1','2');
INSERT INTO audiomanagesystem.products VALUES ('7','VCD','database','2','4');
INSERT INTO audiomanagesystem.products VALUES ('8','CD','web','5','2');
INSERT INTO audiomanagesystem.products VALUES ('9','CD','algorithms','0','5');
INSERT INTO audiomanagesystem.products VALUES ('10','CD','math','3','4');
INSERT INTO audiomanagesystem.products VALUES ('11','CD','english','3','4');


#
# Table structure for table hire
#

CREATE TABLE audiomanagesystem.hire (
  hId INT NOT NULL AUTO_INCREMENT,
  hCustomerId INT NOT NULL,
  hProductId INT NOT NULL,
  hNumber INT NOT NULL,
  hBeginDay DATE NOT NULL,
  hReturnDay DATE NULL DEFAULT NULL,
  hDeposit INT NULL DEFAULT NULL,
  hTotalPrice INT NULL DEFAULT NULL,
  hWhetherReturn VARCHAR(10) NOT NULL,
  PRIMARY KEY (hId),
  UNIQUE INDEX `cId_UNIQUE` (hId ASC)
 )ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
INSERT INTO audiomanagesystem.hire VALUES ('1', '1', '1', '1', '2016-04-17','2016-04-22','0','10','yes');
INSERT INTO audiomanagesystem.hire VALUES ('2', '2', '1', '1', '2016-04-17','2016-04-22','0','10','yes');
INSERT INTO audiomanagesystem.hire VALUES ('3', '2', '2', '1', '2016-04-20','2016-04-22','0','6','yes');
INSERT INTO audiomanagesystem.hire VALUES ('4', '2', '3', '1', '2016-04-20',NULL,'50',NULL,'no');
INSERT INTO audiomanagesystem.hire VALUES ('5', '2', '4', '1', '2016-04-21',NULL,'50',NULL,'no');
INSERT INTO audiomanagesystem.hire VALUES ('6', '2', '5', '1', '2016-04-22',NULL,'50',NULL,'no');
INSERT INTO audiomanagesystem.hire VALUES ('7', '2', '6', '1', '2016-04-22',NULL,'50',NULL,'no');