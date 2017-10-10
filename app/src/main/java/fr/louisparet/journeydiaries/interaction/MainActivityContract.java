package fr.louisparet.journeydiaries.interaction;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class MainActivityContract {
    public interface Presenter {
        public void onSaveData(String data);
    }
    public interface View {
        public void saveData(String data);
    }
}
