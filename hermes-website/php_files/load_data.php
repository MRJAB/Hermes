<?php

session_start();
require( "select_query.php" );
$userInfo = $_SESSION[ 'user_id' ];
if ( !empty( $_POST[ 'saveChat' ] ) ) {

	$data = json_decode( $_POST[ 'saveChat' ] );
	$chatId = $data->chatId;
	$msg = $data->message;
	//print_r( $data );

	if ( empty( $chatId ) ) {

		$friendInfo = $data->friendId;
		$query3 = "CALL sp_Check_Chat($userInfo,$friendInfo)";
		$data = select_query( $query3 );
		if($data==="empty")
		{
		$query2 = "CALL sp_Chat_Insert($userInfo,$friendInfo)";
		select_query( $query2 );
		$query3 = "CALL sp_Check_Chat($userInfo,$friendInfo)";
		$data = select_query( $query3 );
		echo $data;
		$idDecode = json_decode( $data );
		$chatId = $idDecode[0]->chatID;
		}
		else
		{
		echo $data;
		$idDecode = json_decode( $data );
		$chatId = $idDecode[ 0 ]->chatID;	
		}
	}
	

	$query = "CALL sp_Message_Insert ($userInfo,$chatId,'".$msg."')";
	select_query( $query );

} elseif ( !empty( $_POST[ 'loadChat' ] ) ) {

	$data = json_decode( $_POST[ 'loadChat' ] );
	$chatId = $data->chatId;
	$query = "CALL sp_MessageByChat_Select( $chatId)";
	echo select_query( $query );
}

elseif ( !empty( $_POST[ 'search_term' ] ) ) {

	$search_term = $_POST[ 'search_term' ];
	$query = "CALL sp_UserSearch_Select('$search_term')";
	echo select_query( $query );
}
elseif ( isset( $_POST[ 'latestChat' ] ) ) {

	$ids = $_POST[ 'latestChat' ];
//$query = "CALL sp_ChatAll_select($userInfo)";	
	
  $query= "(SELECT
        tbl_chat.ChatID,
        tbl_chat.fk_InitUserID_by,
        tbl_chat.fk_InitUserID_with,
         tbl_user.Username,
         tbl_user.Name
    FROM
        tbl_chat LEFT Join tbl_user on tbl_chat.fk_InitUserID_with = tbl_user.UserID
    WHERE
        tbl_chat.fk_InitUserID_by =$userInfo and tbl_chat.ChatID not IN ($ids)
        )
	UNION
    
	(SELECT        
        tbl_chat.ChatID,
        tbl_chat.fk_InitUserID_by,
        tbl_chat.fk_InitUserID_with,
         tbl_user.Username,
         tbl_user.Name
    FROM
        tbl_chat LEFT Join tbl_user on tbl_chat.fk_InitUserID_by = tbl_user.UserID
    WHERE
        tbl_chat.fk_InitUserID_with = $userInfo and tbl_chat.ChatID not IN ($ids))";
	echo select_query( $query );
}
elseif ( !empty( $_POST['deletetChat'] ) ) {

			$id = $_POST[ 'deletetChat'];
			$query = "CALL sp_Chat_Update($id,$userInfo)";
			select_query( $query );
			$arr[]=$id;
			echo json_encode($arr);

}
else {
	die( "empty" );
}



?>