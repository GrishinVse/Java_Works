package Parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MailParser {

    // Выдает информацию на сегодняшний день
    public static String getTodayForecast(String selected_city) throws IOException {
        String city = Tools.getCityMail(selected_city);

        String url = "https://pogoda.mail.ru/prognoz/" + city + "/";
        System.out.println(url);
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy");
        String curr_date = date_format.format(new Date());

        Element tableWth = page.select("div[class=information block js-city_one]").first();

        String date_part = tableWth.selectFirst("div[class=information__header__left__date]").text().substring(0, 1).toUpperCase() +
                tableWth.selectFirst("div[class=information__header__left__date]").text().substring(1);

        String final_str = "";

        Element other_part = tableWth.selectFirst("div[class=information__content__wrapper information__content__wrapper_left]");

        String temp = other_part.selectFirst("div[class=information__content__temperature]").text();
        String temp_feel = other_part.selectFirst("div[class=information__content__additional__item] > span").attr("title");
        String wth_scene = other_part.selectFirst("div[class=information__content__additional information__content__additional_first] > div[class=information__content__additional__item]").text();

        final_str = "> "+ date_part + " (" + curr_date + ") , " + wth_scene +
                "\n\tТемпература: " + temp + " (" + temp_feel + ")" +
                "\n\t| ";

        for (Element element : other_part.select("div[class=information__content__additional information__content__additional_second] > div[class=information__content__additional__item]")){
            Elements els = element.select("span");
            for (Element el : els){
                if (el.attr("title") != ""){
                    final_str += el.attr("title") + " | ";
                }
            }
        }

        return final_str;
    }

    // Выдает информацию по определенному дню
    public static String getDayForecast(String selected_city, String date) throws IOException {
        String final_str;

        String final_date = "";
        String eng_month = "";
        String year = "";
        HashMap<String, String> final_res = new HashMap<String, String>();

        if (date.matches("(\\d+)\\.([0-1][0-9])\\.(\\d{4})")) {

            // Дата задана в формате <День>.<Месяц>.<Год>
            final_date = date.trim();
            eng_month = Tools.getEngMonth(date.split("\\.")[1]);
            year = date.split("\\.")[2].trim();

        } else if (date.matches("(\\d+) ([а-яА-Я]*)")){

            // Дата задана в формате <День><Пробел><Месяц словом>
            final_date = date.split("\\s")[0] + "." + Tools.getMonth(date.split("\\s")[1]) + "." + Tools.year_format.format(new Date());
            eng_month = Tools.getEngMonth(Tools.getMonth(date.split("\\s")[1]));
            year = Tools.year_format.format(new Date()).trim();

        } else if (date.matches("(\\d+) ([а-яА-Я]*) (\\d{4})")){

            // Дата задана в формате <День><Пробел><Месяц словом><Пробел><Год>
            final_date = date.split("\\s")[0] + "." + Tools.getMonth(date.split("\\s")[1]) + "." + date.split("\\s")[2];
            eng_month = Tools.getEngMonth(Tools.getMonth(date.split("\\s")[1]));
            year = date.split("\\s")[2].trim();

        } else {
            return "Дата задана в неправильном формате!";
        }

        final_res = getMonthForecast(selected_city, eng_month, year);
        final_str = final_res.get(final_date);

        return final_str;
    }

    // Выдает информацию на 2 недели
    public static HashMap<String, String> getTwoWeekForecast(String selected_city) throws IOException {
        String city = Tools.getCityMail(selected_city);

        String url = "https://pogoda.mail.ru/prognoz/" + city + "/14dney/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements tableWth = page.select("div[class=block] > div[class=wrapper] > div[class=cols clearfix] > div[class=cols__column cols__column_left] > div[class=cols__column__inner]");

        for (Element element : tableWth){
            String final_str = "";

            Element date_part = element.selectFirst("div[class=heading heading_minor heading_line]");
            if (date_part == null) {
                date_part = element.selectFirst("div[class=heading heading_minor heading_line text-red]");
            }

            String final_date = date_part.text();
            if (final_date.startsWith("Сегодня - ")){
                final_date = final_date.replace("Сегодня - ","");
            }

            String date_key = final_date.split("\\s")[0] + '.' + Tools.getMonth(final_date.split("\\s")[1]) + '.' + Tools.year_format.format(new Date());

            final_str += Tools.getColumnInner(element);

            final_res.put(date_key, final_str);
        }

        return final_res;
    }

    // Выдает информацию на месяц
    public static HashMap<String, String> getMonthForecast(String selected_city) throws IOException {
        String city = Tools.getCityMail(selected_city);
        SimpleDateFormat month_format = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String month = month_format.format(new Date()).toLowerCase();

        String url = "https://pogoda.mail.ru/prognoz/" + city + "/" + month + "-" + Tools.year_format.format(new Date()) + "/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements rows = page.select("div[class=calendar-month__row]");
        for (Element row : rows){
            if (row.text().equals("Пн Вт Ср Чт Пт Сб Вс"))
            {

            } else {

                Elements days = row.select("a[class=day__link day__link_black]");

                for (Element day : days){

                    Element date_part = day.selectFirst("div[class=day__date]");
                    if (date_part == null) {
                        date_part = day.selectFirst("div[class=day__date text-red]");
                    }
                    String date_key = "";
                    if (date_part.selectFirst("span[class=day__date__today]") != null){

                        String date_str = date_part.text().replace("Сегодня ", "");

                        date_key = Tools.getDay(date_str.split("\\s")[0]) + '.'
                                + Tools.getMonth(date_str.split("\\s")[1]) + '.'
                                + Tools.year_format.format(new Date());
                    } else {
                        date_key = Tools.getDay(date_part.text().split("\\s")[0]) + '.'
                                + Tools.getMonth(date_part.text().split("\\s")[1]) + '.'
                                + Tools.year_format.format(new Date());
                    }

                    String outline = Tools.getDayCalendar(day);

                    final_res.put(date_key, outline);
                }
            }
        }
        return final_res;
    }

    // Выдает информацию на месяц (месяц передается как английское слово | )
    public static HashMap<String, String> getMonthForecast(String selected_city, String month, String year) throws IOException {
        String city = Tools.getCityMail(selected_city);

        String url = "https://pogoda.mail.ru/prognoz/" + city + "/" + month.toLowerCase() + "-" + year.toLowerCase() + "/";
        System.out.println(url);
        HashMap<String, String> final_res = new HashMap<String, String>();
        Document page = Tools.getPage(url);

        if (page.equals(null)){ return null; }

        Elements rows = page.select("div[class=calendar-month__row]");
        for (Element row : rows){
            if (row.text().equals("Пн Вт Ср Чт Пт Сб Вс"))
            {

            } else {

                Elements days = row.select("a[class=day__link day__link_black]");

                for (Element day : days){

                    Element date_part = day.selectFirst("div[class=day__date]");
                    if (date_part == null) {
                        date_part = day.selectFirst("div[class=day__date text-red]");
                    }
                    String date_key = "";
                    if (date_part.selectFirst("span[class=day__date__today]") != null){

                        String date_str = date_part.text().replace("Сегодня ", "");

                        date_key = Tools.getDay(date_str.split("\\s")[0]) + '.'
                                + Tools.getMonth(date_str.split("\\s")[1]) + '.'
                                + Tools.year_format.format(new Date());
                    } else {
                        date_key = Tools.getDay(date_part.text().split("\\s")[0]) + '.'
                                + Tools.getMonth(date_part.text().split("\\s")[1]) + '.'
                                + Tools.year_format.format(new Date());
                    }

                    String outline = Tools.getDayCalendar(day);

                    final_res.put(date_key, outline);
                }
            }
        }
        return final_res;
    }

}
