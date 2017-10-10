package fr.louisparet.journeydiaries.interaction;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class MainActivityContract {
    public interface Presenter {
        public void onSaveData(String name, String from, String to);
    }
    public interface View {
        public void saveData(String name, String from, String to);
    }
}
