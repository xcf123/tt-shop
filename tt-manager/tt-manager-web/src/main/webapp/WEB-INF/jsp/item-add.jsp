<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
商品新增页面
<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载编辑器的容器 -->
                    <script id="container" name="content" type="text/plain">商品描述</script>
                </td>
            </tr>

            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>

<script>

    function submitForm() {
        $('#itemAddForm').form('submit', {
            //提交后台的谁进行处理
            url: 'item',
            //提交前需要做的处理操作，返回false终止提交
            onSubmit: function () {
                //将前台的元转换为后台的分，设置给隐藏域
                $('#price').val($.trim($('#priceView').val()) * 100);


                //获取参数规格部分
                var paramsJson = [];
                var $liList = $('#itemAddForm .paramsShow li');
                $liList.each(function (i, e) {
                    $group = $(e).find('.group');
                    var groupName = $group.text();

                    var params = [];
                    var $trParams = $(e).find('tr').has('td.param');
                    $trParams.each(function (_i, _e) {
                        var $oneDataTr = $(_e);
                        var $keyTd = $oneDataTr.find('.param');
                        var $valueInput = $keyTd.next('td').find('input');
                        var key = $keyTd.text();
                        var value = $valueInput.val();

                        var _o = {
                            k: key,
                            v: value
                        };
                        params.push(_o);
                    });
                    var o = {};
                    o.group = groupName;
                    o.params = params;
                    paramsJson.push(o);
                });
                paramsJson = JSON.stringify(paramsJson);
                $('#paramData').val(paramsJson);

                //当所有控件校验通过才会返回true，否则返回false
                return $(this).form('validate');
            },
            success: function (data) {
                if (data > 0) {
                    $.messager.alert('温馨提示', '新增商品成功！', 'info');
                    ttshop.addTab('查询商品', 'item-list');
                }
            }
        })
    }
    //实例化之前先删除容器
    UE.delEditor('container');
    UE.getEditor('container',{
        /*initialFrameWidth: '100%',
        initialFrameHeight: '400',*/
         serverUrl:'file/upload'
    });
    $(function(){
        //加载商品类别
        $('#cid').combotree({
            url:'itemCat?parentId=0',
            required:true,
            onBeforeSelect:function (node) {
                var isLeaf=$('#cid').tree('isLeaf',node.target);
                if(!isLeaf){
                    $.messager.alert('警告', '请选择最终分类！', 'info');
                    return false;
                }else{
                    $.ajax({
                        url:'itemParam',
                        type:'post',
                        data:{'id':node.id},
                        dataType:'json',
                        success:function (data) {

                            //[{"group":"组名1","params":["组员1","组员2"]}]
                            console.log(data);
                            if(data){
                                var paramData=data.paramData;
                                paramData = JSON.parse(paramData);
                                var $td = $('#itemAddForm .paramsShow td').eq(1);
                                var $ul=$("<ul>");
                                $td.empty().append($ul);
                                $.each(paramData,function (i,e) {
                                    var $li=$("<li>");
                                    var $ta=$("<table>");
                                    var $td2=$("<tr><td colspan='2' class='group'>"+e.group+"</td></tr>");
                                    $ta.append($td2);
                                    $li.append($ta);
                                    $ul.append($li);
                                    $.each(e.params,function (_i,_e) {
                                        var $tr2 = $('<tr><td class="param">'+ _e +'</td><td><input></td></tr>');
                                        $ta.append($tr2);
                                    })
                                    $('#itemAddForm .paramsShow').show();
                                })

                            }else{
                                //原有单元格中的内容置空
                                $('#itemAddForm .paramsShow td').eq(1).empty();
                                //若后台查询出null，把商品规格的行隐藏掉
                                $('#itemAddForm .paramsShow').hide();
                            }
                        }
                    })
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