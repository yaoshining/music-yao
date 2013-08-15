define(["jquery","app/model/Shortcut"],function($,Shortcut){
	return Backbone.Model.extend({
    	defaults: {
    		shortcuts: [
    		],
    		layout: "fit"
    	},
    	initialize: function(){
    		Backbone.Model.apply(this,arguments);
    	},
    	constructor: function(){
    		
    	},
    	say: function(){
			var shortcut = new Shortcut({"kjfts":"hjhdgfs"});
			shortcut.author();
		}
    });
});