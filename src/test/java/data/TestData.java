package data;

import com.github.javafaker.Faker;

public class TestData {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";

    // Генерируем фейковые данные для курьера
    public static final Faker user = new Faker();
    public static final String LOGIN = user.name().lastName() + System.currentTimeMillis();
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String FIRST_NAME = user.name().firstName();

    // Тестовые данные для заказа


}
