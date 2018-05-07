<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="training" uri="/training_random-core"%>
<training:random var="random" />

<h3>Hello JSTL world!</h3>
Here is a random number:&nbsp;<c:out value="${random.output}" />