<?php

function select_query( $query ) {
	require( "database_connection.php" );
	if ( $result = $pdo->query( $query ) ) {
		$arr = array();
		while ( $row = $result->fetch( PDO::FETCH_ASSOC ) ) {
			array_push( $arr, $row );

		}
		if ( !empty( $arr ) ) {
			$jsonstr = json_encode( $arr );
			return $jsonstr;
		} else {
			return "empty";

		}
	} else {
		var_dump( $pdo->errorInfo() );

	}
}
?>