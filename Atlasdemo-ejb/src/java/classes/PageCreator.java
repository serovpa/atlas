/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.ejb.Stateless;

/**
 *
 * @author Павлик
 */
@Stateless
public class PageCreator implements PageCreatorLocal {

    /* Универсальная верхняя частть для каждой страницы приложения*/
    private String head = "<!DOCTYPE html><html><head><title>Atlas</title><meta charset=windows-1251>"
            + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><style>a { text-decoration: none;} textarea { resize: none; } </style>"
            + "</head><body>"
            + "<Center> <h1>  <a href=\"Mainpage\" title=\"На главную страницу\"><font size=\"10\" color=\"teal\" face=\"Arial\">Атлас от serovpa </font></a></h1> </Center>"
            + "<Right><a href=\"index.html\" title=\"На главную страницу\">Выйти из приложения</a><hr>";
    /*Тело главной страницы*/
    private String mainBody = "<hr></br>"
            + "<Center> <table border = \"0\" bordercolor = \"white\" cellspacing=\"10\" cellpadding=\"10\" frame=\"vsides\" rules=\"cols\" align=\"center\"  >"
            + "<tr>"
            + "<th><a href=\"Regions\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Регионы России </font></a></th>"
            + "<th><a href=\"Sights\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Достопримечательности </font></th>"
            + "<th><a href=\"Events\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">События</font></th>"
            + "<th><a href=\"Camp\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Поделиться мнением</font></th></tr>"
            + "<tr><td align=\"center\" width = \"25%\">В этом разделе приведена справочная информация по всем регионам России. Тут Вы сможете ознакомиться различными фактами о регоне.</td>"
            + "<td align=\"center\" width = \"25%\">Здесь собраны лучшие достопримечательности России. Вы узнаете как к ним добраться и как построить маршрут.</td>"
            + "<td align=\"center\" width = \"25%\">Здесь Вы сможете найти информацию по интересным событиям и активностям, происходящим в регионах.</td>"
            + "<td align=\"center\" width = \"25%\">Если у Вас есть, что рассказать или посоветовать другим туристам, то заходите напрямую сюда</td></tr>"
            + "</table>"
            + "<img src=./images/map.png>"
            + "</Center>"
            + "<hr><center> 2019 </center><footer> <hr>  </footer>"
            + "</body>"
            + "</html>";

    /*Тело страницы Regions*/
    private String regionsBody = "<hr></br>"
            + "В России представлено 22 республики, 9 краев, 46 областей, 3 города федерального значения, 1 автономная область и 4 автомных округа"
            + "<form action=\"Regions\" method=\"Post\">"
            + "О каком регионе Вы хотите узнать: <br/>"
            + "<input type=\"text\" name=\"region\" required id=\"region\" size=\"200\" autocomplete=\"off\"> <br/> "
            + "<input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Найти\"/>"
            + "</form>"
            + "</body>"
            + "</html>";

    /*Тело страницы Sights*/
    private String sightBody = "<hr></br>"
            + "Россия - одна из самых богатых достопримечательностями стран."
            + " Путешествуя по нашей Родине Вы сможете в изобилии найти как природные, так и рукотворные сокровища.</br>"
            + "Вы можете выбрать достопримечательность из списка, пользуясь"
            + " фильтрами, или узнать о любой случайной достопримечательности.";

    /*Тело страницы Sigths In Region*/
    private String sightInRegBody = "<br/><form action=\"SightsInReg\" method=\"Get\">"
            + "О какой достопримечательности Вы хотите узнать? Обязательно пишите название с большой буквы: <br/>"
            + "<input type=\"text\" name=\"sight\" required id=\"region\" size=\"200\" autocomplete=\"off\"/> <br/>"
            + "<input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Найти\"/>"
            + "</form>\""
            + "<hr></body></html>";

    /*Тело страницы События*/
    private String eventsBody = "<br/>Помимо посещения достопримечательностей в России также проходит множество различных событий.>"
            + "Каждый сможет найти что-то себе по душе: выставки, концерты, спортивные соревнования, фестивали и многое другое.<br/>"
            + "Выберите регион в котором хотите посетить мероприятие, а также укажите даты, в которые Вы планируете посетить мероприятие: "
            + "<hr>";

    /*Тело страницы События в Регионе*/
    private String eventsInRegBody = "<br/><form action=\"EventsInReg\" method=\"Get\">"
            + "О каком событии Вы хотите узнать? Обязательно пишите название с большой буквы: <br/>"
            + "<input type=\"text\" name=\"event\" required id=\"event\" size=\"200\" autocomplete=\"off\"/> <br/>"
            + "<input type=\"submit\" name=\"submit\" id=\"submit\" value=\"Найти\"/>"
            + "</form>"
            + "<hr></body></html>";

