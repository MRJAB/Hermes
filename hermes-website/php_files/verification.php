<?php

if ( !empty( $_POST[ 'loginData' ] ) ) {

	$loginData = htmlentities( trim( $_POST[ 'loginData' ] ) );
	$data = explode( "|", $loginData );
	$username = htmlentities( trim( $data[ 0 ] ) );
	$password = htmlentities( trim( $data[ 1 ] ) );
	$p = hash( "Sha256", $password );
	require( "database_connection.php" );

	$query = "CALL sp_User_Select('$username','$p')";
	if ( $result = $pdo->query( $query ) ) {
		$arr = array();
		while ( $row = $result->fetch( PDO::FETCH_ASSOC ) ) {
			array_push( $arr, $row );

		}
		if ( count( $arr ) === 1 ) {
			session_start();

			$_SESSION[ 'username' ] = $username;
			$_SESSION[ 'name' ] = $arr[ 0 ][ 'Name' ];
			$_SESSION[ 'user_id' ] = $arr[ 0 ][ 'UserID' ];

			echo "login_success";

		} else {
			echo "false";
		}
		unset( $result );



	} else {
		print_r( $pdo->errorInfo() );

	}

} else if ( $_POST[ 'signupData' ] ) {

	$signupData = trim( $_POST[ 'signupData' ] );
	$data = json_decode( $signupData );

	$username = $data->username;
	$password = $data->password;
	$email = $data->email;
	$name = $data->name;
	$p = hash( "Sha256", $password );
	require( "database_connection.php" );
	$query = "CALL sp_User_Insert('$username','$p','$email','$name')";
	if ( $result = $pdo->exec( $query ) ) {
		echo "signup_success";
	} else {
		$pdo->errorInfo();
		echo "signup_failed";
	}

} else {
	exit( "Javascript disabled" );
}

unset( $pdo );

?>