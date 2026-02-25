async function doit(el) {
	const name = el.dataset.island;
	const props = JSON.parse(el.dataset.props || "{}");

	try {
		const {render, h, App} = await import(`/assets/fe/${name}.js`);
		// since render always sets innerHTML use parent to remove element after it has been processed:
		const parent = el.parentElement;
		el.innerHTML = "";
		console.log('rendering');
		render(h(App, props), parent);
		htmx.process(parent)
	} catch (err) {
		console.error(`Failed to mount island "${name}":`, err);
		el.innerHTML = '<div class="alert alert-error">Component could not be loaded.</div>';
	}
}

function processIslands() {
	const els = document.body.querySelectorAll('div[data-island]:not([processed])')
	els.forEach(async (el) => {
		el.setAttribute('processed', '');
		console.log('island', el.dataset.island);
		doit(el); // do not wait
	});
}

window.preactObserver = new MutationObserver((mutations) => {
	processIslands();
});
window.preactObserver.observe(document.documentElement, {childList: true, subtree: true});

// initial:
processIslands();

/*
// TODO: replace MutationObserver with:
processIslands();
document.body.addEventListener("htmx:afterSwap", (evt) => {
	processIslands(evt.target);
});
*/