    /*Тело страницы комментарии*/
    private String commentsBody = "<br/> Здесь Вы сможете поделиться своим мнением о событиях и"
            + " достопримечательностях с другими туристами, а также узнать чужое мнение о том или ином месте.<br/>"
            + "<Center> <table border = \"0\" bordercolor = \"white\" cellspacing=\"10\" cellpadding=\"10\" frame=\"vsides\" rules=\"cols\" align=\"center\"  >"
            + "<tr>"
            + "<th><a href=\"Writer\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Оставить комментарий </font></a></th>"
            + "<th><a href=\"Reader\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Читать комментарии </font></th>"
            + "<th><a href=\"Manager\" title=\"Для перехода в раздел нажмите\"><font size=\"10\" color=\"teal\" face=\"Arial\">Управлять своими комментариями</font></th>"
            + "<tr><td align=\"center\" width = \"25%\">Заходите сюда, если хотите написать комментарий. Функция доступна только для зарегистрированных пользователей. Чтобы создать учетную запись нажмите <a href=\"Registration\"> сюда</a></td>"
            + "<td align=\"center\" width = \"25%\">Здесь вы сможете почитать комментарии других туристов.</td>"
            + "<td align=\"center\" width = \"25%\">Здесь Вы сможете редактировать свои комментарии.</td>"
            + "</table>"
            + "<hr></body></html>";

