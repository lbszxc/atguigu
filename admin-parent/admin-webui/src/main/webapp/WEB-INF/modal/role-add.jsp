<%--
  Created by IntelliJ IDEA.
  User: 说好的幸福呢
  Date: 2020/6/27
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<!-- 添加role模态框 -->
<div id="addModal" class="modal" tabindex="-1">
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
                <form class="form-signin" role="form">
                    <div class="form-group has-success has-feedback">
                        <input name="roleName" id="roleName" type="text" class="form-control" placeholder="请输入角色名称" autofocus>
                    </div>
                </form>

            </div>
            <!-- 注脚 -->
            <div class="modal-footer">
                <button id="saveRoleBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div>
    </div>
</div>
