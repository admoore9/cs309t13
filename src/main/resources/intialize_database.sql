SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `cs309t13` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `cs309t13` ;

-- -----------------------------------------------------
-- Table `cs309t13`.`Member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `member_name` VARCHAR(45) NULL,
  `member_username` VARCHAR(45) NULL,
  `member_password` VARCHAR(45) NULL,
  `is_admin` TINYINT(1) NULL,
  `is_official` TINYINT(1) NULL,
  `user_type` int(4) NOT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Player`
-- -----------------------------------------------------
CREATE TABLE `cs309t13`.`Player` (
  `member_id` INT NOT NULL,
  `is_free_agent` TINYINT(1) NULL DEFAULT 0,
  INDEX `fk_member_id_idx` (`member_id` ASC),
  CONSTRAINT `fk_member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Admin`
-- -----------------------------------------------------
CREATE TABLE `cs309t13`.`Admin` (
  `member_id` INT NOT NULL,
  `current_view` int(4) NOT NULL,
  INDEX `fk_member_id_idx` (`member_id` ASC),
  CONSTRAINT `fk_member_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Tournament` (
  `tournament_id` INT NOT NULL AUTO_INCREMENT,
  `min_players` INT NULL,
  `max_players` INT NULL,
  `is_double_elimination` TINYINT(1) NULL,
  `is_started` TINYINT(1) NULL,
  `tournament_name` VARCHAR(45) NULL,
  PRIMARY KEY (`tournament_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs309t13`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Game` (
  `game_id` INT NOT NULL AUTO_INCREMENT,
  `game_location` VARCHAR(100) NULL,
  `game_time` TIMESTAMP NULL,
  `tournament_id` INT NOT NULL,
  `next_game_id` INT NOT NULL,
  PRIMARY KEY (`game_id`),
  INDEX `fk_Game_Tournament_idx` (`tournament_id` ASC),
  INDEX `fk_Game_Game1_idx` (`next_game_id` ASC),
  CONSTRAINT `fk_Game_Tournament`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `cs309t13`.`Tournament` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_Game1`
    FOREIGN KEY (`next_game_id`)
    REFERENCES `cs309t13`.`Game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs309t13`.`Team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Team` (
  `team_id` INT NOT NULL AUTO_INCREMENT,
  `game_id` INT NULL,
  `tournament_id` INT NOT NULL,
  `team_name` VARCHAR(45) NULL,
  `accepts_free_agents` TINYINT(1) NULL,
  PRIMARY KEY (`team_id`),
  INDEX `fk_Team_Game1_idx` (`game_id` ASC),
  INDEX `fk_Team_Tournament1_idx` (`tournament_id` ASC),
  CONSTRAINT `fk_Team_Game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `cs309t13`.`Game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Team_Tournament1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `cs309t13`.`Tournament` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
