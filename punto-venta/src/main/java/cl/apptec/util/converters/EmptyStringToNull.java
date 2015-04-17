package cl.apptec.util.converters;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author colivares
 */
public class EmptyStringToNull implements javax.faces.convert.Converter {

    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if ((string == null || string.trim().isEmpty()) && uic instanceof EditableValueHolder) {
            ((EditableValueHolder) uic).setSubmittedValue(null);
            return null;
        }
        return string;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return o == null ? null : o.toString();
    }
}
