require([
        "jquery",
        "jqueryLib/jquery-migrate-1.2.1.min",
        "app/Desktop",
        "underscore",
        "backbone"
        ],function($,migrate,Desktop,underscore,backbone){
	var desktop = new Desktop();
	desktop.say();
	alert(desktop.get("shortcuts"));
});