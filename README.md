**Постановка:**
Система Кинотеатр. Вы пишете интернет витрину маленького
Кинотеатра с одним залом. В нем есть Расписание показа фильмов на все 7
дней недели с 9:00 до 22:00 (начало последнего фильма).
Незарегистрированный пользователь может видеть: расписание,
свободные места в зале, возможность зарегистрироваться.
Зарегистрированный пользователь должен быть в состоянии выкупить
билет на выбранное место. Администратор может: внести в расписание
новый фильм, отменить фильм, просматривать посещаемость зала.

**Инструкция по установке.**
Проект расчитан на работу с postgresql. 
Перейти по com.cinema.model.dao.DataSource. В конструкторе класса (стр 21-23)
прописать данные для подключение к БД: url, userName, password.
Скомпилировать проект в коммандной строке: mvn clean install, архив положить в папку webapps запущенного apache-tomcat

**Инструкция по запуску приложения.**
1. В браузере перейти по ссылке http://localhost:8080/cinema-1.0-SNAPSHOT/main/index
2. Зарегистрировать пользователя - Registration. Первая регистрация создает пользователя с ролью Admin и все таблицы в БД.
Следующие зарегестрированные пользователи всегда создается с ролью User
Admin
3. Зайти под пользователем с ролью Admin. 
4. Создать фильм - Add film
5. Создать комнату - Add room
6. Сконфигурировать зал - Add room places (количество мест и рядов)
7. Создать сеансы - Session
User
8. Зайти под под пользователем с ролью User.
9. Зайти на страничку Session, выбрать сеанс и купить билет(клик по месту в зале). 
