package filters;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import persistence.EtatMission;

@ManagedBean
@ApplicationScoped
public class FilterEtatMissionBean {
	private List<SelectItem> selectItems = new ArrayList<>();

	public List<SelectItem> getSelectItems() {
		SelectItem item = new SelectItem(EtatMission.ATTENTE);
		SelectItem item2 = new SelectItem(EtatMission.NEW);
		SelectItem item3 = new SelectItem(EtatMission.TERMINEE);

		selectItems.add(item);
		selectItems.add(item2);
		selectItems.add(item3);

		return selectItems;
	}

	public void setSelectItems(List<SelectItem> selectItems) {
		this.selectItems = selectItems;
	}

}
