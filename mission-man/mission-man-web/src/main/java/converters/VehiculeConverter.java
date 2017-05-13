package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ctr.VehiculeBean;
import persistence.Vehicule;

@FacesConverter("vc")
public class VehiculeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		VehiculeBean vehiculeBean = context.getApplication().evaluateExpressionGet(context, "#{vehiculeBean}",
				VehiculeBean.class);

		return vehiculeBean.doFindVehiculeByImmatriculation(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String string = null;
		if (value instanceof Vehicule) {
			string = ((Vehicule) value).getImmatriculation();
		}
		return string;
	}

}
