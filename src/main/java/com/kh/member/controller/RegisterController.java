package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;


/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지 이동 용도
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		String memberName = request.getParameter("member-name");
		int memberAge = Integer.parseInt(request.getParameter("member-age"));
		String memberGender = request.getParameter("member-gender");
		String memberEmail = request.getParameter("member-email");
		String memberAddress = request.getParameter("member-address");
		String memberPhone= request.getParameter("member-phone");
		String memberHobby = request.getParameter("member-hobby");
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		MemberService service = new MemberService();
		// INSERT INTO MEMBR_TBL VALUES(?,?,?,?,?,?,?,?,?,DEAFULT,DEFAULT)
		int result = service.insertMember(member);
		if(result > 0) {
			// 성공하면 성공 페이지로 이동 -> RequestDisaptcher
			response.sendRedirect("/index.jsp");
		}else {
			// 실패
			request.setAttribute("url", "/member/regiser.do");
			request.setAttribute("msg", "회원 등록이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/erroPage.jsp").forward(request, response);
			
		}
	}

}