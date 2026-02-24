@param boolean devMode
@param String vm


<!DOCTYPE html>
<html lang="en" x-data="$store.darkMode" x-bind:data-theme="theme">
<head>
	<meta charset="UTF-8">
	<title>People Admin Application</title>
	<link rel="icon" href="data:image/svg+xml,<svg xmlns=%22http://www.w3.org/2000/svg%22 viewBox=%220 0 100 100%22><text y=%22.9em%22 font-size=%2290%22>🎯</text></svg>"/>
	<link rel="stylesheet" href="/assets/css/bulma.min.css"/>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
	@if(devMode)
		<script src="http://localhost:35729/livereload.js"></script>
	@endif
	<script src="/assets/js/htmx.org/extensions/path-params.js"></script>
	<script src="/assets/js/hyperscript.org/0.9.14/_hyperscript.js"></script>
	<script defer src="/assets/js/main.js"></script>
	<script defer src="/assets/js/alpinejs/3.15.4/cdn.min.js"></script>
	<script src="/assets/js/mount.js" type="module"></script>
	<script src="/assets/js/htmx.org/2.0.8/htmx.js"></script>
	<script>
		//htmx.logAll();
	</script>

</head>
<body>
<section class="hero is-link">
	<div class="hero-body">
		<nav className="level">
			<div className="level-left">
				<p className="title">People Admin Application</p>
			</div>
			<div className="level-right">
				<button x-on:click="$store.darkMode.toggle()">
					<span className="icon"><i className="material-icons" x-text="icon"></i></span>
				</button>
			</div>
		</nav>
	</div>
</section>

<div> <%-- wrapper element (see mount.js) --%>
<div data-island="page" data-props="${vm}"></div>
</div>

</body>
</html>
