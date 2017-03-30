DELIMITER $$
CREATE DEFINER=`id865672_admin`@`%` PROCEDURE `sp_Chat_Insert`(IN `fk_InitUserID_byV` BIGINT(20) UNSIGNED, IN `fk_InitUserID_withV` BIGINT(20) UNSIGNED)
BEGIN
  IF (fn_ChatCreateValidity_Bool(fk_InitUserID_byV,fk_InitUserID_withV))
  THEN
  
  INSERT INTO tbl_chat 
  		   (fk_InitUserID_by,
        	fk_InitUserID_with,
        	KeyValue,
        	CreateDate)
  VALUES   (fk_InitUserID_byV,
          	fk_InitUserID_withV,
          	1,
          	NOW());
   END If;
   SELECT LAST_INSERT_ID() as ID;
END$$
DELIMITER ;
