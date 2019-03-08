$(function(){
    //隐藏错误提示框
    $('.add-error-info').css("display","none");
    $('.edit-error-info').css("display","none");
    $("#jqGrid").jqGrid({
    //请求后台 JSON 数据的 URL
    url: 'users/list',
    //后台返回的数据格式
    datatype: "json",
    //列表信息，包括表头、宽度、是否显示、渲染参数等属性
    colModel: [
        {label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true},
        {label: '登录名', name: 'userName', index: 'userName', sortable: false, width: 80},
        {label: '添加时间', name: 'createTime', index: 'createTime', sortable: false, width: 80}
    ],
    //表格高度，可自行调节
    height: 485,
    //默认一页显示多少条数据，可自行调节
    rowNum: 10,
    //翻页控制条中，每页显示记录数可选集合
    rowList: [10, 30, 50],
    //主题，这里选用的是 Bootstrap 主题
    styleUI: 'Bootstrap',
    //数据加载时显示的提示信息
    loadtext: '信息读取中...',
    //是否显示行号，默认值是 false，不显示
    rownumbers: true,
    //行号列的宽度
    rownumWidth: 35,
    //宽度是否自适应
    autowidth: true,
    //是否可以多选
    multiselect: true,
    //分页信息 DOM
    pager: "#jqGridPager",
    jsonReader: {
        root: "data.list",           //数据列表模型
        page: "data.currPage",       //数据页码
        total: "data.totalPage",     //数据总页码
        records: "data.totalCount"   //数据总记录数
    },
    // 向后台请求的参数
    prmNames: {
        page: "page",
        rows: "limit",
        order: "order"
    },
    // 数据加载完成并且 DOM 创建完毕之后的回调函数
    gridComplete: function () {
        //隐藏 Grid 底部滚动条
        $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
    }
});
});
    function userEdit(){
        var id = getSelectedRow();
        if (id == null){
            return;
        }
        $('#userId').val(id);

        //点击编辑按钮后执行操作
        var modal = new Custombox.modal({
            content: {
                effect: 'fadein',
                target: '#modalEdit'
            }
            });
            modal.open();
    }
    //增加按钮modal
    function userAdd(){
        var modal = new Custombox.modal({
            content: {
                effect: 'fadein',
                target: '#modalAdd'
            }
        });
            modal.open();
    }

    //数据验证
function validObjectForAdd() {
    var userName = $('#userName').val();
    if(isNull(userName)){
        showErrorInfo("用户名不能为空！");
        return false;
    }
    if(!validUserName(userName)){
        showErrorInfo("请输入符合规范的用户名!");
        return false;
    }
    var password = $('#password').val();
    if(isNull(password)){
        showErrorInfo("密码不能为空！");
        return false;
    }
    if(!validPassword(password)){
        showErrorInfo("请输入符合规范的密码！");
        return false;
    }
    return true;
}
function   validObjectForEdit(){
        var password = $('#passwordEdit').val();
        if(isNull(password)){
            showErrorInfo("密码不能为空!");
            alert(password);
            return false;
        }
        if(!validPassword(password)){
            showErrorInfo("请输入符合规范的密码！");
            return false;
        }
        return true;
}

$('#saveButton').click(function () {
    if(validObjectForAdd()){
        var userName = $('#userName').val();
        var password = $('#password').val();
        var data={"userName":userName,"password":password};
        $.ajax({
            type:'POST',
            dataType:"json",
            url:'users/add',
            contentType:"application/json;charset=utf-8",
            data:JSON.stringify(data),
            beforeSend: function (request) {
                request.setRequestHeader("token",getCookie("token"));
            },
            success:function (result) {
                checkResultCode(result);
                if(result.resultCode == 200){
                    closeModal();
                    alert("修改成功!");
                    reload();
                }else {
                    closeModal();
                    alert(result.message);
                }
            },
            error:function () {
                reset();
                alert("操作失败!");
            }

        });
    }

});

$('#editButton').click(function () {
        if(validObjectForEdit()){
            var userId = $('#userId').val();
            var  password = $('#passwordEdit').val();
            var data = {"id":userId,"password":password};
            $.ajax({
                type:'POST',
                dataType:"json",
                url:"users/updatePassword",
                contentType:"application/json;charset=utf-8",
                data:JSON.stringify(data),
                beforeSend: function (request) {
                    request.setRequestHeader("token",getCookie("token"));
                },
                success:function (result) {
                    checkResultCode(result);
                    if(result.resultCode == 200){
                        closeModal();
                        alert("更新成功！");
                        reload();
                    }else {
                        closeModal();
                        alert(result.message);
                    }
                },
                error:function () {
                    reset();
                    alert("操作失败!");
                }

            });
        }

});

function closeModal() {
        reset();
        Custombox.modal.closeAll();
}

function reset(){
    //隐藏错误提示框
    $('.add-error-info').css("display", "none");
    $('.edit-error-info').css("display", "none");
    //清空数据
    $('#password').val('');
    $('#passwordEdit').val('');
    $('#userName').val('');
}
function reload() {
    reset();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}