DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserStatus_Update`(IN `UserIDV` BIGINT(20) UNSIGNED, IN `StatusV` BIT(1))
BEGIN
    UPDATE tbl_user
    SET  Status=StatusV,
         ModifyDate=NOW()
    WHERE UserID=UserIDV;  
END$$
DELIMITER ;
