package org.blaec.movies.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class MyCompressionFilter implements Filter {
    private ServletContext ctx;
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        ctx = filterConfig.getServletContext();
        ctx.log(filterConfig.getFilterName() + " initialized.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String valid_encodings = httpRequest.getHeader("Accept-Encoding");
        if (valid_encodings.contains("gzip")) {
            CompressionResponseWrapper wrappedResp = new CompressionResponseWrapper(httpResponse);
            wrappedResp.setHeader("Content-Encoding", "gzip");
            chain.doFilter(httpRequest, wrappedResp);
            GZIPOutputStream gzos = wrappedResp.getGZIPOutputStream();
            gzos.finish();
            ctx.log(filterConfig.getFilterName() + ": finished the request");
        } else {
            ctx.log(filterConfig.getFilterName() + ": no encoding performed.");
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
        ctx = null;
    }
}
