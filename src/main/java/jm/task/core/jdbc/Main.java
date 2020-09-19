package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        // Создание таблицы User(ов)
        userService.dropUsersTable();
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )
        User user1 = new User("Геральт", "Белый волк", (byte)36);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User с именем " + user1.getName() + " добавлен в базу данных.");

        User user2 = new User("Трисс", "Меригольд", (byte)27);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User с именем " + user2.getName() + " добавлен в базу данных.");

        User user3 = new User("Йеннифэр", "из Венгерберга", (byte)26);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User с именем " + user3.getName() + " добавлен в базу данных.");

        User user4 = new User("Цирилла Фиона", "Элен Рианнон", (byte)18);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User с именем " + user4.getName() + " добавлен в базу данных.");

        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(userService.getAllUsers().toString());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}
