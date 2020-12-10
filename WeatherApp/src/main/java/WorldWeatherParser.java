import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class WorldWeatherParser {
    final static SimpleDateFormat year_format = new SimpleDateFormat("yyyy");

    // Выдает информацию на неделю V2.0
    public static HashMap<String, String> getWeekForecast_old(String city_name) throws IOException {
        String city = Tools.getCity(city_name);
        String url = "https://world-weather.ru/pogoda/russia/" + city + "/";
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);
        if (page.equals(null)){ return null; }
        Element tableWth = page.select("ul[id=vertical_tabs]").first(); // выборка 7 дней начиная с текущего

        System.out.println("******************************");

        Elements days = tableWth.select("li");
        for (Element day: days){
            String final_str = "";
            String day_of_week = day.selectFirst("div[class=day-week]").text();
            final_str += "День недели: " + day_of_week + "\n";

            String date = day.selectFirst("div[class=numbers-month]").text();
            String month = day.selectFirst("div[class=month]").text();
            final_str += "Дата: " + date + " " + month + "\n";

            String weather = day.selectFirst("span").attr("title");
            final_str += "Погода: " + weather + "\n";

            String day_t = day.selectFirst("div[class=day-temperature]").text();
            String night_t = day.selectFirst("div[class=night-temperature]").text();
            final_str += "Температура Днем: " + day_t + "\n" + "Температура Ночью: " + night_t;
            String date_key = date + '.' + Tools.getMonth(month) + '.' + year_format.format(new Date());

            final_res.put(date_key, final_str);
        }
        return final_res;
    }

    // Выдает информацию на неделю V2.0
    public static HashMap<String, String> getWeekForecast(String city_name) throws IOException {
        String city = Tools.getCity(city_name);

        String url = "https://world-weather.ru/pogoda/russia/" + city + "/7days/";
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }
        Elements tableWth = page.select("div[class=weather-short]"); // выборка 7 дней начиная с текущего

        System.out.println("\n******************************");

        for (Element day_el: tableWth){
            String final_str = "";
            Element day_of_week = day_el.selectFirst("div[class=dates short-d]");

            if (day_of_week == null){
                day_of_week = day_el.selectFirst("div[class=dates short-d red]");
            }

            final_str += "День недели: " + day_of_week.text().split(",")[0] + "\n";
            String current_date = day_of_week.text().split(",")[1].trim();
            final_str += "Дата: " + current_date + "\n\n< Погода в течении дня >\n\n";

            Element night = day_el.select("tr[class=night]").first();
            Element morning = day_el.select("tr[class=morning]").first();
            Element day = day_el.select("tr[class=day]").first();
            Element evening = day_el.select("tr[class=evening]").first();

            final_str += Tools.getWeatherShort(night) + "\n***---***\n" + Tools.getWeatherShort(morning) + "\n***---***\n" +
                         Tools.getWeatherShort(day) + "\n***---***\n" + Tools.getWeatherShort(evening);

            String date_key = current_date.split("\\s")[0] + '.' + Tools.getMonth(current_date.split("\\s")[1]) + '.' + year_format.format(new Date());

            final_res.put(date_key, final_str);
        }
        return final_res;
    }

    // Выдает информацию по определенному дню
    public static String getDayForecast(String city_name, String date) throws IOException {
        String city = Tools.getCity(city_name);
        //boolean isDate = date.matches("(\\d+) ([а-яА-Я]*)");

        String final_str = "\n";

        String final_date = "";
        if (date.matches("(\\d+)\\.([0-1][0-9])\\.(\\d{4})")) {
            // Дата задана в формате <День>.<Месяц>.<Год>
            final_date = date.split("\\.")[2] + "-" + date.split("\\.")[1] + "-" + date.split("\\.")[0];
        } else if (date.matches("(\\d+) ([а-яА-Я]*)")){
            // Дата задана в формате <День><Пробел><Месяц словом>
            final_date = year_format.format(new Date()) + "-" + Tools.getMonth(date.split("\\s")[1]) + "-" + date.split("\\s")[0];
        } else if (date.matches("(\\d+) ([а-яА-Я]*) (\\d{4})")){
            // Дата задана в формате <День><Пробел><Месяц словом><Пробел><Год>
            final_date = date.split("\\s")[2] + "-" + Tools.getMonth(date.split("\\s")[1]) + "-" + date.split("\\s")[0];
        }

        String url = "https://world-weather.ru/pogoda/russia/" + city + "/14days/#" + final_date;
        System.out.println(url);
        Document page = Tools.getPage(url);

        // можно ли выбрать слелующий элемент после выбранного ???
        //Element tableWth = page.select("a[name=" + final_date + "]").first();
        //Elements tableWth = page.select("div[class=weather-short] > div[class=dates short-d], div[class=weather-short] > table[class=weather-today short]");

        Elements tableWth = page.select("div[class=weather-short]");
        for (Element element : tableWth){
            Element var = element.select("div[class=dates short-d]").first();
            if (var == null) {
                var = element.selectFirst("div[class=dates short-d red]");
            }

            String date_part = var.text();

            Element other_part = element.select("table[class=weather-today short]").first();
            String str = date_part.split(",")[1].trim();
            str = year_format.format(new Date()) + "-" + Tools.getMonth(str.split("\\s")[1]) + "-" + str.split("\\s")[0];
            if (str.equals(final_date)) {
                Element night_forecast = other_part.select("tr[class=evening fourteen-n]").first();
                Element day_forecast = other_part.select("tr[class=day fourteen-d]").first();

                final_str += Tools.getWeatherShort(night_forecast) + "\n\n***---***\n\n" + Tools.getWeatherShort(day_forecast);
            }
        }
        return final_str;
    }

    // Выдает информацию на 2 недели
    public static HashMap<String, String> getTwoWeekForecast(String city_name) throws IOException {
        String city = Tools.getCity(city_name);

        String url = "https://world-weather.ru/pogoda/russia/" + city + "/14days/";
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        Elements tableWth = page.select("div[class=weather-short]");
        for (Element element : tableWth){
            String final_str = "\n";
            Element var = element.select("div[class=dates short-d]").first();
            if (var == null) {
                var = element.selectFirst("div[class=dates short-d red]");
            }

            String date_part = var.text();

            Element other_part = element.select("table[class=weather-today short]").first();
            String str = date_part.split(",")[1].trim();

            str =  str.split("\\s")[0] + '.' + Tools.getMonth(str.split("\\s")[1]) + '.' + year_format.format(new Date());

            final_str += "Дата: " + date_part + "\n\n< Погода в течении дня >\n\n";

            Element night_forecast = other_part.select("tr[class=evening fourteen-n]").first();
            Element day_forecast = other_part.select("tr[class=day fourteen-d]").first();

            final_str += Tools.getWeatherShort(night_forecast) + "\n\n" + Tools.getWeatherShort(day_forecast);
            final_res.put(str, final_str);
        }
        return final_res;
    }

    // Выдает информацию на месяц
    // Использовать выбор по дню как образец
    public static HashMap<String, String> getMonthForecast(String city_name) throws IOException {
        String city = Tools.getCity(city_name);
        SimpleDateFormat date_format = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String month = date_format.format(new Date()).toLowerCase();

        String url = "https://world-weather.ru/pogoda/russia/" + city + "/" + month + "-" + year_format.format(new Date()) + "/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        Elements tableWth = page.select("ul[class=ww-month]").first().select("li");
        //System.out.println(tableWth.first());
        //System.out.println("|" + tableWth.first().text() + "|");
        String month_rus = Tools.getRusMonth(month);

        for (Element element : tableWth){
            if (!element.text().isEmpty()){
                String outline = "";

                String date = element.select("div").first().text() + " " + month_rus;
                String wth_scene = element.select("i").attr("title");
                String day_temp = element.select("span").text();
                String night_temp = element.select("p").text();

                outline = "\n> "+ date + ", " + wth_scene +
                        "\n\tТемпература: Днём = " + day_temp + " | Ночью = " + night_temp;

                String date_key = date.split("\\s")[0] + '.' + Tools.getMonth(date.split("\\s")[1]) + '.' + year_format.format(new Date());
                //System.out.println(date_key + "\n" + outline);
                final_res.put(date_key, outline);
            }
        }

        return final_res;
    }
}