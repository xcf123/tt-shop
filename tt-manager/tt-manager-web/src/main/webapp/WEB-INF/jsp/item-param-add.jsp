<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="easyui-panel" title="商品规格参数模板详情" data-options="fit:true">

    <form class="form" id="itemParamAddForm" name="itemParamAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">规格参数：</td>
                <td>
                    <button class="easyui-linkbutton" onclick="addGroup()" type="button"
                            data-options="iconCls:'icon-add'">添加分组
                    </button>
                    <ul id="item-param-group">

                    </ul>
                    <ul id="item-param-group-template" style="display:none;">
                        <li>
                            <input name="group">
                            <button title="添加参数" class="easyui-linkbutton" onclick="addParam(this)" type="button"
                                    data-options="iconCls:'icon-add'"></button>
                            <button title="删除分组" class="easyui-linkbutton" onclick="delGroup(this)" type="button"
                                    data-options="iconCls:'icon-cancel'"></button>
                            <ul class="item-param">
                                <li>
                                    <input name="param">
                                    <button title="删除参数" class="easyui-linkbutton" onclick="delParam(this)"
                                            type="button" data-options="iconCls:'icon-cancel'"></button>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button class="easyui-linkbutton" onclick="submitForm()" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button class="easyui-linkbutton" onclick="clearForm()" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>

    //添加分组
    function addGroup() {
      var $li= $('#item-param-group-template li:eq(0)').clone();

        $('#item-param-group').append($li);
        debugger;
    }

    //删除分组
    function delGroup(ele) {
        $(ele).parent().remove();
    }

    //添加参数
    function addParam(ele) {
       var $li1= $('.item-param li:eq(0)').clone();
        $(ele).parent().find('.item-param').append($li1);
    }

    //删除参数
    function delParam(ele) {
        $(ele).parent().remove();
    }

    //提交表单
    function submitForm() {
        //[{"group":"主体","params":["型号","入网型号"]}]
        var groupValues=[];
        var $group=$('item-param-group[name=group]');
        $group.each(function (i,e) {
            var $param=$(e).parent().find('.item-param[name=param]');
            var paramValues=[];
            $param.each(function (_i,_e) {
                var value = $.trim($(_e).val());
                if (value.length > 0)
                    paramValues.push(value);
            });
            var obj = {};
            var outvalue = $.trim($(e).val());
            if (outvalue.length > 0)
                obj.group = outvalue;
            obj.params = paramValues;
            groupValues.push(obj);
        })
        //发出一个保存参数规格列表的异步请求
        $.post(
            'itemParam/' + $('#cid').combotree('getValue'),
            {paramData:JSON.stringify(groupValues)},
            function (data) {
                console.log(data);
            }
        );

    }


    $(function () {

        //加载商品类别
        $('#cid').combotree({
            url: 'itemCat?parentId=0',
            required: true,
            onBeforeSelect: function (node) {
                var isLeaf = $('#cid').tree('isLeaf', node.target);
                if (!isLeaf) {
                    $.messager.alert('警告', '请选择最终分类！', 'info');
                    return false;
                }
            },
            onBeforeExpand: function (node) {
                //获取树控件的属性
                var options = $('#cid').combotree('tree').tree('options');
                options.url = 'itemCat?parentId=' + node.id;
            }
        });


    });

</script>

