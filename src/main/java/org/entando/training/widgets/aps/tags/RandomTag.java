/*
*
* <Your licensing text here>
*
*/
package org.entando.training.widgets.aps.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import com.agiletec.aps.system.services.page.Widget;
import com.agiletec.aps.util.ApsProperties;
import com.agiletec.aps.system.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.agiletec.aps.system.RequestContext;
import com.agiletec.aps.util.ApsWebApplicationUtils;
import org.entando.training.widgets.aps.system.services.random.IRandomManager;
import org.entando.training.widgets.aps.system.services.random.Number;


public class RandomTag extends TagSupport {
    
    private static final Logger _logger =  LoggerFactory.getLogger(RandomTag.class);
    
    @Override
    public int doStartTag() throws JspException {
        ServletRequest request =  this.pageContext.getRequest();
        IRandomManager randomManager = (IRandomManager) ApsWebApplicationUtils.getBean("widgetsRandomManager", this.pageContext);
        RequestContext reqCtx = (RequestContext) request.getAttribute(RequestContext.REQCTX);
        try {
            Number random = null;
            random = randomManager.getRandom();
            this.pageContext.setAttribute(this.getVar(), random);
        } catch (Throwable t) {
            _logger.error("Error in doStartTag", t);
            throw new JspException("Error in RandomTag doStartTag", t);
        }
        return super.doStartTag();
    }
    
    @Override
    public int doEndTag() throws JspException {
        this.release();
        return super.doEndTag();
    }
    
    @Override
    public void release() {
        this.setVar(null);
    }
    
    private Widget extractWidget(RequestContext reqCtx) {
        Widget widget = null;
        widget = (Widget) reqCtx.getExtraParam((SystemConstants.EXTRAPAR_CURRENT_WIDGET));
        return widget;
    }
    
    protected String extractWidgetParameter(String parameterName, ApsProperties widgetConfig, RequestContext reqCtx) {
        return (String) widgetConfig.get(parameterName);
    }
    
    public String getVar() {
        return _var;
    }
    public void setVar(String var) {
        this._var = var;
    }
    
    private String _var;
}
