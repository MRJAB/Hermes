DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_User_Select`(IN `UsernameV` VARCHAR(50), IN `PasswordV` CHAR(50))
BEGIN
	SELECT
		tbl_user.UserID,
		tbl_user.Name
	FROM
		tbl_user
	WHERE
		tbl_user.Username = UsernameV
		AND tbl_user.Password = PasswordV;
END$$
DELIMITER ;
