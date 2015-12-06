<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="//docs.meteor.com/favicon.png" type="image/png" />
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<!-- DataTables -->
	<link rel="stylesheet" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
	<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<title>Books</title>
	<script>
		jQuery(document).ready(function() {
		    $('#example').DataTable();
		} );
	</script>
</head>
<body>
<h1>
	Books
</h1>

<table id="example">
	<thead>
		<tr>
			<td>Title</td>
			<td>Author</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="book" items="${books}" varStatus="status">
		<tr>
			<td>${book.title}</td>
			<td>${book.authors[0]}</td>
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td>Title</td>
			<td>Author</td>
		</tr>
	</tfoot>
</table>
</body>
</html>
