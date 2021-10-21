<%--
  Created by IntelliJ IDEA.
  User: Mr.Voyager
  Date: 2021/10/21
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>

    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">

    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">

    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">

    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">

    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.min.js"></script>

    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>

</head>

<body>

<h2>当前登录的用户是</h2>

<p>${user.username}</p>

​

<table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"

       url="<%=request.getContextPath()%>/userlist"

       toolbar="#toolbar" pagination="true"

       rownumbers="true" fitColumns="true" singleSelect="true">

    <thead>

    <tr>

        <th field="username" width="50">用户名</th>

        <th field="password" width="50">密码</th>

        <th field="realname" width="50">真实姓名</th>

    </tr>

    </thead>

</table>

<div id="toolbar">

    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>

    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>

    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>

</div>

​

<div id="dlg" class="easyui-dialog" style="width:400px" data-options="closed:true,modal:true,border:'thin',buttons:'#dlg-buttons'">

    <form id="fm" method="post" novalidate style="margin:0;padding:20px 50px" enctype="text/plain">

        <h3>User Information</h3>

        <div style="margin-bottom:10px">

            <input name="username" class="easyui-textbox" required="true" label="用户名" style="width:100%">

        </div>

        <div style="margin-bottom:10px">

            <input name="password" class="easyui-textbox" required="true" label="密码" style="width:100%">

        </div>

        <div style="margin-bottom:10px">

            <input name="realname" class="easyui-textbox" required="true" label="真实姓名" style="width:100%">

        </div>

    </form>

</div>

<div id="dlg-buttons">

    <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>

    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>

</div>

<script type="text/javascript">

    var url;

    function newUser(){

        $('#dlg').dialog('open').dialog('center').dialog('setTitle','New User');

        $('#fm').form('clear');

        url = 'usersave';

    }

    function editUser(){

        var row = $('#dg').datagrid('getSelected');

        if (row){

            $('#dlg').dialog('open').dialog('center').dialog('setTitle','Edit User');

            $('#fm').form('load',row);

            url = 'usersave?id='+row.id;

        }

    }

    function saveUser(){

        $.ajax({

            url : url,

            type : "POST",

            data : $( '#fm').serialize(),

            success : function(data) {

                $('#dlg').dialog('close');        // close the dialog

                $('#dg').datagrid('reload');

            },

            error : function(data) {

            }

        });

    }

    function destroyUser(){

        var row = $('#dg').datagrid('getSelected');

        if (row){

            $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){

                if (r){

                    $.post('userdelete',{id:row.id},function(result){

                        $('#dg').datagrid('reload');

                    },'json');

                }

            });

        }

    }

</script>

</body>

</html>