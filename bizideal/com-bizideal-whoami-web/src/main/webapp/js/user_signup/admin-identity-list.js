$(function(){
		$('#_btn').click(function(){
			$("#mtrmShenfen").show();
		});
		$('#_yes').click(function(){
			var identity = $('#new_identity').val();
			console.log(identity);
			console.log(JSON.stringify({'identity':identity}));
			$.ajax({
				url:contentpath+'/signupField/addIdentity',
				type:'post',
				contentType:'application/json',
				data:JSON.stringify({'identity':identity,'id':'0'}),
				async:false,
				success:function(data){
					if(data.status==0){
		  				$.prompt("添加成功",function(){
		  					location.reload();
	    				});
					}else{
						$.prompt(data.msg);
					}
				},
				error:function(data){
					$.prompt('系统错误!');
				}
			});
			$('#new_identity').val('');
			$("#mtrmShenfen").hide();
		});
		$('#_no').click(function(){
			$('#new_identity').val('');
			$("#mtrmShenfen").hide();
		})
	});
	$('._delete_identity').click(function(){
		var id = $(this).parents('div.form-group').attr('data-field-id');
		$.affirm('确定删除吗?',function(){
			$.ajax({
				url:contentpath+'/signupField/delIdentity/'+id,
				type:'post',
				async:false,
				success:function(data){
					if(data.status==0){
		  				$.prompt("删除成功",function(){
		  					location.reload();
	    				});
					}else{
						$.prompt(data.msg);
					}
				},
				error:function(data){
					$.prompt('系统错误!');
				}
			})
		})
	});