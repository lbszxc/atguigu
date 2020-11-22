// 声明专门的函数用来在分配Auth模态框中显示Auth的树形结构数据
function fillAuthTree() {

    // 1.发送Ajax请求查询Auth数据
    var ajaxReturn = $.ajax({
                               url:"assign/get/all/auth.json",
                               type:"post",
                               dataType:"json",
                               async:false,
    });

    if (ajaxReturn.status != 200){
        layer.msg("请求处理出错！响应状态码是："+ajaxReturn.status+"说明是："+ajaxReturn.statusText);
    }

    // 2.从响应结果中获取Auth的JSON数据
    // 从服务器端查询的list不需要组装成树形结构，这里我们交给zTree去组装
    var authList = ajaxReturn.responseJSON.data;

    // 3.准备对zTree进行设置的JSON对象
    var setting = {
        "data": {
            "simpleData": {
                // 开启简单的JSON数据
                "enable":true,
                // 使用categoryId属性关联父节点，不使用默认的pId了
                "pIdKey":"categoryId"

            },
            "key":{
                // 使用title属性显示节点名称，不用默认的name作为属性名
                "name":"title"
            }
        },
        "check":{
            "enable":true
        }
    };

    // 4.生成树形结构
    $.fn.zTree.init($("#authTreeDemo"),setting,authList);

    // 获取zTreeObj对象
    var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");

    // 调用zTreeObj对象的方法，把节点给全部展开
    zTreeObj.expandAll(true);

    // 5.查询已经分配的Auth的id组成的数组
    ajaxReturn = $.ajax({
        url:"assign/get/assigned/auth/id/by/role/id.json",
        type:"post",
        data:{
            "roleId":window.roleId
        },
        dataType:"json",
        async:false
    });

    if (ajaxReturn.status != 200){
        layer.msg("请求处理出错！响应状态码是："+ajaxReturn.status+"说明是："+ajaxReturn.statusText);
        return ;
    }

    // 从响应结果中获取authIdArray
    var authIdArray = ajaxReturn.responseJSON.data;
    //alert(authIdArray);

    // 6.根据authIdArray把树形结构对应的节点勾选
    // ①遍历authIdArray数组
    for (var i = 0;i < authIdArray.length;i++){

        var authId = authIdArray[i];

        // ②根据id查询树形结构对应的节点
        var treeNode = zTreeObj.getNodeByParam("id",authId);

        // ③将treeNode设置为被勾选

        // checked设置为true是表示把节点勾选上
        var checked = true;

        //checkTypeFlag设置为false，表示不“联动”，不“联动”是为了避免把不该勾选的勾选上
        var checkTypeFlag = false;

        // 执行
        zTreeObj.checkNode(treeNode,checked,checkTypeFlag);
    }
}

// 声明专门的函数用于显示确认模态框
function showConfirmModal(roleArray) {

    // 打开模态框
    $("#confirmModal").modal("show");

    // 清除旧的数据
    $("#roleNameDiv").empty();

    // 在全局变量范围创建数组来存放角色Id
    window.roleIdArray = [];

    // 遍历roleArray数组
    for (var i = 0; i < roleArray.length; i++){
        var role = roleArray[i];
        var roleName = role.roleName;
        $("#roleNameDiv").append(roleName+"<br/>");
        var roleId = role.roleId;
        // 调用数组对象的push()方法来存入新元素
        window.roleIdArray.push(roleId);
    }

}

// 执行分页，生成页面效果，任何时候调用这个函数都会重新加载页面
function generatePage() {

    // 1.获取分页数据
    var pageInfoRemote = getPageInfoRemote();

    // 2.填充表格
    fillTableBody(pageInfoRemote);

}

// 远程访问服务器端程序获取pageInfo数据
function getPageInfoRemote() {

    // 调用$.ajax()函数发送请求并接受$.ajax()函数的返回值
    var  ajaxResult = $.ajax({
       url:"role/get/page/info.json" ,
       type:"post",
       data:{
          "pageNum":window.pageNum,
          "pageSize":window.pageSize,
          "keyword":window.keyword
       } ,
       dataType:"json",
       async:false
    });

    console.log(ajaxResult);

    // 判断当前响应状态是否成功，注：状态码为200响应成功
    var statusCode = ajaxResult.status;

    // 如果当前响应状态失败（状态码不是200），说明发生了错误或异常，显示提示信息，停止当前函数执行
    if (statusCode != 200){
        layer.msg("失败！响应状态码："+statusCode+"说明信息："+ajaxResult.statusText);
        return null;
    }

    // 如果当前响应状态成功（状态码是200），获取pageInfo
    var resultEntity = ajaxResult.responseJSON;

    // 从resultEntity中获取result属性
    var result = resultEntity.result;

    // 判断result是否成功
    if (result == "FAILED"){
        layer.msg(resultEntity.message);
        return null;
    }

    // 确定result获取成功后获取pageInfo
    var pageInfo = resultEntity.data;

    // 返回pageInfo
    return  pageInfo;

}

// 填充表格
function fillTableBody(pageInfo) {

    // 清除tbody中的旧数据
    $("#rolePageBody").empty();

    // 为了搜索没有结果时不显示页码
   $("#Pagination").empty();

    // 判断pageInfo对象是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0){

        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您要的数据！</td></tr>");

        return;
    }

    // 使用pageInfo的list属性填充tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkboxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>"
        var roleNameTd = "<td>" + roleName + "</td>"
        // 通过button标签的id属性把roleId值传递到button按钮的单击响应函数中
        var checkBtn = "<button id='" + roleId + "' type='button' class='btn btn-success btn-xs checkBtn'><i class=' glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button id='" + roleId + "' type='button' class='btn btn-primary btn-xs pencilBtn'><i class='glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class='glyphicon glyphicon-remove'></i></button>";
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }

    // 生成分页导航条
    generateNavigator(pageInfo);

}

// 生成分页页面导航条
function generateNavigator(pageInfo) {

    // 获取总记录数
    var totalRecord = pageInfo.total;

    // 声明相关属性
    var properties = {
        "num_edge_entries": 3,
        "num_display_entries": 5,
        "callback": paginationCallBack,
        "items_per_page": pageInfo.pageSize,
        "current_page": pageInfo.pageNum - 1,
        "prev_text": "上一页",
        "next_text": "下一页"
    };

    // 调用pagination()函数
    $("#Pagination").pagination(totalRecord, properties);

}

// 翻页时的回调函数
function paginationCallBack(pageIndex,JQuery) {

    // 修改windows对象pageNum对象
    window.pageNum = pageIndex + 1;

    // 调用分页函数
    generatePage();

    // 取消页码超链接的默认行为
    return false;
}

