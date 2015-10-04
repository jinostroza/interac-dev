package cl.interac.util.converters;

import cl.interac.entidades.Totem;
import org.apache.poi.ss.formula.functions.T;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlSelectManyMenu;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class EntityConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().length() == 0) {
            return null;
        }

        List<Object> objetosComponenteList = new ArrayList<Object>();
        if (component instanceof HtmlSelectOneMenu || component instanceof HtmlSelectManyMenu) {
            for (UIComponent child : component.getChildren()) {
                if (child instanceof UISelectItems) {
                    // found it, now get the real SelectItem list
                    UISelectItems items = (UISelectItems) child;
                    List<SelectItem> realitems = (List<SelectItem>) items.getValue();
                    // search for the selected person and return it
                    for (Object item : realitems) {
                        objetosComponenteList.add(item);
                    }
                }
            }
        }

        Class classEntity = getClazz(context, component);

        if (classEntity.getAnnotation(javax.persistence.Entity.class) == null) {
            throw new IllegalArgumentException("No es javax.persistence.Entity!");
        }

        Method getterMethod = null;
        Method setterMethod = null;
        Class<?> classId = null;
        String nombreSetterField = null;

        for (Field field : classEntity.getDeclaredFields()) {
            Annotation annotationId = field.getAnnotation(javax.persistence.Id.class);
            if (annotationId == null) {
                annotationId = field.getAnnotation(javax.persistence.EmbeddedId.class);
            }
            if (annotationId == null) {
                continue;
            }

            //tiene el field que esta anotado como id.
            //obtener el nombre del field
            String nombreGetterField = "get" + field.getName().substring(0, 1).toUpperCase() + (field.getName().length() > 1 ? field.getName().substring(1) : "");
            nombreSetterField = "set" + field.getName().substring(0, 1).toUpperCase() + (field.getName().length() > 1 ? field.getName().substring(1) : "");

            try {
                getterMethod = classEntity.getMethod(nombreGetterField, null);
                break;
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (getterMethod == null)
            for (Method m : classEntity.getDeclaredMethods()) {
                if (!m.getName().startsWith("get")) continue;
                Annotation a = m.getAnnotation(javax.persistence.Id.class);
                if (a == null) {
                    a = m.getAnnotation(javax.persistence.EmbeddedId.class);
                }
                if (a == null) {
                    continue;
                }
                getterMethod = m;
                nombreSetterField = "s" + m.getName().substring(1);
                break;
            }

        if (getterMethod != null) {
            //del getter obtener la Class del Id
            classId = getterMethod.getReturnType();

            try {
                setterMethod = classEntity.getMethod(nombreSetterField, classId);
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }

            //del getter obtener el valor del Id
            Object instanciaId = obtenerValorDeLaId(classId, value);
            try {
                Object nuevaInstancia = classEntity.newInstance();
                setterMethod.invoke(nuevaInstancia, instanciaId);

                int index = objetosComponenteList.indexOf(nuevaInstancia);
                if (index == -1) {
                    return null;
                }
                return objetosComponenteList.get(index);
            } catch (InstantiationException ex) {
                throw new RuntimeException("error al instanciar objeto");
            } catch (IllegalAccessException ex) {
                throw new RuntimeException("error al instanciar objeto");
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("error al instanciar objeto");
            } catch (InvocationTargetException ex) {
                throw new RuntimeException("error al instanciar objeto");
            }
        }


        throw new IllegalArgumentException("No se encontró ID en la Entidad!");
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof Integer) {
            return value + "";
        }
        if (value instanceof Long) {
            return value + "";
        }
        if (value instanceof Short) {
            return value + "";
        }

        Class classEntity = value.getClass();

        if (classEntity.getAnnotation(javax.persistence.Entity.class) == null) {
            throw new IllegalArgumentException("No es javax.persistence.Entity! Component: "+component.getClientId());
        }

        Method getterMethod = null;

        for (Field field : classEntity.getDeclaredFields()) {
            Annotation annotationId = field.getAnnotation(javax.persistence.Id.class);
            if (annotationId == null) {
                annotationId = field.getAnnotation(javax.persistence.EmbeddedId.class);
            }
            if (annotationId == null) {
                continue;
            }

            //tiene el field que esta anotado como id.
            //obtener el nombre del field
            String nombreGetterField = "get" + field.getName().substring(0, 1).toUpperCase() + (field.getName().length() > 1 ? field.getName().substring(1) : "");
            try {
                getterMethod = classEntity.getMethod(nombreGetterField, null);
                break;
            } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (getterMethod == null)
            for (Method m : classEntity.getDeclaredMethods()) {
                if (!m.getName().startsWith("get")) continue;
                Annotation a = m.getAnnotation(javax.persistence.Id.class);
                if (a == null) {
                    a = m.getAnnotation(javax.persistence.EmbeddedId.class);
                }
                if (a == null) {
                    continue;
                }
                getterMethod = m;
                break;
            }

        if (getterMethod != null) {
            //del getter obtener el valor del Id
            Object objectId;
            try {
                objectId = getterMethod.invoke(value, null);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }

            if (objectId == null) {
                return "";
            }

            //Obtener el Objeto real con classEntity , Class del Id con Valor del Id
            return objectId.toString();
        }

        throw new IllegalArgumentException("No se encontró ID en la Entidad!");
    }

    // Gets the class corresponding to the context in jsf page
    @SuppressWarnings("unchecked")
    private Class getClazz(FacesContext facesContext, UIComponent component) {
        Class c = component.getValueExpression("value").getType(facesContext.getELContext());
        if (c.getName().startsWith("[")) c = c.getComponentType();

        return c;
    }

    /*
     * Este metodo se puede sobreescribir para crear un converter de una entidad
     * compuesta o no numerica
     */
    protected <T> T obtenerValorDeLaId(Class<T> claseId, String valorCadena) {
        if (claseId.isInstance(new Integer(0))) {
            return claseId.cast(Integer.valueOf(valorCadena));
        } else if (claseId.isInstance(new Long(0))) {
            return claseId.cast(Long.valueOf(valorCadena));
        } else if (claseId.isInstance(new Short((short) 0))) {
            return claseId.cast(Short.valueOf(valorCadena));
        } else if (claseId.isInstance("")) {
            return claseId.cast(String.valueOf(valorCadena));
        }
        throw new RuntimeException("Tipo de dato no soportado por EntityConverter");
    }
}
