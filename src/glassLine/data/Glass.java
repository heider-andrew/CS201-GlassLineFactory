package glassLine.data;

public class Glass {

	public final int numPopups = 6;

	public boolean[] popupNecessary;
	public boolean[] popupDone;

	public GlassType myType;
	public GlassStatus myStatus;

	public enum GlassStatus {
		Ordered, Loading, Loaded, MovingToPosition, InPosition, InProduction, Completed
	}

	public enum GlassType {
		Type1, Type2, Type3, Type4
	};

	public Glass(GlassType myType) {
		popupNecessary = new boolean[numPopups];
		popupDone = new boolean[numPopups];
		for (int i = 0; i < numPopups; i++) {
			popupNecessary[i] = true;
			popupDone[i] = false;
		}
		this.myType = myType;
		myStatus = GlassStatus.Ordered;
		setRecipe();
	}

	public void setRecipe() {
		if (myType.equals(GlassType.Type1)) {
			popupNecessary[0] = true;
			popupNecessary[1] = true;
			popupNecessary[2] = true;
			popupNecessary[3] = true;
			popupNecessary[4] = true;
			popupNecessary[5] = true;

		}

		if (myType.equals(GlassType.Type2)) {
			popupNecessary[0] = true;
			popupNecessary[1] = false;
			popupNecessary[2] = true;
			popupNecessary[3] = false;
			popupNecessary[4] = true;
			popupNecessary[5] = false;
		}

		if (myType.equals(GlassType.Type3)) {
			popupNecessary[0] = true;
			popupNecessary[1] = true;
			popupNecessary[2] = false;
			popupNecessary[3] = false;
			popupNecessary[4] = false;
			popupNecessary[5] = true;
		}

		if (myType.equals(GlassType.Type4)) {
			popupNecessary[0] = true;
			popupNecessary[1] = false;
			popupNecessary[2] = false;
			popupNecessary[3] = true;
			popupNecessary[4] = true;
			popupNecessary[5] = false;
		}
	}

	public boolean isPopupNecessary(int popupNum) {
		return popupNecessary[popupNum];
	}

	public void doneWithPopup(int popupNum) {
		popupDone[popupNum] = true;
	}
}