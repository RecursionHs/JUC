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