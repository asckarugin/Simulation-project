package by.asckarugin;

import by.asckarugin.Actions.InitAction;
import by.asckarugin.Actions.TurnAction;
import by.asckarugin.Area.Area;
import by.asckarugin.Area.AreaRender;
import by.asckarugin.Area.ClearScreen;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Simulation {
    private Area area;
    private AreaRender renderer;
    private TurnAction turnAction;
    private Scanner scanner;

    public Simulation() {
        area = new Area();
        renderer = new AreaRender();
        turnAction = new TurnAction(area, renderer);
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        InitAction initAction = new InitAction(simulation.area);
        initAction.perform();

        simulation.startSimulation();
    }

    public void nextTurn() {
        turnAction.perform();
    }

    public void startSimulation() {
        Thread simulationThread = new Thread(() -> {
            while (area.hasGrass() && area.hasHerbivore()) {
                if (!turnAction.isPaused()) {
                    ClearScreen.clrscr();
                    nextTurn();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        simulationThread.start();

        String input;

        System.out.println("Симуляция запущенна. Нажмите 'p' для паузы или 'r' для продолжения или 'q' для выхода...");

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("p")) {
                turnAction.pauseSimulation();
                System.out.println("Симуляция запущенна. Нажмите 'r' для продолжения или 'q' для выхода...");
            } else if (input.equalsIgnoreCase("r")) {
                turnAction.resumeSimulation();
                System.out.println("Симуляция остановленна");
            } else if (input.equalsIgnoreCase("q")) {
                System.exit(0);
            }

            if (!area.hasGrass() || !area.hasHerbivore()) {
                break;
            }
        }
        scanner.close();
    }
}
