<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>   
  <head>  
  	<link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon"/>
    <base href="<%=basePath%>">  
      
    <title>图片上传</title>  
       
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
     <link href="admin/moban/bootstrap2.3.2/css/bootstrap.min.css" rel="stylesheet" />
     <link href="admin/moban/bootstrap2.3.2/css/fileinput.css" rel="stylesheet" />
    <script src="admin/moban/scripts/jquery-1.9.1.min.js"></script>
    <!-- 
    <link rel="stylesheet" type="text/css" href="styles.css"> 
    -->  
    <style type="text/css">
    	.file-drop-zone {
		    height:auto; 
		} 
    </style>
<!-- 	<script type="text/javascript" src="js/cookie_util.js"></script> -->
  </head>  
    
  <body>  
   <!--  <form action="admin/upload.do" method="post" enctype="multipart/form-data">  
    
   		 请选择图片:<input type="file" name="fileName"/><input type="submit" value="上传"/>  
   		 
   		 
    </form>   -->
    <div class="container kv-main">
            <h2>图片上传</h2>
            <br>
            <form enctype="multipart/form-data">
                
                <div class="form-group">
                    <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false">
                </div>
                
            </form> 
        </div>
  </body>  
   
  <script src="admin/moban/bootstrap2.3.2/js/fileinput.js" type="text/javascript"></script>
  <script src="admin/moban/bootstrap2.3.2/js/fileinput_locale_zh.js" type="text/javascript"></script> 
  <script src="admin/moban/bootstrap2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
  
  <script type="text/javascript">  
  $("#file-1").fileinput({ 
      uploadUrl: 'admin/upload.do', // you must set a valid URL here else you will get an error
      allowedFileExtensions : ['jpg', 'png','gif','jpeg'],
      overwriteInitial: false, 
      //allowedFileTypes: ['image', 'video', 'flash'],
      slugCallback: function(filename) {
          return filename.replace('(', '_').replace(']', '_');
      }
	});  
  </script>	
</html>  