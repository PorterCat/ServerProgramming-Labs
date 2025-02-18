package multithreadapp;

import java.util.Random;

public class BarThread extends Thread
{
    private final ProgressBar _progressBar;
    private long startTime;

    public BarThread(int x, int y)
    {
        _progressBar = new ProgressBar(x, y);
    }

    @Override
    public void run()
    {
        startTime = System.currentTimeMillis();
        int calculationSteps = ProgressBar.MaxValue;

        for (int i = 0; i < calculationSteps; i++)
        {
            try
            {
                Random random = new Random();
                Thread.sleep(random.nextInt(10, 2000));
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }

            _progressBar.increase().visualise();
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        String resultMessage = String.format("Thread %d has finished for %d ms",
                this.getId(), elapsedTime);

        _progressBar.clear().complete(resultMessage);
    }
}
