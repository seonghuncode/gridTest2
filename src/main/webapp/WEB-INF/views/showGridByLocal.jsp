<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="/resources/jqueryUI/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/resources/jqgrid/css/ui.jqgrid.css">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	// jQuery import 바로아래에 넣어 주면 됩니다.
	// Cannot read property 'msie' of undefined 에러 나올때
	jQuery.browser = {};
	(function() {
		jQuery.browser.msie = false;
		jQuery.browser.version = 0;
		if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
			jQuery.browser.msie = true;
			jQuery.browser.version = RegExp.$1;
		}
	})();
</script>
<script src="/resources/jqgrid/js/jquery.jqGrid.min.js"></script>
<script src="/resources/jqgrid/js/i18n/grid.locale-en.js"></script>
</head>
<body>

	 
	<table id="grid"></table>
	<div id="pager"></div>
	 
	<script type="text/javascript">
	var data = ${testData};
	//var grid_Data = JSON.parse(data);
	
	
		$(document).ready(function() { // 변수를 선언합니다.        
			var customDialog = {
				onclickSubmit : function(params) {
					var selectedRow = $('#grid').getGridParam('selrow');
					rowData = $('#grid').getRowData(selectedRow);
					return {
						id : rowData.id
					};
				}
			};
			$('#grid').jqGrid({
				data : data,
				datatype:'local',
				//url : 'test2', //요청 url
				editurl : 'EditBook',
				//datatype : 'json', //주고 받을 데이터 타입
				mtype: "post", //전송 타입
				pager : '#pager', //페이지가 표시될 곳
				caption : 'Books',
				height : '100%',
				rowNum : 10,
				rowList : [ 10, 20, 30 ],
				colNames : [ 'id', 'test_name', 'test_email', 'test_date' ],
				colModel : [ {
					name : 'id',
					index : 'bookid',
					width : 30
				}, {
					name : 'test_name',
					index : 'test_name',
					width : 270,
					editable : true,
					edittype : 'text'
				}, {
					name : 'test_email',
					index : 'test_email',
					width : 80,
					editable : true,
					edittype : 'text'
				}, {
					name : 'test_date',
					index : 'test_date',
					width : 40,
					editable : true,
					edittype : 'text'
				} ]
			}).navGrid('#pager', {
				search : true,
				edit : true,
				add : true,
				del : true
			}, customDialog, {}, customDialog);
		});
	</script>

</body>
</html>