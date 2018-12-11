<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>考试题目</title>
</head>
<body>

<SCRIPT LANGUAGE = "JavaScript" >
function onSubmit(){
	var optY = new Array();
	var optN = new Array();
    var frm = document.getElementById("questionForm"); // 获取表单
    for(var i=0;i<5;i++){
    	var obj=document.getElementsByName("questions["+i+"].answer");
    	var seq = document.getElementsByName("questions["+i+"].seq");
    		    for(var j=0; j<obj.length; j ++){
    		        if(obj[j].checked){
    		            if(obj[j].value=='是'){
    		            	if(seq[0].value=='0'){
    		            		optY.push(i);
    		            	}
    		            }else{
    		            	if(seq[0].value =='0'){
    		            		optN.push(i);
    		            	}
    		            }
    		        }
    		    }
    }
    if(optY.length>1){
    	for (var i = 0; i < optY.length; i++) {
        	console.log("optY["+i+"]="+optY[i]);
        	var seq = document.getElementsByName("questions["+optY[i]+"].seq");
        	seq[0].disabled =false;
    	}
    	
    	return false;
    }
    if(optN.length>1){
    	for (var i = 0; i < optN.length; i++) {
        	console.log("optN["+i+"]="+optN[i]);
    	}
    	return false;
    }
    return true;
}
</SCRIPT >

<div style="margin:0px&nbsp;auto;">
<s:form id="questionForm" action="save" method="post" onsubmit="return onSubmit();">


	<s:iterator value="questions" var ="qst" status="st">
		<s:textfield type="hidden" name='questions[%{#st.index}].code' />
		<s:textfield type="hidden" name='questions[%{#st.index}].stem' />
		<s:textfield label="答案序号"  disabled="true" name='questions[%{#st.index}].seq' />
		<!--<s:text name="questions[%{#st.index}].content"/><br>-->
		

		<s:radio name='questions[%{#st.index}].answer'  labelposition="top" label ="%{#qst.stem}"  list="options"/>

	</s:iterator>
	<s:submit/>
	<tr><td colspan="3" align="center">
			共<s:property value="lastpage"/> 页，
			第<s:property value="p"/> 页&nbsp;&nbsp;&nbsp;
			<a href='<s:url action="browse1" namespace="/student">
			<s:param name="p" value="1"></s:param>
			</s:url>'>首页
			</a>
			
			<a href='<s:url action="browse1" namespace="/student">
			<s:param name="p" value="%{p-1}"></s:param>
			</s:url>'>上一页
			</a>
			
			<a href='<s:url action="browse1" namespace="/student">
			<s:param name="p" value="%{p+1}"></s:param>
			</s:url>'>下一页
			</a>
			
			<a href='<s:url action="browse1" namespace="/student">
			<s:param name="p" value="lastpage"></s:param>
			</s:url>'>尾页
			</a>
			</td></tr>
			
			
		
</s:form>

</div>

</body>
</html>