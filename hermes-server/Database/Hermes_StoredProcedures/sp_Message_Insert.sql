DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Message_Insert`(IN `fk_SenderUserIDV` BIGINT(20) UNSIGNED, IN `fk_ChatIDV` BIGINT(20) UNSIGNED, IN `ContentV` VARCHAR(8000))
BEGIN
  INSERT INTO tbl_message ( fk_SenderUserID,
        		  			fk_ChatID,
        		  			Content,
        		  			RecieveDateTime)
  VALUES ( fk_SenderUserIDV,
           fk_ChatIDV,
           ContentV,
           now());
END$$
DELIMITER ;
