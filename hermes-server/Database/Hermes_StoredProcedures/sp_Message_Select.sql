DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Message_Select`(IN `MessageIDV` BIGINT(20) UNSIGNED)
BEGIN
 Select tbl_message.MessageID,
  		tbl_message.fk_SenderUserID,
  		tbl_message.fk_ChatID,
  		tbl_message.Content,
  		tbl_message.RecieveDateTime
  From	tbl_message
  Where	tbl_message.MessageID = MessageIDV;
END$$
DELIMITER ;
