package Proyecto;
import Proyecto.controller.MainController;
import Proyecto.model.MainModel;
import Proyecto.view.MainView;

public class App {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView view = new MainView();
        MainController mainController = new MainController(model, view);
    }
}

