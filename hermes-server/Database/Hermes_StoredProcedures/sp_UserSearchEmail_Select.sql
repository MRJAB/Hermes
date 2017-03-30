DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_UserSearchEmail_Select`(IN `user_email` VARCHAR(255))
BEGIN
	SELECT
		tbl_user.UserID,
        tbl_user.Username,
        tbl_user.Password,
		tbl_user.Name
	FROM
		tbl_user
	WHERE
		tbl_user.Email = user_email; 
END$$
DELIMITER ;
