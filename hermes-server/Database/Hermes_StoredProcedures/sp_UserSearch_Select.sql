DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserSearch_Select`(IN `user_username` VARCHAR(50))
    NO SQL
BEGIN
	SELECT
		tbl_user.UserID,
        tbl_user.Username,
		tbl_user.Name
	FROM
		tbl_user
	WHERE
		tbl_user.Username LIKE CONCAT( user_username, '%'); 
END$$
DELIMITER ;
