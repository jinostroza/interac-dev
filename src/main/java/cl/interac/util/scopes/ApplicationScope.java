package cl.interac.util.scopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by claudio on 04-09-14.
 */
public class ApplicationScope implements Scope {
    private Map<String, Object> contexto;

    public Object get(String s, ObjectFactory<?> objectFactory) {
        if (contexto == null) contexto = new HashMap<String, Object>();
        if (contexto.containsKey(s)) {
            return contexto.get(s);
        } else {
            Object object = objectFactory.getObject();
            contexto.put(s, object);
            return object;
        }
    }

    public Object remove(String s) {
        return null;
    }

    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    public Object resolveContextualObject(String s) {
        return null;
    }

    public String getConversationId() {
        return null;
    }
}
