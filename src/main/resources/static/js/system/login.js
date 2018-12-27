layui.use('form', function() {
	var form = layui.form, $ = layui.$;

	// 监听提交
	form.on('submit(login-filter)', function(data) {
		$.ajax({
			url : '/auth/login',
			data : data.field,
			type : 'POST',
			dataType : 'json',
			success : function(result) {
				
			}
		})
		return false;
	});
});