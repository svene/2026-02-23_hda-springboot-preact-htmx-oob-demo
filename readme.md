# HDA using preact components

Hypermedia Driven Application using preact components

Proof of Concept for the architecture described
by Thomas Schilling's https://tschuehly.de/preact-islands-in-spring-boot-with-htmx-when-alpinejs-isnt-enough-anymore[great article].

## Stack

- Spring Boot
- JTE
- preact (for JSX components and signals)

## Usage

- In a Terminal in the root folder run `bun run watch`  
  (there is also the IntelliJ run configuration 'bun watch' to do this)
- Start Springboot Application (`src/main/java/org/svenehrke/demo/Application.java`)
- Point browser to: http://localhost:8080/ which should display page1

## Credits

This is completely inspired by:

- https://tschuehly.de/preact-islands-in-spring-boot-with-htmx-when-alpinejs-isnt-enough-anymore

Initially I just wanted to apply the approach described in the post to my personal mini-demo project I usually use
for hypermedia driven applications.  

I took some of the things from the post like the mount.js script
and then applied HTMX and hyperscript functionality as my app uses this.

It turned out I had to modify mount.js for this to get it working because:
- new HTML elements needed to be processed explicitly after the preact rendering process was done.  
This is because HTMX and hyperscript are not aware of the preact rendering process.  
Therefore after the preact-rendering call in mount.js I call htmx.process() and _hyperscript.process() explicitly.
  (ideally I could hook into a 'render-finished' event which would be dispatched by preact. Maybe there is one, I have to check) 
- Preact's render function always works on the inner HTML of an element.  
But I did not want the element with it's long VM-JSON string to stay in the DOM.  
Therefore I applied a change to mount.js to remove it.

In when I came to the HTML-OOB cases of the app I noticed that they do not work. 
The reason is that usually OOB responses are handled by HTMX when the HTTP response arrives at the browser. 
But with the preact rendering process is not happening at all.
Maybe there is a way to tell HTMX to "process" these elements rendered by react, but I doubt it.
Instead I decided to ditch the OOB functionality and replace it with the event based approach.

## Internal Architecture

## Development
