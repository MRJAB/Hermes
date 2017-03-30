DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserByEmail_Select`(IN `EmailV` VARCHAR(50), IN `PasswordV` CHAR(50))
BEGIN
	SELECT
		tbl_user.UserID,
        tbl_user.Username,
		tbl_user.Name
	FROM
		tbl_user
	WHERE
		tbl_user.Email = EmailV
		AND tbl_user.`Password` = PasswordV;
END$$
DELIMITER ;
