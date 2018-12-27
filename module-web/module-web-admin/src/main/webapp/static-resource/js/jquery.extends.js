jQuery.extend({
	popup : {
		show : function(partid, title) {
			var targetPartId = '#' + partid;
			layer.open({
				 title:title,
				 content: $(targetPartId),
				 type: 1,
				 fixed: true,
				 area: ['30%', '100%'],
				 offset: 'r',
				 anim: 3,
				 skin: 'layui-layer-molv',
				 cancel: function(index){ 
					 $(targetPartId).hide();
					 layer.close(index);
			     }    
			 });
		}
	}
});