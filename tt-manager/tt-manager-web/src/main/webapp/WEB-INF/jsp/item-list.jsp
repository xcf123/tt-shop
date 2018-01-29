<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>





<div id="toolbar">
    <div style="padding: 5px; background-color: #fff;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox" >
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--http://www.cnblogs.com/wisdomoon/p/3330856.html-->
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>
<table  id="itemListDg"></table>



<script>


    function add() {
        console.log('新增');
        ttshop.addTab("新增商品","item-add");
    }
    function edit() {
        console.log('编辑');
    }
    function remove() {
        console.log('删除');
        var selections=$('#itemListDg').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('警告','未选择记录');
        }
        else{
            $.messager.confirm('确认','确认删除？',function () {
                var ids=[];
                var flag=3;
                for(var i=0;i<selections.length;i++){
                    ids.push(selections[i].id);
                }

                $.post(
                    'items/batch',
                    {'ids[]':ids,'flag':flag},
                    function (data) {
                        if(data!=0){
                            $('#itemListDg').datagrid('reload');
                        }
                    }
                )
            });

        }
    }
    function up() {
        console.log('上架');
        var selections=$('#itemListDg').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('警告','未选择记录');
        }
        else{
            $.messager.confirm('确认','确认上架？',function () {
                var ids=[];
                var flag=1;
                for(var i=0;i<selections.length;i++){
                    ids.push(selections[i].id);
                }

                $.post(
                    'items/batch',
                    {'ids[]':ids,'flag':flag},
                    function (data) {
                        if(data!=0){
                            $('#itemListDg').datagrid('reload');
                        }
                    }
                )
            });

        }
    }
    function down() {
        console.log('下架');
        var selections=$('#itemListDg').datagrid('getSelections');
        if(selections.length==0){
            $.messager.alert('警告','未选择记录');
        }
        else{
            $.messager.confirm('确认','确认下架？',function () {
                var ids=[];
                var flag=2;
                for(var i=0;i<selections.length;i++){
                    ids.push(selections[i].id);
                }

                $.post(
                    'items/batch',
                    {'ids[]':ids,'flag':flag},
                    function (data) {
                        if(data!=0){
                            $('#itemListDg').datagrid('reload');
                        }
                    }
                )
            });

        }
    }
    function searchForm() {
        $('#itemListDg').datagrid('load',{
            title: $.trim($('#title').val()),
            status:$('#status').combobox('getValue')
        });
    }
    $(function () {

        var toolbar=[{
            iconCls: 'icon-add',
            text:'新增',
            handler: function(){
                console.log('新增');

            }
        },{
            iconCls: 'icon-edit',
            text:'编辑',
            handler: function(){
                console.log('编辑');
            }
        },{
            iconCls: 'icon-remove',
            text:'删除',
            handler: function(){
                console.log('删除');
                var selections=$('#itemListDg').datagrid('getSelections');
                if(selections.length==0){
                    $.messager.alert('警告','未选择记录');
                }
                else{
                    $.messager.confirm('确认','确认删除？',function () {
                        var ids=[];
                        var flag=3;
                        for(var i=0;i<selections.length;i++){
                            ids.push(selections[i].id);
                        }

                        $.post(
                            'items/batch',
                            {'ids[]':ids,'flag':flag},
                            function (data) {
                                if(data!=0){
                                    $('#itemListDg').datagrid('reload');
                                }
                            }
                        )
                    });

                }
            }
        },{
            iconCls: 'icon-up',
            text:'上架',
            handler: function(){
                console.log('上架');
                var selections=$('#itemListDg').datagrid('getSelections');
                if(selections.length==0){
                    $.messager.alert('警告','未选择记录');
                }
                else{
                    $.messager.confirm('确认','确认上架？',function () {
                        var ids=[];
                        var flag=1;
                        for(var i=0;i<selections.length;i++){
                            ids.push(selections[i].id);
                        }

                        $.post(
                            'items/batch',
                            {'ids[]':ids,'flag':flag},
                            function (data) {
                                if(data!=0){
                                    $('#itemListDg').datagrid('reload');
                                }
                            }
                        )
                    });

                }
            }
        },{
            iconCls: 'icon-down',
            text:'下架',
            handler: function(){
                console.log('下架');
                var selections=$('#itemListDg').datagrid('getSelections');
                if(selections.length==0){
                    $.messager.alert('警告','未选择记录');
                }
                else{
                    $.messager.confirm('确认','确认下架？',function () {
                        var ids=[];
                        var flag=2;
                        for(var i=0;i<selections.length;i++){
                            ids.push(selections[i].id);
                        }

                        $.post(
                            'items/batch',
                            {'ids[]':ids,'flag':flag},
                            function (data) {
                                if(data!=0){
                                    $('#itemListDg').datagrid('reload');
                                }
                            }
                        )
                    });

                }
            }
        }];
        $('#itemListDg').datagrid({
            multiSort:true,
            fitColumns:true,
            toolbar:"#toolbar",
            url:"items",
            fit:true,
            striped:true,
            pagination:true,
            columns:[[
                {field:"ck",checkbox:true},
                {field:"id",title:"商品编号"},
                {field:"title",title:"商品标题",sortable:true},
                {field:"status",title:"商品状态",formatter:function (v,r,i) {
                        switch (v){
                            case 1:
                                return "正常";
                                break;
                            case 2:
                                return "下架";
                                break;
                            case 3:
                                return "删除";
                                break;
                        }

                    }},
                {field:"catName",title:"商品分类"},
                {field:"created",title:"创建时间",formatter:function(v,r,i){
                       return moment(v).format("YYYY-MM-DD");
                    }
                },
                {field:"price",title:"商品价格",sortable:true,formatter:function (v,r,i) {
                        return "￥"+v;
                    }},
                {field:"sellPoint",title:"商品卖点"},
            ]]
        })
    })
</script>

