package net.dc.demo.views;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by diego.daniel on 13/02/2023.
 */
public abstract class AbstractView {

    public static void addInfo(String message) {
        FacesContext.getCurrentInstance().addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static void addInfo(FacesContext context, String message) {
        context.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static void addWarn(String message) {
        FacesContext.getCurrentInstance().addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }

    public static void addWarn(FacesContext context, String message) {
        context.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }

    public static void addError(FacesContext context, String message) {
        context.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public static void addError(String message) {
        FacesContext.getCurrentInstance().addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public static void addError(String message, Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        FacesContext.getCurrentInstance().addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, writer.toString()));
    }

    public static void addInfo(String conponentId, String message) {
        FacesContext.getCurrentInstance().addMessage(conponentId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public static void addInfo(FacesContext context, String conponentId, String message, String details) {
        context.addMessage(conponentId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, details));
    }

    public static void addWarn(String conponentId, String message) {
        FacesContext.getCurrentInstance().addMessage(conponentId, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }

    public static void addError(String conponentId, String message) {
        FacesContext.getCurrentInstance().addMessage(conponentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public static void addMessageWithSummary(String summary, String message) {
        FacesMessage msg = new FacesMessage(summary, message);
        FacesContext.getCurrentInstance().addMessage((String)null, msg);
    }

    public static Boolean containsError() {
        return Boolean.valueOf(!FacesContext.getCurrentInstance().getMessageList().isEmpty());
    }

    public static <T> T getValueEL(String el, Class<T> type) {
        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        ValueExpression valueExpression = context.getApplication().getExpressionFactory().createValueExpression(elContext, el, type);
        return (T) valueExpression.getValue(elContext);
    }

    public static void setValueEL(String el, Class<?> type, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        ELContext elContext = context.getELContext();
        ValueExpression valueExpression = context.getApplication().getExpressionFactory().createValueExpression(elContext, el, type);
        valueExpression.setValue(elContext, value);
    }

    public static Object getPropertyFromRequest(String nomePropriedade) {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getAttribute(nomePropriedade);
    }

    public static void setPropertyInRequest(String nomePropriedade, Object value) {
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute(nomePropriedade, value);
    }

    public static Object getPropertyFromSession(String nomePropriedade) {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return session.getAttribute(nomePropriedade);
    }

    public static void setPropertyInSession(String nomePropriedade, Object value) {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(nomePropriedade, value);
    }

    public static String getDateTimeFormat(Date date) {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(date).toString();
    }

    public static UIComponent findComponentInRoot(String id) {
        UIComponent component = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(facesContext != null) {
            UIViewRoot root = facesContext.getViewRoot();
            component = findComponent(root, id);
        }

        return component;
    }

    public static UIComponent findComponent(UIComponent base, String id) {
        if(id.equals(base.getId())) {
            return base;
        } else {
            UIComponent kid = null;
            UIComponent result = null;
            Iterator kids = base.getFacetsAndChildren();

            while(kids.hasNext() && result == null) {
                kid = (UIComponent)kids.next();
                if(id.equals(kid.getId())) {
                    result = kid;
                    break;
                }

                result = findComponent(kid, id);
                if(result != null) {
                    break;
                }
            }

            return result;
        }
    }

    public static void forward(String outcome) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
        nav.performNavigation(outcome);
    }

    public static void redirect(String outcome) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler)fc.getApplication().getNavigationHandler();
        if(!outcome.contains("faces-redirect=true")) {
            if(outcome.contains("?")) {
                outcome = outcome + "&" + "faces-redirect=true";
            } else {
                outcome = outcome + "?" + "faces-redirect=true";
            }
        }

        nav.performNavigation(outcome);
    }
}
