package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/update.bo")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 10 * 1024 * 1024;
			
			String resources = request.getSession().getServletContext().getRealPath("/resources");
			String savePath = resources + "\\board_upfiles\\";
			System.out.println("savePath : " + savePath);
			
//			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			int bno = Integer.parseInt(multiRequest.getParameter("bno"));
			
			Board b = new Board();
			b.setBoardContent(multiRequest.getParameter("content"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setCategory(multiRequest.getParameter("category"));
			b.setBoardNo(bno);
			
			Attachment at = null;
			
			// 새로운 파일이 들어온거
			if (multiRequest.getOriginalFileName("upFile") != null) {
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upFile"));
				at.setChangeName(multiRequest.getFilesystemName("upFile"));
				at.setFilePath(savePath);
				
				// 회사에서는 안지우는 경우도 있다고함
				if (multiRequest.getParameter("originFile") != null) {
					File deleteFile = new File(savePath + multiRequest.getParameter("originFile"));
					
					deleteFile.delete();
					
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				} else {
					at.setRefBoardNo(bno);
				}
			} 
			
			int result = new BoardService().updateBoard(b, at);
			
			if (result > 0) {
				response.sendRedirect("detail.bo?bno=" + bno);
			} else {
				request.setAttribute("msg", "게시글 수정 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
