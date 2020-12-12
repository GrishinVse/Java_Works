package Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Tools {
    static SimpleDateFormat year_format = new SimpleDateFormat("yyyy");

    // Функции возвращает номер дня в двух знаковом формате
    public static String getDay(String day_date) throws IOException {
        String out = day_date;
        if (day_date.length() == 1){
            out = "0" + day_date;
        }
        return out;
    }

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

    // Функция возвращает русское наименование месяца
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

    // Функция возвращает английское наименование месяца по его номеу
    public static String getEngMonth(String month) throws IOException {
        final HashMap<String, String> months = new HashMap<String, String>(){{
            put("01", "january");
            put("02", "february");
            put("03", "march");
            put("04", "april");
            put("05", "may");
            put("06", "june");
            put("07", "july");
            put("08", "august");
            put("09", "september");
            put("10", "october");
            put("11", "november");
            put("12", "december");}};

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
            put("нижний новгород", "nizhny_novgorod");
            put("владимир", "vladimir");
            put("краснодар", "krasnodar");
            //put("", "");
        }};

        return cities;
    }

    // Функция созвращает название города для сайта "https://world-weather.ru/pogoda/russia/"
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

    // Функция созвращает название города для сайта "https://weather.rambler.ru"
    public static String getCityRambler(String city) throws IOException {
        final HashMap<String, String> cities = new HashMap<String, String>(){{
            put("красногорск", "v-krasnogorske/moskovskaya-oblast");
            put("москва", "v-moskve");
            put("санкт-перербург", "v-sankt-peterburge");
            put("казань", "v-kazani");
            put("нижний новгород", "v-nizhnem-novgorode");
            put("владимир", "vo-vladimire");
            put("краснодар", "v-krasnodare");
            //put("", "");
        }};

        String out = "Не найдено такого города!";

        for (String key : cities.keySet()) {
            if (key.equals(city.toLowerCase())){
                out = cities.get(key);
            }
        }
        return out;
    }

    // Функция созвращает название города для сайта "https://pogoda.mail.ru/prognoz/"
    public static String getCityMail(String city) throws IOException {
        final HashMap<String, String> cities = new HashMap<String, String>(){{
            put("красногорск", "krasnogorsk");
            put("москва", "moskva");
            put("санкт-перербург", "sankt_peterburg");
            put("казань", "kazan");
            put("нижний новгород", "nizhniy_novgorod");
            put("владимир", "vladimir");
            put("краснодар", "krasnodar");
            //put("", "");
        }};

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

    // Функция вытаскивает из класса <div data-block="detail_1"> всю информацию
    public static String getDataBlock(Element element){
        String outline = "";

        Element date_part = element.selectFirst("div[class=_3xSf]");
        String date = date_part.text().substring(0, 1).toUpperCase() + date_part.text().substring(1);

        outline += date + "\n\t| ";
        Element weather_part = element.selectFirst("div[class=_1VRJ]");

        Element temp_part = weather_part.selectFirst("div[class=P2oi _1cWr]");
        for (Element el : temp_part.select("div[class=_1BF_]")){
            outline += el.text() + " | ";
        }

        Element data_part = weather_part.selectFirst("div[class=Df07]");
        outline += "\n\t|";
        for (Element el : data_part.select("span[class=_25L5]")){
            if (el.select("span[class=OARN]").first() != null){
                String text = el.text().replace("Осадки", "");
                outline += " ~ " + text;
            } else {
                outline += " ~ " + el.text();
            }
        }
        outline += " ~ |";

        return outline;
    }

    // Функция вытаскивает из класса <div class="cols__column__inner"> всю информацию
    public static String getColumnInner(Element element){
        String outline = "";

        //System.out.println(element);

        Element date_part = element.selectFirst("div[class=heading heading_minor heading_line]");
        if (date_part == null) {
            date_part = element.selectFirst("div[class=heading heading_minor heading_line text-red]");
        }

        String final_date = date_part.text();
        if (final_date.startsWith("Сегодня - ")){
            final_date = final_date.replace("Сегодня - ","");
        }

        Element temp_part = element.selectFirst("div[class=cols__column__item cols__column__item_2-1 cols__column__item_2-1_ie8]");

        for (Element day_per : temp_part.select("div[class=day day_period]")){
            String info = "";

            String per_name = day_per.select("div[class=day__date]").text();
            String temp = day_per.select("div[class=day__temperature]").text();
            String wth_scene = day_per.select("div[class=day__description] > span").first().attr("title");
            String temp_feel = day_per.select("div[class=day__description] > span[class=day__oschuschkak]").first().attr("title");
            String add_info = "| ";
            for (Element add : day_per.select("div[class=day__additional]")){
                add_info += add.select("span").attr("title") + " | ";
            }

            outline += "> "+ per_name + ", " + wth_scene +
                    "\n\tТемпература: " + temp + " ( " + temp_feel + ")" +
                    "\n\t" + add_info + "\n";
        }

        outline += "Дополнительная информация --> | ";
        Element other_part = element.selectFirst("div[class=cols__column__item cols__column__item_2-1]");
        for (Element meteo : other_part.select("div[class=history-meteo__inner] > div[class=history-meteo__inner]")){
            outline += meteo.selectFirst("div[class=history-meteo__title]").text() + " " + meteo.selectFirst("div[class=history-meteo__info]").text() + " | ";
        }

        for (Element meteo : other_part.select("div[class=history-meteo__inner] > div[class=history-meteo__inner history-meteo__inner_vertical]")){
            outline += meteo.selectFirst("div[class=history-meteo__title]").text() + " " + meteo.selectFirst("div[class=history-meteo__info]").text() + " | ";
        }

        return outline;
    }

    // Функция вытаскивает из класса <a class="day__link day__link_black">  всю информацию
    public static String getDayCalendar(Element element){
        String outline = "";

        Element date_part = element.selectFirst("div[class=day__date]");
        if (date_part == null) {
            date_part = element.selectFirst("div[class=day__date text-red]");
        }

        String date = date_part.text();

        if (date_part.selectFirst("span[class=day__date__today]") != null) {
            String date_str = date_part.text().replace("Сегодня ", "");
            date = date_str;
        }

        String day_temp = element.selectFirst("div[class=day__temperature]").text().split(" ")[0];
        String night_temp = element.selectFirst("div[class=day__temperature]").text().split(" ")[1];
        if (element.selectFirst("div[class=day__description]") == null && element.selectFirst("div[class=day__info-wrapper]") == null){

            String alter = element.selectFirst("div[class=day__alternative]").text();

            outline += "> "+ date +
                    "\n\t" + alter +
                    "\n\t  Температура: Днем " + day_temp + " | Ночью " + night_temp + "\n";

        } else {
            String wth_scene = element.selectFirst("div[class=day__description]").text();

            String info_str = "| ";
            for (Element info : element.select("div[class=day__info-wrapper] > div[class=day__additional]")){
                info_str += info.select("span").attr("title") + " | ";
            }

            outline += "> "+ date + ", " + wth_scene +
                    "\n\t  Температура: Днем " + day_temp + " | Ночью " + night_temp +
                    "\n\t" + info_str + "\n";
        }
        return outline;
    }

    // Функция считывает файл
    public static String readFile(File file) throws IOException {
        Scanner scanner = new Scanner(file, Charset.defaultCharset());
        String data = "";

        scanner.useDelimiter(System.lineSeparator());
        while(scanner.hasNext()){
            data += scanner.next();
        }
        scanner.close();

        return data;
    }
}
