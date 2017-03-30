DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_MessageByChat_Select`(IN `fk_ChatIDV` BIGINT(20) UNSIGNED)
    NO SQL
BEGIN
 Select tbl_message.MessageID,
  		tbl_message.fk_SenderUserID,
  		tbl_message.fk_ChatID,
  		tbl_message.Content,
  		tbl_message.RecieveDateTime
  From	tbl_message
  Where	tbl_message.fk_ChatID = fk_ChatIDV;
END$$
DELIMITER ;
