public class RunMe {
    
    public static void main (String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                InvadersGameController game = new InvadersGameController();
            }
        });
    }
}