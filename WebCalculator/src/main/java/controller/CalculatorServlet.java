package controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CalculatorServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");

    double n1 = Double.parseDouble(request.getParameter("num1"));
    double n2 = Double.parseDouble(request.getParameter("num2"));
    String operacao = request.getParameter("operacao");

    double resultado = 0;

    switch (operacao) {
      case "soma": resultado = n1 + n2; break;
      case "sub": resultado = n1 - n2; break;
      case "mult": resultado = n1 * n2; break;
      case "div": resultado = n1 / n2; break;
    }

    response.getWriter().println("<html><body>");
    response.getWriter().println("<h2>Resultado: " + resultado + "</h2>");
    response.getWriter().println("</body></html>");
  }
}
