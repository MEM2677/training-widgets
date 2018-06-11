<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="wp" uri="/aps-core"%>

<h3>WEBSOCKET number widget</h3>

<wp:headInfo type="JS" info="ws/widget-websoket.js" />

<wp:info key="systemParam" paramName="applicationBaseURL" var="urlVar"/>

</br>Random number pushed by the server:&nbsp;
<div style="margin: 0 auto">
	<div id="randomNumber" style="float: left">Fetching data...</div>
</div>

<script>
	var wsendpoint = "<c:out value="${urlVar}" />" + "wstest";
	doNumberGauge(wsendpoint, "randomNumber");
</script>