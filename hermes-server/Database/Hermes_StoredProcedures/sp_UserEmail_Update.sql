DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserEmail_Update`(IN `UserIDV` BIGINT(20) UNSIGNED, IN `EmailV` VARCHAR(255))
BEGIN
    UPDATE tbl_user
    SET  Email=EmailV,
         ModifyDate=NOW()
    WHERE UserID=UserIDV;  
END$$
DELIMITER ;
