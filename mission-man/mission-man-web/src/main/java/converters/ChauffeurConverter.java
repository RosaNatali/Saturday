package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ctr.ChauffeurBean;
import persistence.Chauffeur;

@FacesConverter("cc")
public class ChauffeurConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		ChauffeurBean chauffeurBean = context.getApplication().evaluateExpressionGet(context, "#{chauffeurBean}",
				ChauffeurBean.class);
		Chauffeur chauffeur = chauffeurBean.doFindChauffeur(value);
		return chauffeur;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String string = null;
		if (value instanceof Chauffeur) {
			string = ((Chauffeur) value).getNom();
		}
		return string;
	}

}
