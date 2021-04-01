package a_review.corejava;

/**
 * created by Ethan-Walker on 2019/3/22
 */
public class Singleton {
    enum SingleHandler {
        SINGLE_HANDLER;
        Singleton singleton;

        private SingleHandler() {
            this.singleton = new Singleton();
        }

        private Singleton getSingleton() {
            return singleton;
        }
    }

    public static Singleton getSingleton() {
        return SingleHandler.SINGLE_HANDLER.getSingleton();
    }

}





