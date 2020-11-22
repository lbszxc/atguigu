<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/14
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- http://localhost:8080/admin_webui_war/test/ssm.html -->
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/ "/>
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btnArrOne").click(function () {
                $.ajax({
                   url:"send/array/one.html",                               //请求目标资源的位置
                   type:"post",                                         //请求的方式
                   data:{"array":[4,8,12]},                           //要发送请求的参数
                   dataType:"text",                                  //如何对待服务器端返回的数据
                   success:function (response) {                     //服务器端成功请求处理后调用的回调函数，response响应数据
                        alert(response);
                   },
                    error:function (response) {                     //服务器端失败请求处理后调用的回调函数，response响应数据
                        alert(response);
                    }
                });
            });

            $("#btnArrTwo").click(function () {
                $.ajax({
                    url:"send/array/two.html",
                    type:"post",
                    data: {
                        "array[0]":5,
                        "array[1]":2,
                        "array[2]":0,
                    },
                    dataType:"text",
                    success:function (response) {
                        alert(response);
                    },
                    error:function (response) {
                        alert(response);
                    }
                });
            });

            $("#btnArrThree").click(function () {

                //准备好要发送到服务器端的数组
                var array = [1,3,1,4];
                console.log(array.length);

                //将JSON数组转换为JSON字符串
                var requestBody = JSON.stringify(array);
                //"['1','3','1','4']"
                console.log(requestBody.length);

                $.ajax({
                    url:"send/array/three.html",
                    type:"post",
                    data: requestBody,                                      //请求体
                    contentType:"application/json;charset=UTF-8",       //设置请求的类型，就是告诉服务器端本次请求的请求体是JSON数据
                    dataType:"text",
                    success:function (response) {
                        alert(response);
                    },
                    error:function (response) {
                        alert(response);
                    }
                });
            });

            $("#btnObject").click(function () {
                //准备好要发送的数据
                var student = {
                    "stuId":101,
                    "stuName":"admin",
                    "address":{
                        "province":"江西省",
                        "city":"赣州市",
                        "street":"犹江大道"
                    },
                    "subjectList":[
                        {
                            "subjectName":"JavaEE",
                            "subjectScore":90
                        },
                        {
                            "subjectName":"HTML5",
                            "subjectScore":85
                        }
                    ],
                    "map":{
                        "k1":"v1",
                        "k2":"v2"
                    }
                };

                //将JSON对象转换为JSON字符串
                var requestBody = JSON.stringify(student);

                //发送Ajax请求
                $.ajax({
                   url:"send/compose/Object.json",
                   type:"post",
                   data:requestBody,
                   contentType: "application/json;charset=UTF-8",
                   dataType:"json",
                   success:function (response) {
                       console.log(response);
                   },
                   error:function (response) {
                       console.log(response);
                   }
                });
            });

            $("#btnLay").click(function () {
                layer.msg("lay的弹窗");
            });

        });
    </script>
</head>
<body>
    <a href="test/ssm.html">测试SSM整合环境</a>
    <br/><br/>

    <!-- 方案一：array 的方式传值 缺陷：Controller方法中接收数据时需要在请求参数的名字后面多写一组“[]”  -->
    <button id="btnArrOne">Send [4,8,12] One</button>
    <br/><br/>

    <!-- 方案二：array[x] 的方式传值 缺陷：要写一个和请求参数名一致的实体类 -->
    <button id="btnArrTwo">Send [5,2,0] Two</button>
    <br/><br/>

    <!-- 方案三：将JSON数组转换JSON字符串的方式传值 -->
    <button id="btnArrThree">Send [1,3,1,4] Three</button>
    <br/><br/>

    <button id="btnObject">Send Compose Student</button>
    <br/><br/>

    <button id="btnLay">点我弹窗</button>
</body>
</html>
