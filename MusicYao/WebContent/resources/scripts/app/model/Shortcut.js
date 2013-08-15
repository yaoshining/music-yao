define(["backbone"],function(backbone){
	return Backbone.Model.extend({
		  defaults: {
			icon: {
				url: undefined
			}
		  },
		  initialize: function(settings) { alert(settings); },
		  constructor: function() {
			  Backbone.Model.apply(this,arguments);
		  },
		  author: function() { alert("author"); },
		  coordinates: function() { alert("coordinates"); },
		  allowedToEdit: function(account) {
		    return true;
		  }
	});
});