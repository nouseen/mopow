function getRootPath(){  
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
    var curWwwPath=window.document.location.href;  
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
    var pathName=window.document.location.pathname;  
    var pos=curWwwPath.indexOf(pathName);  
    //获取主机地址，如： http://localhost:8083  
    var localhostPath=curWwwPath.substring(0,pos);  
    //获取带"/"的项目名，如：/uimcardprj  
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
    if(projectName=="/mopow"||projectName=="/jiayu"){ 
    	return(localhostPath+projectName);
    }else{ 
    	return localhostPath;   
    } 
    	    
}   
 
function load_news_list(page){
	var flag=2;
	$.ajax({
		  url:getRootPath()+"/news/listnews.do",
          type:"post",
          async: false,
          data:{"page":page},
          dataType:"json",
          success:function(result){
        	  if(result.data.length==0){
        		 alert("没有了..");
        		 flag=0;
        		 return flag;
          	}
          	if(result.status){
          		$("#newslist").html("");
          		var newsList=result.data;
          		for(var i=0;i<newsList.length;i++){
          			var title=newsList[i].n_title;
          			var time=newsList[i].n_createtime;
          			var id=newsList[i].n_id;
          			var img=newsList[i].n_img;
          			var body=newsList[i].n_body;
          			var re = /<[^>]*>/g;
          			body=body.replace(re, "");
          			body=body.substr(0,150);
          			var type = newsList[i].n_type;
          			var date = new Date(time);
          			var day=date.getDate()<10 ? '0'+date.getDate():date.getDate();
          			var month=date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
          			var s_li='<li class="newsss">'+
							'<div class="post">'+ 
								'<img src="'+img+'">'+
							'</div>'+
							'<div class="news_title">'+
								'<div class="newsdate">'+
									'<p class="t18">'+day+'<br>'+month+'</p>'+
								'</div>'+
								'<div class="title">'+
									'<p class="t18"><a href="news-'+id+'.do">'+title+'</a></p>'+
								'</div>'+
							'</div>'+
							'<div class="news_introduce">'+
								'<p class="t14">'+body+'</p>'+
							'</div>'+
						'</li>';
	          			var $li = $(s_li);
	          			$li.data("newsid",id);
						$("#newslist").append($li);
					
          		}
          	}else{
          	alert("系统繁忙");
          	}
          }
         
		});
			return flag;
		}

function timeStamp2String (time){
    var datetime = new Date();
     datetime.setTime(time);
     var year = datetime.getFullYear();
     var month = datetime.getMonth() + 1;
     var date = datetime.getDate();
     var hour = datetime.getHours();
     var minute = datetime.getMinutes();
     var second = datetime.getSeconds();
     return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
};

/**
 * 后台管理新闻列表
 * @param page
 * @returns {Number}
 */
function load_admin_news_list(page){
	var flag=2;
	$.ajax({
		  url:getRootPath()+"/news/listnews.do",
          type:"post",
          async: false,
          data:{"page":page},
          dataType:"json",
          success:function(result){
        	  if(result.data.length==0){
        		 alert("没有了..");
        		 flag=0;
        		 return flag;
          	}
          	if(result.status){
          		
          		var newsList=result.data;
          		for(var i=0;i<newsList.length;i++){
          			var title=newsList[i].n_title;
          			var time=newsList[i].n_createtime;
          			var id=newsList[i].n_id;
          			var position = newsList[i].n_position;
          			
          			var tuijian;
          			var sztj;
          			var recommend=newsList[i].n_recommend;
          			var xid='"'+id+'"';
          			if(recommend==1){
          				tuijian="<span class='isno' style='color:green;'>"+position+"</span>"
          			}else{
          				tuijian="<span class='isno' style='color:red;'>"+position+"</span>"
          			}
          			   
          			var id1="'"+id+"'";
          			var s_li='<tr class="newsss">'+
								   '<td class="news_title">'+
										'<p class="t18"><a href="../edit-'+id+'.do">'+title+'</a></p>'+
								  '</td>'+
								'<td class="times">'+timeStamp2String(time)+'</td>'+
								'<td>'+tuijian+'</td>'+
								'<td>'+  
								'<a href="../edit-'+id+'.do"><button type="button" class="btn btn-success">编辑</button></a>&nbsp;&nbsp;'+
								'<a href="javascript:;" onclick="deleNew('+id1+')";><button type="button" class="btn btn-danger">删除</button></a>'+
								'</td>'+
						   	'</tr>';
	          			var $li = $(s_li);
	          			$li.data("newsid",id);
						$(".newslist").append($li);
          		}
          	}else{
          	alert("系统繁忙");
          	} 
          }
         
		});
			return flag;
		}


