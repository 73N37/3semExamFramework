package dat;

import dat.config.ApplicationConfig;

public class Main
{

    public static void main(String[] args)
    {
        ApplicationConfig.startServer(7070);

        dat.Factory.Information.DTO.BaseDTO dto = new dat.Factory.Information.DTO.BaseDTO(1);

    }
}