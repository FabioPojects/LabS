	package controller;
	
	import dao.BankDAO;
	import model.Bank;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.*;
	import java.io.IOException;
	import java.sql.SQLException;
	import java.time.LocalDate;
	import java.util.List;
	
	public class BankServlet extends HttpServlet {
	    private BankDAO bankDAO;
	
	    public void init() {
	        bankDAO = new BankDAO();
	    }
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getServletPath();
	
	        try {
	            String path = request.getPathInfo();
	            if (path == null) {
	                path = "";
	            }
	
	            switch (path) {
	                case "/new":
	                    showNewForm(request, response);
	                    break;
	                case "/insert":
	                    insertBank(request, response);
	                    break;
	                case "/delete":
	                    deleteBank(request, response);
	                    break;
	                case "/edit":
	                    showEditForm(request, response);
	                    break;
	                case "/update":
	                    updateBank(request, response);
	                    break;
	                default:
	                    listBanks(request, response);
	                    break;
	            }
	        } catch (SQLException | ServletException ex) {
	            throw new ServletException(ex);
	        }
	    }
	
	    private void listBanks(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        List<Bank> listBank = bankDAO.selectAllBanks();
	        request.setAttribute("listBank", listBank);
	        request.getRequestDispatcher("/list.jsp").forward(request, response);
	    }
	
	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.getRequestDispatcher("/form.jsp").forward(request, response);
	    }
	
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Bank existingBank = bankDAO.selectBank(id);
	        request.setAttribute("bank", existingBank);
	        request.getRequestDispatcher("/form.jsp").forward(request, response);
	    }
	
	    private void insertBank(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        String name = request.getParameter("name");
	        String birthDateStr = request.getParameter("birthDate");
	
	        LocalDate birthDate = LocalDate.parse(birthDateStr);
	        Bank newBank = new Bank();
	        newBank.setName(name);
	        newBank.setBirthDate(birthDate);
	
	        bankDAO.insertBank(newBank);
	        response.sendRedirect("BankServlet");
	    }
	
	    private void updateBank(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        String name = request.getParameter("name");
	        String birthDateStr = request.getParameter("birthDate");
	
	        LocalDate birthDate = LocalDate.parse(birthDateStr);
	        Bank bank = new Bank();
	        bank.setId((long) id);
	        bank.setName(name);
	        bank.setBirthDate(birthDate);
	
	        bankDAO.updateBank(bank);
	        response.sendRedirect("BankServlet");
	    }
	
	    private void deleteBank(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        bankDAO.deleteBank(id);
	        response.sendRedirect("BankServlet");
	    }
	}
		