/**
 * 移动端
 * @param page
 * @returns {Number}
 */
function m_load_news_list(page){
	var flag=2;
	$.ajax({
		  url:getRootPath()+"/news/listnews.do",
          type:"post",
          async: false,
          data:{"page":page},
          dataType:"json",
          success:function(result){
        	  if(result.data.length==0){
        		 alert("没有了..");
        		 flag=0;
        		 return flag;
          	}
          	if(result.status){
          		$("#newslist").html("");
          		var newsList=result.data;
          		for(var i=0;i<newsList.length;i++){
          			var title=newsList[i].n_title;
          			var time=newsList[i].n_createtime;
          			var id=newsList[i].n_id;
          			var img=newsList[i].n_img;
          			var body=newsList[i].n_body;
          			var re = /<[^>]*>/g;
          			body=body.replace(re, "");
          			body=body.substr(0,50);
          			var type = newsList[i].n_type;
          			var date = new Date(time);
          			var day=date.getDate()<10 ? '0'+date.getDate():date.getDate();
          			var month=date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
          			var s_li='<li class="newsss">'+
							'<div class="post">'+ 
								'<img src="'+img+'">'+
							'</div>'+
							'<div class="news_title">'+
								'<div class="newsdate">'+
									'<p class="t18">'+day+'<br>'+month+'</p>'+
								'</div>'+
								'<div class="title">'+
									'<p class="t18"><a href="m_news-'+id+'.do">'+title+'</a></p>'+
								'</div>'+
							'</div>'+
							'<div class="news_introduce">'+
								'<p class="t14">'+body+'...</p>'+
							'</div>'+
						'</li>';
	          			var $li = $(s_li);
	          			$li.data("newsid",id);
						$("#newslist").append($li);
					
          		}
          	}else{
          	alert("系统繁忙");
          	}
          }
         
		});
			return flag;
		}

function load_recommend_news(){
	//推荐新闻
	$.ajax({
		url:getRootPath()+"/news/recommendnews.do",
		type:"post",
		data:{"page":1},
		dataType:"json",
		success:function(result){
		if(result.status){
			var newsList=result.data;
			for(var i=0;i<newsList.length&&i<5;i++){
				var title=newsList[i].n_title;
				var body=newsList[i].n_body;
				var id=newsList[i].n_id;
				var re = /<[^>]*>/g;
      			body=body.replace(re, "");
      			body=body.substr(0,70);
      			var s_li='<li class="recommend_newssss">'+
							'<p class="t18"><a href="news-'+id+'.do" target="'+id+'_black">'+title+'</a></p>'+
							'<p class="t14">'+body+'</p>'+
						'</li>';
      				
      				/*"<li class='recommend_body'>"+
								"<div class='r_news_title'>"+
									"<a href='news-"+id+".do' target='"+id+"_black'>"+title+"</a>"+
								"</div>"+
								"<div class='r_news_body'>"+
									"<a>"+body+"</a>"+
								"</div>"+
						  "</li>";*/
      					
				var $li=$(s_li);
				$("#recommend_newssssss").append($li);
			}
		}else{
			alert("系统繁忙！");
		}
		
		}
	});
}

function load_hot_news(){
	//热点新闻
	$.ajax({
		url:getRootPath()+"/news/hotnews.do",
		type:"post",
		data:{"page":2},
		dataType:"json",
		success:function(result){
			if(result.status){
				var newsList=result.data;
          		for(var i=0;i<5;i++){
          			var title=newsList[i].n_title;
          			var time=newsList[i].n_createtime;
          			var id=newsList[i].n_id;
          			var date = new Date(time);
          			var day=date.getDate()<10 ? '0'+date.getDate():date.getDate();
          			var month=date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1;
          			var div ='<li class="hot_newss">'+
										'<div>'+
										'<p class="t14 hot_newss_date">'+day+'-'+month+'</p>'+
									'</div>'+
									'<div>'+'<p class="t14"><a href="news-'+id+'.do" target="'+id+'_black">'+title+'</a><p>'+'</div>'+
								'</li>';
          				
          				/*"<div class='hot_body'>"+
									"<div class='hot_time'>"+
									"<a>"+month+"-"+day+"</a>"+
								"</div>"+
								"<div class='hot_title'>"+
									"<a href='news-"+id+".do' target='"+id+"_black'>"+title+"</a>"+
								"</div>"+
							"</div>";*/
          			$("#hottttt").append(div);
          		}
			}else{
				alert("系统繁忙，请稍后再试！");
				
			}
		}
	});
}