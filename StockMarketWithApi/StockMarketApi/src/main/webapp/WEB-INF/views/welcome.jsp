<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Hello</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>${greeting} 
				<img src="https://www.asme.org/getmedia/c2c8ea5a-b690-4ba7-92bb-34bd1432862b/book_guide_hero_books.aspx" class="img-rounded" alt="Web store" align="right" width="150" height="auto">
				</h1>
				
				<p>${info}</p>
				
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Books</h3>
						<p>Display all books</p>
						<p><!-- dodano /all -->
							<a href="/webstore/books/all" class="btn btn-default"> <span
								class="glyphicon-info-sign glyphicon" /></span> Show all books
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="container">
		<div class="row">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Add book</h3>
						<p>Create new book</p>
						<p>
							<a href="/webstore/books/add" class="btn btn-default"> <span
								class="glyphicon-plus glyphicon" /></span> Add book
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<section class="container">
		<div class="row">
			<div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
				<div class="thumbnail">
					<div class="caption">
						<h3>Find book</h3>
						<p>Search for book</p>
						<p>
							<a href="/webstore/books/find" class="btn btn-default"> <span
								class="glyphicon-search glyphicon" /></span> Find book
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
