@import org.svenehrke.demo.inbound.web.OOBPersonPageModel

@param boolean devMode
@param String vm


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Main Page</title>
	@if(devMode)
		<script src="http://localhost:35729/livereload.js"></script>
	@endif
	<script src="/assets/js/mount.js" type="module"></script>
	<style>
		.label { font-size: 1.2em; color: wheat}
		body {
			/*font-family: Arial, Verdana, Tahoma;*/
			color: #ffffff;
			background-color: #003B6F; // tardis-blue;
			padding-top:  20px;
			padding-left: 50px;
			width: 50%;
		}
	</style>

</head>
<body>
<h1>Page 1</h1>

<div data-island="page" data-props="${vm}"></div>

</body>
</html>
