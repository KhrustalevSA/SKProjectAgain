<html>
<head>
<title>Hello World!</title>
</head>
<body>
	<h1>Hello It's a Simple Kitchen!</h1>
	<p>
		It is now
		<%= new java.util.Date() %></p>
	<p>
		You are coming from 
		<%= request.getRemoteAddr()  %></p>
</body>