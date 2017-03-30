DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` FUNCTION `fn_ChatCreateValidity_Bool`(`First_UserID` BIGINT(20) UNSIGNED, `Second_UserID` BIGINT(20) UNSIGNED) RETURNS tinyint(1)
BEGIN

RETURN
    (
    SELECT 
       IF( COUNT(tbl_chat.ChatID) > 0,false,true ) IsValid
    FROM
        tbl_chat
    WHERE
        (tbl_chat.fk_InitUserID_by = First_UserID
        AND tbl_chat.fk_InitUserID_with = Second_UserID)
        OR
       (tbl_chat.fk_InitUserID_by = Second_UserID
        AND tbl_chat.fk_InitUserID_with = First_UserID)
    );
END$$
DELIMITER ;
