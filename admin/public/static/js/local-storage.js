LocalStorage = {
	get: function(key){
		let v = localStroage.getItem(key);
		if(v && typeof(v) !== "undefined" && v !== "undefined"){
			return JSON.parse(v);
		}
	},
	set: function (key, data){
		localStroage.setItem(key, JSON.stringify(data));
	},
	remove: function (key){
		localStroage.removeItem(key);
	},
	clearAll: function (){
		localStroage.clear();
	}
};