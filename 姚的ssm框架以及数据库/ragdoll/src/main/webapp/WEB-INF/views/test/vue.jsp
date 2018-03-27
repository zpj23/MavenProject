<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="mf" uri="/MyFramework-tags"%>    
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="https://cdn.jsdelivr.net/npm/vue"></script> -->
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>

<script type="text/javascript">

var obj = {
  foo: 'bar'
}

Object.freeze(obj)

new Vue({
  el: '#app',
  data: obj
})

</script>
</head>
<body>
<input v-model="message" placeholder="edit me">
<p>Message is: {{ message }}</p>


<div id="app">
  <p>{{ foo }}</p>
  <!-- 这里的 `foo` 不会更新！ -->
  <button @click="foo = 'baz'">Change it</button>
</div>


<%-- <mf:FileUpload moduleID="12" moduleType="12" mode="edit" id="aa"></mf:FileUpload> --%>
</body>
</html>