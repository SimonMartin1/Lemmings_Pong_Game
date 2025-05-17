package Proyecto;
import Proyecto.Model.MainModel;
import Proyecto.View.MainView;
import Proyecto.Controller.MainController;

public class Main {
    public static void main(String[] args) {
        MainModel model = new MainModel();
        MainView mainView = new MainView();
        MainController mainController = new MainController(model, mainView);
    }
}

