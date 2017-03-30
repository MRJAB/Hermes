DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Message_Delete`(IN `MessageIDV` BIGINT(20) UNSIGNED, IN `fk_ChatIDV` BIGINT(20) UNSIGNED)
BEGIN
    DELETE FROM tbl_message
    WHERE tbl_message.MessageID = MessageIDV 
    		AND tbl_message.fk_ChatID = fk_ChatIDV;
END$$
DELIMITER ;
