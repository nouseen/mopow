$(function () {
    $(".content-left,.content-right").height($(window).height() - 130);
    
    $.ajax({
        url:"../user/getUserName.do",
        type:"post",
        data:"",
        dataType:"json",
        success:function(result){
        	  if(result.data){
       			$(".username").html(result.data);
        	  }
			}
		}); 
    
})


    function loginout(){
	if(confirm("确定退出么?")){
    	   $.ajax({
    	        url:"../user/loginout.do",
    	        type:"post",
    	        data:"",
    	        dataType:"json",
    	        success:function(result){
    	        		  window.location.href="../log_in.html";
    			}
    	}); 
		
	}
    }

$(".accordion-inner").click(function () {
    $(".active").html($(this).find(".left-body").text());
})

$(window).resize(function () {
    $(".content-left,.content-right").height($(window).height() - 130);
})