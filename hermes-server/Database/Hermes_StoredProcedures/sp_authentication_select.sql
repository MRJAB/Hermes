DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_authentication_select`(IN `EmailV` VARCHAR(50))
BEGIN
	SELECT
		tbl_user.*
	FROM
		tbl_user
    WHERE 
    username = EmailV;
END$$
DELIMITER ;
