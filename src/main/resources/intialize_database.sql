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
  `name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(128) NULL,
  `user_type` int(4) NOT NULL,
  `sex` VARCHAR(1) NULL,
  `height` INT NULL,
  `weight` INT NULL,
  `availability` INT NULL,
  `context` int(4) NOT NULL,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Tournament`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Tournament` (
  `tournament_id` INT NOT NULL AUTO_INCREMENT,
  `min_players` INT NULL,
  `max_players` INT NULL,
  `teams_per_game` INT NOT NULL DEFAULT 2,
  `officials_per_game` INT NOT NULL DEFAULT 1,
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
  `round_number` INT NOT NULL,
  `game_location` VARCHAR(100) NULL,
  `game_time` TIMESTAMP NULL,
  `tournament_id` INT NOT NULL,
  `next_game_id` INT,
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
  `tournament_id` INT NOT NULL,
  `team_name` VARCHAR(45) NULL,
  `accepts_free_agents` TINYINT(1) NULL,
  `member_id` INT NOT NULL,
  `team_skill` INT NULL DEFAULT 0,
  PRIMARY KEY (`team_id`),
  INDEX `fk_Team_Tournament1_idx` (`tournament_id` ASC),
  INDEX `fk_Team_Player1_idx` (`member_id` ASC),
  CONSTRAINT `fk_Team_Player1`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Team_Tournament1`
    FOREIGN KEY (`tournament_id`)
    REFERENCES `cs309t13`.`Tournament` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Survey` (
  `survey_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `tournament_id` INT NOT NULL,
  `survey_score` INT NOT NULL,
  PRIMARY KEY (`survey_id`),
  INDEX `fk_tournament_id_idx` (`tournament_id` ASC),
  INDEX `fk_Survey_PlayerId_idx` (`member_id` ASC),
  CONSTRAINT `fk_Survey_PlayerId`
    FOREIGN KEY (`member_id`)
    REFERENCES `Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tournament_id` FOREIGN
    KEY (`tournament_id`)
    REFERENCES `Tournament` (`tournament_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`teamgamemapper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`teamgamemapper` (
  `team_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`team_id`, `game_id`),
  INDEX `fk_game_id1_idx` (`game_id` ASC),
  CONSTRAINT `fk_team_id1`
    FOREIGN KEY (`team_id`)
    REFERENCES `cs309t13`.`team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_id1`
    FOREIGN KEY (`game_id`)
    REFERENCES `cs309t13`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`teamplayermapper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`teamplayermapper` (
  `team_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`team_id`, `member_id`),
  INDEX `fk_member_id1_idx` (`member_id` ASC),
  CONSTRAINT `fk_team_id2`
    FOREIGN KEY (`team_id`)
    REFERENCES `cs309t13`.`team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_member_id1`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`officialgamemapper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`officialgamemapper` (
  `member_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`member_id`, `game_id`),
  INDEX `fk_game_mapper2_idx` (`game_id` ASC),
  CONSTRAINT `fk_officialid_mapper1`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`official` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gameid_mapper2`
    FOREIGN KEY (`game_id`)
    REFERENCES `cs309t13`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `cs309t13`.`Availability`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Availability` (
  `availability_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`availability_id`),
  INDEX `fk_Availability_MemberId_idx` (`member_id` ASC),
  CONSTRAINT `fk_Availability_MemberId`
    FOREIGN KEY (`member_id`)
    REFERENCES `Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Day`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Day` (
  `day_id` INT NOT NULL AUTO_INCREMENT,
  `availability_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`day_id`),
  INDEX `fk_Day_AvailabilityId_idx` (`availability_id` ASC),
  CONSTRAINT `fk_Day_AvailabilityId`
    FOREIGN KEY (`availability_id`)
    REFERENCES `Availability` (`availability_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Period`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Period` (
  `period_id` INT NOT NULL AUTO_INCREMENT,
  `day_id` INT NOT NULL,
  `slot` int(7) NOT NULL,
  PRIMARY KEY (`period_id`),
  INDEX `fk_Period_DayId_idx` (`day_id` ASC),
  CONSTRAINT `fk_Period_DayId`
    FOREIGN KEY (`day_id`)
    REFERENCES `Day` (`day_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`teaminvitedplayermapper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`teaminvitedplayermapper` (
  `team_id` INT NOT NULL,
  `member_id` INT NOT NULL,
  PRIMARY KEY (`team_id`, `member_id`),
  INDEX `fk_Mapper_Player_idx` (`member_id` ASC),
  CONSTRAINT `fk_Mapper_Team`
    FOREIGN KEY (`team_id`)
    REFERENCES `cs309t13`.`team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Mapper_Player`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs309t13`.`Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Score` (
  `score` INT NOT NULL DEFAULT 0,
  `team_id` INT NOT NULL,
  `game_id` INT NOT NULL,
  PRIMARY KEY (`team_id`, `game_id`),
  INDEX `fk_game_id2_idx` (`game_id` ASC),
  INDEX `fk_team_id2_idx` (`team_id` ASC),
  CONSTRAINT `fk_team_id4`
    FOREIGN KEY (`team_id`)
    REFERENCES `cs309t13`.`team` (`team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_game_id4`
    FOREIGN KEY (`game_id`)
    REFERENCES `cs309t13`.`game` (`game_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `cs309t13`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cs309t13`.`Notification` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `text` VARCHAR(45) NOT NULL,
  `url` VARCHAR(45) NULL,
  `time` TIMESTAMP,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_Notification_MemberId_idx` (`member_id` ASC),
  CONSTRAINT `fk_Notification_MemberId`
    FOREIGN KEY (`member_id`)
    REFERENCES `Member` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
    
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
