DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_MessageSearch_Select`(in userID bigint(20) unsigned, in searchText varchar(100))
BEGIN
	SELECT
		tbl_chat.fk_InitUserID_by,
		tbl_chat.fk_InitUserID_with,
		tbl_message.Content,
		tbl_message.RecieveDateTime,
		tbl_message.fk_SenderUserID,
		tbl_message.MessageID,
		tbl_chat.ChatID
	FROM
		tbl_message
	INNER JOIN tbl_chat ON tbl_message.fk_ChatID = tbl_chat.ChatID
	WHERE
		(
			tbl_chat.fk_InitUserID_with = userID
			OR tbl_chat.fk_InitUserID_by = userID
		) AND tbl_message.Content LIKE searchText;
END$$
DELIMITER ;
