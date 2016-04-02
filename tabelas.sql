/*****************************************
		TABELA DE MODELOS
*****************************************/
CREATE TABLE IF NOT EXISTS `zup`.`models` (
  `id` BIGINT(20) NOT NULL,
  `url` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8


/*****************************************
		TABELA DE ATRIBUTOS
*****************************************/
CREATE TABLE IF NOT EXISTS `zup`.`attributes` (
  `id` BIGINT(20) NOT NULL,
  `model_id` BIGINT(20) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `type` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_model_id_idx` (`model_id` ASC),
  CONSTRAINT `fk_model_id`
    FOREIGN KEY (`model_id`)
    REFERENCES `zup`.`models` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

/*****************************************
		TABELA DE VALORES DOS ATRIBUTOS
*****************************************/
CREATE TABLE IF NOT EXISTS `zup`.`attribute_values` (
  `id` BIGINT(20) NOT NULL,
  `attribute_id` BIGINT(20) NOT NULL,
  `value_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`, `attribute_id`, `value_id`),
  INDEX `fk_attribute_id_idx` (`attribute_id` ASC),
  CONSTRAINT `fk_attribute_id`
    FOREIGN KEY (`attribute_id`)
    REFERENCES `zup`.`attributes` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

/*****************************************
		TABELA DE VALORES INTEIROS
*****************************************/
CREATE TABLE IF NOT EXISTS `zup`.`int_values` (
  `id` BIGINT(20) NOT NULL,
  `value` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

/*****************************************
		TABELA DE VALORES DE DATA
*****************************************/
CREATE TABLE IF NOT EXISTS `zup`.`datetime_values` (
  `id` BIGINT(20) NOT NULL,
  `value` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

/**************************************************
		TABELA DE VALORES DE PONTO FLUTUANTE
**************************************************/
CREATE TABLE IF NOT EXISTS `zup`.`double_values` (
  `id` BIGINT(20) NOT NULL,
  `value` DECIMAL(10,5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8

/**************************************************
		TABELA DE VALORES DE PALAVRAS
***************************************************/
CREATE TABLE IF NOT EXISTS `zup`.`string_values` (
  `id` BIGINT(20) NOT NULL,
  `value` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8