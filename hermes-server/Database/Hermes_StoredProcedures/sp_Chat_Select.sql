DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Chat_Select`(IN `ChatIDV` BIGINT(20) UNSIGNED)
BEGIN
    SELECT
        tbl_chat.ChatID,
        tbl_chat.fk_InitUserID_by,
        tbl_chat.fk_InitUserID_with,
        tbl_chat.KeyValue
    FROM
        tbl_chat
    WHERE
        tbl_chat.ChatID = ChatIDV;
END$$
DELIMITER ;
