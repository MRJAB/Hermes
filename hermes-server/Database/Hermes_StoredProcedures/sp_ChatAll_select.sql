DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_ChatAll_select`(IN `userIDV` BIGINT(20) UNSIGNED)
BEGIN
        (SELECT
        tbl_chat.ChatID,
        tbl_chat.fk_InitUserID_by,
        tbl_chat.fk_InitUserID_with,
     	tbl_user.Username,
     	tbl_user.Name
    FROM
        tbl_chat LEFT Join tbl_user on tbl_chat.fk_InitUserID_with = tbl_user.UserID
    WHERE
        tbl_chat.fk_InitUserID_by = userIDV
     	
    )

	UNION
    (SELECT        
		tbl_chat.ChatID,
        tbl_chat.fk_InitUserID_by,
        tbl_chat.fk_InitUserID_with,
     	tbl_user.Username,
     	tbl_user.Name
    FROM
        tbl_chat LEFT Join tbl_user on tbl_chat.fk_InitUserID_by = tbl_user.UserID
    WHERE
        tbl_chat.fk_InitUserID_with = userIDV);
END$$
DELIMITER ;
