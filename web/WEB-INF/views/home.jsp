<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="common/head.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/pagination.css"><link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/base.css">
<title>Home</title>
</head>
<body>
<div class="container" >
    <div class="page-header">
        <h1>银行柜员系统 <small>by Kay</small></h1>
    </div>
	<div class="row">
		<div class="span12">
			<ul class="nav nav-tabs">
				<li class="active">
					<a href="#home_view" data-toggle="tab">首页</a>
				</li>
				<li>
					<a href="#info_view" data-toggle="tab">资料</a>
				</li>
				<li class="disabled">
					<a href="#">信息</a>
				</li>
				<li class="dropdown pull-right">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							<a href="#">操作</a>
						</li>
						<li>
							<a href="#">设置栏目</a>
						</li>
						<li>
							<a href="#">更多设置</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="#">退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	<div class="tab-content" style="text-align: center">
		<!--  首页     -->
		<div id="home_view" class="hero-unit tab-pane fade in active">
            <h3>
			Hello,<span id="accountName">${sessionScope.accountInfo.accountName}</span>
                您的银行卡号为&nbsp;<b>${sessionScope.accountInfo.accountId}</b>&nbsp;您可以进行以下操作.</h3>
			<div style="width: 135px; margin-top: 30px;float: left;margin-left: 200px">
			<a class="btn btn-primary btn-large btn-block" href="#checkin_view" data-toggle="tab">存款 &raquo;</a>
			&nbsp; &nbsp;
			<a class="btn btn-primary btn-large btn-block" href="#checkout_view" data-toggle="tab">取款 &raquo;</a>
			&nbsp;&nbsp;
			<a class="btn btn-primary btn-large btn-block" href="#transfer_view" data-toggle="tab">转账 &raquo;</a>
			&nbsp;&nbsp;
			<a id="bankwaterbtn" class="btn btn-primary btn-large btn-block" href="#bankwater_view" data-toggle="tab">查看流水记录 &raquo;</a>
            </div>
			<div class="tab-content">
			<div id="checkin_view" class="panel panel-default tab-pane fade myviews" >
				<div  class="form-horizontal panel-body ">		                 
					  <div class="form-group">
					 	 请输入存款金额
					    <label class="sr-only" for="checkInMoney">金额 (人民币)</label>
					    <div class="input-group">
					      <div class="input-group-addon">￥</div>
					      	<input type="text" class="form-control" id="checkInMoney" placeholder="金额">

					    </div>
					  </div>
					  <button id="checkInbtn" type="button" class="btn btn-primary" >提交</button>
		        </div>
	        </div>
	        <div id="checkout_view" class="panel panel-default tab-pane fade myviews" >
				<div  class="form-horizontal panel-body ">		                 
					  <div class="form-group">
					 	 请输入取款金额
					    <label class="sr-only" for="checkOutMoney">金额 (人民币)</label>
					    <div class="input-group">
					      <div class="input-group-addon">￥</div>
					      <input type="text" class="form-control" id="checkOutMoney" placeholder="金额">

					    </div>
					  </div>
					  <button id="checkOutbtn" type="button" class="btn btn-primary" >提交</button>
		        </div>
	        </div>
	        <div id="transfer_view" class="panel panel-default tab-pane fade myviews" >
				<div class="form-horizontal panel-body ">		                 
					  <div class="form-group">
					 	 请输入以下转账信息
					    <div class="input-group">					    
					      <div class="input-group-addon">转出账户</div>
					      <input type="text" class="form-control" id="outAccount">					      
					    </div>
					    
					    <div class="input-group">					    
					      <div class="input-group-addon">￥</div>
					      <input type="text" class="form-control" id="transferMoney" placeholder="金额">					      
					    </div>
					  </div>
					  <button id="transferbtn" type="button" class="btn btn-primary">提交</button>
		        </div>
	        </div>
            <div id="bankwater_view" class="tab-pane fade">
                <table class="table table-hover" style="margin-bottom: 0px;">
                    <thead>
                    <tr>
                        <th>交易日期</th>
                        <th>流水号</th>
                        <th>摘要</th>
                        <th>发生额</th>
                        <th>余额</th>
                        <th>交易点</th>
                    </tr>
                    </thead>
                    <tbody id="result_body">

                    </tbody>
                </table>
                <div style="text-align: right">
                    <div id="BankWaters-Pagination"></div>
                </div>
            </div>
        </div>
		</div>
		<!--  资料页面 -->
		<div id="info_view" class="hero-unit tab-pane fade">
			<h1>资料页面</h1>
			
			<p>这是一个可视化布局模板, 你可以点击模板里的文字进行修改, 也可以通过点击弹出的编辑框进行富文本修改. 拖动区块能实现排序.</p>
			
			<p><a class="btn btn-primary btn-large" href="#">参看更多 &raquo;</a>&nbsp; &nbsp;<a class="btn btn-primary btn-large" href="#" style="background-image: linear-gradient(rgb(0, 136, 204), rgb(0, 68, 204));">参看更多 &raquo;</a>&nbsp;&nbsp;<a class="btn btn-primary btn-large" href="#" style="background-image: linear-gradient(rgb(0, 136, 204), rgb(0, 68, 204));">参看更多 &raquo;</a>&nbsp;&nbsp;<a class="btn btn-primary btn-large" href="#" style="background-image: linear-gradient(rgb(0, 136, 204), rgb(0, 68, 204));">参看更多 &raquo;</a></p>
		</div>
	</div>
    <!-- 弹出窗-->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">操作结果</h4>
                </div>
                <div class="modal-body">
                    <label id="result_view"></label>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.pagination.js"></script>

