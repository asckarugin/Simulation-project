package by.asckarugin.Area;

public class ClearScreen {
    public static void clrscr() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.print("\033[0;0H");
        System.out.flush();
    }
}
