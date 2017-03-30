DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Check_Chat`(IN `userID` BIGINT(20) UNSIGNED, IN `friendID` BIGINT(20) UNSIGNED)
BEGIN
	SELECT
		tbl_chat.ChatID as chatID
    From tbl_chat
	WHERE
		(tbl_chat.fk_InitUserID_by = userID
		AND tbl_chat.fk_InitUserID_with = friendID)
        OR
		(tbl_chat.fk_InitUserID_by = friendID
		AND tbl_chat.fk_InitUserID_with = userID);
END$$
DELIMITER ;
