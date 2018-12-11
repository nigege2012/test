<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<s:form id="selectForm" action="browse1" method="post" >

<s:textfield name="pageS" label="page"></s:textfield>
<s:textfield name="number" label="number"></s:textfield>
<s:submit value="提交"></s:submit>


</s:form>

</body>
</html>