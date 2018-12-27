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
				if (result.success) {
					window.location.reload();
				} else {
					layer.alert(result.message, {
						skin : 'layui-layer-lan',
						closeBtn : 0
					});
				}
			}
		})
		return false;
	});
});