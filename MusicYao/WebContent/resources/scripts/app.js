requirejs.config({
	"baseUrl": "resources/scripts",
    "paths": {
	    "app": "app",
	    "jqueryLib": "jquery",
	    "jquery": "jquery/jquery-1.10.2",
    }
});
requirejs(["app/main"]);