<script type="text/javascript">
$(document).ready(function(){

    //定义通用方法
	function checkMoney(checkType,checkMoney,outAccount){
		$.ajax({
			type: "POST",
		    url: "${pageContext.request.contextPath}/checkInOut",				  
		    dataType:"json",
		    data: {'checkType':checkType,"checkMoney":checkMoney,"outAccount":outAccount},
		    success: function(result) {
                $("#result_view").html(result.stateinfo);
                $("#myModal").modal();

		    },
		    fail:function(result){
		    	alert(result);
		    }
		});
	}
	
	//存款
	$("#checkInbtn").click(function(){

        var checkInMoney=$("#checkInMoney").val();
        if(!isNaN(checkInMoney)) {
            checkMoney('CHECK_IN', checkInMoney, null);
        }else {
            alert("输入格式错误！");
        }

	});
	
	//取款
	$("#checkOutbtn").click(function(){
        var checkOutMoney=$("#checkOutMoney").val();
        if(!isNaN(checkOutMoney)) {
            checkMoney('CHECK_OUT', checkOutMoney,null);
        }else {
            alert("输入格式错误！");
        }

	});
	
	//转账
	$("#transferbtn").click(function(){	
		var outAccount=$("#outAccount").val();
        var transferMoney=$("#transferMoney").val();
        if(!isNaN(outAccount) && !isNaN(transferMoney)) {
            checkMoney('TRANSFER_CHECK_OUT', transferMoney,outAccount);
        }else {
            alert("输入格式错误！");
        }

	});




	$("#bankwaterbtn").click(function() {
        //点击查看流水，刷新表单
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/bankWaters",
            dataType:"json",
            success: function(result) {
                var data=result[0].dataList;
                $("#result_body").empty();
                for(var i=0;i<data.length;i++){
                    $("#result_body").append("<tr>\n   " +
                        " <td>"+data[i].checkTime+"</td>\n    " +
                        "<td>"+data[i].checkId+"</td>\n    " +
                        "<td>"+data[i].checkType+"</td>\n    " +
                        "<td>"+data[i].checkCount+"</td>\n    " +
                        "<td>"+data[i].balance+"</td>\n    " +
                        "<td>"+data[i].checkPlace+"</td>\n" +
                        "</tr>>\n   ");
                }
                var totalRecord=result[0].totalRecord;
                var currentPage=result[0].currentPage;
                inintpage(totalRecord,currentPage);
            },
            fail:function(result){
                alert(result);
            }
        });

    });


    // 点击分页按钮以后触发的动作,动态添加表单
    function handlePaginationClick(new_page_index, pagination_container) {
        //查询流水
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/bankWaters?pageNum="+(new_page_index+1),
            dataType:"json",
            success: function(result) {
                var data=result[0].dataList;
                $("#result_body").empty();
                for(var i=0;i<data.length;i++){
                    $("#result_body").append("<tr>\n   " +
                        " <td>"+data[i].checkTime+"</td>\n    " +
                        "<td>"+data[i].checkId+"</td>\n    " +
                        "<td>"+data[i].checkType+"</td>\n    " +
                        "<td>"+data[i].checkCount+"</td>\n    " +
                        "<td>"+data[i].balance+"</td>\n    " +
                        "<td>"+data[i].checkPlace+"</td>\n" +
                        "</tr>>\n   ");
                }
            },
            fail:function(result){
                alert(result);
            }
        });
    }

    function inintpage(totalRecord,currentPage){
        $("#BankWaters-Pagination").pagination(totalRecord, {
            items_per_page:5, // 每页显示多少条记录
            current_page:currentPage-1, // 当前显示第几页数据
            num_display_entries:8, // 分页显示的条目数
            next_text:"下一页",
            prev_text:"上一页",
            num_edge_entries:2, // 连接分页主体，显示的条目数
            callback:handlePaginationClick
        });

    }
	
});
</script>

</html>