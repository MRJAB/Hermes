DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserPassword_Update`(IN `UserIDV` BIGINT(20) UNSIGNED, IN `PasswordOldV` CHAR(50), IN `PasswordNewV` VARCHAR(255))
BEGIN
    UPDATE tbl_user
    SET  Password=PasswordNewV,
         ModifyDate=NOW()
    WHERE UserID=UserIDV AND Password= PasswordOldV;  
END$$
DELIMITER ;
