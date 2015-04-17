package cl.apptec.util.scopes;

import java.util.Map;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author colivares
 */
public class ViewScope implements Scope {

    public Object get(String name, ObjectFactory<?> of) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object = of.getObject();
            viewMap.put(name, object);

            return object;
        }
    }

    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }

    public void registerDestructionCallback(String string, Runnable r) {
    }

    public Object resolveContextualObject(String string) {
        return null;
    }

    public String getConversationId() {
        return null;
    }

}
