import Parsers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Переменный определяющие выбор пользователя
        int service_num = -1;
        int city_num = -1;
        int function_num = -1;


        HashMap<String, String> res = new HashMap<String, String>();
        Scanner in = new Scanner(System.in);
        System.out.println("Привет! Это погодное приложение позволит тебе узнать погоду на определенный день, неделю, две недели или целый месяц.\n" +
                "Для начала выбери погодный сервис \n P.S. (нужно ввести только цифру из списка)");

        // Меню выбора сервиса
        ArrayList<String> WeatherServices = new ArrayList(){{
            add("World-Weather.ru");
            add("Weather.Rambler.ru");
            add("Pogoda.Mail.ru");
        }};

        // Меню выбора типа прогноза
        ArrayList<String> FunctionsList = new ArrayList(){{
            add("Прогноз погоды на сегодня");
            add("Прогноз погоды на определенный день");
            add("Прогноз погоды на неделю");
            add("Прогноз погоды на две недели");
            add("Прогноз погоды на месяц");
        }};

        boolean loadFromSave = false;
        boolean restartApp = true;

        /*
        // Позволяет держать приложение в состоянии работы постоянно
        boolean isActive = true;

        while (isActive) {
            isActive = false
        }
        */
        String pathToSave = System.getProperty("user.dir");
        System.out.println(pathToSave);
        File saveFile = new File(pathToSave + "\\save.txt");
        FileWriter writer = new FileWriter(saveFile, true);

        if (saveFile.exists()){
            System.out.print("Хотите ли вы восстановить данный предыдущей сессии?\n Введите <Y> или <Д> чтобы восстановить ИЛИ любое другое значение чтобы задать поиск заново : ");
            String status = in.next();
            if ((status.toLowerCase().equals("y")) || (status.toLowerCase().equals("д"))){
                loadFromSave = true;
                restartApp = false;
            } else /*if ((status.toLowerCase().equals("n")) || (status.toLowerCase().equals("н")))*/ {
                new FileWriter(saveFile, false).write("-");
            }
        } else if (!saveFile.exists()) {
            System.out.println("Создание файла !!! " + saveFile.createNewFile());
        }


        // Выбор сервиса
        String services_str = "";
        for (int i = 0; i < WeatherServices.size(); i++){
            services_str += "\n| " + (i+1) + " | " + WeatherServices.get(i);
        }
        System.out.println(services_str);
        System.out.print("\nНомер сервиса: ");

        // Работа с сохранением (service_num)
        if (restartApp == true && loadFromSave == false) {
            service_num = in.nextInt();
            in.nextLine();

            // Устанавливаем разделитель
            writer.write(service_num + "-");

            writer.flush();

            System.out.println("---> " + Tools.readFile(saveFile));
        } else if (restartApp == false && loadFromSave == true) {
            String text = Tools.readFile(saveFile);
            service_num = Integer.parseInt(text.split("-")[0]);
        }

        System.out.println("Работаем с сайтом "+ WeatherServices.get(service_num - 1));


        // Выбор города
        String cities_str = "";
        String[] cities = Tools.getCities().keySet().toArray(new String[0]);
        for(int el = 0; el < cities.length; el++) {
            cities_str += "\n| " + (el+1) + " | " + cities[el].substring(0, 1).toUpperCase() + cities[el].substring(1);
        }
        System.out.println(cities_str);
        System.out.print("\nВыбери название города из списка: ");

        // Работа с сохранением (city_num)
        if (restartApp == true && loadFromSave == false) {
            city_num = in.nextInt();

            writer.write(city_num + "-");
            writer.flush();

            System.out.println("---> " + Tools.readFile(saveFile));
        } else if (restartApp == false && loadFromSave == true) {
            String text = Tools.readFile(saveFile);
            city_num = Integer.parseInt(text.split("-")[1]);
        }

        String selected_city = cities[city_num - 1]; // Выбранный город
        System.out.println("Выбранный город --> " + selected_city.toUpperCase());


        // Выбор функции (тип прогноза)
        String functions_str = "";
        for (int i = 0; i < FunctionsList.size(); i++){
            functions_str += "\n| " + (i+1) + " | " + FunctionsList.get(i);
        }
        System.out.println(functions_str);
        System.out.print("\nВыбери тип прогноза который хотите получить: ");

        // Работа с сохранением (function_num)
        if (restartApp == true && loadFromSave == false) {
            function_num = in.nextInt();

            writer.write(function_num + "-");
            //writer.append((char) function_num);
            //writer.append('-');
            writer.flush();

            System.out.println("---> " + Tools.readFile(saveFile));
        } else if (restartApp == false && loadFromSave == true) {
            String text = Tools.readFile(saveFile);
            function_num = Integer.parseInt(text.split("-")[2]);
        }

        System.out.println("Выбранный тип прогноза --> " + FunctionsList.get(function_num-1));

        // Работа непосредственно с сервисами
        // World-weather.ru
        if (service_num == 1){
            if (function_num == 1){
                System.out.println(WorldWeatherParser.getTodayForecast(selected_city));
            } else if (function_num == 2){
                System.out.println("\nВведите желаемую дату в одном из предмагаемых форматах" +
                        "\n1 -> <День>.<Месяц>.<Год>" +
                        "\n2 -> <День><Пробел><Месяц словом>" +
                        "\n3 -> <День><Пробел><Месяц словом><Пробел><Год>");
                in.nextLine();
                System.out.print(" > ");
                String input_date = in.nextLine();
                System.out.println("Введенная дата --> " + input_date);

                System.out.println(WorldWeatherParser.getDayForecast(selected_city, input_date));
            } else if (function_num == 3){
                res = WorldWeatherParser.getWeekForecast(selected_city);
            } else if (function_num == 4){
                res = WorldWeatherParser.getTwoWeekForecast(selected_city);
            } else if (function_num == 5){
                res = WorldWeatherParser.getMonthForecast(selected_city);
            }
        }
        // Weather.Rambler.ru
        else if (service_num == 2) {
            if (function_num == 1){
                System.out.println(RamblerParser.getTodayForecast(selected_city));
            } else if (function_num == 2){
                System.out.printf("К сожалению сервис %s не предоставляет возможность выбора такой функции!", WeatherServices.get(service_num));
            } else if (function_num == 3){
                res = RamblerParser.getWeekForecast(selected_city);
            } else if (function_num == 4){
                res = RamblerParser.getTwoWeekForecast(selected_city);
            } else if (function_num == 5){
                res = RamblerParser.getMonthForecast(selected_city);
            }

            /*

            for (Map.Entry<String, String> pair : res.entrySet())
            {
                String key = pair.getKey();             //ключ
                String value = pair.getValue();         //значение
                System.out.println("KEY = " + key + "\n*******VALUE*******\n" + value);
                System.out.println("</+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\\>");
            }
            */

        }
        // Pogoda.Mail.ru
        else if (service_num == 3) {
            if (function_num == 1){
                System.out.println(MailParser.getTodayForecast(selected_city));
            } else if (function_num == 2){
                System.out.println("\nВведите желаемую дату в одном из предмагаемых форматах" +
                        "\n1 -> <День>.<Месяц>.<Год>" +
                        "\n2 -> <День><Пробел><Месяц словом>" +
                        "\n3 -> <День><Пробел><Месяц словом><Пробел><Год>");
                in.nextLine();
                System.out.print(" > ");
                String input_date = in.nextLine();
                System.out.println("Введенная дата --> " + input_date);

                System.out.println(MailParser.getDayForecast(selected_city, input_date));
            } else if (function_num == 3){
                System.out.printf("К сожалению сервис %s не предоставляет возможность выбора такой функции!", WeatherServices.get(service_num));
            } else if (function_num == 4){
                res = MailParser.getTwoWeekForecast(selected_city);
            } else if (function_num == 5){
                res = MailParser.getMonthForecast(selected_city);
            }
        }

        // Вывод информации из словаря
        if (!res.isEmpty()) {
            TreeMap<String, String> sorted = new TreeMap<>();
            sorted.putAll(res);

            for (Map.Entry<String, String> pair : sorted.entrySet())
            {
                String key = pair.getKey();             //ключ
                String value = pair.getValue();         //значение
                System.out.println("KEY = " + key + "\n*******VALUE*******\n" + value);
                System.out.println("</+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\\>\n");
            }
        }
    }
}
