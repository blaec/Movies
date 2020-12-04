package org.blaec.movies.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class GalleryFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String name = httpReq.getRemoteUser();
        if (name != null) {
            filterConfig.getServletContext().log("User " + name + " is updating.");
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
