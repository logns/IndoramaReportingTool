/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.11.v20150529
 * Generated at: 2017-11-30 09:23:34 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("\t<div class=\"row\">\n");
      out.write("\t\t<nav class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\n");
      out.write("\t\t\t<div class=\"container-fluid\">\n");
      out.write("\t\t\t\t<div class=\"navbar-header\">\n");
      out.write("\t\t\t\t\t<button type=\"button\" class=\"navbar-toggle collapsed\"\n");
      out.write("\t\t\t\t\t\tdata-toggle=\"collapse\" data-target=\"#sidebar-collapse\">\n");
      out.write("\t\t\t\t\t\t<span class=\"sr-only\">Toggle navigation</span> <span\n");
      out.write("\t\t\t\t\t\t\tclass=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span\n");
      out.write("\t\t\t\t\t\t\tclass=\"icon-bar\"></span>\n");
      out.write("\t\t\t\t\t</button>\n");
      out.write("\t\t\t\t\t");
      out.write("<a class=\"navbar-brand\" href=\"#\"><span>Indorama</span>Admin</a>\n");
      out.write("\t\t\t\t\t<ul class=\"user-menu\">\n");
      out.write("\t\t\t\t\t\t<li class=\"dropdown pull-right\"><a href=\"#\"\n");
      out.write("\t\t\t\t\t\t\tclass=\"dropdown-toggle\" data-toggle=\"dropdown\"><svg\n");
      out.write("\t\t\t\t\t\t\t\t\tclass=\"glyph stroked male-user\">\n");
      out.write("\t\t\t\t\t\t\t\t\t<use xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\txlink:href=\"#stroked-male-user\"></use></svg> User <span\n");
      out.write("\t\t\t\t\t\t\t\tclass=\"caret\"></span></a>\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"dropdown-menu\" role=\"menu\">\n");
      out.write("\t\t\t\t\t\t\t\t<li><a href=\"#\"><svg class=\"glyph stroked male-user\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<use xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\txlink:href=\"#stroked-male-user\"></use></svg> Profile</a></li>\n");
      out.write("\t\t\t\t\t\t\t\t<li><a href=\"#\"><svg class=\"glyph stroked gear\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<use xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\txlink:href=\"#stroked-gear\"></use></svg> Settings</a></li>\n");
      out.write("\t\t\t\t\t\t\t\t<li><a href=\"#\"><svg class=\"glyph stroked cancel\">\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<use xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\txlink:href=\"#stroked-cancel\"></use></svg> Logout</a></li>\n");
      out.write("\t\t\t\t\t\t\t</ul></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<!-- /.container-fluid -->\n");
      out.write("\t\t</nav>\n");
      out.write("\t</div>\n");
      out.write("</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
