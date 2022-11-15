package model;
import java.util.List;

public class RemoveMutterLogic {
	public void execute(List<Mutter> mutterList) {
		mutterList.remove(0);
	}
}
