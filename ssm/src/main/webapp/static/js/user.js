$(function(){
	
	$.ajax({
		type:'post',
		url:'user_list2',
		data:{
			
		},
		success:function(data) {
			$('#list').empty();
			var th = '<tr><th>ID</th><th>姓名</th><th>密码</th></tr>';
			$('#list').append(th);
			for (var i = 0 ; i < data.length; i ++ ) {
				var tr = '<tr><td>'+data[i].id+'</td><td>'+data[i].username+'</td><td>'+data[i].password+'</td></tr>';
				$('#list').append(tr);
			}
		},
		dataType:'json'
	});
	
});