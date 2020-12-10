import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> res = new HashMap<String, String>();
        Scanner in = new Scanner(System.in);
        System.out.println("Привет! Это погодное приложение позволит тебе узнать погоду на определенный день, неделю, две недели или целый месяц.\n" +
                "Для начала выбери погодный сервис \n P.S. (нужно ввести только цифру из списка)");

        ArrayList<String> WeatherServices = new ArrayList(){{
            add("World-Weather.ru");
        }};


        String services_str = "";
        for (int i = 0; i < WeatherServices.size(); i++){
            services_str += "\n| " + (i+1) + " | " + WeatherServices.get(i);
        }
        System.out.println(services_str);
        System.out.print("\nНомер сервиса: ");
        int service_num = in.nextInt();
        service_num = service_num - 1;

        System.out.println("Работаем с сайтом "+ WeatherServices.get(service_num));

        if (service_num == 0){
            // World-weather.ru
            String cities_str = "";
            String[] cities = Tools.getCities().keySet().toArray(new String[0]);
            for(int el = 0; el < cities.length; el++) {
                cities_str += "\n| " + (el+1) + " | " + cities[el].substring(0, 1).toUpperCase() + cities[el].substring(1);
            }
            System.out.println(cities_str);
            System.out.print("\nВыбери название города из списка: ");
            int city_num = in.nextInt();

            String selected_city = cities[city_num-1]; // Выбранный город
            System.out.println("Выбранный город --> " + selected_city.toUpperCase());

            // Меню выбора типа прогноза
            ArrayList<String> FunctionsList = new ArrayList(){{
                add("Прогноз погоды на определенный день");
                add("Прогноз погоды на неделю");
                add("Прогноз погоды на две недели");
                add("Прогноз погоды на месяц");
            }};

            String functions_str = "";
            for (int i = 0; i < FunctionsList.size(); i++){
                functions_str += "\n| " + (i+1) + " | " + FunctionsList.get(i);
            }
            System.out.println(functions_str);
            System.out.print("\nВыбери тип прогноза который хотите получить: ");
            int function_num = in.nextInt();
            in.nextLine();

            System.out.println("Выбранный тип прогноза --> " + FunctionsList.get(function_num - 1));

            if (function_num == 1){
                System.out.println("\nВведите желаемую дату в одном из предмагаемых форматах" +
                        "\n1 -> <День>.<Месяц>.<Год>" +
                        "\n2 -> <День><Пробел><Месяц словом>" +
                        "\n3 -> <День><Пробел><Месяц словом><Пробел><Год>");
                System.out.print(" > ");
                String input_date = in.nextLine();
                System.out.println("Введенная дата --> " + input_date);

                System.out.println(WorldWeatherParser.getDayForecast(selected_city, input_date));
            } else if (function_num == 2){
                res = WorldWeatherParser.getWeekForecast(selected_city);
            } else if (function_num == 3){
                res = WorldWeatherParser.getTwoWeekForecast(selected_city);
            } else if (function_num == 4){
                res = WorldWeatherParser.getMonthForecast(selected_city);
            }

            for (Map.Entry<String, String> pair : res.entrySet())
            {
                String key = pair.getKey();             //ключ
                String value = pair.getValue();         //значение
                System.out.println("KEY = " + key + "\n*******VALUE*******" + value);
                System.out.println("</+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\\>");
            }

        } else if (service_num == 1) {
            // второй сервис
        } else if (service_num == 2) {
            // третий сервис
        }
    }
}
