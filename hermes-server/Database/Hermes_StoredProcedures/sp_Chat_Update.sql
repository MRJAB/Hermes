DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Chat_Update`(IN `ChatIDV` BIGINT(20) UNSIGNED, IN `userIDV` BIGINT(20) UNSIGNED)
BEGIN
    UPDATE tbl_chat
    SET  tbl_chat.fk_InitUserID_by = Null
    WHERE
  		tbl_chat.ChatID =ChatIDV AND
        tbl_chat.fk_InitUserID_by = userIDV;
        
    UPDATE tbl_chat
    SET  tbl_chat.fk_InitUserID_with = Null
    WHERE
  		tbl_chat.ChatID =ChatIDV AND
        tbl_chat.fk_InitUserID_with = userIDV;
END$$
DELIMITER ;
