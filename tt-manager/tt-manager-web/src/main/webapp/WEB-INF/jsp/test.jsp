<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/23
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>付钱拉pcjssdk_示例</title>
    <script src="http://www.css88.com/jqapi-1.9/js/jquery.min.js"></script>
    <script src="https://lib.fuqian.la/pcjssdk.4.0.js"></script>
    <style>
        button { width: 200px; height: 35px; background-color: #000; border-radius: 5px; color: #fff; border: 0; margin: 15px;}
    </style>
</head>
<body>
<p>备注：请把该文件放到静态服务器里访问。</p>
<button id="test3">支付宝支付</button>
<script>
    $('#test3').on('click', function(){
        var charge = {
            app_id: '3hmd5BArSIk87ELxUBXWFA',       //应用ID号
            order_no: new Date().getTime(),     //订单号，此处为模拟订单号。具体以接入为准
            amount: '1',        //支付金额
            channel: 'ali_direct_pay_pc',       //开通的通道简称
            subject: '支付宝pc测试',     //商品标题
            version: 'v2.1.1',
            notify_url: 'http://fuqian.la'  //异步支付结果通知地址
        };

        FUQIANLAPC.init({
            isCashierDesk: false,
            app_id: charge.app_id,
            order_no: charge.order_no, //订单号，
            channel: charge.channel,
            amount: charge.amount,
            subject: charge.subject,
            notify_url: charge.notify_url,
            extra: {
                return_url:'http://fuqian.la'
            }
        });
    });
</script>
</body>
</html>
