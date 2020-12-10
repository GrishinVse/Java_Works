import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class Tools {
    // Функция возвращает номер месяца по его названию
    public static String getMonth(String month) throws IOException {
        final HashMap<String, String> months = new HashMap<String, String>(){{
            put("январь", "01");
            put("февраль", "02");
            put("март", "03");
            put("апрель", "04");
            put("май", "05");
            put("июнь", "06");
            put("июль", "07");
            put("август", "08");
            put("сентябрь", "09");
            put("октябрь", "10");
            put("ноябрь", "11");
            put("декабрь","12");}};

        String out = "";

        for (String key : months.keySet()) {
            String loc_key = key.substring(0, 3);
            if (loc_key.equals(month.toLowerCase().substring(0, 3))){
                out = months.get(key);
            }
        }
        return out;
    }

    // Функция возвращает английское наименование месяца
    public static String getRusMonth(String month) throws IOException {
        final HashMap<String, String> months = new HashMap<String, String>(){{
            put("january", "января");
            put("february", "февраля");
            put("march", "марта");
            put("april", "апреля");
            put("may", "мая");
            put("june", "июня");
            put("july", "июля");
            put("august", "августа");
            put("september", "сентября");
            put("october", "октября");
            put("november", "ноября");
            put("december", "декабря");}};

        String out = "";

        for (String key : months.keySet()) {
            String loc_key = key;
            if (loc_key.equals(month.toLowerCase())){
                out = months.get(key);
            }
        }
        return out;
    }

    // Функция возвращает страницу, с которой работаем дальше
    public static Document getPage(String url) throws IOException {
        try {
            Document page = Jsoup.parse(new URL(url), 10000);
            return page;
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    // Возвращает словарь городов и их английских названий
    public static HashMap<String, String> getCities() throws IOException {
        final HashMap<String, String> cities = new HashMap<String, String>(){{
            put("красногорск", "krasnogorsk");
            put("москва", "moscow");
            put("санкт-перербург", "saint_petersburg");
            put("казань", "kazan");
            put("новосибирск", "novosibirsk");
            put("екатеринбург", "yekaterinburg");
            put("нижний новгород", "nizhny_novgorod");
            //put("", "");
        }};

        return cities;
    }

    // Функция созвращает название города
    public static String getCity(String city) throws IOException {
        final HashMap<String, String> cities = getCities();

        String out = "Не найдено такого города!";

        for (String key : cities.keySet()) {
            if (key.equals(city.toLowerCase())){
                out = cities.get(key);
            }
        }
        return out;
    }

    // Функция вытаскивает из класса <tr class="something"> всю информацию
    public static String getWeatherShort(Element element){
        String outline = "";

        String day_time = element.select("td[class=weather-day]").text();
        String wth_scene = element.select("div").attr("title");
        String temp = element.select("td[class=weather-temperature]").text();
        String temp_feel = element.select("td[class=weather-feeling]").text();
        String fallout = element.select("td[class=weather-probability]").text();
        String pressure = element.select("td[class=weather-pressure]").text();
        String wind = element.select("td[class=weather-wind]").select("span").attr("title");
        String wind_sd = element.select("span[class=tooltip]").text() + " м/с (" +  element.select("span[class=tooltip]").attr("title").trim() + ")";
        String wet = element.select("td[class=weather-humidity]").text();

        outline = "> "+ day_time + ", " + wth_scene +
                "\n\tТемпература: " + temp + " (ощущается как " + temp_feel + ")" +
                "\n\tВероятность осадков: " + fallout + " | Давление " + pressure + "мм рт. ст. | Влажность воздуха: " + wet +
                "\n\tВетер: " + wind + " | скорость = " + wind_sd;
        return outline;
    }
}
