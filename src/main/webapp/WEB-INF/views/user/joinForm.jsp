<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- tailwind 사용 -->
<script src="https://cdn.tailwindcss.com"></script>
<!-- 부트스트랩 적용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/resources/css/login.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">

<meta id="_csrf" name="_csrf" content="${_csrf.token }" />
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName }" />
<title>Insert title here</title>
</head>
<body>


<form method="post" action="/doJoin" >
	<div class="flex justify-center h-screen w-1/3 flex-col m-0 m-auto">
		<!-- 로그인 창 화면 중앙 우치 -->
		<div class="border-solid border border-black">
			<!-- 로그인창 테두리 검정 -->
			<div class="text-center m-7 ">
				<!-- 로그인 메인 문구 위치, 마진값 -->
				<div class="text-lg font-bold">지출관리 웹</div>
				<div class="text-2xl font-bold">회원가입</div>
			</div>
			<div class="text-center mx-3 mt-14 ">
				<!-- input box정중앙 위치, 좌우 마진, 위쪽 마진 -->
				<p>
					<input type="email" name="email" id="email" placeholder="이메일"
						class="login_input m-2" />
				</p>
				<div id="validNoId" class="validError"></div>
				<p>
					<input type="password" name="password" id="password"
						placeholder="비밀번호" class="login_input m-2">
				</p>
				<div id="validNoPw" class="validError"></div>
			</div>
			<div id="validNotCorrect" class="validError"></div>
			<div class="text-center login_function">
				<!-- id,pw찾지 / 회원가입 기능 -->
				<div>
					<a href="/usr/findUserId" class="mx-3">아이디찾기</a> <span>|</span> <a
						href="/usr/findUserPw" class="mx-3">비밀번호찾기</a> <span>|</span> <a
						href="/usr/joinForm" class="mx-3">회원가입</a>
				</div>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-outline-primary login_btn"
					id="try-login">회원가입</button>
			</div>
			<div class="text-center my-9">
				<span class="login_copy">&copy;seonghun.</span>
			</div>
		</div>
	</div>
	
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>


<script type="text/javascript">

</script>


</body>
</html>