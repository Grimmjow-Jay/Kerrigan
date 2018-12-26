layui.use('form', function() {
	var form = layui.form, $ = layui.$;

	// 监听提交
	form.on('submit(login-filter)', function(data) {
		$.ajax({
			url : '/rest/login',
			data : data.field,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				$.ajax({
					url : '/rest/logout',
					data : data.field,
					type : 'POST',
					dataType : 'json',
					success : function(result) {
						layer.msg(JSON.stringify(result));
					}
				})
			}
		})
		return false;
	});
});