<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/27
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!-- 添加role模态框 -->
<div id="confirmModal" class="modal" tabindex="-1">
    <!-- 窗口声明 -->
    <div class="modal-dialog">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部 -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
                <h4 class="modal-title">尚筹网系统弹窗</h4>
            </div>
            <!-- 主体 -->
            <div class="modal-body">
                <h4>请确认是否要删除下列角色名称：</h4>
                <div id="roleNameDiv" style="text-align: center"></div>
            </div>
            <!-- 注脚 -->
            <div class="modal-footer">
                <button id="removeRoleBtn" type="button" class="btn btn-danger">确定删除</button>
            </div>
        </div>
    </div>
</div>
