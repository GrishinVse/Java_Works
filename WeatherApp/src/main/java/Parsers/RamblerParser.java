package Parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class RamblerParser {

    // Выдает информацию на сегодняшний день
    public static String getTodayForecast(String selected_city) throws IOException {
        String city = Tools.getCityRambler(selected_city);

        String url = "https://weather.rambler.ru/" + city + "/today/";
        System.out.println(url);
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy");
        String curr_date = date_format.format(new Date());

        Element tableWth = page.select("div[class=_3OEL]").first();

        Element temp_part = tableWth.selectFirst("div[class=P2oi _3zyW]");

        String final_str = curr_date + "\n\t| ";
        for (Element el : temp_part.select("div[class=_1BF_]")){
            final_str += el.text() + " | ";
        }

        Element data_part = tableWth.selectFirst("div[class=_2_qR _3ngO]");
        final_str += "\n\t|";
        for (Element el : data_part.select("span[class=_25L5]")){
            if (el.select("span[class=OARN]").first() != null){
                String text = el.text().replace("Осадки", "");
                final_str += " ~ " + text;
            } else if (el.text().equals("Календарь садовода")){
            } else {
                final_str += " ~ " + el.text();
            }
        }
        final_str += " ~ |";

        return final_str;
    }

    // Выдает информацию по определенному дню (можно покопаться)
    public static String getDayForecast(String selected_city, String date) throws IOException {
        String city = Tools.getCityRambler(selected_city);
        //boolean isDate = date.matches("(\\d+) ([а-яА-Я]*)");

        String final_str = "\n";

        return final_str;
    }

    // Выдает информацию на неделю
    public static HashMap<String, String> getWeekForecast(String selected_city) throws IOException {
        String city = Tools.getCityRambler(selected_city);

        String url = "https://weather.rambler.ru/" + city + "/7-days/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements tableWth = page.select("div[class=_3NDm _1gkt] > div");

        for (Element day_el: tableWth){
            Element day_of_week = day_el.selectFirst("div[class=_2J0v _1PKb]");

            if (day_of_week == null){
                day_of_week = day_el.selectFirst("div[class=_2J0v _1PKb zWaK]");
            }

            String info = Tools.getDataBlock(day_of_week);

            String date = info.split("\n")[0].split("\\s")[1] + " " + info.split("\n")[0].split("\\s")[2];
            String date_key = date.split("\\s")[0] + '.' + Tools.getMonth(date.split("\\s")[1]) + '.' + Tools.year_format.format(new Date());

            //System.out.println("KEY = " + date_key + "\n"+ info);

            final_res.put(date_key, info);
        }
        return final_res;
    }

    // Выдает информацию на 2 недели
    public static HashMap<String, String> getTwoWeekForecast(String selected_city) throws IOException {
        String city = Tools.getCityRambler(selected_city);

        String url = "https://weather.rambler.ru/" + city + "/14-days/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements tableWth = page.select("div[class=_3NDm _1gkt] > div");

        for (Element day_el: tableWth){
            Element day_of_week = day_el.selectFirst("div[class=_2J0v _1PKb]");

            if (day_of_week == null){
                day_of_week = day_el.selectFirst("div[class=_2J0v _1PKb zWaK]");
            }

            String info = Tools.getDataBlock(day_of_week);

            String date = info.split("\n")[0].split("\\s")[1] + " " + info.split("\n")[0].split("\\s")[2];
            String date_key = date.split("\\s")[0] + '.' + Tools.getMonth(date.split("\\s")[1]) + '.' + Tools.year_format.format(new Date());

            final_res.put(date_key, info);
        }

        return final_res;
    }

    // Выдает информацию на месяц
    public static HashMap<String, String> getMonthForecast(String selected_city) throws IOException {
        String city = Tools.getCityRambler(selected_city);
        SimpleDateFormat month_format = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String month = month_format.format(new Date()).toLowerCase();

        String url = "https://weather.rambler.ru/" + city + "/" + month + "/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements rows = page.select("div[class=_3XvB]");
        for (Element row : rows){
            Elements days = row.select("div[class=_1uuo]");
            for (Element day : days){
                String outline = "";

                String date_part = day.select("span[class=_11bw]").first().text();

                String final_date = date_part + " " + Tools.getRusMonth(month);

                String day_temp = day.select("span[class=_13tb]").first().text();
                String night_temp = day.select("span[class=_3Vq_]").first().text();

                String wind = day.select("div[class=uR4w] > span").first().text();

                outline = "> "+ final_date +
                        "\n\tТемпература: Днём = " + day_temp + " | Ночью = " + night_temp +
                        "\n\tВетер: " + wind;

                String date_key = Tools.getDay(final_date.split("\\s")[0]) + '.' + Tools.getMonth(final_date.split("\\s")[1]) + '.' + Tools.year_format.format(new Date());

                final_res.put(date_key, outline);
            }
        }
        return final_res;
    }

}
