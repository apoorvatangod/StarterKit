<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Books</h1>
				<p>This page contains all informations about books</p>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<c:forEach items="${bookList}" var="book">
				<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
					<div class="thumbnail">
						<div class="caption">
							<h3>${book.id}</h3>
							<p>${book.title}</p>
							<p>${book.authors}</p>
							<p>Status: ${book.status}</p>
							<p>
								<a
									href=" <spring:url value="/books/book?id=${book.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-info-sign glyphicon" /></span> Details
								</a>
								<a
									href=" <spring:url value="/books/delete?id=${book.id}" /> "
									class="btn btn-primary"> <span
									class="glyphicon-remove glyphicon" /></span> Delete
								</a>
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-md-5">
				<p>
					<a href="<spring:url value="/" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> back
					</a>

				</p>

			</div>
		</div>
	</section>
	
	<div class="container">
	  <h2>Book table</h2>   
	  <table class="table table-hover">
	    <thead>
	      <tr>
	        <th>Id</th>
	        <th>Title</th>
	        <th>Authors</th>
	        <th>Status</th>
	        <th>Actions</th>
	      </tr>
	    </thead>
	    <tbody>
	     <c:forEach items="${bookList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.authors}</td>
				<td>${book.status}</td>
				<td>
					<a href=" <spring:url value="/books/book?id=${book.id}" /> " class="btn btn-primary"> 
					<span class="glyphicon-info-sign glyphicon" /></span> Details </a>
					
					<a href=" <spring:url value="/books/delete?id=${book.id}" /> " class="btn btn-primary"> 
					<span class="glyphicon-remove glyphicon" /></span> Delete </a>
				</td>
			</tr>
			</c:forEach>
	    </tbody>
	  </table>
	</div>
	
</body>
</html>
