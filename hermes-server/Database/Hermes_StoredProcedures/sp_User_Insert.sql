DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_User_Insert`(IN `UsernameV` VARCHAR(50), IN `PasswordV` CHAR(50), IN `EmailV` VARCHAR(255), IN `NameV` VARCHAR(80))
BEGIN
    INSERT INTO tbl_user (Username, Password, Email, Name, Status, 		   							CreateDate, ModifyDate)
    VALUES ( UsernameV, PasswordV, EmailV, NameV, 1, NOW(), Null );
END$$
DELIMITER ;
