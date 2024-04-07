<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css" href="/resources/jqueryUI/jquery-ui.min.css" />
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
<script src="/resources/jqgrid/js/i18n/grid.locale-en.js"></script>
<script src="/resources/jqgrid/js/jquery.jqGrid.min.js"></script>
</head>
<body>

	<table id="grid"></table>
	<div id="pager"></div>

	
	 

	<script type="text/javascript" src="/resources/js/jqGridTest.js"></script>
	<script type="text/javascript">	
		
		//페이징을 사용하지 않고 서버에 요청해서 모든 데이터를 가지고 올 경우 사용하는 방법----------------------------------
		/* $('#grid').jqGrid({
            datatype: function() {
                $.ajax({
                    url:'showGridFromserverWithoutPaging',   
                    dataType:'json',
                    complete:function(p_result) {
                    	console.log("p_result : " + p_result)
                         var l_result = JSON.parse(p_result.responseText);
                    	console.log("l_result : " + l_result)
                        for(var i = 0 ; i < l_result.length ; i++) {
                            $('#grid').addRowData(l_result[i], l_result[i]);
                        }  
                    }
                });
            },
            colNames : [ "id", "test_name", "test_email", "test_date" ],
            colModel : [ {
				name : "id",
				align : "center",
				width : 50
			},

			{
				name : "test_name",
				align : "center",
				width : 50
			}, {
				name : "test_email",
				align : "center",
				width : 50
			}, {
				name : "test_date",
				align : "left"
			}

			],
            rowNum:20,
            rowList:[10,20,30],
            emptyrecords:"데이터가 존재하지 않습니다",
            viewrecords: true,
            sortorder: "desc",
            caption: "학교/단체 목록"
        }); */
		//----------------------------------------------------------------------------------------------------------------------
		
		
		//페이징을 사용하고 서버에 요청하는 경우-----------------------------------------------------------------------------------
		$(function() {

			//alert('')
			//Test_Grid_2
			$("#grid").jqGrid({
				url : '/showGridFromserverWithPaging',
				mtype : 'POST', //데이터 전송 방식
				datatype : 'json',
				height : 'auto',
				//width: 'auto',
				autowidth : true,
				caption: "jqGrid테스트", //그리드 제목
				loadText :"로딩중...",
				colNames : [ "id", "test_name", "test_email", "test_date" ],
				colModel : [ {
					name : "id",
					align : "center",
					width : 60
				},

				{
					name : "test_name",
					align : "center",
					width : 50
				}, {
					name : "test_email",
					align : "center",
					width : 50
				}, {
					name : "test_date",
					align : "left",
					width : 50
				}

				],
				pager : "#pager",
				rowNum : 5, //한페이지에 로드할 갯수
				rowList : [ 10, 20, 30 ],
				emptyrecords:"데이터가 존재하지 않습니다",
				sortable:true,
				editable:true,
				rownumbers: true, // row숫자 표시
				multiselect:true, //멀티 select박스 첫 컬럼에 생성
				//sortname : "id",
				//sortorder : "desc", //정렬기준
				gridview : true,
				viewrecords : true, //그리드가 보여줄 총 페이지 현재페이지등 정보
				autoencode : true,
				loadonce:false, //true : 모든 페이지의 데이터를 가지고와 변수에 담아 두고 페이징, false : 페이지 이동 할 때마다 그리드에 출력할 데이터 서버에서 select
				jsonReader : {
					id: 'id', //키 컬럼명
					root:'rows', //그리드에 로드할 json형태의 데이터
					total: 'total', //총 페이지 갯수
					records:'records', //총 row count
					repeatitems : false
				},loadComplete:function(data){
					console.log("종료");
					console.log("data : " + JSON.stringify(data));
				}
			});
		});

/* 		jQuery("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false
		});  */
		//--------------------------------------------------------------------------------------------------------------------------
	</script>


</body>
</html>