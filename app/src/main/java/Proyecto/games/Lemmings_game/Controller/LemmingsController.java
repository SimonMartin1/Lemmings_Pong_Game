/*package Proyecto.games.Lemmings_game.controller;
import Proyecto.games.Lemmings_game.model.LemmingsModel;
import Proyecto.games.Lemmings_game.view.LemmingsView;

public class LemmingsController {
    // Atributos del controlador
    private LemmingsModel model;
    private LemmingsView view;

    // Constructor
    public LemmingsController(LemmingsModel model, LemmingsView view) {
        this.model = model;
        this.view = view;
    }

    // Métodos para manejar la lógica del juego
    public void startGame() {
        // Lógica para iniciar el juego
    }

    public void updateGame() {
        // Lógica para actualizar el juego
    }

    public void endGame() {
        // Lógica para finalizar el juego
    }

}




// public void fullScreen(){
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//         if(!this.fullscreen){
//         this.dispose();
//         this.setUndecorated(true); 
//         this.setResizable(false);
//         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//         this.setBounds(0, 0, screenSize.width, screenSize.height); 
//         this.setVisible(true); 
//         this.fullscreen = true;
//         }
//     }

//     public void exitfullScreen(){
//         this.dispose();
//         this.setUndecorated(false);
//         this.setResizable(true);
//         this.setExtendedState(JFrame.NORMAL);
//         this.setSize(800, 600);
//         this.setLocationRelativeTo(null);
//         this.setVisible(true);
//         this.fullscreen = false;
//     }


//     public void addListeners(){
//         this.fullScreeen.addActionListener(this);

//         addWindowListener(new WindowAdapter() {
//             @Override public void windowClosing(WindowEvent we) {
//                 System.exit(0);
//             }
//         });
//     }

//     public void keyPressed(KeyEvent e) {
//         int code = e.getKeyCode();
        

//         if ((code == KeyEvent.VK_ENTER) && this.fullscreen) {
            
//             this.exitfullScreen();
            
            
//         }

//     }

//     @Override public void actionPerformed(ActionEvent e) {
//         if(fullScreeen.isSelected()){
            
//             this.fullScreen();
            
//         }
//         if(!fullScreeen.isSelected()){
            
//             this.exitfullScreen();
            
//         }
//     }
// }
*/