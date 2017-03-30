DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_ChatAndMessages_Select`(IN `userIDV` BIGINT(20) UNSIGNED)
BEGIN
	SELECT
		tbl_chat.ChatID ,
		tbl_message.Content,
		tbl_message.RecieveDateTime,
		tbl_message.MessageID,
		tbl_message.fk_SenderUserID
	FROM
		tbl_message
	RIGHT OUTER JOIN tbl_chat 
    				ON tbl_message.fk_ChatID = tbl_chat.ChatID
	WHERE
		tbl_chat.fk_InitUserID_by = userIDV
		OR tbl_chat.fk_InitUserID_with = userIDV
	
	ORDER BY
    	tbl_chat.ChatID,
		tbl_message.RecieveDateTime ASC;
END$$
DELIMITER ;
