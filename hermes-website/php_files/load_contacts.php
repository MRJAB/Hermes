<?php
require( "database_connection.php" );
$user = $_SESSION[ 'user_id' ];
$query = "CALL sp_ChatAll_select($user)";

if ( $result = $pdo->query( $query ) ) {
	$arr = NULL;
	while ( $row = $result->fetch( PDO::FETCH_ASSOC ) ) {
		$arr[] = $row;
	}
	if ( count( $arr ) > 0 ) {
		for ( $i = 0; $i < count( $arr ); $i++ )

		{
			if ( empty( $arr[ $i ][ 'Name' ] ) ) {
				$arr[ $i ][ 'Name' ] = "Unknown";
			}
			echo "<li id='" . $arr[ $i ][ 'ChatID' ] . "' title=''><span>" . $arr[ $i ][ 'Name' ] . "</span>
			<span class='deleteButton'>Delete</span></li>";
		}
	}

} else {
	var_dump( $pdo->errorInfo() );

}
unset( $pdo );






?>