/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.11.v20150529
 * Generated at: 2017-12-06 12:16:43 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.tiles_002dlayout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class site_005flayout_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("jar:file:/C:/Users/admin/.m2/repository/taglibs/standard/1.1.2/standard-1.1.2.jar!/META-INF/c.tld", Long.valueOf(1098691290000L));
    _jspx_dependants.put("jar:file:/C:/Users/admin/.m2/repository/org/apache/tiles/tiles-jsp/3.0.3/tiles-jsp-3.0.3.jar!/META-INF/tld/tiles-jsp.tld", Long.valueOf(1383761562000L));
    _jspx_dependants.put("jar:file:/C:/Users/admin/.m2/repository/org/springframework/spring-webmvc/4.3.7.RELEASE/spring-webmvc-4.3.7.RELEASE.jar!/META-INF/spring-form.tld", Long.valueOf(1488337488000L));
    _jspx_dependants.put("file:/C:/Users/admin/.m2/repository/taglibs/standard/1.1.2/standard-1.1.2.jar", Long.valueOf(1491027386556L));
    _jspx_dependants.put("file:/C:/Users/admin/.m2/repository/org/apache/tiles/tiles-jsp/3.0.3/tiles-jsp-3.0.3.jar", Long.valueOf(1502954461075L));
    _jspx_dependants.put("file:/C:/Users/admin/.m2/repository/org/springframework/spring-webmvc/4.3.7.RELEASE/spring-webmvc-4.3.7.RELEASE.jar", Long.valueOf(1489466166026L));
  }

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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("\n");
      out.write("<!-- favicon  -->\n");
      out.write("<link rel=\"icon\" type=\"image/png\" sizes=\"32x32\" href=\"static/images/favicon-32x32.png\">\n");
      out.write("<link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"static/images/favicon-96x96.png\">\n");
      out.write("<link rel=\"icon\" type=\"image/png\" sizes=\"16x16\" href=\"static/images/favicon-16x16.png\">\n");
      out.write("<link rel='shortcut icon' type='image/x-icon' href='static/images/favicon.ico'> <!-- IE -->\n");
      out.write("<link rel='apple-touch-icon' type='image/png' href='static/images/apple-icon-57x57.png'> <!-- iPhone -->\n");
      out.write("<link rel='apple-touch-icon' type='image/png' sizes='72x72' href='static/images/apple-icon-57x57.png'> <!-- iPad -->\n");
      out.write("<link rel='apple-touch-icon' type='image/png' sizes='114x114' href='static/images/apple-icon-114x114.png'> <!-- iPhone4 -->\n");
      out.write("\n");
      out.write("<!-- W3 School  -->\n");
      out.write("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\n");
      out.write("\n");
      out.write("<!-- Bootstrap  -->\n");
      out.write("<link rel=\"stylesheet\" href=\"webjars/bootstrap/3.3.6/css/bootstrap.min.css\">\n");
      out.write("<link href=\"//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("<link rel=\"stylesheet\" href=\"webjars/bootstrap/3.3.6/css/bootstrap-theme.min.css\">\n");
      out.write("<link href=\"//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<!-- Custom CSS -->\n");
      out.write("<link rel=\"stylesheet\" href=\"static/css/styles.css\">\n");
      out.write("\n");
      out.write("<!-- lumino stylesheet in csshome  -->\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-table.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-theme.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-theme.css.map\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-theme.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap.css.map\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap.min.css\"media=\"screen\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/datepicker.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/datepicker3.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/styles.css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/timepicker.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-timepicker.min.css\" rel=\"stylesheet\" media=\"screen\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"static/csshome/bootstrap-datetimepicker.min.css\" rel=\"stylesheet\" media=\"screen\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\n");
      out.write("\n");
      out.write("<!-- jquery -->\n");
      out.write("<script src=\"webjars/jquery/2.2.3/jquery.min.js\"></script>\n");
      out.write("<script src=\"webjars/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n");
      out.write("<script src=\"https://code.jquery.com/jquery-2.2.4.min.js\" integrity=\"sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=\"\n");
      out.write("  crossorigin=\"anonymous\"></script>\n");
      out.write("  \n");
      out.write("<!-- lumino javascript in js  -->\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/bootstrap-table.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/bootstrap.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/bootstrap.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/chart-data.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/chart-min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/easypiechart-data.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/easypiechart.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/html5shiv.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/kalrav.glyphs.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/lumino.glyphs.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/respond.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/bootstrap-datetimepicker.js\" ></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/bootstrap-timepicker.js\" ></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- adding mustache template engine  -->\n");
      out.write("<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mustache.js/2.3.0/mustache.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<!-- load indorama.js after jquery -->\n");
      out.write("<script type=\"text/javascript\" src=\"static/js/indorama.js\"></script>\n");
      out.write("\n");
      out.write(" <!-- glyph icons from https://glyphs.co/docs/getting-started/installation  -->\n");
      out.write("<script type=\"text/javascript\" src=\"https://kit.glyphs.co/3ko27b.js\"></script>\n");
      out.write("\n");
      out.write("<title>");
      if (_jspx_meth_tiles_005finsertAttribute_005f0(_jspx_page_context))
        return;
      out.write("</title>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t<!-- header -->\n");
      out.write("\t<div id=\"site_header\">\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t<!-- Menu Page -->\n");
      out.write("\t<div id=\"site_menu\">\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f2(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t<!-- body -->\n");
      out.write("\t<div id=\"site_body\">\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f3(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t<!-- footer -->\n");
      out.write("\t<div id=\"site_footer\">\n");
      out.write("\t\t");
      if (_jspx_meth_tiles_005finsertAttribute_005f4(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t</div>\n");
      out.write("\t\n");
      out.write("</body>\n");
      out.write("</html>");
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

  private boolean _jspx_meth_tiles_005finsertAttribute_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f0 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f0);
    _jspx_th_tiles_005finsertAttribute_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(88,7) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f0.setName("title");
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(88,7) name = ignore type = boolean reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f0.setIgnore(true);
    _jspx_th_tiles_005finsertAttribute_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f0);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f1 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f1);
    _jspx_th_tiles_005finsertAttribute_005f1.setJspContext(_jspx_page_context);
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(94,2) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f1.setName("header");
    _jspx_th_tiles_005finsertAttribute_005f1.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f1);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f2(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f2 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f2);
    _jspx_th_tiles_005finsertAttribute_005f2.setJspContext(_jspx_page_context);
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(98,2) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f2.setName("menu");
    _jspx_th_tiles_005finsertAttribute_005f2.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f2);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f3(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f3 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f3);
    _jspx_th_tiles_005finsertAttribute_005f3.setJspContext(_jspx_page_context);
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(102,2) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f3.setName("body");
    _jspx_th_tiles_005finsertAttribute_005f3.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f3);
    return false;
  }

  private boolean _jspx_meth_tiles_005finsertAttribute_005f4(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertAttribute
    org.apache.tiles.jsp.taglib.InsertAttributeTag _jspx_th_tiles_005finsertAttribute_005f4 = (new org.apache.tiles.jsp.taglib.InsertAttributeTag());
    _jsp_instancemanager.newInstance(_jspx_th_tiles_005finsertAttribute_005f4);
    _jspx_th_tiles_005finsertAttribute_005f4.setJspContext(_jspx_page_context);
    // /WEB-INF/pages/tiles-layout/site_layout.jsp(106,2) name = name type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertAttribute_005f4.setName("footer");
    _jspx_th_tiles_005finsertAttribute_005f4.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_tiles_005finsertAttribute_005f4);
    return false;
  }
}
