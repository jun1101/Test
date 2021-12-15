<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<section id="container" style="height: 100%">
		<table>
			<thead>
				<tr>
					<th>업로드 파일 이름</th>
					<th>변환 종류</th>
					<th>Size</th>
					<th>다운로드</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list" varStatus="status">
					<c:if test="${list.filesort eq 'S' or list.filesort eq 'N' or list.filesort eq 'H'}">
						<tr>
							<td><c:out value="${list.originalfilename}" /></td>
							<td>
							<c:choose>
								<c:when test="${list.filesort eq 'S'}"><c:out value="Soft" /></c:when>
								<c:when test="${list.filesort eq 'N'}"><c:out value="Normal" /></c:when>
								<c:when test="${list.filesort eq 'H'}"><c:out value="Hard" /></c:when>
								<c:otherwise><c:out value="Upload"></c:out></c:otherwise>
							</c:choose>
							</td>
							<td><c:out value="${list.filesize}" /></td>
							<td>
								<form action="${pageContext.request.contextPath}/fileDownLoad.do" id="downform${status.index }" method="POST">
									<input type="hidden" name="f_idx" id="f_idx" value="${list.f_idx }"/>
									<input type="submit" class="downbtn" value="Download"/>
								</form>
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	
</section>