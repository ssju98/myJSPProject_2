package kr.util;

public class PaginationUtil {
	private int startCount;	 // 한 페이지에서 보여줄 게시글의 시작 번호
	private int endCount;	 // 한 페이지에서 보여줄 게시글의 끝 번호
	private StringBuffer pagingHtml;// 페이지 표시 문자열

	/**
	 * currentPage : 현재페이지
	 * totalCount : 전체 게시물 수
	 * rowCount : 한 페이지의  게시물의 수
	 * pageCount : 한 화면에 보여줄 페이지 수
	 * pageUrl : 호출 페이지 url
	 * addKey : 부가적인 key 없을 때는 null 처리 (&num=23형식으로 전달할 것)
	 * */
	public PaginationUtil(int currentPage, int totalCount, int rowCount,
			int pageCount, String pageUrl) {
		this(null,null,currentPage,totalCount,rowCount,pageCount,pageUrl,null);
	}
	public PaginationUtil(int currentPage, int totalCount, int rowCount,
			int pageCount, String pageUrl, String addKey) {
		this(null,null,currentPage,totalCount,rowCount,pageCount,pageUrl,addKey);
	}
	public PaginationUtil(String keyfield, String keyword, int currentPage, int totalCount, int rowCount,
			int pageCount,String pageUrl) {
		this(keyfield,keyword,currentPage,totalCount,rowCount,pageCount,pageUrl,null);
	}
	public PaginationUtil(String keyfield, String keyword, int currentPage, int totalCount, int rowCount,
			int pageCount,String pageUrl,String addKey) {
		
		if(addKey == null) addKey = ""; //부가키가 null 일때 ""처리
		
		// 전체 페이지 수
		int totalPage = (int) Math.ceil((double) totalCount / rowCount);
		if (totalPage == 0) {
			totalPage = 1;
		}
		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}
		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startCount = (currentPage - 1) * rowCount + 1;
		endCount = currentPage * rowCount;
		// 시작 페이지와 마지막 페이지 값 구하기.
		int startPage = (int) ((currentPage - 1) / pageCount) * pageCount + 1;
		int endPage = startPage + pageCount - 1;
		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// 이전 block 페이지
		pagingHtml = new StringBuffer();
		pagingHtml.append("<ul class='pagination justify-content-center'>");
		if (currentPage > pageCount) {
			if(keyword==null){//검색 미사용시
				pagingHtml.append("<li class='page-item'>");
				pagingHtml.append("<a class='page-link' href='"+pageUrl+"?pageNum="+ (startPage - 1) + addKey +"'>");
			}else{
				pagingHtml.append("<li class='page-item'>");
				pagingHtml.append("<a class='page-link' href='"+pageUrl+"?keyfield="+keyfield+"&keyword="+keyword+"&pageNum="+ (startPage - 1) + addKey +"'>");
			}
			pagingHtml.append("prev");
			pagingHtml.append("</a></li>");
		}
		
		//페이지 번호, 현재 페이지 처리
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("<li class='page-item active'>");
				pagingHtml.append("<a class='page-link'>");
				pagingHtml.append(i);
				pagingHtml.append("</a></li>");
			} else {
				if(keyword==null){//검색 미사용시
					pagingHtml.append("<li class='page-item'>");
					pagingHtml.append("<a class='page-link' href='"+pageUrl+"?pageNum=");
				}else{
					pagingHtml.append("<li class='page-item'>");
					pagingHtml.append("<a class='page-link' href='"+pageUrl+"?keyfield="+keyfield+"&keyword="+keyword+"&pageNum=");
				}
				pagingHtml.append(i);
				pagingHtml.append(addKey+"'>");
				pagingHtml.append(i);
				pagingHtml.append("</a></li>");
			}
		}
		// 다음 block 페이지
		if (totalPage - startPage >= pageCount) {
			if(keyword==null){//검색 미사용시
				pagingHtml.append("<li class='page-item'>");
				pagingHtml.append("<a class='page-link' href='" + pageUrl + "?pageNum=" + (endPage + 1) + addKey +"'>");
			}else{
				pagingHtml.append("<li class='page-item'>");
				pagingHtml.append("<a class='page-link' href='"+pageUrl+"?keyfield="+keyfield+"&keyword="+keyword+"&pageNum="+ (endPage + 1) + addKey +"'>");
			}
			pagingHtml.append("next");
			pagingHtml.append("</a></li>");
		}
		pagingHtml.append("</ul>");
	}
	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}
	public int getStartCount() {
		return startCount;
	}
	public int getEndCount() {
		return endCount;
	}
}
