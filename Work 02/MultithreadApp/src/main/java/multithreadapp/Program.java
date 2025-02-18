package multithreadapp;

import java.util.Scanner;

public class Program
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Num of Threads: ");
        int numThreads = scanner.nextInt();

        System.out.print("Enter calculation length (1 - 20): ");
        ProgressBar.MaxValue = scanner.nextInt();

        ConsolePrinter.clear();

        BarThread[] threads = new BarThread[numThreads];
        for (int i = 0; i < numThreads; i++)
        {
            threads[i] = new BarThread(0, i + 1);
            threads[i].start();
        }

        for (BarThread thread : threads)
        {
            try
            {
                thread.join();
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        //ConsolePrinter.clear();
        ConsolePrinter.printAt(numThreads + 1, 0, "Final.");
        scanner.close();
    }
}
