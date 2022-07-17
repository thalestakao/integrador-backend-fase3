CREATE SCHEMA IF NOT EXISTS `icontas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `icontas` ;

-- -----------------------------------------------------
-- Table `icontas`.`tb_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icontas`.`tb_usuario` ;

CREATE TABLE IF NOT EXISTS `icontas`.`tb_usuario` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_by` VARCHAR(255) NULL DEFAULT NULL,
  `creation_date_time` DATETIME(6) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(255) NULL DEFAULT NULL,
  `last_modified_date` DATETIME(6) NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  `senha` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `papel` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_plh3sd5xqp709wamcutkiq85m` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `icontas`.`tb_gerente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icontas`.`tb_gerente` ;

CREATE TABLE IF NOT EXISTS `icontas`.`tb_gerente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_by` VARCHAR(255) NULL DEFAULT NULL,
  `creation_date_time` DATETIME(6) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(255) NULL DEFAULT NULL,
  `last_modified_date` DATETIME(6) NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  `cpf` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `usuario_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_nwybky9nld0f5xneohpth98f1` (`cpf` ASC) VISIBLE,
  INDEX `FK5tfx6sjrxn6aurf5i70di049c` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FK5tfx6sjrxn6aurf5i70di049c`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `icontas`.`tb_usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `icontas`.`tb_correntista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icontas`.`tb_correntista` ;

CREATE TABLE IF NOT EXISTS `icontas`.`tb_correntista` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_by` VARCHAR(255) NULL DEFAULT NULL,
  `creation_date_time` DATETIME(6) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(255) NULL DEFAULT NULL,
  `last_modified_date` DATETIME(6) NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  `agencia` VARCHAR(255) NOT NULL,
  `bairro` VARCHAR(255) NOT NULL,
  `cep` VARCHAR(255) NOT NULL,
  `cidade` VARCHAR(255) NOT NULL,
  `conta` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `endereco` VARCHAR(255) NOT NULL,
  `nome` VARCHAR(255) NOT NULL,
  `telefone` VARCHAR(255) NOT NULL,
  `uf` INT NOT NULL,
  `gerente_id` BIGINT NOT NULL,
  `usuario_id` BIGINT NULL DEFAULT NULL,
  `cpf` VARCHAR(255) NOT NULL,
  `situacao` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4a3ib93fehhtch8pbp2njhn30` (`gerente_id` ASC) VISIBLE,
  INDEX `FKq0jkcptwydi7197xpvdiac3nm` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `FK4a3ib93fehhtch8pbp2njhn30`
    FOREIGN KEY (`gerente_id`)
    REFERENCES `icontas`.`tb_gerente` (`id`),
  CONSTRAINT `FKq0jkcptwydi7197xpvdiac3nm`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `icontas`.`tb_usuario` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `icontas`.`tb_transacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `icontas`.`tb_transacao` ;

CREATE TABLE IF NOT EXISTS `icontas`.`tb_transacao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `created_by` VARCHAR(255) NULL DEFAULT NULL,
  `creation_date_time` DATETIME(6) NULL DEFAULT NULL,
  `last_modified_by` VARCHAR(255) NULL DEFAULT NULL,
  `last_modified_date` DATETIME(6) NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  `saldo_anterior` DECIMAL(19,2) NOT NULL,
  `saldo_atual` DECIMAL(19,2) NOT NULL,
  `tipo_operacao` INT NOT NULL,
  `valor` DECIMAL(19,2) NOT NULL,
  `correntista_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKm9vjmvf7kaxxxvaey2kiqusp4` (`correntista_id` ASC) VISIBLE,
  CONSTRAINT `FKm9vjmvf7kaxxxvaey2kiqusp4`
    FOREIGN KEY (`correntista_id`)
    REFERENCES `icontas`.`tb_correntista` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;