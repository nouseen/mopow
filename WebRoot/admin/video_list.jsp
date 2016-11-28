<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
<base href="<%=basePath%>">  
 <link href="admin/moban/bootstrap2.3.2/css/bootstrap.css" rel="stylesheet" />
<title>视频提交</title>

<style type="text/css">
#vid{
	margin: auto;
	padding-top:20px;
}
.container{
	width:1300px;
}
.col-xs-3 {
    margin-bottom:20px; 
}
.thumbnail {
	margin-bottom:0px;
}
.text-center{
	display:block;
}
.modal-content iframe {
	width:100%;
	height:300px;
}
</style>	

</head>
  
  <body>
 	 
  	   <div class="container">
        <h2>视频列表</h2>
        <div class="row">
            <div class="col-xs-12 ">
                <h3 class="page-header">
                   <small>&nbsp;</small>
                    <div class="btn-group pull-right">
                      <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                      	    操作<span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="#" data-toggle="modal" data-target="#myModa3">删除</a></li>
                      </ul>
                    </div>
                </h3>
            </div>
        </div>
             <div class="row" id="vid">
                      
           </div>
       	  <nav style="text-align:center;" >
			 <ul class="pagination pages">
			 </ul>
			</nav>
    </div>
    <!-- 显示视频 -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4 class="modal-title" id="myModalLabel"></h4>
          </div>
          <div class="modal-body" id="modal-content">
          		<iframe width="100%"  frameborder="0" allowfullscreen="" >
						       			  暂时无法播放视频！！！
				</iframe>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 删除视频-->
    <div class="modal fade" id="myModa3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title" id="myModalLabe3">确定删除吗？</h4>
          </div>
          <div class="modal-footer">
            <span class="pull-left  xuanze"></span>
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-danger" id="delete">确定</button>
          </div>
        </div>
      </div>
    </div>
	
	
  </body>
  <script type="text/javascript" src="admin/moban/scripts/jquery-1.9.1.min.js"></script>
  	 <script src="admin/moban/bootstrap2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
  <script type="text/javascript">
	$(function(){
		$.ajax({
       			url:"home/homevideo.do",
       			type:"post",
       			data:{
					"page":1,
				},
       			dataType:"json",
       			success:function(result){
       				if(result.status==1){
       					var video = result.data;
       					for(var i=0;i<video.length;i++){
       						var pic=video[i].v_pic;
       						var url=video[i].v_url;
       						var id=video[i].v_id;
       						var name=video[i].v_name;
       						
       						var   s_li='<div class="col-xs-3">'+
	                          '<a href="javascript:;" class="thumbnail">'+
	                         	 '<img class="tupian" urls="'+url+'" name="'+name+'"  style="width:100%; height:180px;" src="'+pic+'">'+
		                         ' </a>'+
		                         '<input class="pull-left" type="checkbox" value="'+id+'"/>'+
		                         '<a href="admin/video_edit.html?id='+id+'"  class="text-center">'+name+'</a>'+
		                       '</div>';
       						
       						var $li=$(s_li);
							$li.data("id",id);
							$("#vid").append($li);
       					}
       				}else{ 
       					alert("系统错误！");
       				
       				}
       			
       			}
       		});
				$.ajax({
					url:"product/vidpage.do",
					type:"post",
					data:{
						"page":1,
					},
					dataType:"json",
					success:function(result){
						if(result.status==1){
							$(".pages").append(result.data);
							return false;
						}else{
							alert("系统繁忙！")
						}
					}
				});	
				
		
			 $("body").on("click",".tupian",function(){
	        	 $('#myModalLabel').html($(this).attr('name'));
	             $('#myModal iframe').attr('src',$(this).attr('urls') );
	             $('#myModal').modal('show');
	      	});
			
			 $(function () { $('#myModal').on('hide.bs.modal', function () {
				 $('#myModal iframe').removeAttr('src');
			  });
			 });
			 
			 
			 
			 $('#delete').click(function() {
	                var ids = "";
	                if(!$("input[type='checkbox']").is(':checked')){
	                	$(".xuanze").html("请至少选择一个");
	                }else{
	                $('input[type=checkbox]:checked').each(function() {
	                    ids += $(this).val() + ',';
	                }); 
	                $.post('video/delevideo.do', {
	                    id: ids
	                    
	                },function(data, status) {
	                	$(".xuanze").html("");
	                    $('#myModa3').modal('hide');
	                    location.href = 'admin/video_list.jsp';
	                });
	                }
	          });
        	/* $("#vid").on("click",".vname",function(){
			var id=$(this).parent().data("id");
			$("#v_id").val(id);
			$.ajax({
        			url:"../home/findbyid.do",
        			type:"post",
        			data:{"id":id},
        			dataType:"json",
        			success:function(result){
        				if(result.status==1){
        				var video = result.data;
        				var pic = video.v_pic;
        				$("#option img").attr("src",pic);
        				$("#delete").unbind("click").click(function(){
        					$.ajax({
        					url:"video/delevideo.do?id="+id,
        					type:"post",
        					dataType:"json",
        					success:function(result){
        						if(result.status==1){
        							alert("删除成功");
        							location.reload();
        						}
        						
        					}
        					});
        				});
        				}
        				}
        	});
		
			}); */
		});
		
	
	  function jumpPage(page){
			$(".col-xs-3").remove();
			$(".pages li").remove();
			$.ajax({
       			url:"home/homevideo.do",
       			type:"post",
       			data:{
					"page":page,
				},
       			dataType:"json",
       			success:function(result){
       				if(result.status==1){
       					var video = result.data;
       					for(var i=0;i<video.length;i++){
       						var pic=video[i].v_pic;
       						var url=video[i].v_url;
       						var id=video[i].v_id;
       						var name=video[i].v_name;
       						
       						var   s_li='<div class="col-xs-3">'+
	                          '<a href="javascript:;" class="thumbnail">'+
	                         	 '<img class="tupian" urls="'+url+'" name="'+name+'"  style="width:100%; height:180px;" src="'+pic+'">'+
		                         ' </a>'+
		                         '<input class="pull-left" type="checkbox" value="'+id+'"/>'+
		                         '<a href="admin/video_edit.html?id='+id+'" class="text-center">'+name+'</a>'+
		                       '</div>';
       						
       						var $li=$(s_li);
							$li.data("id",id);
							$("#vid").append($li);
       					}
       				}else{ 
       					alert("系统错误！");
       				
       				}
       			
       			}
       		});
				$.ajax({
					url:"product/vidpage.do",
					type:"post",
					data:{
						"page":page,
					},
					dataType:"json",
					success:function(result){
						if(result.status==1){
							$(".pages").append(result.data);
							return false;
						}else{
							alert("系统繁忙！")
						}
					}
				});	
	  };
</script>  
</html>