    /*Метод для создания страницы Главная для Гостя*/
    @Override
    public String mainpageGuest(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append("<p>Добро пожаловать, " + attribute + "<p/>"
                + "<div>На данном портале вы сможете найти информацию о регионах нашей страны, "
                + "достопримечательностях, которые в них есть и самое главное - "
                + "обменяться впечатлениями с другими туристами в лагере. </div>"
                + "<div>Обращаем внимание, что в режиме гостя некоторые функции могут быть не доступны.<div/>");
        sb.append(mainBody);
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Главная для Пользователя*/
    @Override
    public String mainpageUser(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append("<p>Добро пожаловать, " + attribute + "<p/>"
                + "<div>На данном портале вы сможете найти информацию о регионах нашей страны, "
                + "достопримечательностях, которые в них есть, и самое главное - "
                + "обменяться впечатлениями с другими туристами. </div>");
        sb.append(mainBody);
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Регионы для Гостя*/
    @Override
    public String regions(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append(regionsBody);
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Достопримечательности для Гостя*/
    @Override
    public String sights(String attribte) {
        StringBuilder sb = new StringBuilder();
        Loader loader = new Loader();
        sb = sb.append(head);
        sb.append(sightBody);
        sb.append("<form action=\"Sights\" method=\"Post\">"
                + "<p><select size=\"1\" name=\"region\" required>"
                + "<option  selected value=\"\">Выберите регион</option>");
        for (int i = 0; i < loader.loadAllReg().length; i++) {
            sb.append("<option value=\"" + loader.loadAllReg()[i] + "\">" + loader.loadAllReg()[i] + "</option>");
        }
        sb.append("</select>  "
                + "<select size=\"1\" name=\"type\" required>"
                + "<option  selected value=\"\">Выберите тип достопримечательности</option>"
                + "<option value=\"Культура\">Культура</option>"
                + "<option value=\"Природа\">Природа</option>"
                + "<option value=\"Архитектура\">Архитектура</option>"
                + "<option value=\"\">Все достопримечательности</option>"
                + "</select>"
                + "</p>"
                + "<input type=\"submit\" value=\"Выбрать\"></p>"
                + "</form>"
                + "<form action=\"SightsInReg\" method=\"Get\">"
                + "<input type=\"submit\" name=\"random\" id=\"random\" value=\"Случайный выбор\"/>"
                + "</form>"
                + "</body>"
                + "</html>");

        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Достопримечательности в Регионе для Гостя*/
    @Override
    public String sightsInReg(String attribte) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(sightInRegBody);
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы События для Гостя*/
    @Override
    public String events(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append(eventsBody);
        Loader loader = new Loader();
        sb.append("<form action=\"Events\" method=\"Post\">"
                + "<p><select size=\"1\" name=\"region\" required>"
                + "<option  selected value=\"\">Выберите регион</option>");
        for (int i = 0; i < loader.loadAllReg().length; i++) {
            sb.append("<option value=\"" + loader.loadAllReg()[i] + "\">" + loader.loadAllReg()[i] + "</option>");
        }
        sb.append("</select> <br/>"
                + "Дата приезда: <input type = \"date\" name = \"startdate\" required>"
                + "Дата отъезда: <input type = \"date\" name = \"enddate\" required>"
                + "</p>"
                + "<input type=\"submit\" value=\"Выбрать\"></p>"
                + "</form>"
                + "</body>"
                + "</html>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы События в Регионе для Гостя*/
    @Override
    public String eventInReg(String attribte) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(eventsInRegBody);
        String page = sb.toString();
        return page;
    }

    /*Мето для создания страницы Комментарии*/
    @Override
    public String commentsMainPage(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        sb.append(commentsBody);
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Writer*/
    @Override
    public String writer(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        Loader loader = new Loader();
        sb.append("Уважаемый " + attribute + ", укажите, к чему вы хотите добавить комментарий и выберете регион объекта. <br/>"
                + "<form action=\"Writer\" method=\"Post\">"
                + "<p><select size=\"1\" name=\"region\" required>"
                + "<option  selected value=\"\">Выберите регион</option>");
        for (int i = 0; i < loader.loadAllReg().length; i++) {
            sb.append("<option value=\"" + loader.loadAllReg()[i] + "\">" + loader.loadAllReg()[i] + "</option>");
        }
        sb.append("</select> <br/>"
                + "<p><select size=\"1\" name=\"type\" required>"
                + "<option  selected value=\"\">Выберите тип объекта</option>"
                + "<option value=\"sight\">Достопримечательность</option>"
                + "<option value=\"event\">Событие</option>"
                + "</select>"
                + "</p>"
                + "<input type=\"submit\" value=\"Выбрать\"></p>"
                + "</form>"
                + "</body>"
                + "</html>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы перенаправления для Гостя*/
    @Override
    public String redirectGuest(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append("Уважаемый " + attribute + ", к сожалению, этот сервис не доступен для "
                + "неавторизованных пользователей. Сейчас вы будете перенаправлены на главную страницу приложения."
                + "</body>"
                + "<script type=\"text/javascript\"> setTimeout(function(){ location=\"index.html\";}, 5000);</script>)"
                + "</html>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы написания комментария*/
    @Override
    public String writerCommWrite(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        sb.append("Для того чтобы оставить комментарий, Вам необходимо заполнить все поля ниже. "
                + "Ваш комментарий увидят все пользователи системы. Вы сможете отредактировать его после размещения."
                + "Ниже представлен список всех объектов выбранного Вами типа в регионе. <br/>");
        String page = sb.toString();
        return page;
    }

    /*Метод для отрисовки форм для комментария*/
    @Override
    public String formsForComm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<form action=\"CommentsLoader\" method=\"Get\">"
                + "Введите название комментария. По нему Вы сможете найти его позже.<br/>"
                + "<input type=\"text\" name=\"title\" required size=\"120\" autocomplete=\"off\" placeholder=\"Название\" "
                + "/> <br/>"
                + "Введите название объекта, к которому Вы добавляете комментарий. <br/>"
                + "<input type=\"text\" name=\"dest\" required size=\"120\" autocomplete=\"off\" placeholder=\"Объект\" "
                + "/> <br/>"
                + "Введите текст Вашего комментария. Этот текст будут видеть все пользователи системы. <br/>"
                + "<textarea rows=\"10\" cols=\"50\" name=\"comment\" required maxlength=\"500\" ></textarea><br/>"
                + "<input type=\"submit\" value=\"Отправить\"></form>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы подтверждения записи комментария*/
    @Override
    public String confirmComment(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(head);
        sb.append("Ваш комментарий \"" + title + "\" был успешно добавлен в базу. "
                + "Теперь он будет отражаться на странице комментариев к объекту. "
                + "Вы сможете редактировать его в соответствующем разделе сайта.");
        sb.append("<form action=\"Mainpage\"><button type=\"submit\">Вернуться на главную</button></form>");
        sb.append("</body></html>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы Reader*/
    @Override
    public String reader(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        Loader loader = new Loader();
        sb.append("Уважаемый " + attribute + ", укажите, о каком объекте Вы хотите узнать мнение. <br/>"
                + "<form action=\"Reader\" method=\"Post\">"
                + "<p><select size=\"1\" name=\"region\" required>"
                + "<option  selected value=\"\">Выберите регион</option>");
        for (int i = 0; i < loader.loadAllReg().length; i++) {
            sb.append("<option value=\"" + loader.loadAllReg()[i] + "\">" + loader.loadAllReg()[i] + "</option>");
        }
        sb.append("</select> <br/>"
                + "<p><select size=\"1\" name=\"type\" required>"
                + "<option  selected value=\"\">Выберите тип объекта</option>"
                + "<option value=\"sight\">Достопримечательность</option>"
                + "<option value=\"event\">Событие</option>"
                + "</select>"
                + "</p>"
                + "<input type=\"submit\" value=\"Выбрать\"></p>"
                + "</form>"
                + "</body>"
                + "</html>");
        String page = sb.toString();
        return page;
    }

    /*Метод для создания страницы где содержаться комментарии к выбранному объекту*/
    @Override
    public String readCommets(String attribute) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(head);
        String page = sb.toString();
        return page;
    }

}
