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
<title>websocket聊天awdawdawd</title>
<link rel="stylesheet" href="${ctx}/static/layui/css/layui.css"/>
<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
 <script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
  <style>
    html{background-color: #D9D9D9;}
  </style>
</head>
<body>

<div style="margin: 300px auto; text-align: center; font-size: 20px;">
  <a href="http://www.layui.com/doc/" target="blank">前去看官方文档</a>
</div>

<script>

    var socket = null;
    var path = '<%=basePath%>';
    var im = {
        getUid:function () {
          var uid = '${sessionScope.user.id }';
            return uid;
        },
        init:function(){
            if ('WebSocket' in window){
                var uid = im.getUid();
                if(!uid){
                    alert("您未登录！");
                    window.location.href='${ctx}';
                }else {
                    var socketUrl = 'ws://'+path+'/chat?uid='+ uid;
                    socket = new WebSocket(socketUrl);
                    im.startListener();
                }
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
                    console.log("接收到消息");
                    im.handleMessage(event.data);
                }
                // 连接关闭的回调方法
                socket.onclose = function () {
                    console.log("关闭连接！!");
                    alert("异地登录");
                    window.location.href='${ctx}';
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

    //基础配置
    layim.config({

      //初始化接口
      init: {
          url: '${ctx}/api/getList'
          , data: {uid: im.getUid()}
      }

      //简约模式（不显示主面板）
      //,brief: true

      //查看群员接口
      ,members: {
        url: '/layim/api?action=member'
        ,data: {}
      }

      ,uploadImage: {
        url: '/layim/upload?t=img' //（返回的数据格式见下文）
      }

      ,uploadFile: {
        url: '/layim/upload?t=file' //（返回的数据格式见下文）
      }

      //,skin: ['aaa.jpg'] //新增皮肤
      //,isfriend: false //是否开启好友
      //,isgroup: false //是否开启群组
      //,min: true //是否始终最小化主面板（默认false）
      ,title:'我的通讯工具'
      ,notice:true
      ,chatLog: './demo/chatlog.html' //聊天记录地址
      ,find: './demo/find.html'
      ,copyright: true //是否授权
    });


    //监听发送消息
    layim.on('sendMessage', function(data){

        var msg = JSON.stringify(data);
        socket.send(msg);
    });

    //监听在线状态的切换事件
    layim.on('online', function(data){
      console.log(data);
    });


    //layim建立就绪
    layim.on('ready', function(res){
    	
    });

    //监听查看群员
    layim.on('members', function(data){
      console.log(data);
    });

    //监听聊天窗口的切换
    layim.on('chatChange', function(data){
      console.log(data);
    });



  });

</script>
</body>
</html>
