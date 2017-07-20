<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String path = request.getContextPath();
String basePath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实时日志系统</title>
<link rel="stylesheet" href="${ctx}/static/layui/css/layui.css"/>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
 <script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
  <style>
    html{background-color: #D9D9D9;}
  </style>
</head>
<body>
<div>
	<button class="layui-btn" onclick="clearCache()" style="position: fixed;top: 20px;right:50px">清空日志</button>
</div>
<div id="log-container" style="overflow-y: scroll;padding: 10px;height:620px">
		<div>
		</div>
</div>

<script>
	function clearCache(){
		$("#log-container div").empty();
	}



    var socket = null;
    var path = '<%=basePath%>';
    var im = {
        getUid:function () {
          var uid = '${sessionScope.user.id }';
            return uid;
        },
        init:function(){
            if ('WebSocket' in window){
            	 var socketUrl = 'ws://'+path+'/log';
                 socket = new WebSocket(socketUrl);
                 im.startListener();
            } else {
                alert('当前浏览器不支持WebSocket功能，请更换浏览器访问。');
            }
        },
        startListener:function () {
            if (socket) {
                // 连接发生错误的回调方法
                socket.onerror = function () {
                    console.log("连接失败!");
                };
                // 连接成功建立的回调方法
                socket.onopen = function (event) {
                    console.log("连接成功");
                    layer.msg("链接成功");
                }
                // 接收到消息的回调方法
                socket.onmessage = function (event) {
                	$("#log-container div").append(event.data);
        			// 滚动条滚动到最低部
        			$("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
                }
                // 连接关闭的回调方法
                socket.onclose = function () {
                    console.log("关闭连接！!");
                    layer.msg("连接关闭，请刷新页面!");
                }
            }
        },
        handleMessage:function (msg) {
            var msg = JSON.parse(msg);
            switch (msg.type){
                case 'TYPE_TEXT_MESSAGE':
                    layim.getMessage(msg.msg);
                    break;
                default:
                    break;
            }
        }
    };

    


  layui.use(['layim','layer'], function(layim){

    window.layim = layim;
    window.layer = layer;
    im.init();



  });

</script>
</body>
</html>
