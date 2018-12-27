// OK
/**
layui.define(function(e) {
    e("mytest", {
		mytestfunction: function(t) {
			// layer.msg('abc test');
			alert("abc");
		}
	})
});
*/

// OK
layui.define("layer", function(e) {
    e("mytest", {
		mytestfunction: function(t) {
			layer.msg('abc test');
		}
	})
});